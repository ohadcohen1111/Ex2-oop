package gui;

import algorithms.Graph_Algo;

import utils.Point3D;
import utils.StdDraw;

import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;

public class GUI extends JFrame implements ActionListener, MouseListener {
	private graph Dgraph;
	private Graph_Algo g;

	public GUI(graph Dg) {
		this.Dgraph = Dg;
		Graph_Algo g = new Graph_Algo();
		g.init(Dgraph);
		// drawGraph();
	}

	private void initGUI() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();
		Menu algo = new Menu("Graph-Algo");
		menuBar.add(algo);
		this.setMenuBar(menuBar);

		MenuItem draw = new MenuItem("Draw graph");
		draw.addActionListener(this);

		MenuItem sortPath = new MenuItem("Short-path");
		sortPath.addActionListener(this);

		algo.add(draw);
		algo.add(sortPath);

		Menu file = new Menu("file");
		menuBar.add(file);

		MenuItem save = new MenuItem("save");
		save.addActionListener(this);

		MenuItem init = new MenuItem("init");
		init.addActionListener(this);

		file.add(save);
		file.add(init);

		this.addMouseListener(this);

	}

	public void actionPerformed(ActionEvent e) {
		Graph_Algo g = new Graph_Algo();
		g.init(Dgraph);
		String str = e.getActionCommand();

		if (str.equals("Draw graph")) {
			drawGraph();
		}

		if (str.equals("Short-path")) {
			shortpath(4, 2);

		}

		if (str.equals("save")) {
			g.save("C:\\Users\\amite\\Desktop\\test2");
		}

		if (str.equals("init")) {
			g.init("C:\\Users\\amite\\Desktop\\test2");
		}
	}

	public void setScale() {
		// scale
		int maxX = 0;
		int maxY = 0;
		for (node_data vertex : this.Dgraph.getV()) {
			if (maxX < vertex.getLocation().ix()) {
				maxX = vertex.getLocation().ix();
			}
			if (maxY < vertex.getLocation().iy()) {
				maxY = vertex.getLocation().iy();
			}
		}
		StdDraw.setXscale(-4, maxX + 7);
		StdDraw.setYscale(-4, maxY + 7);
	}

	public double equasionY(int x0, int x1, int y0, int y1) { // to find the point of the direction.
		double f;
		double yM = y0 - y1;
		double xM = x0 - x1;
		double m = yM / xM;
		if (x1 > x0) {
			double x = (x1 - 0.2);
			f = (m * x) - (m * x1) + y1;
			return f;
		}
		if (x1 < x0) {
			double x = (x1 + 0.2);
			f = (m * x) - (m * x1) + y1;
			return f;
		}
		return 0;
	}

	public double equasionWegit(int x0, int x1, int y0, int y1) { // find the point of the weight.
		double f;
		double yM = y0 - y1;
		double xM = x0 - x1;
		double m = yM / xM;
		if (x1 > x0) {
			double x = (x1 - x0);
			x = (x / 2) + x0;
			f = (m * x) - (m * x1) + y1;
			return f;
		}
		if (x1 < x0) {
			double x = x0 - x1;
			x = (x / 2) + x1;
			f = (m * x) - (m * x1) + y1;
			return f;
		}
		return 0;
	}

	public void drawGraph() {
		StdDraw.setCanvasSize(800, 400);
		setScale();
		// point, vertex
		for (node_data vertex : this.Dgraph.getV()) {
			StdDraw.setPenRadius(0.00999);
			int x = vertex.getLocation().ix();
			int y = vertex.getLocation().iy();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.point(x, y);
			String point = "";
			point += vertex.getKey();
			StdDraw.setPenRadius(0.0009);
			StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
			StdDraw.text(x, y + 0.5, point); // the number of the vertex.
		}
		// edge
		for (node_data vertex : this.Dgraph.getV()) {
			Collection<edge_data> edge = this.Dgraph.getE(vertex.getKey());
			if (edge != null) {
				for (edge_data e : edge) {
					StdDraw.setPenRadius(0.00099);
					StdDraw.setPenColor(StdDraw.DARK_GRAY);
					int x0 = this.Dgraph.getNode(e.getSrc()).getLocation().ix();
					int y0 = this.Dgraph.getNode(e.getSrc()).getLocation().iy();
					int x1 = this.Dgraph.getNode(e.getDest()).getLocation().ix();
					int y1 = this.Dgraph.getNode(e.getDest()).getLocation().iy();

					StdDraw.line(x0, y0, x1, y1);
					StdDraw.setPenRadius(0.0099);
					StdDraw.setPenColor(StdDraw.YELLOW);
					if ((x0 - x1) != 0) {
						double y = equasionY(x0, x1, y0, y1);

						if (x1 < x0) {
							StdDraw.point(x1 + 0.2, y);
							double wY = equasionWegit(x0, x1, y0, y1);
							double x = x0 - x1;
							x = (x / 2) + x1;
							StdDraw.setPenColor(StdDraw.BOOK_RED);
							StdDraw.text(x, wY, "" + e.getWeight());
						}
						if (x1 > x0) {
							StdDraw.point(x1 - 0.2, y);
							double wY = equasionWegit(x0, x1, y0, y1);
							double x = (x1 - x0);
							x = (x / 2) + x0;
							StdDraw.setPenColor(StdDraw.BOOK_RED);
							StdDraw.text(x, wY, "" + e.getWeight());
						}
					} else if ((x0 - x1) == 0) {
						if (y1 > y0) {
							StdDraw.point(x1, y1 - 0.3);
							StdDraw.setPenColor(StdDraw.BOOK_RED);
							StdDraw.text(x1, (((y1 - y0) / 2) + y0), "" + e.getWeight());
						}
						if (y1 < y0) {
							StdDraw.point(x0, y1 + 0.3);
							StdDraw.setPenColor(StdDraw.BOOK_RED);
							StdDraw.text(x1, (((y0 - y1) / 2) + y1), "" + e.getWeight());
						}
					}

				}
			}
		}

	}

	public void shortpath(int src, int dest) {
		drawGraph();
		List<node_data> shortPath = new ArrayList<node_data>();
		Graph_Algo g = new Graph_Algo();
		g.init(Dgraph);
		shortPath = g.shortestPath(src, dest);
		if (shortPath != null) {
			for (int i = 0; i < shortPath.size() - 1; i++) {
				int x = shortPath.get(i).getLocation().ix();
				int y = shortPath.get(i).getLocation().iy();
//				StdDraw.setPenRadius(0.00999);
//				StdDraw.setPenColor(StdDraw.RED);
//				StdDraw.point(x, y);
				if (shortPath.get(i + 1) != null) {
					int x1 = shortPath.get(i + 1).getLocation().ix();
					int y1 = shortPath.get(i + 1).getLocation().iy();
					StdDraw.setPenRadius(0.00099);
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.line(x, y, x1, y1);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		DGraph g1 = new DGraph();
		Point3D p = new Point3D(1, 1, 3);
		Point3D p1 = new Point3D(1, 4, 3);
		Point3D p2 = new Point3D(1, 7, 3);
		Point3D p3 = new Point3D(4, 7, 3);
		Point3D p4 = new Point3D(4, 3, 3);
		Point3D p5 = new Point3D(3, 1, 3);
		nodeData nd = new nodeData(p);
		nodeData nd1 = new nodeData(p1);
		nodeData nd2 = new nodeData(p2);
		nodeData nd3 = new nodeData(p3);
		nodeData nd4 = new nodeData(p4);
		nodeData nd5 = new nodeData(p5);
		g1.addNode(nd);
		g1.addNode(nd1);
		g1.addNode(nd2);
		g1.addNode(nd3);
		g1.addNode(nd4);
		g1.addNode(nd5);
		g1.connect(nd3.getKey(), nd1.getKey(), 3);
		g1.connect(nd3.getKey(), nd4.getKey(), 0.5);
		g1.connect(nd5.getKey(), nd3.getKey(), 5);
		g1.connect(nd5.getKey(), nd4.getKey(), 4);
		g1.connect(nd4.getKey(), nd2.getKey(), 0.5);
		g1.connect(nd5.getKey(), nd.getKey(), 1);
		g1.connect(nd2.getKey(), nd1.getKey(), 0.5);
		g1.connect(nd.getKey(), nd4.getKey(), 6);

		// g1.draw();
		// Graph_Algo g = new Graph_Algo();
		GUI d = new GUI(g1);
		// d.shortpath(4,2);
		d.setVisible(true);
		d.initGUI();

		// d.drawGraph();

	}
}