package com.neu.calculate;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import DataProcess.JdtAstUtil;
import DataProcess.ReadPath;

public class EffectiveMethods {

	private ArrayList<String>effectiveMethods;

	public EffectiveMethods(ArrayList<String> paths){
		effectiveMethods=new ArrayList<String>();
		for(String path:paths){
			CompilationUnit comp=JdtAstUtil.getCompilationUnit(path);
			MethodsForClass mfc=new MethodsForClass();
			comp.accept(mfc);
			for(MethodDeclaration method:mfc.getMethods()){
				String [] str=path.split("src");
			    String fullName=str[1].substring(1,str[1].length()-5).replace('\\', '.');
				effectiveMethods.add(fullName+"."+method.getName().getFullyQualifiedName());
			}

		}
	}

	public ArrayList<String> getEffectiveMethods(){
		return this.effectiveMethods;
	}


}
