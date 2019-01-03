package graphes;

import java.util.HashSet;

public class UsefulAlgorithme {

	/*
	 * return true if adding the edge e to the graph g create a cycle
	 * else false
	 */
	@SuppressWarnings("unchecked")
	public static boolean cycleDetection(Graph g, Edge e) {
		UnionFind unionFind = new UnionFind(g.vertices());
		for(Edge eTemp : g.edges()) {
			unionFind.unifie(eTemp.from, eTemp.to);
		}
		
		return unionFind.unifie(e.from, e.to);
	}
	
	
	
	/*
	 * return true if the graph g is a covering tree
	 * WARNING may have no cycle
	 */
	public static boolean isACoveringTree(Graph g) {
		UnionFind unionFind = new UnionFind(g.vertices());
		for(Edge e : g.edges()) {
			unionFind.unifie(e.to, e.from);
		}
		if(unionFind.getNumberOfSets() == 1) {
			return true;
		}
		return false;
	}
	
	
	
	public static void main(String... args) {
		Graph g = new Graph(5);
		g.addEdge(new Edge(0, 1));
		g.addEdge(new Edge(1, 2));
		g.addEdge(new Edge(3, 2));
		g.addEdge(new Edge(3, 4));

		
		System.out.println(UsefulAlgorithme.isACoveringTree(g));

		
	}
}
