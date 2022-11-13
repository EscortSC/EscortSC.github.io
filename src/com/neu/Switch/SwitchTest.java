package com.neu.Switch;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.PackageObject;

public class SwitchTest {
	private List<String> candidateSwitch = new ArrayList<String>();

	public SwitchTest(List<CompilationUnit> comUnits) {
		this.SearchCandidateSwitch(comUnits);
	}

	public void SearchCandidateSwitch(List<CompilationUnit> comunits) {
		for (CompilationUnit com : comunits) {
			ClaObject co = new ClaObject();
			com.accept(co);
			TypeDeclaration type = co.getType();
			if (co.getType() != null) {
				PackageObject po = new PackageObject();
				type.getParent().accept(po);
				String className = po.getPackageName() + "." + type.getName();
				List<MethodDeclaration> methods = co.getMethods();
				SwitchDetector typeCast = new SwitchDetector();
				SwitchInstance TCInstance = typeCast.calculateComplexity(methods);
				double magnitude = TCInstance.magnitude();
				if (magnitude > 0.93)
					this.candidateSwitch.add(className);
			}
		}
	}

	public List<String>  getCandidateSwitch() {
		return this.candidateSwitch;
	}

}
