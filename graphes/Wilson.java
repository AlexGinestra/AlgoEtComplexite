package graphes;

import java.util.ArrayList;

public class Wilson {

	
	public static Graph algorithmeWilson(Graph g) {
		Graph gRes = Graph.initialisation((int) Math.sqrt(g.vertices()));
		UnionFind unionFind = new UnionFind(g.vertices());
		
		Edge randomEdge;
		int vertice = 0;
		while(!UsefulAlgorithme.isACoveringTree(gRes)) {
			
			/* test if the vertice is already discovered */
			while(unionFind.isInSameSet(0, vertice)) {
				vertice = (int) (Math.random()*g.vertices());
			}
			ArrayList<Integer> verticesList = new ArrayList<Integer>();
			randomEdge = g.adj(vertice).get((int)(Math.random()*g.adj(vertice).size()));
			
			/* walk randomly until it joins a discovered vertice */
			while(!unionFind.isInSameSet(0, vertice)) {
				verticesList.add(vertice);
				vertice = randomEdge.other(vertice);
				randomEdge = g.adj(vertice).get((int)(Math.random()*g.adj(vertice).size()));
			}
			verticesList.add(vertice);
			
			/* delete the duplicates vertice */
			for(int i = 0 ; i < verticesList.size() ; i++) {
				int lastIndex = verticesList.lastIndexOf(verticesList.get(i));
				if(lastIndex != -1 && lastIndex != i) {
					for(int j = i ; j < lastIndex ; j++) {
						verticesList.remove(i);
					}
				}
			}
			
			/* add to the graph result the edges */
			for(int i = 1 ; i < verticesList.size() ; i++) {
				gRes.addEdge(new Edge(verticesList.get(i-1), verticesList.get(i)));
				unionFind.unifie(0, verticesList.get(i-1));
			}
			
		}
		return gRes;
	}
	
	
	
	/*
	 * test requis pour la question 6
	 */
	public static void testQ6() {
		
		Graph graphModel = Graph.Grid(2);
		graphModel.addEdge(new Edge(0,3));
		
		
		Graph gTemp;
		Graph[] g = new Graph[8];
		int[] nbGraphWilson = new int[8];
		
		for(int i = 0 ; i < 8 ; i++) {
			g[i] = new Graph(4);
			nbGraphWilson[i] = 0;
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
			gTemp = Wilson.algorithmeWilson(graphModel);
			
			for(int j = 0 ; j < 8 ; j++) {
				if(g[j].graphIsEquals(gTemp)){
					nbGraphWilson[j]++;
					break;
				}
			}
		}
		
		int somme = 0;
		for(int i = 0 ; i < 8 ; i++) {
			somme += nbGraphWilson[i];
		}
		System.out.println("Nombre total de graphes classes: "+somme);
		for(int i = 0 ; i < 8 ; i++) {
			System.out.println("graphe num " + (i+1) +" : "+nbGraphWilson[i]);
		}
	}
	
	
	public static void main(String... args) {
		Wilson.testQ6();
		
		/*ArrayList<Integer> ar = new ArrayList<Integer>();
		ar.add(0);
		ar.add(1);
		ar.add(2);
		ar.add(3);
		ar.add(4);
		System.out.println(ar.lastIndexOf(3));
		System.out.println(ar.get(1));
		System.out.println(ar.subList(1, 3).toString());
		ar.removeAll(ar.subList(1, 3));
		System.out.println(ar.toString());
		 */
	}
	
	
}
