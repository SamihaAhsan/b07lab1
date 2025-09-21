public class Polynomial {
    
	double[] coef;

    public Polynomial() {
    	coef = new double[1];
    	coef[0]=0;
    }

    // Constructor with array argument
    public Polynomial(double[] coefs) { 	
    	coef = coefs;
    }

    
    public Polynomial add(Polynomial poly) {
    	double[] newone;
    	if (poly.coef.length < coef.length) {
    		newone= new double[coef.length];
    	    for (int i = 0; i < poly.coef.length; i++) {
    	    	newone[i]=poly.coef[i]+coef[i]; 	    	}	
    		for (int i=poly.coef.length; i<coef.length;i++) {
        		newone[i]=coef[i]; 
        	}
    	}	
    	else {
    		newone= new double[poly.coef.length];
    		for (int i = 0; i <coef.length; i++) {
    	    	newone[i]=poly.coef[i]+coef[i]; 
    	}
    		for (int i=coef.length; i<poly.coef.length;i++) {
        		newone[i]=poly.coef[i]; 
        	}
    	}
    	Polynomial newObj = new Polynomial(newone);
    	return newObj;
    }

    public double evaluate(double x) {
    	double value=0;
        for (int i=0; i< coef.length; i++) {
        	value+=coef[i]*(Math.pow(x, i));	
        }
        return value;
    }

    // Method to check if x is a root
    public boolean hasRoot(double x) {
    	
    	double answer=evaluate(x);
    	
    	return (answer==0.0);
        
    }

		
}