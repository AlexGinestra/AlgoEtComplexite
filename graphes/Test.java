package graphes;
import java.io.*;
import java.util.*;

import junit.framework.TestCase;

public class Test extends TestCase{


    public static void printLaby(Graph G, int size, String file){
    {
	/* suppose que G est une grille de taille size x size et 
           crée un .tex qui contient le labyrinthe correspondant */
	
	try
	    {                      
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		writer.println("\\documentclass{article}\\usepackage{tikz}\\begin{document}");
		writer.println("\\begin{tikzpicture}");

		for (int i = 0; i < size; i++)
		    for (int j = 0; j < size; j++)
		    {			
			writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , j));
			writer.println("\\draw (0.1,0.1) -- (0.4,0.1);");
			writer.println("\\draw (0.6,0.1) -- (0.9,0.1);");
			writer.println("\\draw (0.1,0.9) -- (0.4,0.9);");
			writer.println("\\draw (0.6,0.9) -- (0.9,0.9);");
			writer.println("\\draw (0.1,0.1) -- (0.1, 0.4);");
			writer.println("\\draw (0.1,0.6) -- (0.1, 0.9);");
			writer.println("\\draw (0.9,0.1) -- (0.9,0.4);");
			writer.println("\\draw (0.9,0.6) -- (0.9,0.9);");
			writer.println("\\end{scope}");
		    }

		/* bord */
		for (int i = 0; i < size; i++)
		    {
			writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , 0));
			writer.println("\\draw(0.4,0.1) -- (0.6, 0.1);");
			writer.println("\\end{scope}");			
			writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , size-1));
			writer.println("\\draw(0.4,0.9) -- (0.6, 0.9);");
			writer.println("\\end{scope}");
			if (i > 0)
			    {
				writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", 0 , i));
				writer.println("\\draw(0.1,0.4) -- (0.1, 0.6);");
				writer.println("\\end{scope}");
			
			    }
			if (i < size - 1)
			    {
				writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", size -1 , i));
				writer.println("\\draw(0.9,0.4) -- (0.9, 0.6);");
				writer.println("\\end{scope}");
			
			    }
			writer.println("\\draw (0,0.4) -- (0.1, 0.4);");
			writer.println("\\draw (0,0.6) -- (0.1, 0.6);");
			writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.4)  -- ++ (-0.1, 0); ", size , size -1));
			writer.println(String.format(Locale.US, "\\draw (%d, %d) ++ (0, 0.6)  -- ++ (-0.1, 0); ", size , size -1));

		    }
		
		
		for (Edge e: G.edges())
		    {
			int i = e.from % size;
			int j = e.from / size;
			writer.println(String.format(Locale.US, "\\begin{scope}[xshift=%dcm, yshift=%dcm]", i , j));
			if (e.to == e.from + size){
			    /* arête verticale */
			    if (!e.used)
				{
				    writer.println("\\draw (0.4,0.9) -- (0.6,0.9);");
				    writer.println("\\draw (0.4,1.1) -- (0.6,1.1);");			    			    
				}
			    else
				{
				    writer.println("\\draw (0.4,0.9) -- (0.4,1.1);");
				    writer.println("\\draw (0.6,0.9) -- (0.6,1.1);");			    			    
				}
			}
			else{
			    /* arête horizontale */
			    
			    if (!e.used)
				{
				    writer.println("\\draw (0.9,0.4) -- (0.9,0.6);");
				    writer.println("\\draw (1.1,0.4) -- (1.1,0.6);");			    			    
				}
			    else
				{
				    writer.println("\\draw (0.9,0.4) -- (1.1,0.4);");
				    writer.println("\\draw (0.9,0.6) -- (1.1,0.6);");			    			    
				}
			    }
			    writer.println("\\end{scope}");
		    }
		writer.println("\\end{tikzpicture}");
		writer.println("\\end{document}");
		writer.close();
	    }
	catch (IOException e)
	    {
	    }                                             
    }    
	
    }	
    
    /*
     * fonction de test sur AldousBroder
     */
    private static void testAldousBroder() {
	    	Graph G = Graph.Grid(4);
    		G = AldousBroder.algorithme(G);
	    	Display d = new Display();
	    	d.setImage(G.toImage());
	    	System.out.println("appuyez sur une touche");
	    	new Scanner(System.in).nextLine();
	    	d.close();
    }
    
    
    /*
     * fonction de test sur Kruskal
     */
    private static void testKruskal() {
    		Display d = new Display();
     	Graph G = Graph.Grid(2);
     	d.setImage(G.toImage());

		G = Kruskal.algorithmeKruskal(G);
	    	
	    d.setImage(G.toImage());
    }
    
    
    /*
     * fonction de test sur Wilson
     */
    private static void testWilson() {
    		Display d = new Display();
     	Graph G = Graph.Grid(2);
     	//d.setImage(G.toImage());


		G = Wilson.algorithmeWilson(G);
	    	
	    d.setImage(G.toImage());
    }
    
    
    
    
    /*
     * fonction de test sur l'egalite de deux graphes
     */
    private static void testEgalite() {
		Graph g2 = new Graph(4);
    		Graph g = new Graph(4);
    		assertTrue(g.graphIsEquals(g2));

    		g.addEdge(new Edge(0, 1));
    		g.addEdge(new Edge(1, 2));
    		g.addEdge(new Edge(3, 2));
    		assertFalse(g.graphIsEquals(g2));
    		
    		g2.addEdge(new Edge(3, 2));
    		g2.addEdge(new Edge(0, 1));
    		g2.addEdge(new Edge(1, 2));
    		assertTrue(g.graphIsEquals(g2));

    }
    
    
    public static void main(String[] args) {
	/*int size = 4;
	Graph G = Graph.Grid(size);
	Display d = new Display();
	d.setImage(G.toImage());
	System.out.println("appuyez sur une touche");
	
	
	
	new Scanner(System.in).nextLine();
	d.close();
	printLaby(G,size, "toto.tex");*/
    	
    	//testAldousBroder();
    	
    	//testKruskal();
    	
    //	testWilson();
	
    //testEgalite();
    	
    	LabyrintheAlex.testQ7();
	
    }
} 
