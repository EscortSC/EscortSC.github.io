package com.neu.calculate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.Refactor.classparser.Feature;
import com.neu.invocation.matrics.GetAllMethods;

public class MiddleManVisitor extends ASTVisitor {

	private  int count=0;
	private ArrayList<MethodDeclaration> methods;
	private List<Feature> allFeatures;
	private List<String> allFeaturesNames;
	public String className;
	
	public MiddleManVisitor(){
		allFeatures=GetAllMethods.allFeatures;
		allFeaturesNames=GetAllMethods.allMethodNames;
		methods = new ArrayList<MethodDeclaration>();
		count=0;
	}

    @SuppressWarnings("deprecation")
    public boolean visit(TypeDeclaration node){
    	PackageObject po=new PackageObject();
    	node.getParent().accept(po);
    	className=po.getPackageName()+"."+node.getName();
    	return true;
    }
	public boolean visit(MethodDeclaration node) {
    	methods.add(node);
    	int loc=Loc(node);
    	int Num=0;
    	 Iterator it=node.parameters().iterator();
		 String Parameters="(";
		 while(it.hasNext()){
			 String name=it.next().toString();
			 String []str=name.split(" ");
			 Parameters=Parameters+str[0]+",";
		 }
		 if(Parameters.length()>1) Parameters=Parameters.substring(0,Parameters.length()-1);
		 Parameters=Parameters+")";
		 String methodName=className+"."+node.getName()+Parameters;
    	if(!node.getName().equals("cloneDate")){
    		for(int i=0;i<allFeaturesNames.size();i++){   			
    			if(allFeaturesNames.get(i).equals(methodName)){   
    				Feature fe=allFeatures.get(i);
    				List<String> outBounds=fe.getOutboundFeatureList();  				
    				for(String outBound:outBounds){
    					if(outBound.contains("(")){
    						Num++;
    					}
    				}
    			if(loc<10&&(Num==1))count++;
                break;
    			}
    		}
    	}
        return true;
    }

    public ArrayList<MethodDeclaration> getMethods(){
    	return this.methods;
    }

    private int Loc(MethodDeclaration node){
    	int count=0;
    	char [] toChar=node.toString().toCharArray();
    	for(int i=0;i<toChar.length;i++)
    		if(toChar[i]=='\n') count++;
    	return count;
    }

    public int getCount(){
    	return this.count;
    }



}
