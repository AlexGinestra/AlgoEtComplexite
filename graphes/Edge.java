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
    
    
    /*
     * return true if the edge is equals to the current one
     */
    public boolean edgeIsEquals(Edge e) {
    		if(e.to == this.to) {
    			if(e.from == this.from) {
    				return true;
    			}
    			return false;
    		}
    		else if(e.to == this.from){
    			if(e.from == this.to){
    				return true;
    			}
    			return false;
    		}
    		return false;
    }
}
