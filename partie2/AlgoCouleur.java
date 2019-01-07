package partie2;

public class AlgoCouleur {

	public static int combinaison(int k, int n, int b, int m) {
		int res = 0;
		
		if( b+m == n ) {
			if(n == 0 || b == n) {
				return 1;
			}
			if(b > 0 && m > 0) {
				res += 1*combinaison(k-1, n-1, b-1, m) + (n-1)*combinaison(k-1,n-1,b,m-1);
			}
			else if(b > 0) {
				res += 1*combinaison(k-1, n-1, b-1, m);
			}
			else if(m > 0) {
				res += n*combinaison(k-1,n-1,b,m-1);
			}
			
		}
		else {
			if(n == 0 || b == n) {
				return 1;
			}
			if(b > 0 && m > 0) {
				res += 1*combinaison(k-1, n-1, b-1, m) + (n-1)*combinaison(k-1,n-1,b,m-1);
			}
			else if(b > 0) {
				res += 1*combinaison(k-1, n-1, b-1, m);
			}
			else if(m > 0) {
				res += n*combinaison(k-1,n-1,b,m-1);
			}
			res += (k-n)*combinaison(k-1,n-1,b,m);
		}
		
		return res;
	}

	public static void main(String... args) {
		System.out.println("resultat:"+AlgoCouleur.combinaison(6,4,1,2));
	}
	
}
