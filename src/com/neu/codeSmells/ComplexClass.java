package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.PackageObject;

import DataProcess.JdtAstUtil;
import spirit.metrics.calculate.McCabe;

public class ComplexClass {
	private ArrayList<String> candidateComplexClasses;
	private List<String> allClassDetail = new ArrayList<String>();

	public ComplexClass() {
		candidateComplexClasses = new ArrayList<String>();
	}
	
	public List<String> getAllClassDetail(){
		return this.allClassDetail;
	}

	public ArrayList<String> searchComplexClasses(ArrayList<String> paths) {
		for (String path : paths) {
			CompilationUnit comp1 = JdtAstUtil.getCompilationUnit(path);
			ClaObject co = new ClaObject();
			comp1.accept(co);
			if (co.getType() != null) {
				TypeDeclaration type = co.getType();
				McCabe mc = new McCabe();
				comp1.accept(mc);
				int mcCabe = mc.getCyclomatic();
				int loc = this.LOC(comp1);
				PackageObject po = new PackageObject();
				type.getParent().accept(po);
				String className = po.getPackageName() + "." + type.getName();
				if (className.startsWith("."))
					continue;
				allClassDetail.add(className+","+loc+","+mcCabe);
				if ((loc > 100) && (mcCabe > 30)) {
					candidateComplexClasses.add(className + ";" + loc + ";" + mcCabe);
				}
			}
		}
		return this.candidateComplexClasses;
	}

	private int LOC(CompilationUnit pClass) {
		int loc = 0;
		String source = pClass.toString();
		String regex = "[\n]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find())
			loc++;
		return loc + 1;
	}

}
