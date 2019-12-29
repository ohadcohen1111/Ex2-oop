package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.Graph_Algo;
import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Point3D;

public class TestGraphAlgo {

	@Test
	public void testInitGraph() {
		fail("Not yet implemented");
	}

	@Test
	public void testInitString() {
		fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
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
		double[] shortestPath = new double[5];

		shortestPath[0] = ga.shortestPathDist(n.getKey(), n5.getKey());
		shortestPath[1] = ga.shortestPathDist(n4.getKey(), n5.getKey());
		shortestPath[2] = ga.shortestPathDist(n.getKey(), n3.getKey());
		shortestPath[3] = ga.shortestPathDist(n1.getKey(), n.getKey());
		shortestPath[4] = ga.shortestPathDist(n5.getKey(), n4.getKey());

		double[] expected = { 7, 7.5, 3.5, 5, 11 };

		for (int i = 0; i < shortestPath.length; i++) {
			assertEquals(expected[i], shortestPath[i], 0000.1);
		}
	}

	@Test
	public void testFindMinNode() {
		fail("Not yet implemented");
	}

	@Test
	public void testShortestPath() {
		fail("Not yet implemented");
	}

	@Test
	public void testTSP() {
		fail("Not yet implemented");
	}

}
