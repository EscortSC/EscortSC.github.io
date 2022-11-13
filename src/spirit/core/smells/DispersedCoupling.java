/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.core.smells.CodeSmell;
import spirit.metrics.storage.MethodMetrics;

public class DispersedCoupling extends CodeSmell {
	public static String NAME = "Dispersed Coupling";

	public DispersedCoupling(MethodMetrics node) {
		super(NAME);
		this.element = node.getDeclaration();
		this.node = node;
	}

	public TypeDeclaration getMainClass() {
		MethodDeclaration element = (MethodDeclaration) this.getElement();
		return element.getParent() instanceof TypeDeclaration
				? (TypeDeclaration) element.getParent()
				: (TypeDeclaration) element.getParent().getParent();
	}

	public Set<String> getAffectedClasses() {
		List list = (List) this.node.getAttribute("ListOfClassInvoked");
		List listInvoking = (List) this.node
				.getAttribute("ListOfClassInvoking");
		if (listInvoking != null) {
			Iterator arg3 = listInvoking.iterator();

			while (arg3.hasNext()) {
				String name = (String) arg3.next();
				if (!list.contains(name)) {
					list.add(name);
				}
			}
		}

		return new TreeSet();
	}
}