package br.cefetrj.eic.ppcic.apa.al;

import java.util.Date;

/**
 * @author rtavares
 */
public class GaussianElimination implements Method {
	
	public static void main(String[] args){
		
		double[][] a = new double[][]{
			{ 5,-1, 0,-2, 0},
			{-1, 4,-1, 0, 0},
			{ 0,-1, 6, 0,-3},
			{-2, 0, 0, 4, 0},
			{ 0, 0,-3, 0, 5}
		};
		
		double[] b = new double[]{2.5, 1, 0, 0, 1.5};
		
		Date start = new Date();
		
		double[] x = new GaussianElimination().solve(a, b);
		
		Date end = new Date();
		
		long time = end.getTime() - start.getTime();
		long timeS = time / 1000;
		
		System.out.println("Time / tempo: " + time + " (ms) / " + timeS + " (s).");
		
		String print = x[0] + "";
		for(int i = 1; i < x.length; i++)
			print += " ," + x[i];
		
		System.out.println("x = " + print);
		
	}
	
	@Override
	public double[] solve(double[][] a, double[] b) {
		
		int qtdLinhas  = b.length;

        for (int p = 0; p < qtdLinhas; p++) {
        	
        	// Localiza maior valor da coluna
        	
            int max = p;
            
            for (int i = p + 1; i < qtdLinhas; i++) {
                
            	if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                    max = i;
                }
            	
            }
            
            // Reordena a matriz
            
            double[] temp = a[p]; 
            a[p] = a[max]; 
            a[max] = temp;
            
            double t = b[p]; 
            b[p] = b[max]; 
            b[max] = t;

            for (int i = p + 1; i < qtdLinhas; i++) {
                
            	double fator = a[i][p] / a[p][p];
                
            	b[i] -= fator * b[p];
                
                for (int j = p; j < qtdLinhas; j++) {
                    a[i][j] -= fator * a[p][j];
                }
                
            }
            
        }
        
        // Retrosubstituição
        
        double[] x = new double[qtdLinhas];
        
        for (int i = qtdLinhas - 1; i >= 0; i--) {
            
        	double soma = 0.0;
            
            for (int j = i + 1; j < qtdLinhas; j++) {
            	soma += a[i][j] * x[j];
            }
            
            x[i] = (b[i] - soma) / a[i][i];
            
        }
        
        return x;
		
	}

}