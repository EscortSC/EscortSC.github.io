package com.neu.DataClumps;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;



public class ClumpGroup {
	private ClumpSignature signature;
	private Set<MethodDeclaration> methods;

	public ClumpGroup(ClumpSignature cs) {
		this.signature = cs;
		this.methods = new HashSet();
	}

	public int occurrences() {
		return this.methods.size();
	}

	public void mergeIfClumped(Set<ClumpGroup> group) {
		if ((signatureSize() > 1) && (this.methods.size() > 1))
			group.add(this);
	}

	public int signatureSize() {
		return this.signature.size();
	}

	public void add(MethodDeclaration m) {
		this.methods.add(m);
	}

	public Set<MethodDeclaration> methodsIn(CompilationUnit icu) {
		HashSet result = new HashSet(this.methods);
		result.retainAll(allMethodsIn(icu));
		return result;
	}

	public static Set<MethodDeclaration> allMethodsIn(CompilationUnit icu) {
		Set allMethodsInCU = new HashSet();
		for (Object type : icu.types()) {
			TypeDeclaration t=(TypeDeclaration)type;
			for (MethodDeclaration m : t.getMethods())
				allMethodsInCU.add(m);
		}
		return allMethodsInCU;
	}
	
	public Set<MethodDeclaration> getMethods(){
		return this.methods;
	}

}
