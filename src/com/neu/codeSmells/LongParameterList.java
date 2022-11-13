package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.PackageObject;
import com.neu.invocation.matrics.GetAllMethods;

import DataProcess.JdtAstUtil;
import javassist.bytecode.stackmap.TypeData.ClassName;

public class LongParameterList {
	private ArrayList<String> allclassnames;
	private ArrayList<String> candidateLongParameterLists;
	private List<String> allMethods;

	public LongParameterList() {
		allclassnames = new ArrayList<String>();
		candidateLongParameterLists = new ArrayList<String>();
		allMethods = GetAllMethods.allMethodNames;
		
	}
    public ArrayList<String> getAllClassName(ArrayList<String> paths){
    	for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject co = new ClaObject();
			comp1.accept(co);
			TypeDeclaration type = co.getType();
			if (co.getType() != null) {
				PackageObject po = new PackageObject();
				type.getParent().accept(po);
				String className = po.getPackageName() + "." + type.getName();
				if (className.startsWith("."))
					continue;
				allclassnames.add(className);
			}
    	}
    	
		return this.allclassnames;
    }
	public ArrayList<String> searchLongParamenterListAntipattern(ArrayList<String> paths) {
		for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject co = new ClaObject();
			comp1.accept(co);
			TypeDeclaration type = co.getType();
			if (co.getType() != null) {
				PackageObject po = new PackageObject();
				type.getParent().accept(po);
				String className = po.getPackageName() + "." + type.getName();
				ArrayList<MethodDeclaration> methods = co.getMethods();			
				if (className.startsWith("."))
					continue;
				for (MethodDeclaration method : methods) {
					
					if ((method.parameters().size() >= 0)) {
						String fullMethodName = className + "." + method.getName();
						candidateLongParameterLists.add(fullMethodName);
					}
			    }
		     }
		}
		return this.candidateLongParameterLists;
	}

	private int Loc(MethodDeclaration method) {
		int loc = 0;
		String source = method.toString();
		String regex = "[\n]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find())
			loc++;
		return loc + 1;
	}

}
