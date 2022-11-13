package com.neu.MessageChains;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.calculate.CObject;
import com.neu.calculate.ClaObject;
import com.neu.calculate.PackageObject;

public class MessageChainTest {
	private Set<String> candidateMessageChains = new HashSet<String>();

	public MessageChainTest(List<CompilationUnit> comunits) {
		this.SearchCandidateMessageChains(comunits);
	}

	public void SearchCandidateMessageChains(List<CompilationUnit> comunits) {
		for (CompilationUnit com : comunits) {
			ClaObject co = new ClaObject();
			com.accept(co);
			TypeDeclaration type = co.getType();
			if (co.getType() != null) {
				List<MethodDeclaration> methods = co.getMethods();
				MessageChainDetector messageChain = new MessageChainDetector();
				MessageChainInstance MCInstances = messageChain.calculateComplexity(methods);
				if (MCInstances.magnitude() > 0.80) {
					ASTNode self = type;
					ASTNode parent = self.getParent();
					while (parent.getNodeType() == 55) {
						self = parent;
						parent = parent.getParent();
					} 
					PackageObject po = new PackageObject();
					CObject coo = new CObject();
					parent.accept(po);
					self.accept(coo);
					String className = po.getPackageName() + "." + coo.getClassName();
					this.candidateMessageChains.add(className);
				}
			}
		}
	}

	public Set<String> getCandidateMessageChains() {
		return this.candidateMessageChains;
	}

}
