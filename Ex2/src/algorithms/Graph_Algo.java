package algorithms;

import static org.junit.Assert.assertFalse;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import dataStructure.DGraph;
import dataStructure.edgeData;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Point3D;
import gui.GUI;

/**
 * This empty class represents the set of graph-theory algorithms which should
 * be implemented as part of Ex2 - Do edit this class.
 * 
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms, Serializable {
	final int INFINITE = Integer.MAX_VALUE;
	private graph Dgraph;

	@Override
	public void init(graph g) {
		this.Dgraph = g;
	}

	public void init(String file_name) {
		graph g = null;
		try {
			FileInputStream file = new FileInputStream(file_name);
			ObjectInputStream in = new ObjectInputStream(file);

			g = (graph) in.readObject();

			in.close();
			file.close();

			System.out.println("Object has been deserialized");

		}

		catch (IOException ex) {
			System.out.println("IOException is caught");
		}

		catch (ClassNotFoundException ex) {
			System.out.println("ClassNotFoundException is caught");
		}

	}

	@Override
	public void save(String file_name) {
		String filename = file_name;

		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(file);

			out.writeObject(Dgraph);

			out.close();
			file.close();

			System.out.println("Object has been serialized");
		} catch (IOException ex) {
			System.out.println("IOException is caught");
		}

	}

	@Override
	public boolean isConnected() {
		Collection<node_data> vertex = this.Dgraph.getV();
		for (node_data src : vertex) {
			for (node_data dest : vertex) {
				if (shortestPathDist(src.getKey(), dest.getKey()) == INFINITE) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		Collection<node_data> vertex = this.Dgraph.getV();
		for (node_data ver : vertex) {
			ver.setTag(0);
			ver.setInfo("");
			ver.setWeight(INFINITE);
		}
		this.Dgraph.getNode(src).setWeight(0);
		for (int i = 1; i <= this.Dgraph.nodeSize();) {
			int min = findMinNode();
			Collection<edge_data> edge = this.Dgraph.getE(this.Dgraph.getNode(min).getKey());
			if (edge != null) {
				for (edge_data e : edge) {
					// change the destination value to the minimum value between the current weight
					// and the previous weight
					double currentWeight = this.Dgraph.getNode(e.getDest()).getWeight();
					double srcPlusEdge = this.Dgraph.getNode(min).getWeight() + e.getWeight();
					if (srcPlusEdge < currentWeight) {
						this.Dgraph.getNode(e.getDest()).setWeight(srcPlusEdge);
						this.Dgraph.getNode(e.getDest()).setInfo(this.Dgraph.getNode(min).getInfo() + min + ",");
					}
				}
			}
			this.Dgraph.getNode(min).setTag(1);
			i++;
		}
		return this.Dgraph.getNode(dest).getWeight();
	}

	public int findMinNode() {
		Collection<node_data> vertex = this.Dgraph.getV();
		// initiate minWeight variable to find the min node
		double minWeight = INFINITE;
		int id = 1;
		for (node_data ver : vertex) {
			// if the current weight node is lower then weight, update the weight
			if (ver.getWeight() <= minWeight && (ver.getTag() == 0)) {
				minWeight = ver.getWeight();
				id = ver.getKey();
			}
		}
		return id;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> shortPath = new ArrayList<node_data>();
		double path = shortestPathDist(src, dest);// find the short path
		if (path == 0) {
			return shortPath;
		} else if (path == INFINITE) {
			throw new RuntimeException("No path");
		}
		String ans = this.Dgraph.getNode(dest).getInfo();// the string of the short path
		ans += this.Dgraph.getNode(dest).getKey(); // add the destination
		String[] node = ans.split(",");
		for (int i = 0; i < node.length; i++) {
			int key = Integer.parseInt(node[i]);
			shortPath.add(this.Dgraph.getNode(key));
		}
		return shortPath;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		if (!this.isConnected()) {
			return null;
		}
		double minDist = INFINITE;
		List<node_data> res = new ArrayList<node_data>();
		int[][] greatPath = new int[2][targets.size() - 1];
		List<node_data> smallestPath = new ArrayList<node_data>();
		Collection<node_data> vertex = this.Dgraph.getV();
		for (int i = 0; i < targets.size(); i++) {
			for (int j = 0; j < targets.size(); j++) {
				double currentRes = shortestPathDist(targets.get(i), targets.get(j));
				if (currentRes < minDist && currentRes != 0) {
					minDist = currentRes;
					smallestPath = shortestPath(targets.get(i), targets.get(j));
					greatPath[0][i] = targets.get(i);
					greatPath[1][i] = targets.get(j);
				}
			}
			targets.remove(i);
		}

		return null;
	}

	@Override
	public graph copy() {
		graph copy = new DGraph();
		Collection<node_data> vertex = this.Dgraph.getV();
		for (node_data ver : vertex) {
			node_data n = ver;
			copy.addNode(n);
		}
		for (node_data ver : vertex) {
			Collection<edge_data> edge = this.Dgraph.getE(ver.getKey());
			if (edge != null) {
				for (edge_data e : edge) {
					copy.connect(e.getSrc(), e.getDest(), e.getWeight());
				}
			}
		}
		return copy;
	}

}
