package graphes;

public class LabyrintheAlex {

	
	/*
	 * return a graph due to a covering tree g
	 */
	public static Graph getLabyrinthe(Graph g) {
		Graph gTemp = Graph.Grid((int) Math.sqrt(g.vertices()));
		Graph gRes = new Graph(g.vertices());
		for(int i = 0 ; i < g.vertices() ; i++) {
			for(Edge eTemp : gTemp.adj(i)) {
				boolean addEdge = true;
				for(Edge e : g.adj(i)) {
					if(eTemp.edgeIsEquals(e)) {
						addEdge = false;
					}
				}
				if(addEdge) {
					gRes.addEdge(eTemp);
				}
			}
		}
		return gRes;
	}
	
	
	public static void testQ7() {
		Graph g = Graph.Grid(20);
		/* kruskal */
    		Test.printLaby(LabyrintheAlex.getLabyrinthe(Kruskal.algorithmeKruskal(g)), 20, "Q7Kruskal.tex");
		
		/* Wilson ou Aldous-Broder */
    		Test.printLaby(LabyrintheAlex.getLabyrinthe(Wilson.algorithmeWilson(g)), 20, "Q7Wilson.tex");
	}
	
}
