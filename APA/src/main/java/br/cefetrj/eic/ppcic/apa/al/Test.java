package br.cefetrj.eic.ppcic.apa.al;

import java.text.DecimalFormat;

public class Test {

	public static void main(String[] args) {
		
		double[] x = {1, 7, 3, 5, 10, 18, 15, 13, 11, 7, 4, 17, 15, 2, 6};
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		System.out.println("");
		System.out.println("X:");
		System.out.println("");
		
		String linha = "{";
		
		for(int i = 0; i < x.length; i++){
			
			if(i != 0)
				linha += "; "; 
			
			linha += df.format(x[i]);
			
		}
		
		System.out.println(linha + "}");
		System.out.println("");
		
		int l = x.length;
		
		double[][] a = new double[l][l];
		double[] b = new double[l];
		
		double c = 2;
		
		for(int i = 0; i < l; i++){
			
			for(int j = 0; j < l; j++){
				
				a[i][j] = x[j] * c;
				
			}
			
			double sum = 0.00;
			
			for(int j = 0; j < l; j++)
				sum += a[i][j];
			
			b[i] = sum;
			
			c++;
			
		}
		
		System.out.println("A:");
		System.out.println("");
		
		for(int i = 0; i < a.length; i++){
			
			linha = "{";
			
			for(int j = 0; j < a[i].length; j++){
				
				if(j != 0)
					linha += "; ";
					
				
				linha += df.format(a[i][j]);
				
			}
			
			System.out.println(linha + "}");
		
		}
		
		System.out.println("");
		System.out.println("B:");
		System.out.println("");
		
		linha = "{";
		
		for(int i = 0; i < b.length; i++){
			
			if(i != 0)
				linha += "; "; 
			
			linha += df.format(b[i]);
			
		}
		
		System.out.println(linha + "}");

	}

}
