package graphes;

import java.util.ArrayList;

public class AldousBroder {
	/*
	 * Q5 : implantation de l'algorithme de Aldous-Broder
	 *  param: graph G
	 *  return: covering tree of G
	 */
	public static Graph algorithme(Graph g) {
		// graph res is the graph which will return so we initialize the coordinates of the graph with the coordinates of the initial graph
		Graph res = Graph.initialisation((int) Math.sqrt(g.vertices()));    			
		
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
	
	
	/*
	 * test requis pour la question 5
	 */
	public static void testQ5() {
		
		Graph graphModel = Graph.Grid(2);
		graphModel.addEdge(new Edge(0,3));
		
		
		Graph gTemp;
		Graph[] g = new Graph[8];
		int[] nbGraphAldousBroder = new int[8];
		
		for(int i = 0 ; i < 8 ; i++) {
			g[i] = new Graph(4);
			nbGraphAldousBroder[i] = 0;
		}
		//graph g1
		g[0].addEdge(new Edge(0,1));
		g[0].addEdge(new Edge(0,2));
		g[0].addEdge(new Edge(2,3));
		
		//graph g2
		g[1].addEdge(new Edge(0,2));
		g[1].addEdge(new Edge(1,3));
		g[1].addEdge(new Edge(2,3));
		
		//graph g3
		g[2].addEdge(new Edge(0,1));
		g[2].addEdge(new Edge(0,2));
		g[2].addEdge(new Edge(1,3));
		
		//graph g4
		g[3].addEdge(new Edge(0,1));
		g[3].addEdge(new Edge(1,3));
		g[3].addEdge(new Edge(2,3));
		
		//graph g5
		g[4].addEdge(new Edge(0,3));
		g[4].addEdge(new Edge(1,3));
		g[4].addEdge(new Edge(2,3));

		//graph g6
		g[5].addEdge(new Edge(0,1));
		g[5].addEdge(new Edge(0,3));
		g[5].addEdge(new Edge(2,3));

		//graph g7
		g[6].addEdge(new Edge(0,1));
		g[6].addEdge(new Edge(0,2));
		g[6].addEdge(new Edge(0,3));

		//graph g8
		g[7].addEdge(new Edge(0,2));
		g[7].addEdge(new Edge(0,3));
		g[7].addEdge(new Edge(1,3));

		// do 1 000 000 times AldousBroder on the example in the project subject
		for(int i = 0 ; i < 1000000 ; i++) {
			
			if(i % 100000 == 0)
				System.out.println(i);
			gTemp = AldousBroder.algorithme(graphModel);
			
			for(int j = 0 ; j < 8 ; j++) {
				if(g[j].graphIsEquals(gTemp)){
					nbGraphAldousBroder[j]++;
					break;
				}
			}
		}
		
		int somme = 0;
		for(int i = 0 ; i < 8 ; i++) {
			somme += nbGraphAldousBroder[i];
		}
		System.out.println("Nombre total de graphes classes: "+somme);
		for(int i = 0 ; i < 8 ; i++) {
			System.out.println("graphe num " + (i+1) +" : "+nbGraphAldousBroder[i]);
		}
	}
	
	
	public static void main(String... args) {
		AldousBroder.testQ5();
	}

}
