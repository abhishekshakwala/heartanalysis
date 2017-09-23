package com.classify;

import weka.classifiers.trees.*;
import weka.classifiers.meta.Bagging;
import weka.core.*;
import weka.core.converters.ArffSaver;
import weka.experiment.InstanceQuery;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;

public class LoadAndSave {
	
	static Instances dataset, train, test;
	static ArffSaver saver;
	static int trainSize, testSize;
	static int seed = 1, folds = 20;
	static Instance pred;
	
	static J48 decisiontree;
	static RandomForest random;
	static Bagging bag;
	
	
	public static void divide() {
		trainSize = Math.round(dataset.numInstances() * 90/ 100);
		testSize = dataset.numInstances() - trainSize;
		train = new Instances(dataset, 0, trainSize);
		test = new Instances(dataset, trainSize, testSize);
	}
	
	public static void bag() throws Exception {
		bag = new Bagging();
		bag.buildClassifier(dataset);
		
	}
	
	public static void J() throws Exception {
		decisiontree = new J48();
		decisiontree.buildClassifier(dataset);			     
	}
	
	public static void Rando() throws Exception {	
		random = new RandomForest();
		random.buildClassifier(dataset);
	}
	
	public static double classifyrandom(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k) throws Exception {
		double[] values = new double[]{a,b,c,d,e,f,g,h,i,j,k};
	    DenseInstance newInst = new DenseInstance(1.0,values);
	    
	    Instances dataUnlabeled = new Instances(dataset);
	    dataUnlabeled.add(newInst);
	    dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);
	    double classif = random.classifyInstance(dataUnlabeled.lastInstance());
	    
