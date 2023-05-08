package fr.langlois.utility;

import java.util.function.Function;

class Matrice{
	public int n=0;
	public int p=0;
	public Double[][] matrice;
	Matrice(int n, int p){
		if (n >=0 && p>=0) {
			this.n=n;
			this.p=p;
			this.matrice=new Double[n][p];
			for (int i=0; i <n; i++) {
				for (int j=0; j<p;j++) {
					this.matrice[i][j]=0.;
				}
			}
		}else {
			System.err.println("Dimension du tableau fausse");
			System.exit(1);
		}
	}
	Matrice(int n, int p,Double v){
		if (n >=0 && p>=0) {
			this.n=n;
			this.p=p;
			this.matrice=new Double[n][p];
			for (int i=0; i <n; i++) {
				for (int j=0; j<p;j++) {
					this.matrice[i][j]=v;
				}
			}
		}else {
			System.err.println("Dimension du tableau fausse");
			System.exit(1);
		}
	}
	public static Matrice somme(Matrice m,Matrice n) {
		Matrice s=new Matrice(m.n,m.p,0.);
		if (m.n==n.n && m.p==n.p) {
			for (int i=0; i<n.n;i++) {
				for (int j=0; j<n.p;j++) {
					s.matrice[i][j]=m.matrice[i][j]+n.matrice[i][j];
				}
			}
		}else {
			System.err.println("Dimension pour la somme pas compatible");
			System.exit(1);
		}
		return s;
	}
	public static Matrice somme(Double q,Matrice m) {
		Matrice s=new Matrice(m.n,m.p);
			for (int i=0; i<m.n;i++) {
				for (int j=0; j<m.p;j++) {
					s.matrice[i][j]=m.matrice[i][j]+q;
				}
			}
		return s;
	}
	
	public static Matrice produit(Matrice m0,Matrice m1)
	{
		
		if (m0.p==m1.n) {
			Matrice prod=new Matrice(m0.n,m1.p);
			for (int i=0; i<m0.n;i++) {
				for (int j=0; j< m1.p;j++) {
					for (int k = 0; k < m0.p; k++) {
			               prod.matrice[i][j] += m0.matrice[i][k] * m1.matrice[k][j];
			        }
				}
			}
			return prod;
		}else {
			System.err.println("Dimension pour le produit pas compatible");
			System.exit(1);
			return null;
		}
	}
	public static Matrice mult(Matrice m1,Matrice m2) {
		Matrice mult=new Matrice(m1.n,m1.p);
		if (m1.n==m2.n && m1.p==m2.p) {
			for(int i=0; i<m1.n;i++) {
				for (int j=0; j<m1.p;j++) {
					mult.matrice[i][j]=m1.matrice[i][j]*m2.matrice[i][j];
				}
			}
		}else {
			System.err.println("Dimension pas compatible pour la multiplication terme à terme");
			System.exit(0);
		}
		return mult;
	}
	public static Matrice eval(Function<Double,Double> f,Matrice m) {
		Matrice e=new Matrice(m.n,m.p);
		for (int i=0; i<m.n;i++) {
			for (int j=0; j<m.p;j++) {
				e.matrice[i][j]=f.apply(m.matrice[i][j]);
			}
		}
		return e;
	}
	public static Matrice produit(Double q,Matrice m) {
		Matrice prod=Matrice.eval(x->q*x,m);
		return prod;
	}
	public static Matrice t(Matrice m) {
		Matrice tm=new Matrice(m.p,m.n);
		for (int i=0; i < m.n;i++) {
			for (int j=0; j< m.p;j++) {
				tm.matrice[j][i]=m.matrice[i][j];
			}
		}
		return tm;
	}
	public void show() {
		
		for (int i=0; i<this.n;i++) {
			String str="[";
			for (int j=0; j<this.p;j++) {
				str+=this.matrice[i][j] + ", ";
			}
			System.out.println(str + "]");
		}
		System.out.println("--------------");
	}
	public void shape() {
		System.out.println(this.n+"x"+this.p);
	}
	public static boolean shapeTest(Matrice m1,Matrice m2) {
		return m1.n==m2.n && m1.p==m2.p;
	}
	public static int countValue(Matrice m,Double value,Double epsilon) {
		int count=0;
		for (int i=0; i <m.n;i++) {
			for(int j=0; j<m.p;j++) {
				if (m.matrice[i][j]>=value-epsilon && m.matrice[i][j]<=value+epsilon) {
					count++;
				}
			}
		}
		return count;
	}
	
}

class Identity extends Matrice{
	Identity(int n,int p){
		super(n,p);
		for (int i=0; i<n;i++) {
			for (int j=0; j<p;j++) {
				if (i==j) {
					super.matrice[i][j]=1.;
				}else {
					super.matrice[i][j]=0.;
				}
			}
		}
	}
}
