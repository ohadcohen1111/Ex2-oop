package gui;

import algorithms.Graph_Algo;

import utils.Point3D;
import utils.StdDraw;

import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;

public class GUI extends JFrame implements ActionListener, MouseListener {
	private graph Dgraph;
	private Graph_Algo g = new Graph_Algo();

	public GUI(graph Dg) {
		this.Dgraph = Dg;
		g.init(Dgraph);
		initGUI();
	}

	private void initGUI() {
		this.setSize(500, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MenuBar menuBar = new MenuBar();

		Menu file = new Menu("File");
		menuBar.add(file);
		this.setMenuBar(menuBar);

		MenuItem save = new MenuItem("Save");
		save.addActionListener(this);

		MenuItem init = new MenuItem("Init");
		init.addActionListener(this);

		file.add(save);
		file.add(init);

		Menu algo = new Menu("Graph-Algo");
		menuBar.add(algo);

		MenuItem draw = new MenuItem("Draw graph");
		draw.addActionListener(this);

		MenuItem addVertex = new MenuItem("Add Vertex");
		addVertex.addActionListener(this);

		MenuItem addEdge = new MenuItem("Add Edge");
		addEdge.addActionListener(this);

		MenuItem removeVertex = new MenuItem("Remove Vertex");
		removeVertex.addActionListener(this);

		MenuItem removeEdge = new MenuItem("Remove Edge");
		removeEdge.addActionListener(this);

		algo.add(draw);
		algo.add(addVertex);
		algo.add(removeVertex);
		algo.add(addEdge);
		algo.add(removeEdge);

		Menu algoFunc = new Menu("Graph-Algo function");
		menuBar.add(algoFunc);

		MenuItem isConnect = new MenuItem("Is Connected");
		isConnect.addActionListener(this);

		MenuItem sortPath = new MenuItem("Short-path");
		sortPath.addActionListener(this);

		MenuItem TSP = new MenuItem("TSP");
		TSP.addActionListener(this);

		algoFunc.add(sortPath);
		algoFunc.add(isConnect);
		algoFunc.add(TSP);

		this.addMouseListener(this);
		setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		Graph_Algo g = new Graph_Algo();
		g.init(Dgraph);
		String str = e.getActionCommand();

		if (str.equals("Save")) {
			saveGraphGui();
		}

		if (str.equals("Init")) {
			initGraphGui();
		}

		if (str.equals("Draw graph")) {
			drawGraph();
		}

		if (str.equals("Add Vertex")) {
			addVertexGUI();
		}

		if (str.equals("Add Edge")) {
			addEdgeGUI();
		}

		if (str.equals("Remove Vertex")) {
			removeVertexGUI();
		}

		if (str.equals("Remove Edge")) {
			removeEdgeGUI();
		}

		if (str.equals("Short-path")) {
			shortestPathGUI();
		}

		if (str.equals("Is Connected")) {
			isConnectedGUI();
		}

		if (str.equals("TSP")) {
			TSPGUI();
		}
	}

	// ActionEvent function

	private void saveGraphGui() {
		// try write to the file
		FileDialog fd = new FileDialog(this, "Save the text file", FileDialog.SAVE);
		fd.setFile("*.txt");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		if (fileName != null) {
			this.g.save(folder + fileName);
		}

	}

	private void initGraphGui() {
		// try read from the file
		FileDialog fd = new FileDialog(this, "Load Graph", FileDialog.LOAD);
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".txt");
			}
		});

		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		if (fileName != null) {
			this.g.init(folder + fileName);
		}
	}

	private void addVertexGUI() {
		final JFrame window = new JFrame("Add Vertex");
		final JTextField vertexX = new JTextField();
		final JTextField vertexY = new JTextField();
		JLabel verX = new JLabel("Enter x Coordinate: ");
		JLabel verY = new JLabel("Enter y Coordinate: ");

		JButton enter = new JButton("Enter");
		JButton cancel = new JButton("Cancel");

		GridLayout g1 = new GridLayout();
		g1.setRows(3);
		g1.setColumns(2);

		window.setLayout(g1);
		window.add(verX);
		window.add(vertexX);
		window.add(verY);
		window.add(vertexY);
		window.add(enter);
		window.add(cancel);
		window.setSize(300, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int x = Integer.parseInt(vertexX.getText());
					int y = Integer.parseInt(vertexY.getText());
					Point3D p = new Point3D(x, y);
					Dgraph.addNode(new nodeData(p));
					window.setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2 + "");
				}
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);

			}
		});
	}

	private void addEdgeGUI() {
		final JFrame window = new JFrame("Add Edge");
		final JTextField srcText = new JTextField();
		final JTextField destText = new JTextField();
		final JTextField weightText = new JTextField();

		JLabel src = new JLabel("Enter source key: ");
		JLabel dest = new JLabel("Enter destination key: ");
		JLabel weight = new JLabel("Enter weight: ");

		JButton enter = new JButton("Enter");
		JButton cancel = new JButton("Cancel");

		GridLayout g1 = new GridLayout();
		g1.setRows(4);
		g1.setColumns(2);

		window.setLayout(g1);
		window.add(src);
		window.add(srcText);
		window.add(dest);
		window.add(destText);
		window.add(weight);
		window.add(weightText);
		window.add(enter);
		window.add(cancel);
		window.setSize(300, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int src = Integer.parseInt(srcText.getText());
					int dest = Integer.parseInt(destText.getText());
					int w = Integer.parseInt(weightText.getText());
					Dgraph.connect(src, dest, w);
					window.setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2 + "");
				}
			}
		});

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);

			}
		});
	}

	private void removeVertexGUI() {
		final JFrame window = new JFrame("Add Vertex");
		final JTextField vertexKey = new JTextField();
		JLabel verKey = new JLabel("Enter vertex ID: ");

		JButton enter = new JButton("Enter");
		JButton cancel = new JButton("Cancel");

		GridLayout g1 = new GridLayout();
		g1.setRows(2);
		g1.setColumns(2);

		window.setLayout(g1);
		window.add(verKey);
		window.add(vertexKey);
		window.add(enter);
		window.add(cancel);
		window.setSize(300, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int key = Integer.parseInt(vertexKey.getText());
					Dgraph.removeNode(key);
					window.setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2 + "");
				}
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);

			}
		});
	}

	private void removeEdgeGUI() {
		final JFrame window = new JFrame("Remove Edge");
		final JTextField srcText = new JTextField();
		final JTextField destText = new JTextField();

		JLabel src = new JLabel("Enter source key: ");
		JLabel dest = new JLabel("Enter destination key: ");

		JButton enter = new JButton("Enter");
		JButton cancel = new JButton("Cancel");

		GridLayout g1 = new GridLayout();
		g1.setRows(3);
		g1.setColumns(2);

		window.setLayout(g1);
		window.add(src);
		window.add(srcText);
		window.add(dest);
		window.add(destText);
		window.add(enter);
		window.add(cancel);
		window.setSize(300, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int src = Integer.parseInt(srcText.getText());
					int dest = Integer.parseInt(destText.getText());
					Dgraph.removeEdge(src, dest);
					window.setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2 + "");
				}
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);

			}
		});
	}

	private void isConnectedGUI() {
		boolean isConnected = g.isConnected();
		String ans = "";
		ans += isConnected;
		// change the first letter to captial letter
		String upper = ans.substring(0, 1);
		upper = upper.toUpperCase();
		ans = upper + ans.substring(1);
		final JFrame window = new JFrame("Is Connected");
		JLabel isConnect = new JLabel(ans);
		isConnect.setFont(new Font("David", isConnect.getFont().getStyle(), 20));
		JButton close = new JButton("Close");

		GridLayout g1 = new GridLayout();
		g1.setRows(2);

		window.setLayout(g1);
		window.add(isConnect);
		window.add(close);
		window.setSize(200, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		close.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);
			}
		});
	}

	private void shortestPathGUI() {
		final JFrame window = new JFrame("Shortest Path");
		final JTextField srcText = new JTextField();
		final JTextField destText = new JTextField();
		JLabel srcLabel = new JLabel("Src: ");
		JLabel destLabel = new JLabel("Dest: ");
		JButton enter = new JButton("Enter");
		JButton cancel = new JButton("Cancel");

		GridLayout g1 = new GridLayout();
		g1.setColumns(2);
		g1.setRows(3);

		window.setLayout(g1);
		window.add(srcLabel);
		window.add(srcText);
		window.add(destLabel);
		window.add(destText);
		window.add(cancel);
		window.add(enter);
		window.setSize(300, 200);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int src = Integer.parseInt(srcText.getText());
					int dest = Integer.parseInt(destText.getText());
					shortPathDraw(src, dest);
					window.setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2 + "");
				}
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);
			}
		});
	}

	private void TSPGUI() {
		final JFrame window = new JFrame("TSP");
		final JTextField targetsText = new JTextField();
		JLabel targetsLabel = new JLabel("Please inset List of targets in the shape 1,4,8...\n Targets: ");
		JButton enter = new JButton("Enter");
		JButton cancel = new JButton("Cancel");

		GridLayout g1 = new GridLayout();
		g1.setColumns(1);
		g1.setRows(2);

		window.setLayout(g1);
		window.add(targetsLabel);
		window.add(targetsText);
		window.add(cancel);
		window.add(enter);
		window.setSize(650, 150);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String tar = targetsText.getText();
					List<Integer> targets = new ArrayList<Integer>();
					while (!tar.isEmpty()) {
						int target;
						if (!tar.contains(",")) {
							target = Integer.parseInt(tar.substring(0));
							tar = "";
						} else {
							target = Integer.parseInt(tar.substring(0, tar.indexOf(",")));
							tar = tar.substring(tar.indexOf(",") + 1);
						}
						targets.add(target);
					}
					TSPDraw(targets);
					window.setVisible(false);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2 + "");
				}
			}
		});
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				window.setVisible(false);
			}
		});

	}

	// draw the Graph

	public void drawGraph() {
		StdDraw.setCanvasSize(800, 400);
		setScale();
		// edge
		for (node_data vertex : this.Dgraph.getV()) {
			Collection<edge_data> edge = this.Dgraph.getE(vertex.getKey());
			if (edge != null) {
				for (edge_data e : edge) {
					StdDraw.setPenRadius(0.005);
					StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
					int x0 = this.Dgraph.getNode(e.getSrc()).getLocation().ix();
					int y0 = this.Dgraph.getNode(e.getSrc()).getLocation().iy();
					int x1 = this.Dgraph.getNode(e.getDest()).getLocation().ix();
					int y1 = this.Dgraph.getNode(e.getDest()).getLocation().iy();

					StdDraw.line(x0, y0, x1, y1);
					StdDraw.setPenRadius(0.015);
					StdDraw.setPenColor(StdDraw.YELLOW);
					if ((x0 - x1) != 0) {
						double y = equasionY(x0, x1, y0, y1);

						if (x1 < x0) {
							StdDraw.point(x1 + 0.2, y);
							double wY = equasionWeight(x0, x1, y0, y1);
							double x = x0 - x1;
							x = (x / 2) + x1;
							StdDraw.setPenRadius(0.01);
							StdDraw.setPenColor(StdDraw.DARK_GRAY);
							StdDraw.text(x, wY, "" + e.getWeight());
						}
						if (x1 > x0) {
							StdDraw.point(x1 - 0.2, y);
							double wY = equasionWeight(x0, x1, y0, y1);
							double x = (x1 - x0);
							x = (x / 2) + x0;
							StdDraw.setPenRadius(0.01);
							StdDraw.setPenColor(StdDraw.DARK_GRAY);
							StdDraw.text(x, wY, "" + e.getWeight());
						}
					} else if ((x0 - x1) == 0) {
						if (y1 > y0) {
							StdDraw.point(x1, y1 - 0.3);
							StdDraw.setPenRadius(0.01);
							StdDraw.setPenColor(StdDraw.DARK_GRAY);
							StdDraw.text(x1, (((y1 - y0) / 2) + y0), "" + e.getWeight());
						}
						if (y1 < y0) {
							StdDraw.point(x0, y1 + 0.3);
							StdDraw.setPenRadius(0.01);
							StdDraw.setPenColor(StdDraw.DARK_GRAY);
							StdDraw.text(x1, (((y0 - y1) / 2) + y1), "" + e.getWeight());
						}
					}

				}
			}
		}
		// draw point, vertex
		for (node_data vertex : this.Dgraph.getV()) {
			StdDraw.setPenRadius(0.015);
			int x = vertex.getLocation().ix();
			int y = vertex.getLocation().iy();
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.point(x, y);
			String point = "";
			point += vertex.getKey();
			StdDraw.setPenRadius(0.02);
			StdDraw.setPenColor(StdDraw.PRINCETON_ORANGE);
			StdDraw.text(x, y + 0.5, point); // the number of the vertex.
		}

	}

	// set X and Y scales

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

	// find Y point

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

	// find weight point to write

	public double equasionWeight(int x0, int x1, int y0, int y1) { // find the point of the weight.
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

	// draw short path

	public void shortPathDraw(int src, int dest) {
		drawGraph();
		List<node_data> shortPath = new ArrayList<node_data>();
		Graph_Algo g = new Graph_Algo();
		g.init(Dgraph);
		shortPath = g.shortestPath(src, dest);
		if (shortPath != null) {
			for (int i = 0; i < shortPath.size() - 1; i++) {
				int x = shortPath.get(i).getLocation().ix();
				int y = shortPath.get(i).getLocation().iy();
				if (shortPath.get(i + 1) != null) {
					int x1 = shortPath.get(i + 1).getLocation().ix();
					int y1 = shortPath.get(i + 1).getLocation().iy();
					StdDraw.setPenRadius(0.006);
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.line(x, y, x1, y1);
				}
			}
		}
	}

	// draw TSP path

	public void TSPDraw(List<Integer> targets) {
		drawGraph();
		Graph_Algo g = new Graph_Algo();
		g.init(Dgraph);
		List<node_data> TSP = new ArrayList<node_data>();
		TSP = g.TSP(targets);
		if (TSP != null) {
			for (int i = 0; i < TSP.size() - 1; i++) {
				int x = TSP.get(i).getLocation().ix();
				int y = TSP.get(i).getLocation().iy();
				if (TSP.get(i + 1) != null) {
					int x1 = TSP.get(i + 1).getLocation().ix();
					int y1 = TSP.get(i + 1).getLocation().iy();
					StdDraw.setPenRadius(0.006);
					StdDraw.setPenColor(StdDraw.PINK);
					StdDraw.line(x, y, x1, y1);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		;
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		;
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		;
	}
}