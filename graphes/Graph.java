package graphes;

import java.util.ArrayList;
import java.io.*;
import java.awt.*;
import java.awt.image.*;

class Graph{
	private ArrayList<Edge>[] adj;
	private int[] coordX;
	private int[] coordY;
	private final int V;
	private int E;

	@SuppressWarnings("unchecked")
	public Graph(int N){
		this.V = N;
		this.E = 0;
		adj = (ArrayList<Edge>[]) new ArrayList[N];
		for (int v= 0; v < N; v++)
		adj[v] = new ArrayList<Edge>();
		coordX = new int[N];
		coordY = new int[N];
		for (int v= 0; v < N; v++)
		coordX[v] = 0;
		for (int v= 0; v < N; v++)
		coordY[v] = 0;
	}
    
	/*
	 * return the number of vertice
	 */
	public int vertices(){
        return V;
   	}
   
	/*
	 * set the coordinate x and y of a vertice i
	 */
	public void setCoordinate(int i, int x, int y){
		coordX[i] = x;
		coordY[i] = y;
	}
    
    /*
     * add an edge e to the graph
     */
	public void addEdge(Edge e){
		int v = e.from;
		int w = e.to;
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}
   
	/*
	 * return a copy of the list of the edges connected to the vertice v
	 */
   public ArrayList<Edge> adj(int v){
	   return new ArrayList<Edge>(adj[v]);
   }

   /*
    * return all of the edges
    */
   public ArrayList<Edge> edges(){
	   ArrayList<Edge> list = new ArrayList<Edge>();
	   for (int v = 0; v < V; v++)
		for (Edge e : adj(v) ){
			if (e.from == v) {
				list.add(e);
			}
		}
		return list;
	}
   
   public int getEdgesNumber() {
	   return E;
   }

	static Graph example(){
		Graph g = new Graph(4);
		g.setCoordinate(0, 100,100);
		g.setCoordinate(1, 300,300);
		g.setCoordinate(2, 300,100);
		g.setCoordinate(3, 100,300);
		g.addEdge(new Edge(0,1));
		g.addEdge(new Edge(0,2));
		g.addEdge(new Edge(0,3));
		g.addEdge(new Edge(1,2));
		g.addEdge(new Edge(1,3));
		return g;
	}


	static Graph Grid(int n){
		Graph g = new Graph(n*n);
		int i,j;
		for (i = 0 ; i < n; i ++) {
		    for (j = 0 ; j < n; j ++) {
			g.setCoordinate(n*i+j, 50+(300*i)/n,50+(300*j)/n);
		    }
		}
		
		for (i = 0 ; i < n; i ++) {
		    for (j = 0 ; j < n; j ++){
				if (i < n-1) {
				    g.addEdge(new Edge(n*i+j,n*(i+1)+j));
				}
				if (j < n-1) {
				    g.addEdge(new Edge(n*i+j,n*i+j+1));
				}
		    }
		}
		return g;
	}
    

    public BufferedImage toImage(){
		BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = image.createGraphics();
		g2d.setBackground(Color.WHITE);
		g2d.fillRect(0, 0, 400, 400);
		g2d.setColor(Color.BLACK);
		BasicStroke bs = new BasicStroke(2);
		g2d.setStroke(bs);
		// dessine les arêtes 
		for (Edge e: edges()){
			int i = e.from;
			int j = e.to;
			if (e.used) {
			    g2d.setColor(Color.RED);
			}
			else {
			    g2d.setColor(Color.GRAY);
			}
			g2d.drawLine(coordX[i], coordY[i], coordX[j], coordY[j]);
		}
		// dessine les sommets 
		for (int i = 0; i < V; i++){
			g2d.setColor(Color.WHITE);
			g2d.fillOval(coordX[i]-15, coordY[i]-15,30,30);
			g2d.setColor(Color.BLACK);
			g2d.drawOval(coordX[i]-15, coordY[i]-15,30,30);
			g2d.drawString(Integer.toString(i), coordX[i], coordY[i]);
		}
		return image;
	}


	public void writeFile(String s){
		try {                      
			PrintWriter writer = new PrintWriter(s, "UTF-8");
			writer.println("digraph G{");
			for (Edge e: edges())
		    writer.println(e.from + "->" + e.to+";");
			writer.println("}");
			writer.close();
	    }
		catch (IOException e)
	    {
	    }                                             
    }    
	
	
	public static Graph initialisation(int n) {
		Graph g = new Graph(n*n);
		for (int i = 0 ; i < n; i ++) {
		    for (int j = 0 ; j < n; j ++) {
		    		g.setCoordinate(n*i+j, 50+(300*i)/n,50+(300*j)/n);
		    }
		}
		return g;
	}
	
	/*
	 * return true if the graph g is equals to the current one
	 */
	public boolean graphIsEquals(Graph g) {
		if(g.vertices() != V || g.getEdgesNumber() != E) {
			return false;
		}
		ArrayList<Edge> edgesTemp;
		int j, k;
		boolean isEquals = true;
		
		for(int i = 0 ; i < V ; i++) {
			edgesTemp = g.adj(i);
			if(edgesTemp.size() != adj[i].size()) {
				return false;
			}
			for(j = 0 ; j < adj[i].size() ; j++) {
				if(!isEquals) {
					return false;
				}
				k = 0;
				isEquals = false;
				while(!isEquals && k < adj[i].size()){
					if(edgesTemp.get(0).edgeIsEquals(adj[i].get(k))) {
						isEquals = true;
					}
					k++;
				}
				edgesTemp.remove(0);
			}
			
		}
		return isEquals;
	}
}
