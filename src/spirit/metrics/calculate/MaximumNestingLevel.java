/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.Iterator;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class MaximumNestingLevel extends ASTVisitor implements IAttribute {
	private int MNL = 0;
	private int tempMNL = 0;

	public boolean visit(CatchClause node) {
		++this.tempMNL;
		if (node.getBody() != null) {
			node.getBody().accept(this);
			this.updateMNL();
		}

		--this.tempMNL;
		return false;
	}

	public boolean visit(ConditionalExpression node) {
		++this.tempMNL;
		if (node.getThenExpression() != null) {
			node.getThenExpression().accept(this);
			this.updateMNL();
		}

		if (node.getElseExpression() != null) {
			node.getElseExpression().accept(this);
			this.updateMNL();
		}

		--this.tempMNL;
		return false;
	}

	public boolean visit(DoStatement node) {
		++this.tempMNL;
		if (node.getBody() != null) {
			node.getBody().accept(this);
			this.updateMNL();
		}

		--this.tempMNL;
		return false;
	}

	public boolean visit(ForStatement node) {
		++this.tempMNL;
		if (node.getBody() != null) {
			node.getBody().accept(this);
			this.updateMNL();
		}

		--this.tempMNL;
		return false;
	}

	public boolean visit(EnhancedForStatement node) {
		++this.tempMNL;
		if (node.getBody() != null) {
			node.getBody().accept(this);
			this.updateMNL();
		}

		--this.tempMNL;
		return false;
	}

	public boolean visit(IfStatement node) {
		++this.tempMNL;
		if (node.getThenStatement() != null) {
			node.getThenStatement().accept(this);
			this.updateMNL();
		}

		if (node.getElseStatement() != null) {
			node.getElseStatement().accept(this);
			this.updateMNL();
		}

		--this.tempMNL;
		return false;
	}

	public boolean visit(WhileStatement node) {
		++this.tempMNL;
		if (node.getBody() != null) {
			node.getBody().accept(this);
			this.updateMNL();
		}

		--this.tempMNL;
		return false;
	}

	public int getMNL() {
		return this.MNL;
	}

	private void updateMNL() {
		if (this.tempMNL > this.MNL) {
			this.MNL = this.tempMNL;
		}

	}

	public void calculate(ClassMetrics node) {
		float sumOfMNL = 0.0F;

		MethodMetrics methodMetrics;
		for (Iterator arg3 = node.getMethodsMetrics().iterator(); arg3
				.hasNext(); sumOfMNL += methodMetrics.getMetric(this.getName())
				.floatValue()) {
			methodMetrics = (MethodMetrics) arg3.next();
		}

		node.setMetric(this.getName(), sumOfMNL);
	}

	public String getName() {
		return "MNL";
	}

	public void calculate(MethodMetrics node) {
		this.MNL = 0;
		node.getDeclaration().accept(this);
		node.setMetric(this.getName(), (float) this.MNL);
	}
}