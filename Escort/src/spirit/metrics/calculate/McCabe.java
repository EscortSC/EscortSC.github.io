/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.Iterator;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.WhileStatement;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class McCabe extends ASTVisitor implements IAttribute {
	private int cyclomatic = 1;

	public boolean visit(CatchClause node) {
		++this.cyclomatic;
		return true;
	}

	public boolean visit(ConditionalExpression node) {
		++this.cyclomatic;
		this.inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(DoStatement node) {
		++this.cyclomatic;
		this.inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(ForStatement node) {
		++this.cyclomatic;
		this.inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(IfStatement node) {
		++this.cyclomatic;
		this.inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(SwitchCase node) {
		if (!node.isDefault()) {
			++this.cyclomatic;
		}

		return true;
	}

	public boolean visit(WhileStatement node) {
		++this.cyclomatic;
		this.inspectExpression(node.getExpression());
		return true;
	}

	public boolean visit(ExpressionStatement exs) {
		this.inspectExpression(exs.getExpression());
		return false;
	}

	private void inspectExpression(Expression ex) {
		if (ex != null) {
			String expression = ex.toString();
			char[] chars = expression.toCharArray();

			for (int i = 0; i < chars.length - 1; ++i) {
				char next = chars[i];
				if ((next == 38 || next == 124) && next == chars[i + 1]) {
					++this.cyclomatic;
				}
			}
		}

	}

	public int getCyclomatic() {
		return this.cyclomatic;
	}

	public void calculate(ClassMetrics node) {
		float sumOfATFD = 0.0F;

		MethodMetrics methodMetrics;
		for (Iterator arg3 = node.getMethodsMetrics().iterator(); arg3
				.hasNext(); sumOfATFD += methodMetrics
				.getMetric(this.getName()).floatValue()) {
			methodMetrics = (MethodMetrics) arg3.next();
		}

		node.setMetric(this.getName(), sumOfATFD);
	}

	public String getName() {
		return "WMC";
	}

	public void calculate(MethodMetrics node) {
		this.cyclomatic = 1;
		node.getDeclaration().accept(this);
		node.setMetric(this.getName(), (float) this.cyclomatic);
	}
	
	public int getValue() {
		return this.cyclomatic;
	}
}