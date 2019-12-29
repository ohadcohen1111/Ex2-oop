package Tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.nodeData;
import dataStructure.node_data;
import gui.GUI;
import utils.Point3D;

public class TestDGraph {

	@Test
	public void testAddNode() {
		Graph_Algo ga = new Graph_Algo();
		DGraph g = new DGraph();
		Point3D p = new Point3D(5, 7);
		Point3D p1 = new Point3D(2.3, 4.6);
		Point3D p2 = new Point3D(2.5, 5);
		Point3D p3 = new Point3D(5, 5);
		Point3D p4 = new Point3D(6, 9);
		Point3D p5 = new Point3D(9, 1);
		node_data n = new nodeData(p);
		node_data n1 = new nodeData(p1);
		node_data n2 = new nodeData(p2);
		node_data n3 = new nodeData(p3);
		node_data n4 = new nodeData(p4);
		node_data n5 = new nodeData(p5);
		int node_size = 0;
		node_data[] nodes = { n, n1, n2, n3, n4, n5 };

		for (int i = 0; i < nodes.length; i++) {
			g.addNode(nodes[i]);
			node_size++;
		}

		Collection<node_data> vertex = g.getV();
		int i = 0;

		for (node_data ver : vertex) {
			assertEquals(ver.getLocation(), nodes[i++].getLocation());
		}

		for (int j = 0; j < nodes.length; j++) {
			assertEquals(node_size--, g.nodeSize());
			g.removeNode(nodes[j].getKey());
		}
	}

	@Test
	public void testConnect() {
		DGraph g = new DGraph();
		Point3D p = new Point3D(3, 1.7);
		Point3D p1 = new Point3D(8.12, 1.33);
		Point3D p2 = new Point3D(2.5, 5);
		Point3D p3 = new Point3D(1, 7.7);
		Point3D p4 = new Point3D(2, 0.4);
		Point3D p5 = new Point3D(0.5, 12);
		node_data n = new nodeData(p);
		node_data n1 = new nodeData(p1);
		node_data n2 = new nodeData(p2);
		node_data n3 = new nodeData(p3);
		node_data n4 = new nodeData(p4);
		node_data n5 = new nodeData(p5);
		node_data[] nodes = { n, n1, n2, n3, n4, n5 };

		for (int i = 0; i < nodes.length; i++) {
			g.addNode(nodes[i]);
		}
  
		edgeData e = new edgeData(n, n1, 6);
		edgeData e1 = new edgeData(n1, n2, 11.2);
		edgeData e2 = new edgeData(n, n5, 5.3);
		edgeData e3 = new edgeData(n, n2, 6);
		edgeData e4 = new edgeData(n2, n5, 11.2);
		edgeData e5 = new edgeData(n4, n, 5.3);
		edgeData e6 = new edgeData(n4, n5, 2.3);
		edgeData e7 = new edgeData(n3, n, 7);
		edgeData e8 = new edgeData(n2, n4, 8);
		edgeData e9 = new edgeData(n5, n4, 4);

		g.connect(n.getKey(), n1.getKey(), 6);
		g.connect(n1.getKey(), n2.getKey(), 11.2);
		g.connect(n.getKey(), n5.getKey(), 5.3);
		g.connect(n.getKey(), n2.getKey(), 6);
		g.connect(n2.getKey(), n5.getKey(), 11.2);
		g.connect(n4.getKey(), n.getKey(), 5.3);
		g.connect(n4.getKey(), n5.getKey(), 2.3);
		g.connect(n3.getKey(), n.getKey(), 7);
		g.connect(n2.getKey(), n4.getKey(), 8);
		g.connect(n5.getKey(), n4.getKey(), 4);

		assertTrue(g.removeEdge(n.getKey(), n1.getKey()).getWeight() == e.getWeight());
		assertTrue(g.removeEdge(n1.getKey(), n2.getKey()).getWeight() == e1.getWeight());
		assertTrue(g.removeEdge(n.getKey(), n5.getKey()).getWeight() == e2.getWeight());
		assertTrue(g.removeEdge(n.getKey(), n2.getKey()).getWeight() == e3.getWeight());
		assertTrue(g.removeEdge(n2.getKey(), n5.getKey()).getWeight() == e4.getWeight());
		assertTrue(g.removeEdge(n4.getKey(), n.getKey()).getWeight() == e5.getWeight());
		assertTrue(g.removeEdge(n4.getKey(), n5.getKey()).getWeight() == e6.getWeight());
		assertTrue(g.removeEdge(n3.getKey(), n.getKey()).getWeight() == e7.getWeight());
		assertTrue(g.removeEdge(n2.getKey(), n4.getKey()).getWeight() == e8.getWeight());
		assertTrue(g.removeEdge(n5.getKey(), n4.getKey()).getWeight() == e9.getWeight());

		assertTrue(g.edgeSize() == 0);
	}

}
