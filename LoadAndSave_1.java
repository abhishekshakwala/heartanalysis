package com.classify;

import weka.classifiers.trees.*;
import weka.classifiers.meta.Bagging;
import weka.core.*;
import weka.core.converters.ArffSaver;
import weka.experiment.InstanceQuery;

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
	    
	    Instances dataUnlabeled = new Instances(dataset, 0);
	    dataUnlabeled.add(newInst);
	    dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);
	    
	    double classif = random.classifyInstance(dataUnlabeled.firstInstance());
	    
	    return classif;
	}
	
	
	public static double classifybag(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k) throws Exception {
		double[] values = new double[]{a,b,c,d,e,f,g,h,i,j,k};
	    DenseInstance newInst = new DenseInstance(1.0,values);
	    
	    Instances dataUnlabeled = new Instances(dataset, 0);
	    dataUnlabeled.add(newInst);
	    dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);
	    
	    double classif = decisiontree.classifyInstance(dataUnlabeled.firstInstance());
	    return classif;
	}
	
	
	public static double classifyj(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k) throws Exception {
		double[] values = new double[]{a,b,c,d,e,f,g,h,i,j,k};
	    DenseInstance newInst = new DenseInstance(1.0,values);
	    
	    Instances dataUnlabeled = new Instances(dataset, 0);
	    dataUnlabeled.add(newInst);
	    dataUnlabeled.setClassIndex(dataUnlabeled.numAttributes() - 1);
	    
	    double classif = decisiontree.classifyInstance(dataUnlabeled.firstInstance());
	    return classif;
	}

	public static int classifyall(double a, double b, double c, double d, double e, double f, double g, double h, double i, double j, double k) throws Exception {
		

	 	InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("Mysuccess@26");
		query.setQuery("Select cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num from persontest");
		query.setDatabaseURL("jdbc:mysql://localhost:3306/heartdb");
		dataset = query.retrieveInstances();
		dataset.setClassIndex(dataset.numAttributes()-1);
		 
		
		J();
		Rando();
		bag();
		
		
		double count1 = classifyrandom(a,b,c,d,e,f,g,h,i,j,k);
		double count2 = classifybag(a,b,c,d,e,f,g,h,i,j,k);
		double count3 = classifyj(a,b,c,d,e,f,g,h,i,j,k);
		return (int)( (count1 + count2 + count3) / 3);
	}
	
	
	public static void main(String args[]) throws Exception {
	}
}
