package ensoft;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

/**
 * Find all the possible meeting locations for Sam and Peggy.
 * 
 * @author Quan Huynh
 */
public class FindDestinations {
	
	/**
	 * Find all the possible locations that can be reached from a
	 * specific start location going downstream.
	 * 
	 * @param starts  the array containing all start locations' names
	 * @param nodeMap the hash map containing all the nodes in the map
		              with their names as keys
	 * @param s       set of all the nodes that can be reached
	 */
	public static void traverseDown(String[] starts, Map<String, Node> nodeMap,
			Set<String> s) {
		if (starts == null || nodeMap == null || s == null)
			throw new IllegalArgumentException();
		Queue<String> q = new LinkedList<String>();

		// Add all the start locations to the queue.
		for (String str : starts)
			q.add(str);
		
		// This count variable is used to keep track of 
		// the number of elements in the queue.
		int count = starts.length;
		while (!q.isEmpty()) {
			String[] arr = new String[count];
			
			// Move all the elements from the queue to the array arr.
			for (int i = 0; i < count; i++) {
				arr[i] = q.poll();
			}
			
			for (int i = 0; i < arr.length; i++) {
				if (!nodeMap.get(arr[i]).mustAvoid()) {
					s.add(arr[i]);
					nodeMap.get(arr[i]).setPeggyDiscovered(true);
				}
			}
			
			// Set count back to 0.
			count = 0;
			
			// Loop through all the downstream neighbor nodes of
			// each node in the array arr.
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < nodeMap.get(arr[i]).getDownNeighbors().size(); j++) {
					
					// Only add a node to the queue when that node mustn't be avoided
					// and hasn't been discovered by Peggy yet.
					if (!nodeMap.get(arr[i]).getDownNeighbor(j).mustAvoid()
							&& !nodeMap.get(arr[i]).getDownNeighbor(j).isDiscoveredByPeggy()) {
						q.add(nodeMap.get(arr[i]).getDownNeighbor(j).getName());
						nodeMap.get(arr[i]).getDownNeighbor(j).setPeggyDiscovered(true);
						count++;			
					}
				}
			}
		}
	}
	
	/**
	 * Find all the possible locations that can be reached from a
	 * specific start location going upstream.
	 * 
	 * @param starts  the array containing all start locations' names
	 * @param nodeMap the hash map containing all the nodes in the map
		              with their names as keys
	 * @param s       set of all the nodes that can be reached
	 */
	public static void traverseUp(String[] starts, Map<String, Node> nodeMap,
			Set<String> s) {
		if (starts == null || nodeMap == null || s == null)
			throw new IllegalArgumentException();
		Queue<String> q = new LinkedList<String>();
		
		// Add all the start locations to the queue.
		for (String str : starts)
			q.add(str);
		
		// This count variable is used to keep track of 
		// the number of elements in the queue.
		int count = starts.length;
		while (!q.isEmpty()) {
			String[] arr = new String[count];
			
			// Move all the elements from the queue to the array arr.
			for (int i = 0; i < count; i++) {
				arr[i] = q.poll();
			}
			
			for (int i = 0; i < arr.length; i++) {
				if (!nodeMap.get(arr[i]).mustAvoid()) {
					s.add(arr[i]);
					nodeMap.get(arr[i]).setSamDiscovered(true);
				}
			}
			
			// Set count back to 0.
			count = 0;
			
			// Loop through all the upstream neighbor nodes of
			// each node in the array arr.
			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < nodeMap.get(arr[i]).getUpNeighbors().size(); j++) {
					
					// Only add a node to the queue when that node mustn't be avoided
					// and hasn't been discovered by Sam yet.
					if (!nodeMap.get(arr[i]).getUpNeighbor(j).mustAvoid()
							&& !nodeMap.get(arr[i]).getUpNeighbor(j).isDiscoveredBySam()) {
							q.add(nodeMap.get(arr[i]).getUpNeighbor(j).getName());
							nodeMap.get(arr[i]).getUpNeighbor(j).setSamDiscovered(true);
							count++;
					}
				}
			}
		}
		
	}
	
	/**
	 * Find all the possible meeting locations for Sam and Peggy.
	 * 
	 * @param nodeMap            the hash map containing all the nodes in the map
		                         with their names as keys
	 * @param peggyStarts        the array containing all of Peggy's start locations' names
	 * @param samStarts          the array containing all of Sam's start locations' names
	 * @param peggyVisitedNodes  a tree set containing all the names of the nodes
	 *                           which are reachable from Peggy's start locations.
	 * @param samVisitedNodes    a tree set containing all the names of the nodes
	 *                           which are reachable from Sam's start locations.
	 */
	public static void find(Map<String, Node> nodeMap,
			String[] peggyStarts, String[] samStarts,
			Set<String> peggyVisitedNodes, Set<String> samVisitedNodes) {
		if (nodeMap == null || peggyVisitedNodes == null || samVisitedNodes == null)
			throw new IllegalArgumentException();
		
		Scanner s = new Scanner(System.in);
		
		String line = s.nextLine();
			
		while (!(line = s.nextLine().trim()).equals("Avoid:")) {	
			String[] nodeNames = line.split(" ");
			String name1 = nodeNames[0];
			String name2 = nodeNames[1];
			Node n1 = new Node(name1);
			Node n2 = new Node(name2);
			
			// Node n1 exists in the nodeMap but n2 doesn't.
			if (nodeMap.containsKey(name1) && !nodeMap.containsKey(name2)) {
				nodeMap.put(name2, n2);		
				
			// Node n2 exists in the nodeMap but n1 doesn't.
			} else if (!nodeMap.containsKey(name1) && nodeMap.containsKey(name2)){
				nodeMap.put(name1, n1);
				
			// Both nodes n1, n2 doesn't exist in the nodeMap.
			} else if (!nodeMap.containsKey(name1) && !nodeMap.containsKey(name2)) {
				nodeMap.put(name1, n1);
				nodeMap.put(name2, n2);
			}	
			
			// Connect n1, n2 together.
			nodeMap.get(name1).addDownNeighbor(nodeMap.get(name2));
			nodeMap.get(name2).addUpNeighbor(nodeMap.get(name1));	
		}
		
		line = s.nextLine();
		
		// Read all the information about which nodes should be avoided.
		if (!line.trim().isEmpty()) {
			String[] avoidNodeNames = line.trim().split(" ");
			for (String str : avoidNodeNames) {
				nodeMap.get(str).setAvoid();
			}
		}
		
		line = s.nextLine();
		
		// Read all the information about Peggy start's locations.
		peggyStarts = (line = s.nextLine()).trim().split(" ");
		
		line = s.nextLine();
		
		// Read all the information about Peggy start's locations.
		samStarts = (line = s.nextLine()).trim().split(" ");
		
		s.close();
		
		// Traverse downstream from Peggy's start locations and
		// add the visited nodes to peggyVisitedNodes
		traverseDown(peggyStarts, nodeMap, peggyVisitedNodes);
		
		// Traverse upstream from Sam's start locations and
		// add the visited nodes to samVisitedNodes
		traverseUp(samStarts, nodeMap, samVisitedNodes);
		
		Iterator<String> iter = samVisitedNodes.iterator();
		
		// Keep only the nodes which is in both Sam's and Peggy's 
		// set of reachable locations.
		while (iter.hasNext()) {
			if (!peggyVisitedNodes.contains(iter.next())) {
				iter.remove();
			}
		}
		
		String[] locations = new String[samVisitedNodes.size()];
		samVisitedNodes.toArray(locations);
		Arrays.sort(locations);
		
		// Print out all the possible meeting locations
		// for Sam and Peggy alphabetically.
		for (String str : locations) {
			System.out.println(str);
		}
	}
	
	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {	
		Map<String, Node> nodeMap = new HashMap<String, Node>();
		String[] peggyStarts = null;
		String[] samStarts = null;
		Set<String> peggyVisitedNodes = new HashSet<String>();
		Set<String> samVisitedNodes = new HashSet<String>();
		
		/* Find all the possible meeting locations for Sam and Peggy. */
		find(nodeMap, peggyStarts, samStarts,
				peggyVisitedNodes, samVisitedNodes);
	}

}
