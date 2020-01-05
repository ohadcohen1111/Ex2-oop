package dataStructure;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import gui.GUI;
import utils.Point3D;
import utils.StdDraw;

public class DGraph implements graph, Serializable {
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
		if (!Vertex.containsKey(key)) {
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

}
