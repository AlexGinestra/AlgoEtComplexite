package graphes;

class Edge
{
   int from;
   int to;
   boolean used;
   
    public Edge(int x, int y)
    {
		this.from = x;
		this.to = y;
		this.used = false;
    }
    
    /*
     * return the other vertice of the edge
     */
    final int other(int v)
    {
    		if (this.from == v) {
    			return this.to;
    		}
    		else {
    			return this.from;
    		}
    }    
}
