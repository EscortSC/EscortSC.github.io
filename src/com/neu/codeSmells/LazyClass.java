package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.dependencyfinder.gui.SaveFileAction;
import com.neu.codeSmell.calculate.PackageObject;

import DataProcess.JdtAstUtil;
import spirit.metrics.calculate.McCabe;

public class LazyClass extends ASTVisitor{
	private  List<ClassObject> classesArrList;
	private ArrayList<String> candidateLazyClasses;
	private  List LazyClassIn;
	private  List LazyClassOut;
	private String classFullName;


	public LazyClass(){
		candidateLazyClasses=new ArrayList<String>();
		classesArrList=SaveFileAction.classesArrList;
	}

	public boolean visit(TypeDeclaration clazz) {

		int index=0;
		 index = clazz.toString().indexOf("{");
		 if(index!=-1) {
				String signature=clazz.toString().substring(0, index-1);
				if(!signature.contains(" abstract class")&&(!signature.contains(" interface"))){
					LazyClassIn=new ArrayList<String>();
					LazyClassOut=new ArrayList<String>();
					PackageObject po=new PackageObject();
					clazz.getParent().accept(po);
				    classFullName=po.getPackageName()+"."+clazz.getName();
					for( int i=0;i<this.classesArrList.size();i++){
						if(classesArrList.get(i).getName().equals(classFullName)){
								ClassObject co=classesArrList.get(i);
								List<Feature> features=co.getFeatureList();
								for(Feature fe:features){
									if(fe.getName().contains("(")&&fe.getName().contains(")")){
										List<String> outboundFeatureList =fe.getOutboundFeatureList();
										for(String OutBound:outboundFeatureList ){
											String []str=OutBound.split("\\(");
											String []str1=str[0].split("\\.");
											String mName="";
											for(int k=0;k<str1.length-1;k++){
												mName=mName+str1[k]+".";
											}
											mName=mName.substring(0,mName.length()-1);
											if(!LazyClassOut.contains(mName)) LazyClassOut.add(mName);
										}
										List<String> inboundFeatureList =fe.getInboundFeatureList();
										for(String OutBound:inboundFeatureList ){
											String []str=OutBound.split("\\(");
											String []str1=str[0].split("\\.");
											String mName="";
											for(int k=0;k<str1.length-1;k++){
												mName=mName+str1[k]+".";
											}
											mName=mName.substring(0,mName.length()-1);
											if(!LazyClassIn.contains(mName)) LazyClassIn.add(mName);
										}
									}
							}
								if(LazyClassIn.size()<1&&LazyClassOut.size()<1&&this.Loc(clazz)<30&&this.WMC(clazz)<5){
									this.candidateLazyClasses.add(classFullName+";In;"+LazyClassIn.size()+";Out;"+LazyClassOut.size()
											+";loc;"+this.Loc(clazz)+";WMC;"+this.WMC(clazz));
								}
							break;
						}
					}
				}
		 }
		return false;
	}

	public ArrayList<String> searchLazyClasses(ArrayList<String> paths){
		for(String path:paths){
			CompilationUnit comp= JdtAstUtil.getCompilationUnit(path);
			comp.accept(this);
		}
 		return this.candidateLazyClasses;
	}

	public int Loc(TypeDeclaration pClass){
		int loc=0;
		String source=pClass.toString();
		String regex="[\n]";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find())
			loc++;
		return loc+1;
	}

	public int WMC(TypeDeclaration pClass){
		McCabe mc=new McCabe();
		pClass.accept(mc);
		return mc.getCyclomatic();

	}

}
