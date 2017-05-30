package Dijkstras;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Dijkstras {
	static int[][] adj;
	static int distance[];
	static boolean visited[];
	static int source = 0;
	static int V;
	
	
	public static void main(String args[]) {
		adj = new int[][] {
			{0, 6, 10, 0, 0, 0, 0, 0, 0, 0}, 
			{6, 0, 12, 11, 14, 0, 0, 0, 0, 0}, 
			{10, 12, 0, 12, 0, 0, 8, 16, 0, 0}, 
			{0, 11, 12, 0, 0, 6, 3, 0, 0, 0}, 
			{0, 14, 0, 0, 0, 4, 0, 0, 6, 0}, 
			{0, 0, 0, 6, 4, 0, 0, 0, 12, 0}, 
			{0, 0, 8, 3, 0, 0, 0, 0, 16, 6}, 
			{0, 0, 16, 0, 0, 0, 0, 0, 0, 8}, 
			{0, 0, 0, 0, 6, 12, 16, 0, 0, 13}, 
			{0, 0, 0, 0, 0, 0, 6, 8, 13, 0}, 
		};
		V = adj.length;
		distance = new int[V];
		visited = new boolean[V];
		Arrays.fill(distance, Integer.MAX_VALUE);
		PriorityQueue<Node> vertices = new PriorityQueue<>();
		distance[source] = 0;
		vertices.add(new Node(source, 0));
		int parent[] = new int[adj.length];
		Arrays.fill(parent, -1);
		while (!vertices.isEmpty()) {
			Node start = vertices.remove();
			visited[start.getNode()] = true;
			for (int i = 0; i < adj.length; i++) {
				if (!visited[i]) {
					if (adj[start.getNode()][i] != 0) {
						int startdist = adj[start.getNode()][i];
						int newdist = distance[start.getNode()] + startdist;
						if (newdist < distance[i]) {
							distance[i] = newdist;
							parent[i] = start.getNode();
						}
						vertices.add(new Node(i, distance[i]));

					}
				}

			}
		}
		printSolution(distance, V, parent);

	}

	static void printPath(int parent[], int j) {
		// Base Case : If j is source
		if (parent[j] == -1)
			return;

		printPath(parent, parent[j]);

		System.out.printf("%d ", j);
	}

	static void printSolution(int dist[], int n, int parent[]) {
		int src = 0;
		System.out.printf("Vertex\tDistance\tPath");
		for (int i = 0; i < V; i++) {
			System.out.printf("\n%d -> %d \t %d\t\t%d ", src, i, dist[i], src);
			printPath(parent, i);
		}
	}

}

class Node implements Comparable<Node> {

	public int node;
	public int d;

	public Node(int node, int d) {
		this.node = node;
		this.d = d;
	}

	public int getNode() {
		return node;
	}

	@Override
	public int compareTo(Node n2) {
		if (this.d < n2.d) {
			return -1;
		}
		if (this.d > n2.d) {
			return 1;
		}
		return 0;
	}

}