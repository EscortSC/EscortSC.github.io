package spirit.core.smells;

import java.util.Set;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import spirit.metrics.storage.ClassMetrics;

public final class LazyClass extends CodeSmell {

	public static String NAME = "Lazy Class";

	public LazyClass(ClassMetrics node) {
		super(NAME);
		this.element = node.getDeclaration();
		this.node = node;
	}

	public TypeDeclaration getMainClass() {
		return this.getElement() instanceof TypeDeclaration
				? (TypeDeclaration) this.getElement()
				: null;
	}

	@Override
	public Set<String> getAffectedClasses() {
		// TODO Auto-generated method stub
		return null;
	}

}
