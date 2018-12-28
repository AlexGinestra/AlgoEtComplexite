package graphes;

import java.util.ArrayList;

public class AldousBroder {
	/*
	 * Q5 : implantation de l'algorithme de Aldous-Broder
	 *  param: graph G
	 *  return: covering tree of G
	 */
	public static Graph algorithme(Graph g) {
		// graph res is the graph which will return.
		Graph res = g;
		res.removeList();
		// n is the first vertice (random generate). We start the new graph from this vertice. 
		int n = (int) (Math.random()*(g.vertices()-0));
		// declaration of a list of edge 
		ArrayList<Edge> liste = new ArrayList<Edge>();
		// declaration of variables for the loop while
		int id, newVertice;
		while(!finish(res)) {
			liste = g.adj(n);
			id = (int) (Math.random()*liste.size());
			System.out.println("ok\n");
			// beug ici 
			newVertice = liste.get(id).other(n);
			if(g.adj(newVertice) == null ) {
				g.addEdge(liste.get(id));
			};
			n = newVertice;
		}
		// TODO
		return res;
	}
	
	// fonction which test if the graph is connexe. So if this algo is finish.
	private static boolean finish(Graph g){
		for(int i = 0; i < g.vertices() ; i++) {
			if(g.getAdj(i).size() == 0) {
				return false;
			}
		}
		return true;
	}
}
