package graphes;

public class UsefulAlgorithme {

	/*
	 * return true if adding the edge e to the graph g create a cycle
	 * else false
	 */
	@SuppressWarnings("unchecked")
	public static boolean cycleDetection(Graph g, Edge e) {
		boolean hasCycle = false;
		if(!(g.adj(e.from).size() == 0 || g.adj(e.to).size() == 0)) {
			for(Edge eTemp : g.adj(e.to)) {
				hasCycle = UsefulAlgorithme.cycleDetectionRecursive(g, eTemp, e.from, 0);
				if(hasCycle) {
					return true;
				}
			}
		}
		
		return hasCycle;
	}
	
	/*
	 * used by cycleDetection()
	 * param :
	 * - g is the graph without the new edge
	 * - e is the current edge
	 * - dest is the vertice where the new edge start
	 * - depthcount is used to know if there is a other cycle
	 * 
	 * return :
	 * - true if there is a cycle in the graphe with or without the new edge
	 * - false in other case
	 */
	@SuppressWarnings("unchecked")
	private static boolean cycleDetectionRecursive(Graph g, Edge e, int dest, int depthCount) {
		boolean hasCycle = false;
		if(e.to == dest || e.from == dest || depthCount > g.getEdgesNumber()) {
			return true;
		}
		for(Edge eTemp : g.adj(e.to)) {
			if(eTemp != e) {
				hasCycle = UsefulAlgorithme.cycleDetectionRecursive(g, eTemp, dest, depthCount++);
				if(hasCycle) {
					return true;
				}
			}
		}
		return hasCycle;
	}
	
	
	public static void main(String... args) {
		Graph g = new Graph(5);
		g.addEdge(new Edge(0, 1));
		g.addEdge(new Edge(1, 2));
		g.addEdge(new Edge(3, 4));

		System.out.println(UsefulAlgorithme.cycleDetection(g, new Edge(3, 4)));
		System.out.println(UsefulAlgorithme.cycleDetection(g, new Edge(2, 3)));
		System.out.println(UsefulAlgorithme.cycleDetection(g, new Edge(0, 4)));
		System.out.println(UsefulAlgorithme.cycleDetection(g, new Edge(2, 0)));


		
		
	}
}
