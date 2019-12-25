package dataStructure;

import utils.Point3D;

public class nodeData implements node_data {
	private Point3D location;
	private int key;
	private int tag;
	private double weight;
	private String info;
	static int id = 1;

	public nodeData(Point3D location) {
		this.location = location;
		this.weight = 0;
		this.key = id++;
		this.tag = 0;
		this.info = "";
	}

	public int getKey() {
		return this.key;
	}

	public Point3D getLocation() {
		return this.location;
	}

	public void setLocation(Point3D p) {
		this.location = p;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double w) {
		this.weight = w;
	}

	public String getInfo() {
		return this.info;
	}

	public void setInfo(String s) {
		this.info = s;
	}

	public int getTag() {
		return this.tag;
	}

	public void setTag(int t) {
		this.tag = t;

	}
}
