package br.cefetrj.eic.ppcic.apa.al;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rtavares
 */
public class GaussianEliminationX implements Method {
	
	public static void main(String[] args){
		
		double[][] a = new double[][]{
				{2.00, 14.00, 6.00, 10.00, 20.00, 36.00, 30.00, 26.00, 22.00, 14.00, 8.00, 34.00, 30.00, 4.00, 12.00},
				{3.00, 21.00, 9.00, 15.00, 30.00, 54.00, 45.00, 39.00, 33.00, 21.00, 12.00, 51.00, 45.00, 6.00, 18.00},
				{4.00, 28.00, 12.00, 20.00, 40.00, 72.00, 60.00, 52.00, 44.00, 28.00, 16.00, 68.00, 60.00, 8.00, 24.00},
				{5.00, 35.00, 15.00, 25.00, 50.00, 90.00, 75.00, 65.00, 55.00, 35.00, 20.00, 85.00, 75.00, 10.00, 30.00},
				{6.00, 42.00, 18.00, 30.00, 60.00, 108.00, 90.00, 78.00, 66.00, 42.00, 24.00, 102.00, 90.00, 12.00, 36.00},
				{7.00, 49.00, 21.00, 35.00, 70.00, 126.00, 105.00, 91.00, 77.00, 49.00, 28.00, 119.00, 105.00, 14.00, 42.00},
				{8.00, 56.00, 24.00, 40.00, 80.00, 144.00, 120.00, 104.00, 88.00, 56.00, 32.00, 136.00, 120.00, 16.00, 48.00},
				{9.00, 63.00, 27.00, 45.00, 90.00, 162.00, 135.00, 117.00, 99.00, 63.00, 36.00, 153.00, 135.00, 18.00, 54.00},
				{10.00, 70.00, 30.00, 50.00, 100.00, 180.00, 150.00, 130.00, 110.00, 70.00, 40.00, 170.00, 150.00, 20.00, 60.00},
				{11.00, 77.00, 33.00, 55.00, 110.00, 198.00, 165.00, 143.00, 121.00, 77.00, 44.00, 187.00, 165.00, 22.00, 66.00},
				{12.00, 84.00, 36.00, 60.00, 120.00, 216.00, 180.00, 156.00, 132.00, 84.00, 48.00, 204.00, 180.00, 24.00, 72.00},
				{13.00, 91.00, 39.00, 65.00, 130.00, 234.00, 195.00, 169.00, 143.00, 91.00, 52.00, 221.00, 195.00, 26.00, 78.00},
				{14.00, 98.00, 42.00, 70.00, 140.00, 252.00, 210.00, 182.00, 154.00, 98.00, 56.00, 238.00, 210.00, 28.00, 84.00},
				{15.00, 105.00, 45.00, 75.00, 150.00, 270.00, 225.00, 195.00, 165.00, 105.00, 60.00, 255.00, 225.00, 30.00, 90.00},
				{16.00, 112.00, 48.00, 80.00, 160.00, 288.00, 240.00, 208.00, 176.00, 112.00, 64.00, 272.00, 240.00, 32.00, 96.00}
		};
		
		double[] b = new double[]{268.00, 402.00, 536.00, 670.00, 804.00, 938.00, 1072.00, 1206.00, 1340.00, 1474.00, 1608.00, 1742.00, 1876.00, 2010.00, 2144.00};
		
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
                
            	BigDecimal fator = new BigDecimal(a[i][p]).setScale(2, BigDecimal.ROUND_HALF_EVEN).divide(new BigDecimal(a[p][p]));
                
            	b[i] -= fator.multiply(new BigDecimal(b[p]).setScale(2, BigDecimal.ROUND_HALF_EVEN)).doubleValue();
                
                for (int j = p; j < qtdLinhas; j++) {
                    a[i][j] -= fator.multiply(new BigDecimal(a[p][j]).setScale(2, BigDecimal.ROUND_HALF_EVEN)).doubleValue();
                }
                
            }
            
        }
        
        // Retrosubstituição
        
        double[] x = new double[qtdLinhas];
        
        for (int i = qtdLinhas - 1; i >= 0; i--) {
            
        	double soma = 0.0;
            
            for (int j = i + 1; j < qtdLinhas; j++) {
            	soma += new BigDecimal(a[i][j]).setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(new BigDecimal(x[j]).setScale(2, BigDecimal.ROUND_HALF_EVEN)).doubleValue();
            }
            
            x[i] = new BigDecimal((b[i] - soma)).setScale(2, BigDecimal.ROUND_HALF_EVEN).divide(new BigDecimal(a[i][i]).setScale(2, BigDecimal.ROUND_HALF_EVEN)).doubleValue();
            
        }
        
        return x;
		
	}

}
