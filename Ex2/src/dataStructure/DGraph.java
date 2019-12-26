package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import utils.Point3D;
import utils.StdDraw;

public class DGraph implements graph {
	private HashMap<Integer, node_data> Vertex;
	private HashMap<Integer, HashMap<Integer, edge_data>> Neib;
	private int mc;

	public DGraph() {
		this.Vertex = new HashMap<Integer, node_data>();
		this.Neib = new HashMap<Integer, HashMap<Integer, edge_data>>();
		this.mc = 0;
	}

	@Override
	public node_data getNode(int key) {
		return this.Vertex.get(key);
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return this.Neib.get(src).get(dest);
	}

	@Override
	public void addNode(node_data n) {
		if (!this.Vertex.containsKey(n.getKey())) {
			this.Vertex.put(n.getKey(), n);
			mc++;
		}
	}

	@Override
	public void connect(int src, int dest, double w) {
		if (src == dest) {
			throw new RuntimeException("Its impossible to connect a vertex to itself");
		}
		if (this.Vertex.containsKey(src) && this.Vertex.containsKey(dest)) { // check if the vertex are exists
			node_data s = this.Vertex.get(src);
			node_data d = this.Vertex.get(dest);
			edge_data e = new edgeData(s, d, w);
			if (this.Neib.get(src) == null) {
				HashMap<Integer, edge_data> t = new HashMap<>(); // add new neighbor
				t.put(dest, e);
				this.Neib.put(src, t);
			} else {
				this.Neib.get(src).put(dest, e);
			}
		}
		mc++;
	}

	@Override
	public Collection<node_data> getV() {
		Collection<node_data> V = this.Vertex.values();
		return V;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if (Neib.get(node_id) == null) {
			return null;
		}
		return Neib.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		if (!Neib.containsKey(key)) {
			return null;
		}
		node_data nd = Vertex.get(key);
		for (HashMap edge : Neib.values()) {
			if (edge.containsKey(key)) {
				edge.remove(key);
			}
		}
		this.Vertex.remove(key);
		this.Neib.remove(key);
		mc++;
		return nd;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if (Neib.get(src).containsKey(dest)) {
			mc++;
			return Neib.get(src).remove(dest);
		}
		throw new RuntimeException("The egde doesnt exists");
	}

	@Override
	public int nodeSize() {
		return this.Vertex.size();
	}

	@Override
	public int edgeSize() {
		int size = 0;
		for (HashMap edgeSize : Neib.values()) {
			size += edgeSize.size();
		}
		return size;
	}

	@Override
	public int getMC() {
		return mc;
	}

	public static void main(String[] args) {
//		DGraph g = new DGraph();
//		Point3D p = new Point3D(1, 2, 3);
//		Point3D p1 = new Point3D(3, 4, 3);
//		Point3D p2 = new Point3D(8, 11, 3);
//		Point3D p3 = new Point3D(6, 1, 3);
//		nodeData nd = new nodeData(p);
//		nodeData nd1 = new nodeData(p1);
//		nodeData nd2 = new nodeData(p2);
//		nodeData nd3 = new nodeData(p3);
//		g.addNode(nd);
//		g.addNode(nd1);
//		g.addNode(nd2);
//		g.addNode(nd3);
//		g.connect(nd.getKey(), nd1.getKey(), 0);
//		g.connect(nd.getKey(), nd2.getKey(), 0);
//		g.connect(nd.getKey(), nd3.getKey(), 0);
//		// g.connect(nd2.getKey(), nd3.getKey(), 0);
//		g.draw();
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
		g1.connect(nd3.getKey(), nd1.getKey(), 0);
		g1.connect(nd3.getKey(), nd4.getKey(), 0);
		g1.connect(nd5.getKey(), nd3.getKey(), 0);
		g1.connect(nd5.getKey(), nd4.getKey(), 0);
		g1.connect(nd4.getKey(), nd2.getKey(), 0);
		g1.connect(nd5.getKey(), nd.getKey(), 0);
		g1.connect(nd.getKey(), nd4.getKey(), 0);

		g1.connect(nd4.getKey(), nd3.getKey(), 0);

		// g1.connect(nd5.getKey(), nd.getKey(), 0);
		// System.out.println(g1.isConnected(nd2.getKey()));
		// g1.draw();
		// g1.removeNode(nd3.getKey());
		// g1.draw();
		// g1.removeEdge(nd3.getKey(), nd1.getKey());
		// g1.draw();
		// System.out.println(g1.getEdge(nd3.getKey(), nd1.getKey()));
		// System.out.println(g1.edgeSize());

	}

}
