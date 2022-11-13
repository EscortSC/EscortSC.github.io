package com.neu.MessageChains;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;

public class MessageChainDetector {
	public MessageChainInstance calculateComplexity(List<MethodDeclaration> visibleMethods) {

		final List<MethodInvocation> stmts = new ArrayList<MethodInvocation>();
		for (MethodDeclaration m : visibleMethods) {

			MethodDeclaration node = m;
			node.accept(new ASTVisitor() {
				public boolean visit(MethodInvocation inv) {
					if (!(inv.getParent() instanceof MethodInvocation))
						stmts.add(inv);
					return true;
				}
			});
		}

		return new MessageChainInstance(stmts);
	}

}

class MessageChainInstance {

	private List<MethodInvocation> invocations;
	

	public MessageChainInstance(List<MethodInvocation> invs) {
		this.invocations = invs;
	}

	public double magnitude() {

		double severity = 0;
		for (MethodInvocation inv : invocations) {
			double chainSize = sizeOf(inv);
			severity += chainSize < 2 ? 0 : chainSize;
		}

		return Math.log(severity) / (8 * Math.log(2));
	}

	private double sizeOf(MethodInvocation inv) {

		if (inv.getExpression() instanceof MethodInvocation) {
			return 1 + sizeOf((MethodInvocation) inv.getExpression());
		}

		return 1;
	}
}
