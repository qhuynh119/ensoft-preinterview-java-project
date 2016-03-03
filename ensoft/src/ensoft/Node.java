package ensoft;

import java.util.ArrayList;

/**
 * Node class used to represent a location in the map.
 * 
 * @author Quan Huynh
 */
public class Node {
	
	/**
	 * Indicate whether this node is discovered when traversed downstream.
	 */
	private boolean peggyDiscovered;
	
	/**
	 * Indicate whether this node is discovered when traversed upstream.
	 */
	private boolean samDiscovered;
	
	/**
	 * An ArrayList of upstream neighbors.
	 */
	private ArrayList<Node> upNeighbors;
	
	/**
	 * An ArrayList of downstream neighbors.
	 */
	private ArrayList<Node> downNeighbors;
	
	/**
	 * Name of the node.
	 */
	private String name;
	
	/**
	 * Indicate whether a node must be avoid or not.
	 */
	private boolean mustAvoid;
	
	/**
	 * Node constructor with a name.
	 * @param name name of the node
	 */
	public Node(String name) {
		if (name == null) 
			throw new IllegalArgumentException();
		peggyDiscovered = false;
		samDiscovered = false;
		mustAvoid = false;
		upNeighbors = new ArrayList<Node>();
		downNeighbors = new ArrayList<Node>();
		this.name = name;
	}
	
	/**
	 * Return name of the node.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the boolean value of peggyDiscovered.
	 */
	public boolean isDiscoveredByPeggy() {
		return peggyDiscovered;
	}
	
	/**
	 * Return the boolean value of samDiscovered.
	 */
	public boolean isDiscoveredBySam() {
		return samDiscovered;
	}
	
	/**
	 * Set the boolean value of peggyDiscovered.
	 */
	public void setPeggyDiscovered(boolean value) {
		peggyDiscovered = value;
	}
	
	/**
	 * Set the boolean value of samDiscovered.
	 */
	public void setSamDiscovered(boolean value) {
		samDiscovered = value;
	}
	
	/**
	 * Return an ArrayList of upstream neighbors.
	 */
	public ArrayList<Node> getUpNeighbors() {
		return upNeighbors;
	}
	
	/**
	 * Return an ArrayList of downstream neighbors.
	 */
	public ArrayList<Node> getDownNeighbors() {
		return downNeighbors;
	}
	
	/**
	 * Return a specific Node upstream neighbor by index.
	 * @param i index in upNeighbors ArrayList
	 */
	public Node getUpNeighbor(int i) {
		if (i < 0 || i >= upNeighbors.size())
			throw new IllegalArgumentException();
		return upNeighbors.get(i);
	}
	
	/**
	 * Return a specific Node downstream neighbor by index.
	 * @param i index in downNeighbors ArrayList
	 */
	public Node getDownNeighbor(int i) {
		if (i < 0 || i >= downNeighbors.size())
			throw new IllegalArgumentException();
		return downNeighbors.get(i);
	}
	
	/**
	 * Return the number of upstream neighbors.
	 */
	public int numUpNeighbors() {
		return upNeighbors.size();
	}
	
	/**
	 * Return the number of upstream neighbors.
	 */
	public int numDownNeighbors() {
		return downNeighbors.size();
	}
	
	/**
	 * Add a new Node upstream neighbor.
	 * @param n the node of new upstream neighbor
	 */
	public void addUpNeighbor(Node n) {
		if (n == null) 
			throw new IllegalArgumentException();
		upNeighbors.add(n);
	}
	
	/**
	 * Add a new Node downstream neighbor.
	 * @param n the node of new downstream neighbor
	 */
	public void addDownNeighbor(Node n) {
		if (n == null) 
			throw new IllegalArgumentException();
		downNeighbors.add(n);
	}
	
	/**
	 * Set this node's mustAvoid to true.
	 */
	public void setAvoid() {
		mustAvoid = true;
	}
	
	/**
	 * Return the boolean value of mustAvoid.
	 */
	public boolean mustAvoid() {
		return mustAvoid;
	}
	
}
