package dataStructure;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class DGraph implements graph {

	HashMap<Integer, nodeData> Graph = new HashMap<Integer, nodeData>();

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
	}

	@Override
	public void connect(int src, int dest, double w) {
		this.Graph.get(src).addEdge(this.Graph.get(dest));
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
		// TODO Auto-generated method stub
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

}
