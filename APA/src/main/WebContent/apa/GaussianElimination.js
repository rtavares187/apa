/**
 * 
 */

var a = [
             [5,-1, 0,-2, 0],
             [-1, 4,-1, 0, 0],
             [0,-1, 6, 0,-3],
             [-2, 0, 0, 4, 0],
             [0, 0,-3, 0, 5]
           ];
	
var b = [2.5, 1, 0, 0, 1.5];

function initMatrix(){
	
	var matrixText = "[<br/>";
	
	for(var i = 0; i < a.length; i++){
		
		var line = "[";
		
		for(var j = 0; j < a[i].length; j++){
			
			if(j != 0){
				
				line += ", "; 
				
			}
				
			line += a[i][j];
			
		}
		
		line += "]";
		matrixText += line;
		
		if(i != (a.length - 1)){
			
			matrixText += ",<br/>";
			
		}
		
	}
	
	matrixText += "<br/>]";
		
	document.getElementById("mA").innerHTML = matrixText;
	
	matrixText = "[";
	
	for(var r = 0; r < b.length; r++){
		
		if(r != 0){
			
			matrixText += ", ";
			
		}
		
		matrixText += b[r];
		
	}
	
	matrixText += "]";
	
	document.getElementById("vB").innerHTML = matrixText;
	
}

function exGaussianElimination(){
	
	var start = Date.now();
	
	var x = gaussianElimination();
	
	var end = Date.now();
    
    var timeMs = end - start;
    var timeS = timeMs / 1000
    
    document.getElementById("time").innerHTML = timeMs + " (ms) / " + timeS + " (s).";
	
    var print = x[0] + "";
	for(var i = 1; i < x.length; i++)
		print += " ," + x[i];
	
	document.getElementById("result").innerHTML = "x = { " + print + " }.";
    
}

function gaussianElimination(){
	
	var qtdLinhas  = b.length;

    for (var p = 0; p < qtdLinhas; p++) {
    	
    	// Localiza maior valor da coluna
    	
        var max = p;
        
        for (var i = p + 1; i < qtdLinhas; i++) {
            
        	if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                max = i;
            }
        	
        }
        
        // Reordena a matriz
        
        var temp = a[p]; 
        a[p] = a[max]; 
        a[max] = temp;
        
        var t = b[p]; 
        b[p] = b[max]; 
        b[max] = t;

        for (var i = p + 1; i < qtdLinhas; i++) {
            
        	var fator = a[i][p] / a[p][p];
            
        	b[i] -= fator * b[p];
            
            for (var j = p; j < qtdLinhas; j++) {
                a[i][j] -= fator * a[p][j];
            }
            
        }
        
    }
    
    // Retrosubstituição
    
    var x = [];
    
    for (var i = qtdLinhas - 1; i >= 0; i--) {
        
    	var soma = 0.0;
        
        for (var j = i + 1; j < qtdLinhas; j++) {
        	soma += a[i][j] * x[j];
        }
        
        x[i] = (b[i] - soma) / a[i][i];
        
    }
    
    return x;
	
}