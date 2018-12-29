package graphes;

import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {
	
	
	/*
	 * Q2 : implantation de l'algorithme de Kruskal
	 *  param: graph G
	 *  return: covering tree of G
	 */
	public static Graph algorithme(Graph g) {
		Graph gRes = new Graph(g.vertices());
		ArrayList<Edge> edgesTemp = g.edges();
		Collections.shuffle(edgesTemp);
		
		
		return gRes;
		
	}

}
