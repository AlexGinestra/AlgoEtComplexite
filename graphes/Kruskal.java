package graphes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Kruskal {
	
	
	/*
	 * Q2 : implantation de l'algorithme de Kruskal
	 *  param: graph G
	 *  return: covering tree of G
	 */
	public static Graph algorithmeKruskal(Graph g) {
		Graph gRes = Graph.initialisation((int) Math.sqrt(g.vertices()));
		ArrayList<Edge> edgesTemp = g.edges();
		Collections.shuffle(edgesTemp);
		while(edgesTemp.size() > 0) {
			// test if the graph has a cycle if we had the edge 
			if(!UsefulAlgorithme.cycleDetection(gRes, edgesTemp.get(0))) {
				gRes.addEdge(edgesTemp.get(0));
				edgesTemp.remove(0);
				if(UsefulAlgorithme.isACoveringTree(gRes)) {
					return gRes;
				}
			}
			else {
				edgesTemp.remove(0);
			}
		}
		return new Graph(0);		
	}

	/*
	 * test requis pour la question 3
	 */
	public static void testQ3() {
		
		Graph graphModel = Graph.Grid(4);
		graphModel.addEdge(new Edge(0,1));
		graphModel.addEdge(new Edge(0,2));
		graphModel.addEdge(new Edge(0,3));
		graphModel.addEdge(new Edge(1,3));
		graphModel.addEdge(new Edge(2,3));
		
		Graph gTemp;
		Graph[] g = new Graph[8];
		int[] nbGraphKruskal = new int[8];
		
		for(int i = 0 ; i < 8 ; i++) {
			g[i] = Graph.Grid(4);
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
			if(g[0].graphIsEquals(gTemp)){
				nbGraphKruskal[0]++;
			}
			else if(g[1].graphIsEquals(gTemp)){
				nbGraphKruskal[1]++;
			}
			else if(g[2].graphIsEquals(gTemp)){
				nbGraphKruskal[2]++;
			}
			else if(g[3].graphIsEquals(gTemp)){
				nbGraphKruskal[3]++;
			}
			else if(g[4].graphIsEquals(gTemp)){
				nbGraphKruskal[4]++;
			}
			else if(g[5].graphIsEquals(gTemp)){
				nbGraphKruskal[5]++;
			}
			else if(g[6].graphIsEquals(gTemp)){
				nbGraphKruskal[6]++;
			}
			else if(g[7].graphIsEquals(gTemp)){
				nbGraphKruskal[7]++;
			}
		}
		
		int somme = 0;
		for(int i = 0 ; i < 8 ; i++) {
			somme += nbGraphKruskal[i];
		}
		System.out.println(somme);
		for(int i = 0 ; i < 8 ; i++) {
			System.out.println("graphe num " + (i+1) +" : "+nbGraphKruskal[i]);
		}
	}
	
	
	public static void main(String... args) {
		Kruskal.testQ3();
	}
	
}
