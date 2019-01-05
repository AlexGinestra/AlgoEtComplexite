package graphes;

import java.util.ArrayList;

public class Labyrinthe {

	
	/*
	 * return a graph due to a covering tree g
	 */
	public static Graph getLabyrinthe(Graph g) {
		Graph grilleLab = Graph.Grid((int) Math.sqrt(g.vertices()));
		Graph gRes = Graph.initialisation((int) Math.sqrt(g.vertices()));
		for(int i = 0 ; i < g.vertices() ; i++) {
			for(Edge eGrille : grilleLab.adj(i)) {
				boolean addEdge = true;
				for(Edge e : g.adj(i)) {
					if(eGrille.edgeIsEquals(e)) {
						addEdge = false;
					}
				}
				if(addEdge && (eGrille.to >= i || eGrille.from >= i)) {
					gRes.addEdge(eGrille);
				}
			}
		}
		return gRes;
	}
	
	/*
	 * return the number of vertices to reach the goal
	 */
	public static int stepNumber(Graph g, Edge currentEdge, int currentVertice, int deepth, int goal) {
		int res = 0;
		if(goal == currentVertice) {
			return deepth;
		}
		for(Edge e : g.adj(currentVertice)) {
			if(currentEdge != e) {
				res += stepNumber(g, e, e.other(currentVertice), deepth+1, goal);
			}
		}
		return res;
	}
	
	
	/*
	 * return the number of dead-end in a graphe of ways (kruskal/wilson/Aldous)
	 */
	public static int deadEndNumber(Graph g, Edge currentEdge, int currentVertice) {
		int res = 0;
		ArrayList<Edge> listeTemp = g.adj(currentVertice);
		for(Edge e : listeTemp) {
			if(currentEdge != e) {
				res += deadEndNumber(g, e, e.other(currentVertice));
			}
		}
		if(currentVertice == 0 && listeTemp.size() == 2) {
			res++;
		}
		else if(listeTemp.size() == 3) {
			res++;
		}
		else if(listeTemp.size() == 4) {
			res += 2;
		}
		return res;
	}
	
	public static void testQ7() {
		Graph g = Graph.Grid(20);
		/* kruskal */
    		Test.printLaby(Labyrinthe.getLabyrinthe(Kruskal.algorithmeKruskal(g)), 20, "Q7Kruskal.tex");
		
		/* Wilson ou Aldous-Broder */
    		Test.printLaby(Labyrinthe.getLabyrinthe(Wilson.algorithmeWilson(g)), 20, "Q7Wilson.tex");
	}
	
}
