/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.core.smells.CodeSmell;
import spirit.metrics.storage.ClassMetrics;

public class BrainClass extends CodeSmell {
	public static String NAME = "Brain Class";

	public BrainClass(ClassMetrics node) {
		super(NAME);
		this.element = node.getDeclaration();
		this.node = node;
	}

	public TypeDeclaration getMainClass() {
		return this.getElement() instanceof TypeDeclaration
				? (TypeDeclaration) this.getElement()
				: null;
	}

	public Set<String> getAffectedClasses() {
		List list = (List) this.node.getAttribute("ListOfClassInvoked");
		Iterator arg2 = ((List) this.node.getAttribute("ListOfClassInvoking"))
				.iterator();

		while (arg2.hasNext()) {
			String name = (String) arg2.next();
			if (!list.contains(name)) {
				list.add(name);
			}
		}

		return new TreeSet(list);
	}
}