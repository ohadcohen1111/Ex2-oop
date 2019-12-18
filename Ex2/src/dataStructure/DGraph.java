package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

import utils.Point3D;

public class DGraph implements graph {
	static int size_vertex = 0;
	static int size_edge = 0;
	private HashMap<Integer, nodeData> Graph = new HashMap<Integer, nodeData>();

	public DGraph() {
		;
	}

	@Override
	public node_data getNode(int key) {
		return this.Graph.get(key);

	}

	@Override
	public edge_data getEdge(int src, int dest) {
		return this.Graph.get(src).getEdg(dest);
	}

	@Override
	public void addNode(node_data n) {
		this.Graph.put(n.getKey(), (nodeData) n);
		size_vertex++;
	}

	@Override
	public void connect(int src, int dest, double w) {
		if (this.Graph.containsKey(src)) {
			this.Graph.get(src).addEdge(this.Graph.get(dest));
			size_edge++;
		}
	}

	@Override
	public Collection<node_data> getV() {

		return null;
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public node_data removeNode(int key) {

		return null;
	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int nodeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int edgeSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		String ans = "";
		for (HashMap.Entry me : this.Graph.entrySet()) {
			ans += me.getKey() + ", ";
		}
		return ans;
	}

	public static void main(String[] args) {
		DGraph g = new DGraph();
		Point3D p = new Point3D(1, 2, 3);
		nodeData nd = new nodeData(1, p, 0);
		nodeData nd1 = new nodeData(5, p, 0);
		g.addNode(nd);
		g.addNode(nd1);
		g.connect(nd.getKey(), nd1.getKey(), 0);
		System.out.println(g);
	}
}
