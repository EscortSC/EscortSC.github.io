/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.core.smells.CodeSmell;
import spirit.metrics.storage.MethodMetrics;

public class ShotgunSurgery extends CodeSmell {
	public static String NAME = "Shotgun Surgery";

	public ShotgunSurgery(MethodMetrics node) {
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
		return new TreeSet((List) this.node.getAttribute("ListOfClassInvoking"));
	}
}