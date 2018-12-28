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
				hasCycle = UsefulAlgorithme.cycleDetectionRecursive(g, eTemp, e.to, 0);
				if(hasCycle) {
					return true;
				}
			}
		}
		
		return hasCycle;
	}
	
	/*
	 * used by cycleDetection()
	 */
	@SuppressWarnings("unchecked")
	public static boolean cycleDetectionRecursive(Graph g, Edge e, int dest, int depthCount) {
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
}
