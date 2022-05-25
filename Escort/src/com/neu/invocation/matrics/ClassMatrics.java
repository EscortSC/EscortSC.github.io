package com.neu.invocation.matrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import jdk.internal.dynalink.support.ClassMap;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;


public class ClassMatrics {
	private  List<String>classNames;
	private  List<ClassObject> classObjects;
    private String relativePath=SaveFileAction.relativePath;
	private int[][]CMatrics;

	public ClassMatrics(){
	    classNames=new ArrayList<String>();
		classObjects=new ArrayList<ClassObject>();
		classNames=SaveFileAction.classname;
		classObjects=SaveFileAction.classesArrList;
		this.CMatrics=new int[classObjects.size()][classObjects.size()];
		System.out.println("This Project "+this.classObjects.size());
		this.IntialMatrics();
	//	check();
		this.print();
	}

	private void check(){
		for(int i=0;i<classObjects.size();i++){
			if(classObjects.get(i).getName()!=classNames.get(i)){
				System.out.println(classObjects.get(i).getName()+","+classNames.get(i));
			}
			
		}
	}
	public void IntialMatrics(){
		for(ClassObject co:classObjects){
			if(classNames.contains(co.getName())){
				int i=classNames.indexOf(co.getName());
				List<Feature> features=co.getFeatureList();
				for(Feature fe:features){
					if(fe.getName().contains("(")&&fe.getName().contains(")")){
						List<String> outboundFeatureList =fe.getOutboundFeatureList();
						for(String OutBound:outboundFeatureList){
							String []str=OutBound.split("\\(");
							int index=str[0].lastIndexOf(".");
							String mName=str[0].substring(0,index);
						         if(classNames.contains(mName)){
						        	int j=classNames.indexOf(mName);
									CMatrics[i][j]=1;
								//	break;
								}
							}						
					}
				}
			}
		}
	}
	public void print(){
		try{
			File writename=new File("D:\\smell\\output\\"+relativePath+"\\classMatrix.txt");
			writename.createNewFile();
			BufferedWriter out=new BufferedWriter(new FileWriter(writename));
		   out.write("  "+",");
			for(String st:classNames){
				out.write(st+",");
			}
			out.write('\n');
		for(int i=0;i<classNames.size();i++){
			out.write(classNames.get(i)+",");
				for(int j=0;j<classNames.size();j++){
					if((j+1)==classNames.size())out.write(CMatrics[i][j]+"");
					else  {out.write(CMatrics[i][j]+",");}
				}
				out.write('\n');
			}
			out.flush();
			out.close();
		}
		catch(Exception e){

		}
	}



}
