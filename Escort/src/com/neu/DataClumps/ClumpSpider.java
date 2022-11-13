package com.neu.DataClumps;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ClumpSpider{
	private List<MethodDeclaration> currentMethods = new ArrayList();
	private Set<MethodDeclaration> visitedMethods = new HashSet();
	private DataClumpCollection clumpColl = new DataClumpCollection();

	public Set<ClumpGroup> currentClumps() {
		Set currentClumps = new HashSet();

		for (MethodDeclaration m : this.currentMethods) {
			for (ClumpGroup cg : this.clumpColl.inGroupOf(m)) {
				cg.mergeIfClumped(currentClumps);
			}
		}

		return currentClumps;
	}
	public DataClumpCollection getColloection(){
		return this.clumpColl;
	}

	public double magnitude()
	  {
	    Set<ClumpGroup> clumps = currentClumps();
	    double magnitude = 0.0D;
	    for (ClumpGroup clump : clumps)
	    {
	      int clumpSize = clump.signatureSize();
	      int clumpOccurences = clump.occurrences();
	      magnitude += clumpSize * Math.pow(clumpOccurences, 1.75D);
	    }
	    return Math.log(magnitude) / 4.0D;
	  }

	public void spiderFrom(List<MethodDeclaration> methods) {
		this.currentMethods = methods;
		for(MethodDeclaration method:this.currentMethods) {
			CompilationUnit unit=compilationUnit(method);
			if (unit == null) {
				return;
			}
			for (MethodDeclaration m : ClumpGroup.allMethodsIn(unit))
				try {
					process(m);
				} catch (JavaModelException e) {
					e.printStackTrace();
				}
		}

		
	}

	private void process(MethodDeclaration m) throws JavaModelException {
		boolean doVisit = this.visitedMethods.add(m);
		if (!(doVisit)) {
			return;
		}
		this.clumpColl.addClump(m);
	}

	public CompilationUnit compilationUnit(MethodDeclaration method) {
		if (this.currentMethods.size() < 1) {
			return null;
		}
		ASTNode node=method.getParent();
		while(!(node instanceof CompilationUnit))  {
			node=node.getParent();
		}
		return (CompilationUnit)node;
	}

	

}
