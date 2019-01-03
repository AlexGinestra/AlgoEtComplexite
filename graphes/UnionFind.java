package graphes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UnionFind {

	private ArrayList<Set<Integer>> setCollection;
	
	public UnionFind(int nbElements) {
		setCollection = new ArrayList<Set<Integer>>();
		for(int i = 0 ; i < nbElements ; i++) {
			setCollection.add(new HashSet<Integer>());
			setCollection.get(i).add(i);
		}
	}
	
	/*
	 * return false if there is no x or y in the Union
	 * else
	 * put all the elements of the y's set , in the x's set
	 * and return true
	 */
	public boolean unifie(int x, int y) {
		int xPos = -1;
		int yPos = -1;
		for(int i = 0 ; i < setCollection.size() ; i++) {
			if(setCollection.get(i).contains(x)) {
				xPos = i;
			}
			if(setCollection.get(i).contains(y)) {
				yPos = i;
			}
		}
		if(xPos != -1 && yPos != -1 && xPos != yPos) {
			Iterator<Integer> it = setCollection.get(yPos).iterator();
			while(it.hasNext()) {
				setCollection.get(xPos).add(it.next());
			}
			setCollection.remove(yPos);
			return true;
		}
		return false;
	}
	
	/*
	 * return the number of set in the union-find
	 */
	public int getNumberOfSets() {
		return setCollection.size();
	}
}
