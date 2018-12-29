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
	public static Graph algorithme(Graph g) {
		Graph gRes = new Graph(g.vertices());
		ArrayList<Edge> edgesTemp = g.edges();
		Collections.shuffle(edgesTemp);
		while(edgesTemp.size() > 0) {
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

	
	
	public static void main(String... args) {
		Graph g = new Graph(5);
		g.addEdge(new Edge(0, 1));
		g.addEdge(new Edge(1, 2));
		g.addEdge(new Edge(3, 2));
		g.addEdge(new Edge(3, 4));
		g.addEdge(new Edge(3, 0));
		g.addEdge(new Edge(0, 4));
		
		Graph graph = Kruskal.algorithme(g);
		graph.writeFile("resultat");
		
	}
}
