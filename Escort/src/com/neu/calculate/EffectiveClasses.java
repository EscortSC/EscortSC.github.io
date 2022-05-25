package com.neu.calculate;

import java.util.ArrayList;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import DataProcess.JdtAstUtil;

public class EffectiveClasses {
	public static ArrayList<String> effectiveTypes=new ArrayList<String>();

	public EffectiveClasses(ArrayList<String> paths){
		for(String path:paths){
			CompilationUnit comp=JdtAstUtil.getCompilationUnit(path);
			ClaObject co=new ClaObject();
			comp.accept(co);
			if(co.getType()!=null){
				TypeDeclaration type=co.getType();
				effectiveTypes.add(type.getName().toString());
			}
		}
	}

}