	    return classif;
	}
	
	
	public static double classifybag(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k) throws Exception {
		double[] values = new double[]{a,b,c,d,e,f,g,h,i,j,k};
	    DenseInstance newInst = new DenseInstance(1.0,values);
	    
	    Instances dataUnlabeled = new Instances(dataset);
	    dataUnlabeled.add(newInst);
	    dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);
	    double classif = decisiontree.classifyInstance(dataUnlabeled.lastInstance());
	    return classif;
	}
	
	
	public static double classifyj(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k) throws Exception {
		double[] values = new double[]{a,b,c,d,e,f,g,h,i,j,k};
	    DenseInstance newInst = new DenseInstance(1.0,values);
	    
	    Instances dataUnlabeled = new Instances(dataset);
	    dataUnlabeled.add(newInst);
	    dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);
	    double classif = decisiontree.classifyInstance(dataUnlabeled.lastInstance());
	    
	    return classif;
	}

	public static int classifyall(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k) throws Exception {
				
		dataset = new Instances(new BufferedReader(new FileReader("C://Users//avs23//Desktop//Neon//HeartAnalysis//HeartArff.arff")));
		dataset.setClassIndex(dataset.numAttributes() - 1);
				
		J();
		Rando();
		bag();
		
		double count1 = classifyrandom(a,b,c,d,e,f,g,h,i,j,k);
		double count2 = classifybag(a,b,c,d,e,f,g,h,i,j,k);
		double count3 = classifyj(a,b,c,d,e,f,g,h,i,j,k);
		
		if ( ((count1 + count2 + count3 )/ 3) >= 0.5) {
			return 1;
		}
		else {
			return 0;
		}		
	}
	
	
	
	public static int testheart(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k, double l, double m, double n, double o, Instances dataset1) throws Exception {
		double[] values = new double[]{l,m,n,o,a,b,c,d,e,f,g,h,i,j,k};
	    DenseInstance newInst = new DenseInstance(1.0,values);
	    
	    Instances dataUnlabeled = new Instances(dataset1);
	    dataUnlabeled.add(newInst);
	    dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);

	    
	    double classif1 = bag.classifyInstance(dataUnlabeled.lastInstance());
	    double classif2 = decisiontree.classifyInstance(dataUnlabeled.lastInstance());
	    double classif3 = random.classifyInstance(dataUnlabeled.lastInstance());
	    
	    int meanpred = (int)( ( classif1 + classif2 + classif3 ) / 3 );
	    if ( classif1 == 1  || classif2 == 1 || classif3 == 1) {
			meanpred =  1;
		}
		else {
			meanpred =  0;
		}
	    
	    return meanpred;
	}
	
	
	public static String preventheartdisease(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k, double l, double m, double n, double o, double p, double q, double z) throws Exception{
		
		Instances dataset1 = new Instances(new BufferedReader(new FileReader("C://Users//avs23//Desktop//Neon//HeartAnalysis//history1.csv.arff")));
		dataset1.setClassIndex(dataset1.numAttributes() - 1);
		
		bag = new Bagging();
		bag.buildClassifier(dataset1);
		
		decisiontree = new J48();
		decisiontree.buildClassifier(dataset1);
		
		random = new RandomForest();
		random.buildClassifier(dataset1);
	     
		// display classifier
//	     final javax.swing.JFrame jf = 
//	       new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
//	     jf.setSize(500,400);
//	     jf.getContentPane().setLayout(new BorderLayout());
//	     TreeVisualizer tv = new TreeVisualizer(null,
//	         decisiontree.graph(),
//	         new PlaceNode2());
//	     jf.getContentPane().add(tv, BorderLayout.CENTER);
//	     jf.addWindowListener(new java.awt.event.WindowAdapter() {
//	       public void windowClosing(java.awt.event.WindowEvent e) {
//	         jf.dispose();
//	       }
//	     });
//
//	     jf.setVisible(true);
//	     tv.fitToScreen();
//	     
//	     
	     
		if( z == 0 ) {
		    System.out.println("No heart attack");
			String output = "";
			int out = testheart(a,b,c,d,e,f,g,h,i,j,k,l+30,m,n,o,dataset1);
		    if (out == 1) {
		    	output = "If you increase the number of cigarettes you smoke per day then you have a chance of heart attack";
		    	return output;
		    }
		    
		    out = testheart(a,b,c,d,e,f,g,h,i,j,k,l,m+20,n,o,dataset1);
		    if (out == 1) {
		    	output = "If you continue smoking then you have a chance of heart attack";
		    	return output;
		    }

		    out = testheart(a,b,c,d,e,f,g,h,i,j,k,l+20,m+20,n,o,dataset1);
		    if (out == 1) {
		    	output = "If you continue smoking and increase the number of cigarettes then you have a chance of heart attack";
		    	return output;
		    }
		    
		    out = testheart(a,b,c,d,e,f,g,h,i,j,k,l,m,n-20,o-20,dataset1);
		    if (out == 1) {
		    	output = "If reduce your exercise then you have a chance of heart attack";
		    	return output;
		    }
		    
		    out = testheart(a,b,c,d,e,f,g,h,i,j,k,l+20,m+30,n-10,o-10,dataset1);
		    if (out == 1) {
		    	output = "If reduce your exercise and increase your cigs per day then you have a chance of heart attack";
		    	return output;
		    }
		    
		    if (output.length() == 0) {
		    	if(m > 20 || l > 20) {
		    		output = "Reduce your cig consumption, ";
		    	}
		    	output += "you are fine, no need to worry";
		    }
		    return output;
		}
		
		if(z==1) {
		    System.out.println("you have a heart attack");
			String output = "";
			int out = testheart(a,b,c,d,e,f,g,h,i,j,k,l-30,m,n,o,dataset1);
		    if (out == 0) {
		    	output = "Reduce your Cigs per day";
		    	if(q != -9) {
		    		output += " and control your sugar,";
		    	}
		    	return output;
		    }

		    out = testheart(a,b,c,d,e,f,g,h,i,j,k,l,m,n+20,o+20,dataset1);
		    if (out == 0) {
		    	output = "Increase your exercise";
		    	if (out == 0) {
			    	output += "and control your sugar";
			    }
		    	return output;
		    }
		    
		    
		    out = testheart(a,b,c,d,e,f,g,h,i,j,k,l-15,m,n+15,o+15,dataset1);
		    if (out == 0) {
		    	output = "Reduce your Cigs per day and Increase your exercise";
		    	if (out == 0) {
			    	output += "and control your sugar";
			    }
		    	return output;
		    }
		    
		    
		    out = testheart(a,b,c,d,e,f,g,h,i,j,k,l-25,m,n+20,o+20,dataset1);
		    if (out == 0) {
		    	output = "Reduce your Cigs per day and Increase your exercise Now";
		    	if (out == 0) {
			    	output += "and control your sugar";
			    }
		    	return output;
		    }
		    
		    if (output.length() == 0) {
		    	if(q != -9) {
		    		output += "Control your sugar and ";
		    	}
		    	if(m > 20 || l > 20) {
		    		output += " Reduce your cig consumption and ";
		    	}
		    	output += "Reduce your stress";
		    	return output;
		    }
		}
		return "You are fine";
	}
	
	
	
	public static void main(String args[]) throws Exception {
	
		int a = classifyall(3,130,256,1,2,142,1,0.6,2,1,6);
		System.out.println(preventheartdisease(3,110,175,0,0,123,0,0.6,0,0,4,50,50,17,10,-9,1,a));
	}
	
}
