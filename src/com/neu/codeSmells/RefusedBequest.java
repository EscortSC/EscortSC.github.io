package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.MethodsForClass;
import com.neu.codeSmell.calculate.PackageObject;

import DataProcess.JdtAstUtil;

public class RefusedBequest {
	private ArrayList<String> candidateRefusedBequests;
	public RefusedBequest(){
		candidateRefusedBequests=new ArrayList<String>();
	}

	public ArrayList<String> searchRefusedBequests(ArrayList<String> paths) {
		ArrayList<CompilationUnit> comps=new ArrayList<CompilationUnit>();
		double refusedCounter=0.0;
		double numberOfSuperclassMethods=0.0;
		for(String path:paths){
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			comps.add(comp1);
		}
		for(CompilationUnit comp: comps){
			if(this.LOC(comp) > 300) {
				ClaObject co=new ClaObject();
				comp.accept(co);
				TypeDeclaration type=co.getType();
				if(type.getSuperclassType()!=null){
					TypeDeclaration superclass=this.SearchSuperClass(paths, type.getSuperclassType().toString());
					if((superclass!=null) && (! superclass.toString().contains("abstract"))) {
						MethodsForClass mfc=new MethodsForClass();
						superclass.accept(mfc);
						ArrayList<MethodDeclaration> methodsForSupClass=mfc.getMethods();
						numberOfSuperclassMethods=methodsForSupClass.size();
						MethodsForClass mfc1=new MethodsForClass();
						type.accept(mfc1);
						ArrayList<MethodDeclaration> methods=mfc1.getMethods();
						for(MethodDeclaration method:methods){
							for(MethodDeclaration methodForSupClass:methodsForSupClass){
								if(methodForSupClass.getModifiers()==method.getModifiers()&&methodForSupClass.getName().equals(method.getName())){
									refusedCounter++;
								}
							}
						}
						if((refusedCounter/numberOfSuperclassMethods)>0.7){
							PackageObject po=new PackageObject();
							type.accept(po);
							System.out.println(po.getPackageName()+"."+type.getName());
							candidateRefusedBequests.add(po.getPackageName()+"."+type.getName()+";"+refusedCounter);
						}
					}
				}
			}
		}
		return this.candidateRefusedBequests;

	}

	private int LOC(CompilationUnit comp){
		int loc=0;
		String source=comp.toString();
		String regex="[\n]";
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find())
		      loc++;
		return loc+1;
	}

	private TypeDeclaration SearchSuperClass(ArrayList<String> paths,String superClassName){
		for(String path: paths){
			if(path.endsWith(superClassName+".java")){
				CompilationUnit com1=JdtAstUtil.getCompilationUnit(path);
				ClaObject co=new ClaObject();
				com1.accept(co);
				return co.getType();
			}
		}
		return null;
	}




}
