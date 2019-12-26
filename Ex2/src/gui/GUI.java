package gui;

import javax.swing.JFrame;

import algorithms.Graph_Algo;

import utils.Point3D;
import utils.StdDraw;

import java.util.Collection;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;

public class GUI extends JFrame {
	private graph Dgraph;
	private Graph_Algo g;

	public GUI(graph Dg) {
		this.Dgraph = Dg;
		Graph_Algo g = new Graph_Algo();
		g.init(Dgraph);
	}

	public void draw() {
		StdDraw.setCanvasSize(800, 400);
		// scale
		StdDraw.setXscale(0, 20);
		StdDraw.setYscale(0, 20);
		// point
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
			StdDraw.text(x, y + 0.5, point);
		}

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
					int p = Math.abs(x0 - x1) / 2;
					int p1 = Math.abs(y0 - y1) / 2;
					System.out.println(p);
					System.out.println(p1);
					StdDraw.setPenRadius(0.0099);
					StdDraw.setPenColor(StdDraw.GRAY);
					StdDraw.text(p, p1, "" + e.getWeight());
					// StdDraw.point(p, p1);

				}
			}
		}
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
		// Graph_Algo g = new Graph_Algo();
		graph g = g1;
		graph copy = g;
		GUI d = new GUI(g);
		GUI co = new GUI(copy);
		// co.draw();
		// d.draw();
		Point3D p10 = new Point3D(12, 5, 8);
		nodeData nd10 = new nodeData(p10);
		Point3D p11 = new Point3D(5, 9, 11);
		nodeData nd11 = new nodeData(p11);
		Point3D p12 = new Point3D(7, 13, 8.6);
		nodeData nd12 = new nodeData(p12);
		copy.addNode(nd10);
		copy.addNode(nd11);
		copy.addNode(nd12);
		copy.connect(nd10.getKey(), nd12.getKey(), 10);
		// co.draw();
		d.draw();
		co.draw();
		// g1.removeNode(nd3.getKey());
		d.draw();
		System.out.println(g.edgeSize());
	}
}
