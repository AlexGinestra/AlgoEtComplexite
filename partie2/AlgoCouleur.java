package partie2;

public class AlgoCouleur {

	public static int combinaison(int k, int n, int b, int m) {
		
		if(n == 0 || b == n) {
			
			return 0;
			
		}else if(n == (b+m)) {
			
			return 1+(k*(combinaison(n-1,n-1,b-1,m)+combinaison(n-1,n-1,b,m-1)));
			
		}else {
			
			return 1+((k-b-m)*(combinaison(k-b-m,n-1,b-1,m)+combinaison(k-b-m,n-1,b,m-1)));
			
		}
	}

	public static void main(String... args) {
		//System.out.println("resultat:"+AlgoCouleur.combinaison(6,4,1,2));
	}
	
}
