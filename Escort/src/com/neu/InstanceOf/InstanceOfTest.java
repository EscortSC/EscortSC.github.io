package com.neu.InstanceOf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.calculate.ClaObject;
import com.neu.calculate.PackageObject;

public class InstanceOfTest {
	private List<String> candidateInstanOf = new ArrayList<String>();

	public InstanceOfTest(List<CompilationUnit> comunits) {
		this.SearchCandidateInstanceof(comunits);
	}

	public void SearchCandidateInstanceof(List<CompilationUnit> comunits) {
		for (CompilationUnit com : comunits) {
			ClaObject co = new ClaObject();
			com.accept(co);
			TypeDeclaration type = co.getType();
			if (co.getType() != null) {
				PackageObject po = new PackageObject();
				String className=type.getName().toString();
				ASTNode node=type.getParent();
				if (node instanceof CompilationUnit) {
					node.accept(po);
					className = po.getPackageName() + "." +className ;
					List<MethodDeclaration> methods = co.getMethods();
					InstanceOfDetector iod = new InstanceOfDetector();
					InstanceOfInstance ioi = iod.calculateComplexity(methods);
					double magnitude = ioi.calculateMagnitude();
					if (magnitude > 0.945)
						candidateInstanOf.add(className);
				}
			}
		}
	}

	public List<String> getCandidateInstanceOf() {
		return this.candidateInstanOf;
	}

}
