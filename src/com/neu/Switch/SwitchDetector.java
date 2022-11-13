package com.neu.Switch;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SwitchStatement;



public class SwitchDetector {

	public double obviousness() {	return 0.93;		}

	public SwitchInstance calculateComplexity(List<MethodDeclaration> visibleMethods) {
		
		final List<SwitchStatement> stmts = new ArrayList<SwitchStatement>();
		for (MethodDeclaration m : visibleMethods){
			
			m.accept(new ASTVisitor(){
				public boolean visit(SwitchStatement switchStmt){
					stmts.add(switchStmt);
					return true;
				}
			});
		}

		return new SwitchInstance(stmts);
	}


	public String getName() {
		return "Switch Statement";
	}
	
	
}

class SwitchInstance {

	private List<SwitchStatement> switches;

	public SwitchInstance(List<SwitchStatement> stmts){
		this.switches = stmts;
	}
	
	public double magnitude() {
		
		double severity = 0;
		for(SwitchStatement stmt : switches){
			severity += stmt.statements().size();
		}
		
		return Math.log(severity) / (8 * Math.log(2));
	}
	
}