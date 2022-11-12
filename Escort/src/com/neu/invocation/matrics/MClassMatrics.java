package com.neu.invocation.matrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class MClassMatrics {
	private  List<String>classNames;
	private  List<ClassObject> classObjects;
	public static  List<String> methodNames;
	public static List<Feature> allMethods;
    private String relativePath;
	private int[][]MClassMatrics;

	public MClassMatrics(GetAllMethods gam){
		classNames=SaveFileAction.classname;
		classObjects=SaveFileAction.classesArrList;
		relativePath=SaveFileAction.relativePath;
		this.methodNames=GetAllMethods.allMethodNames;
		this.allMethods=GetAllMethods.allFeatures;
		System.out.println("Method Size "+this.methodNames.size());
		this.CreateMatrix();
		this.print();
	}

	public void CreateMatrix(){
		this.MClassMatrics=new int[this.methodNames.size()][this.classNames.size()];
		for(int i=0;i<allMethods.size();i++){
			Feature method=allMethods.get(i);
			List<String> outboundFeatureList =method.getOutboundFeatureList();
			for(String OutBound:outboundFeatureList){
				String []str=OutBound.split("\\(");
				String []str1=str[0].split("\\.");
				String mName="";
				for(int k=0;k<str1.length-1;k++){
					mName=mName+str1[k]+".";
				}
				mName=mName.substring(0,mName.length()-1);
				for(int j=0;j<classNames.size();j++){
					if((MClassMatrics[i][j]==0)&&mName.equals(classNames.get(j))){
						String classNameForMethod="";
						String[] st1=method.getName().split("\\(");
						String[] st2=st1[0].split("\\.");
						for(int m=0;m<st2.length-1;m++)
							classNameForMethod=classNameForMethod+st2[m]+".";
						classNameForMethod=classNameForMethod.substring(0,classNameForMethod.length()-1);
						if(!classNameForMethod.equals(classNames.get(j))) MClassMatrics[i][j]=1;
						//MClassMatrics[i][j]=1;
						break;
					}
				}
			}
		}
	}

	public void print(){
		try{
			File writename=new File("D:\\smell\\output\\"+relativePath+"\\methodToClassMatrix.txt");
			writename.createNewFile();
			BufferedWriter out=new BufferedWriter(new FileWriter(writename));
			out.write("  "+",");
			for(String className:classNames){
				out.write(className+",");
			}
			out.write('\n');
		    for(int j=0;j<methodNames.size();j++){
		    	String method=methodNames.get(j).replace(",", ";");
				out.write(method+",");
				for(int k=0;k<classNames.size();k++){
			    	 out.write(MClassMatrics[j][k]+",");
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
