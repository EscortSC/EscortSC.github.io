/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.core.smells.CodeSmell;
import spirit.metrics.storage.ClassMetrics;

public class RefusedParentBequest extends CodeSmell {
	public static String NAME = "Refused Parent Bequest";

	public RefusedParentBequest(ClassMetrics node) {
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
		return new TreeSet((List) this.node.getAttribute("SC"));
	}
}