package Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Point3D;

public class TestGraphAlgo {

	@Test
	public void testInitGraph() {
		DGraph g = new DGraph();
		Point3D p = new Point3D(3, 5);
		Point3D p1 = new Point3D(1, 4);
		Point3D p2 = new Point3D(2, 8);
		Point3D p3 = new Point3D(11, 5);

		node_data n = new nodeData(p);
		node_data n1 = new nodeData(p1);
		node_data n2 = new nodeData(p2);
		node_data n3 = new nodeData(p3);

		node_data[] nodes = { n, n1, n2, n3 };

		for (int i = 0; i < nodes.length; i++) {
			g.addNode(nodes[i]);
		}
		for (int i = 0; i < g.nodeSize(); i++) {
			for (int j = 1; j < g.nodeSize(); j++) {
				if (nodes[i].getKey() != nodes[j].getKey()) {
					double weight = (int) (Math.random() * (14 - 1) + 1);
					g.connect(nodes[i].getKey(), nodes[j].getKey(), weight);
				}
			}
		}

		Graph_Algo ga = new Graph_Algo();
		ga.init(g);
		assertFalse(ga.isConnected());

		for (int i = 0; i < g.nodeSize(); i++) {
			for (int j = 1; j < g.nodeSize(); j++) {
				if (nodes[i].getKey() != nodes[j].getKey()) {
					double weight = (int) (Math.random() * (14 - 1) + 1);
					g.connect(nodes[j].getKey(), nodes[i].getKey(), weight);
				}
			}
		}
		Graph_Algo ga1 = new Graph_Algo();
		ga1.init(g);
		assertTrue(ga1.isConnected());

		ga.save("Graph_Algo");
		Graph_Algo init = new Graph_Algo();
		init.init("Graph_Algo");
		graph gra = init.copy();
		assertEquals(gra.nodeSize(), g.nodeSize());
		assertEquals(gra.edgeSize(), g.edgeSize());

	}

	@Test
	public void testIsConnected() {
		DGraph g = new DGraph();
		Point3D p = new Point3D(3, 1.7);
		Point3D p1 = new Point3D(8.12, 6.33);
		Point3D p2 = new Point3D(2.5, 5);
		Point3D p3 = new Point3D(11, 7.7);
		Point3D p4 = new Point3D(2, 5.1);
		Point3D p5 = new Point3D(2.5, 12);
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
		g.connect(n5.getKey(), n4.getKey(), 4);

		Graph_Algo ga = new Graph_Algo();
		ga.init(g);

		assertFalse(ga.isConnected());

		g.connect(n.getKey(), n3.getKey(), 4);
		assertTrue(ga.isConnected());
	}

	@Test
	public void testShortestPathDist() {
		DGraph g = new DGraph();
		Point3D p = new Point3D(3, 1.7);
		Point3D p1 = new Point3D(8.12, 6.33);
		Point3D p2 = new Point3D(2.5, 5);
		Point3D p3 = new Point3D(11, 7.7);
		Point3D p4 = new Point3D(2, 2);
		Point3D p5 = new Point3D(2.5, 12);
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

		g.connect(n.getKey(), n1.getKey(), 3);
		g.connect(n1.getKey(), n2.getKey(), 4);
		g.connect(n.getKey(), n5.getKey(), 10);
		g.connect(n.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n5.getKey(), 5);
		g.connect(n4.getKey(), n.getKey(), 0.5);
		g.connect(n4.getKey(), n5.getKey(), 13);
		g.connect(n3.getKey(), n.getKey(), 4.5);
		g.connect(n2.getKey(), n4.getKey(), 8);
		g.connect(n5.getKey(), n4.getKey(), 11);
		g.connect(n.getKey(), n3.getKey(), 4);
		g.connect(n1.getKey(), n3.getKey(), 0.5);

		Graph_Algo ga = new Graph_Algo();
		ga.init(g);
		double[] shortestPathDist = new double[5];

		shortestPathDist[0] = ga.shortestPathDist(n.getKey(), n5.getKey());
		shortestPathDist[1] = ga.shortestPathDist(n4.getKey(), n5.getKey());
		shortestPathDist[2] = ga.shortestPathDist(n.getKey(), n3.getKey());
		shortestPathDist[3] = ga.shortestPathDist(n1.getKey(), n.getKey());
		shortestPathDist[4] = ga.shortestPathDist(n5.getKey(), n4.getKey());

		double[] expected = { 7, 7.5, 3.5, 5, 11 };

		for (int i = 0; i < shortestPathDist.length; i++) {
			assertEquals(expected[i], shortestPathDist[i], 0000.1);
		}
	}

