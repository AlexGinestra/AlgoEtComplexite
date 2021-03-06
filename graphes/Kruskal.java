package graphes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kruskal {
	
	
	/*
	 * Q2 : implantation de l'algorithme de Kruskal
	 *  param: graph G
	 *  return: covering tree of G if it exist
	 *  			or a graph without cycle else
	 */
	public static Graph algorithmeKruskal(Graph g) {
		Graph gRes = Graph.initialisation((int) Math.sqrt(g.vertices()));
		ArrayList<Edge> edgesTemp = g.edges();
		Collections.shuffle(edgesTemp);
		UnionFind unionFind = new UnionFind(g.vertices());
		
		while(edgesTemp.size() > 0) {
			// test if the graph has a cycle if we had the edge 
			if(!unionFind.isInSameSet(edgesTemp.get(0).from, edgesTemp.get(0).to)) {
				unionFind.unifie(edgesTemp.get(0).from, edgesTemp.get(0).to);
				gRes.addEdge(edgesTemp.get(0));
				edgesTemp.remove(0);
				if(unionFind.getNumberOfSets() == 1) {
					return gRes;
				}
			}
			else {
				edgesTemp.remove(0);
			}
		}
		return gRes;		
	}
	
	
	
	

	/*
	 * test requis pour la question 3
	 */
	public static void testQ3() {
		
		Graph graphModel = Graph.Grid(2);
		graphModel.addEdge(new Edge(0,3));
		
		
		Graph gTemp;
		Graph[] g = new Graph[8];
		int[] nbGraphKruskal = new int[8];
		
		for(int i = 0 ; i < 8 ; i++) {
			g[i] = new Graph(4);
			nbGraphKruskal[i] = 0;
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

		// do 1 000 000 times kruskal on the example in the project subject
		for(int i = 0 ; i < 1000000 ; i++) {
			
			if(i % 100000 == 0)
				System.out.println(i);
			gTemp = Kruskal.algorithmeKruskal(graphModel);
			for(int j = 0 ; j < 8 ; j++) {
				if(g[j].graphIsEquals(gTemp)){
					nbGraphKruskal[j]++;
					break;
				}
			}
		}
		
		int somme = 0;
		for(int i = 0 ; i < 8 ; i++) {
			somme += nbGraphKruskal[i];
		}
		System.out.println("Nombre total de graphes classes: "+somme);
		for(int i = 0 ; i < 8 ; i++) {
			System.out.println("graphe num " + (i+1) +" : "+nbGraphKruskal[i]);
		}
	}
	
	
	public static void main(String... args) {
		Kruskal.testQ3();
	}
	
}
