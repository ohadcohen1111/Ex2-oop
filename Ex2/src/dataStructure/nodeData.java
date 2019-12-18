package dataStructure;

import java.util.HashMap;

import utils.Point3D;

public class nodeData implements node_data {
	private int key;
	private Point3D location;
	private double weight;
	HashMap<Integer, edgeData> edge = new HashMap<Integer, edgeData>();

	public nodeData(int key, Point3D location, double weight) {
		this.key = key;
		this.location = location;
		this.weight = weight;
	}

	public void addEdge(nodeData dest) {
		if (!edge.containsKey(dest.getKey())) {
			this.edge.put(dest.key, new edgeData(this, dest, 0));
		}
	}

	public void removeEdge(nodeData dest) {
		if (edge.containsKey(dest.getKey())) {
			this.edge.remove(dest.key);
		}
	}

	public edgeData getEdg(int dest) {
		if (edge.containsKey(dest)) {
			return this.edge.get(dest);
		}
		return null;
	}

	/**
	 * This interface represents the set of operations applicable on a node (vertex)
	 * in a (directional) weighted graph.
	 * 
	 * @author boaz.benmoshe
	 *
	 */
	/**
	 * Return the key (id) associated with this node.
	 * 
	 * @return
	 */

	public int getKey() {
		return this.key;
	}

	/**
	 * Return the location (of applicable) of this node, if none return null.
	 * 
	 * @return
	 */
	public Point3D getLocation() {
		return this.location;
	}

	/**
	 * Allows changing this node's location.
	 * 
	 * @param p - new new location (position) of this node.
	 */
	public void setLocation(Point3D p) {
		this.location = p;
	}

	/**
	 * Return the weight associated with this node.
	 * 
	 * @return
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * Allows changing this node's weight.
	 * 
	 * @param w - the new weight
	 */
	public void setWeight(double w) {
		this.weight = w;
	}

	/**
	 * return the remark (meta data) associated with this node.
	 * 
	 * @return
	 */
	public String getInfo() {
		String info = "";
		info += this.getKey() + "," + this.getLocation() + "," + this.getWeight();
		return info;
	}

	/**
	 * Allows changing the remark (meta data) associated with this node.
	 * 
	 * @param s
	 */
	public void setInfo(String s) {

	}

	/**
	 * Temporal data (aka color: e,g, white, gray, black) which can be used be
	 * algorithms
	 * 
	 * @return
	 */
	public int getTag() {
		return 0;
	}

	/**
	 * Allow setting the "tag" value for temporal marking an node - common practice
	 * for marking by algorithms.
	 * 
	 * @param t - the new value of the tag
	 */
	public void setTag(int t) {

	}
}
