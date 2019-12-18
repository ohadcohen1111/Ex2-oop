package dataStructure;

public class edgeData implements edge_data {
	private nodeData src;
	private nodeData des;
	private double weight;

	public edgeData(nodeData src, nodeData des, double weight) {
		this.src = src;
		this.des = des;
		this.weight = weight;
	}

	/**
	 * The id of the source node of this edge.
	 * 
	 * @return
	 */
	public int getSrc() {
		return this.src.getKey();
	}

	/**
	 * The id of the destination node of this edge
	 * 
	 * @return
	 */
	public int getDest() {
		return this.des.getKey();
	}

	/**
	 * @return the weight of this edge (positive value).
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * return the remark (meta data) associated with this edge.
	 * 
	 * @return
	 */
	public String getInfo() {
		return null;
	}

	/**
	 * Allows changing the remark (meta data) associated with this edge.
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
	 * Allow setting the "tag" value for temporal marking an edge - common practice
	 * for marking by algorithms.
	 * 
	 * @param t - the new value of the tag
	 */
	public void setTag(int t) {

	}

}
