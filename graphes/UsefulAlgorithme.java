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
	
	
	/*
	 * return true if the graph g is a covering tree
	 */
	@SuppressWarnings("not working")
	public static boolean isACoveringTree(Graph g) {
		boolean res = true;
		boolean[] vertices = new boolean[g.vertices()];
		for(int i = 0 ; i < vertices.length ; i++) {
			vertices[i] = false;
		}
		
		for(Edge e : g.adj(0)) {
			vertices[0] = true;
			if(e.to == 0) {
				UsefulAlgorithme.isACoveringTreeAuxilary(vertices, g, e.from, e);
			}
			else {
				UsefulAlgorithme.isACoveringTreeAuxilary(vertices, g, e.to, e);
			}
		}
		for(int i = 0 ; i < vertices.length && res ; i++) {
			res = res && vertices[i];
		}
		return res;
	}
	
	/*
	 * param :
	 * - tab[] is the vertices' boolean tab which is set on true if the vertice is in the way
	 * - g is the graph
	 * - origin is the origin of the edges explorations
	 * - e is the current edge
	 */
	@SuppressWarnings("not working")
	private static void isACoveringTreeAuxilary(boolean[] tab, Graph g, int origin, Edge e) {
		tab[origin] = true;
		for(Edge eTemp : g.adj(origin)) {
			if(eTemp != e) {
				if(eTemp.to == origin) {
					UsefulAlgorithme.isACoveringTreeAuxilary(tab, g, eTemp.from, eTemp);
				}
				else {
					UsefulAlgorithme.isACoveringTreeAuxilary(tab, g, eTemp.to, eTemp);
				}
			}
		}
	}
	
	
	public static void main(String... args) {
		Graph g = new Graph(5);
		g.addEdge(new Edge(0, 1));
		g.addEdge(new Edge(1, 2));
		g.addEdge(new Edge(3, 2));

		g.addEdge(new Edge(3, 4));

		System.out.println(UsefulAlgorithme.cycleDetection(g, new Edge(3, 4)));
		System.out.println(UsefulAlgorithme.cycleDetection(g, new Edge(2, 3)));
		System.out.println(UsefulAlgorithme.cycleDetection(g, new Edge(0, 4)));
		System.out.println(UsefulAlgorithme.cycleDetection(g, new Edge(2, 0)));
		
		System.out.println(UsefulAlgorithme.isACoveringTree(g));

		
	}
}
