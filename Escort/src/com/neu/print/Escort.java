package com.neu.print;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.neu.calculate.ClaObject;

import DataProcess.JdtAstUtil;
import DataProcess.ReadPath;
import spirit.metrics.calculate.McCabe;

public class Escort {

	public Escort() {
		List<CompilationUnit> comUnits = new ArrayList<CompilationUnit>();
		CompilationUnit com = JdtAstUtil.getCompilationUnit("D:\\smell\\Project\\old\\Lucene\\lucene-6.2.0\\org\\apache\\lucene\\index\\ParallelLeafReader.java");
		comUnits.add(com);

		for (CompilationUnit unit : comUnits) {
			int sumMC=0;
			McCabe wmc1 = new McCabe();
			ClaObject co = new ClaObject();
			unit.accept(wmc1);
			unit.accept(co);
			for (MethodDeclaration method : co.getMethods()) {
				McCabe wmc2 = new McCabe();
				method.accept(wmc2);
				System.out.println(method.getName() + "," + wmc2.getCyclomatic());
				sumMC+=wmc2.getCyclomatic();
			}
			System.out.println(sumMC);
		}
	}

}
