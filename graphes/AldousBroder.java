package graphes;

import java.util.ArrayList;

public class AldousBroder {
	/*
	 * Q5 : implantation de l'algorithme de Aldous-Broder
	 *  param: graph G
	 *  return: covering tree of G
	 */
	public static Graph algorithme(Graph g) {
		// graph res is the graph which will return
		Graph res = Graph.initialisation((int) Math.sqrt(g.vertices()));    			// fonctionne mais racine carré -> c'est sale
		
		// n is the first vertice (random generate). We start the new graph from this vertice 
		int n = (int) (Math.random()*(g.vertices()-0));
		
		// declaration of a list of edge which will use in the loop
		ArrayList<Edge> liste = null;
		
		// declaration of variables for the loop while
		int id, newVertice;
		
		while(!UsefulAlgorithme.isACoveringTree(res)) {
			liste = g.adj(n); 								// we get back the list of edges 
			id = (int) (Math.random()*(liste.size()-0));	    // we take a edge randomly from the current vertice
			newVertice = liste.get(id).other(n);				// we get back the neighboring vertice of this edge
			if(res.adj(newVertice).isEmpty() ) {				// if this neighbor has no edge joining him then we add this current edge
				res.addEdge(liste.get(id));
			};
			n = newVertice;	
			liste.clear();
		}
		// TODO
		return res;
	}

}
