package com.neu.TypeCast;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.PackageObject;

public class TypeCastTest {
	private List<String> candidateTypeCast = new ArrayList<String>();

	public TypeCastTest(List<CompilationUnit> comunits) {
		this.SearchCandidateTypeCast(comunits);
	}

	public void SearchCandidateTypeCast(List<CompilationUnit> comunits) {
		for (CompilationUnit com : comunits) {
			ClaObject co = new ClaObject();
			com.accept(co);
			TypeDeclaration type = co.getType();
			if (co.getType() != null) {
				PackageObject po = new PackageObject();
				type.getParent().accept(po);
				String className = po.getPackageName() + "." + type.getName();
				List<MethodDeclaration> methods = co.getMethods();
				TypeCastDetector typeCast = new TypeCastDetector();
				TypeCastInstance TCInstance = typeCast.calculateComplexity(methods);
				double magnitude = TCInstance.calculateMagnitude();
				if (magnitude > 0.94)
					this.candidateTypeCast.add(className);
			}
		}
	}

	public List<String> getCandidateTypeCast() {
		return this.candidateTypeCast;
	}

}
