package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dataStructure.DGraph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Point3D;

public class MilionVertexTest {

	@Test
	public void testM() {
		DGraph Dg = new DGraph();
		long startTime = System.nanoTime();
		for (int i = 0; i < 1000000; i++) {
			double x = Math.random() * (10000 + 1) - 1;
			double y = Math.random() * (10000 + 1) - 1;
			Point3D p = new Point3D(x, y);
			node_data n = new nodeData(p);
			Dg.addNode(n);
		}
		for (int i = 1; i < 1000000 - 6; i++) {
			Dg.connect(i, i + 1, 0);
			Dg.connect(i + 1, i, 0);
			Dg.connect(i, i + 2, 0);
			Dg.connect(i + 2, i, 0);
			Dg.connect(i, i + 3, 0);
			Dg.connect(i + 3, i, 0);
			Dg.connect(i, i + 4, 0);
			Dg.connect(i + 4, i, 0);
			Dg.connect(i, i + 5, 0);
			Dg.connect(i + 5, i, 0);

		}
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		duration = duration / 1000000000;
		assertTrue(duration < 10);

	}

}
