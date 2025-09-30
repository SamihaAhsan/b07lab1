import java.util.ArrayList;
import java.io.File;                  
import java.io.FileNotFoundException; 
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
public class Polynomial {
    
	double[] coef;
	int[] exp;

    public Polynomial() {
    	exp = new int[0];
    	coef =new double[0];
    }

    // Constructor with array argument
    public Polynomial(double[] coefs, int[] exps) { 	
    	coef = coefs;
    	exp = exps;
    }
    
    public Polynomial (File polyfile){
    	
    	try {
    	ArrayList<Double> coefs = new ArrayList<>();
    	ArrayList<Integer> exps = new ArrayList<>();
    	
    	Scanner polyread = new Scanner(polyfile);
    	String poly = polyread.nextLine();
    	polyread.close();
    	
    	String[] parts = poly.split("(?=[+-])");
    	
    	for (String word : parts) {
    		String[] word_parts = word.split("x");
    		if (word_parts.length==1) {
    			if(!(word_parts[0].equals(""))){
    			exps.add(0);
    			double num = Double.parseDouble(word_parts[0]);
    			coefs.add(num);}
    			else {
    				exps.add(1);
        			coefs.add(1);
    			}
    		
    		}
    		else if(word_parts[0].equals("") || word_parts[0].equals("-")|| word_parts[0].equals("+") ) {
    			if (word_parts[0].equals("") ||word_parts[0].equals("+")) {
    				coefs.add(1);	
    			}
    			else {
    				coefs.add(-1);
    				
    			}

    			int ex = Integer.parseInt(word_parts[1]);
    			exps.add(ex);
    		}
    		
    		else if(word_parts.length==2 && word_parts[1].equals("")) {
    			exps.add(1);
    			double num = Double.parseDouble(word_parts[0]);
    			coefs.add(num);}
    		
    		else {
    			double num = Double.parseDouble(word_parts[0]);
    			int ex = Integer.parseInt(word_parts[1]);
    			coefs.add(num);
    			exps.add(ex);
    			
    		}
    		
    		
    	}
    	
    	coef = new double[coefs.size()];
    	exp = new int[exps.size()];
    	for (int i = 0; i < coefs.size(); i++) {
    	    coef[i] = coefs.get(i); 
    	    exp[i] = exps.get(i);
    	}
    	}
    	
    		
    	
    	catch (Exception error) {
 	       
	    }
    	
    }
    	
	
    	


    
    public Polynomial add(Polynomial poly) {
    	ArrayList<Double> coefs = new ArrayList<>();
    	ArrayList<Integer> exps = new ArrayList<>();
    	
    	for (int i=0; i<poly.coef.length; i++) {
    		coefs.add(poly.coef[i]);
    		exps.add(poly.exp[i]);
    	}
    	
    	for (int j=0; j<coef.length;j++) {
    		coefs.add(coef[j]);
    		exps.add(exp[j]);
    	}
    	
    	ArrayList<Double> newcoefs = new ArrayList<>();
    	ArrayList<Integer> newexps = new ArrayList<>();
    	
    	for (int i=0; i<exps.size();i++) {
    		if (exps.get(i)==-1) {
    			continue;
    		}
    		double coefsum=coefs.get(i);
    		
    		for (int j=i+1; j<exps.size();j++) {
    			if (exps.get(i)==exps.get(j)) {
    				coefsum+=coefs.get(j);
    				exps.set(j, -1);
    				break;
    			}
    		}
    		if (coefsum!=0) {
    			newexps.add(exps.get(i));
				newcoefs.add(coefsum);}
    		
    	}
    	
    	double[] newcoefa = new double[newcoefs.size()];
    	int [] newexpa = new int[newexps.size()];
    	for (int i = 0; i < newcoefs.size(); i++) {
    	    newcoefa[i] = newcoefs.get(i); 
    	    newexpa[i] = newexps.get(i);
    	}
    	
    	return new Polynomial(newcoefa, newexpa);
    }

    public double evaluate(double x) {
    	double value=0;
        for (int i=0; i< coef.length; i++) {
        	value+=coef[i]*(Math.pow(x, exp[i]));	
        }
        return value;
    }

    // Method to check if x is a root
    public boolean hasRoot(double x) {
    	
    	double answer=evaluate(x);
    	
    	return (answer<0.0001 && answer>-0.0001);
        
    }
    
   public Polynomial multiply(Polynomial poly) {
	   ArrayList<Double> coefs = new ArrayList<>();
   	ArrayList<Integer> exps = new ArrayList<>();
   	
   	for (int i=0; i<poly.coef.length; i++) {
   		for (int j=0; j<coef.length;j++) {
   			coefs.add(poly.coef[i]*coef[j]);
   			exps.add(poly.exp[i]+exp[j]);
   		}
		
	}
	
   	ArrayList<Double> newcoefs = new ArrayList<>();
	ArrayList<Integer> newexps = new ArrayList<>();
	
	for (int i=0; i<exps.size();i++) {
		if (exps.get(i)==-1) {
			continue;
		}
		double coefsum=coefs.get(i);
		
		for (int j=i+1; j<exps.size();j++) {
			if (exps.get(i)==exps.get(j)) {
				coefsum+=coefs.get(j);
				exps.set(j, -1);
			}
		}
			newexps.add(exps.get(i));
			newcoefs.add(coefsum);
		
	}
	
	double[] newcoefa = new double[newcoefs.size()];
	int [] newexpa = new int[newexps.size()];
	for (int i = 0; i < newcoefs.size(); i++) {
	    newcoefa[i] = newcoefs.get(i); 
	    newexpa[i] = newexps.get(i);
	}
	
	return new Polynomial(newcoefa, newexpa);
 	  	
   }
   
   public void saveToFile(String file) {
	   
	   try {
		   
	        FileWriter put = new FileWriter(file);

	        for (int i = 0; i < coef.length; i++) {
	           
	        	if (i==0) {
	        		put.write(coef[i]+"x"+exp[i]);
	        		
	        	}
	        	
	        	else if (coef[i]>0) {
	        		put.write("+"+coef[i]+"x"+exp[i]);
	        		
	        	}
	        	else {put.write(coef[i]+"x"+exp[i]);}

	        }
	        put.close();}
	        
	    catch (IOException error) {
	       
	    }
	}
	   
	   
   }

   
		
