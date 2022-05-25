package com.neu.invocation.matrics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;

public class MMethodMatrics {
	private List<String> methodNames;
	private List<Feature> allMethods;
	int [][]MMethod;
	private String relativePath;

	public MMethodMatrics(){
		methodNames=MClassMatrics.methodNames;
		allMethods=MClassMatrics.allMethods;
		MMethod=new int[this.allMethods.size()][this.allMethods.size()];
		this.relativePath=SaveFileAction.relativePath;
		this.CreateMatrics();
		this.print();
	}

	public void CreateMatrics(){
		for(int i=0;i<this.allMethods.size();i++){
			Feature method=allMethods.get(i);
			List<String> outBoundList=method.getOutboundFeatureList();
			for(String outBound:outBoundList){
				if(outBound.contains("(")&&outBound.contains(")")){
					String paramenters="";
					String str1[]=outBound.split("\\(");
					String str2[]=str1[1].split("\\)");
					if(str2.length>0){
						String str3[]=str2[0].split("\\, ");
						for(int k=0;k<str3.length;k++){
							String str4[]=str3[k].split("\\.");
							paramenters=paramenters+str4[str4.length-1]+",";
						}
						paramenters=paramenters.substring(0,paramenters.length()-1);
					}
					String method1Name=str1[0]+"("+paramenters+")";
					for(int j=0;j<methodNames.size();j++){
						if(methodNames.get(j).equals(method1Name)){
							MMethod[i][j]=1;
							break;
						}
					}
				}
			}
		}
	}

	public void print(){
		try{
			File writename=new File("D:\\smell\\output\\"+relativePath+"\\methodToMethodMatrix.txt");
			writename.createNewFile();
			BufferedWriter out=new BufferedWriter(new FileWriter(writename));
			out.write("  "+",");
			for(String className:methodNames){
				String method=className.replace(",", ";");
				out.write(method+",");
			}
			out.write('\n');
		    for(int j=0;j<methodNames.size();j++){
		    	String method=methodNames.get(j).replace(",", ";");
				out.write(method+",");
				for(int k=0;k<methodNames.size();k++){
			    	 out.write(MMethod[j][k]+",");
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
