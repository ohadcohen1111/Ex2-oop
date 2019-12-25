package algorithms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.tools.sjavac.server.SysInfo;

import dataStructure.DGraph;
import dataStructure.edge_data;
import dataStructure.graph;
import dataStructure.nodeData;
import dataStructure.node_data;
import utils.Point3D;

/**
 * This empty class represents the set of graph-theory algorithms which should
 * be implemented as part of Ex2 - Do edit this class.
 * 
 * @author
 *
 */
public class Graph_Algo implements graph_algorithms {

	private graph Dgraph;

	public Graph_Algo() {
		this.Dgraph = new DGraph();
	}

	public Graph_Algo(graph dgraph) {
		this.Dgraph = dgraph;
	}

	@Override
	public void init(graph g) {

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
		for (node_data ver : vertex) {
			Collection<edge_data> edge = this.Dgraph.getE(ver.getKey());
			if (edge == null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		Collection<node_data> vertex = this.Dgraph.getV();
		for (node_data ver : vertex) {
			ver.setWeight(Integer.MAX_VALUE);
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
		double minWeight = Integer.MAX_VALUE;
		int id = 0;
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
		} else if (path == Integer.MAX_VALUE) {
			throw new RuntimeException("No path");
		}
		String ans = this.Dgraph.getNode(dest).getInfo();// the string of the short path
		String[] node = ans.split(",");
		for (int i = 0; i < node.length; i++) {
			int key = Integer.parseInt(node[i]);
			shortPath.add(this.Dgraph.getNode(key));
		}
		return shortPath;
	}

	@Override
	public List<node_data> TSP(List<Integer> targets) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public graph copy() {
		// TODO Auto-generated method stub
		return null;
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
		// g1.addNode(nd3);
		// g1.addNode(nd4);
		// g1.addNode(nd5);
		// g1.connect(nd3.getKey(), nd1.getKey(), 3);
		// g1.connect(nd3.getKey(), nd4.getKey(), 0.5);
		// g1.connect(nd5.getKey(), nd3.getKey(), 5);
		// g1.connect(nd5.getKey(), nd4.getKey(), 4);
		// g1.connect(nd4.getKey(), nd2.getKey(), 0.5);
		// g1.connect(nd5.getKey(), nd.getKey(), 1);
		// g1.connect(nd.getKey(), nd4.getKey(), 6);
		// g1.connect(nd2.getKey(), nd1.getKey(), 0.5);

		// g1.connect(nd4.getKey(), nd3.getKey(), 0.5);

		g1.connect(nd.getKey(), nd1.getKey(), 2);
		g1.connect(nd1.getKey(), nd2.getKey(), 4);
		g1.connect(nd2.getKey(), nd.getKey(), 3);

		// g1.connect(nd5.getKey(), nd.getKey(), 0);
		// System.out.println(g1.isConnected(nd2.getKey()));
		// g1.draw();
		Graph_Algo g = new Graph_Algo(g1);
		// System.out.println(g.shortestPath(nd3.getKey(), nd3.getKey()));
		// System.out.println(g.shortestPathDist(nd.getKey(), nd1.getKey()));
		System.out.println(g.isConnected());
	}

}