	@Test
	public void testShortestPath() {
		DGraph g = new DGraph();
		Point3D p = new Point3D(3, 1.7);
		Point3D p1 = new Point3D(8.12, 6.33);
		Point3D p2 = new Point3D(2.5, 5);
		Point3D p3 = new Point3D(11, 7.7);
		Point3D p4 = new Point3D(2, 2);
		Point3D p5 = new Point3D(2.5, 12);
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

		g.connect(n.getKey(), n1.getKey(), 3);
		g.connect(n1.getKey(), n2.getKey(), 4);
		g.connect(n.getKey(), n5.getKey(), 10);
		g.connect(n.getKey(), n2.getKey(), 2);
		g.connect(n2.getKey(), n5.getKey(), 5);
		g.connect(n4.getKey(), n.getKey(), 0.5);
		g.connect(n4.getKey(), n5.getKey(), 13);
		g.connect(n3.getKey(), n.getKey(), 4.5);
		g.connect(n2.getKey(), n4.getKey(), 8);
		g.connect(n5.getKey(), n4.getKey(), 11);
		g.connect(n.getKey(), n3.getKey(), 4);
		g.connect(n1.getKey(), n3.getKey(), 0.5);

		Graph_Algo ga = new Graph_Algo();
		ga.init(g);
		List<node_data> l = ga.shortestPath(n.getKey(), n5.getKey());
		List<node_data> l1 = ga.shortestPath(n4.getKey(), n5.getKey());
		List<node_data> l2 = ga.shortestPath(n.getKey(), n3.getKey());
		List<node_data> l3 = ga.shortestPath(n1.getKey(), n.getKey());
		List<node_data> l4 = ga.shortestPath(n5.getKey(), n4.getKey());

		node_data[] ex = { n, n2, n5 };
		node_data[] ex1 = { n4, n, n2, n5 };
		node_data[] ex2 = { n, n1, n3 };
		node_data[] ex3 = { n1, n3, n };
		node_data[] ex4 = { n5, n4 };

		List<node_data> expected = new ArrayList<node_data>();
		List<node_data> expected1 = new ArrayList<node_data>();
		List<node_data> expected2 = new ArrayList<node_data>();
		List<node_data> expected3 = new ArrayList<node_data>();
		List<node_data> expected4 = new ArrayList<node_data>();

		for (int i = 0; i < ex.length; i++) {
			expected.add(ex[i]);
		}
		for (int i = 0; i < ex1.length; i++) {
			expected1.add(ex1[i]);
		}
		for (int i = 0; i < ex2.length; i++) {
			expected2.add(ex2[i]);
		}
		for (int i = 0; i < ex3.length; i++) {
			expected3.add(ex3[i]);
		}
		for (int i = 0; i < ex4.length; i++) {
			expected4.add(ex4[i]);
		}

		assertEquals(expected, l);
		assertEquals(expected1, l1);
		assertEquals(expected2, l2);
		assertEquals(expected3, l3);
		assertEquals(expected4, l4);

	}

	@Test
	public void testTSP() {
		DGraph g = new DGraph();
		Point3D p1 = new Point3D(1, 1);
		Point3D p2 = new Point3D(5, 1);
		Point3D p3 = new Point3D(1, 5);
		Point3D p4 = new Point3D(5, 5);

		node_data n1 = new nodeData(p1);
		node_data n2 = new nodeData(p2);
		node_data n3 = new nodeData(p3);
		node_data n4 = new nodeData(p4);

		node_data[] nodes = { n1, n2, n3, n4 };
		for (int i = 0; i < nodes.length; i++) {
			g.addNode(nodes[i]);
		}

		g.connect(n1.getKey(), n2.getKey(), 1);
		g.connect(n1.getKey(), n4.getKey(), 5);
		g.connect(n1.getKey(), n3.getKey(), 8);

		g.connect(n2.getKey(), n3.getKey(), 5);
		g.connect(n2.getKey(), n4.getKey(), 1);
		g.connect(n2.getKey(), n1.getKey(), 7);

		g.connect(n3.getKey(), n1.getKey(), 4);
		g.connect(n3.getKey(), n4.getKey(), 1);

		g.connect(n4.getKey(), n3.getKey(), 3);

		Graph_Algo ga = new Graph_Algo();
		ga.init(g);
		List<Integer> targets = new ArrayList<Integer>();
		targets.add(1);
		targets.add(4);
		targets.add(3);

		List<node_data> expected = new ArrayList<node_data>();
		expected.add(n3);
		expected.add(n4);
		expected.add(n3);
		expected.add(n1);

		assertEquals(ga.TSP(targets), expected);
	}

}
