package spirit.metrics.storage;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;

public class MethodMetrics extends NodeMetrics{
	private Feature feature;
	private ClassMetrics classMetrics;
	private MethodDeclaration declaration;

	public MethodMetrics(Feature feature,
			ClassMetrics classMetrics,MethodDeclaration declaration) {
		this.declaration = declaration;
		this.classMetrics = classMetrics;
		this.feature=feature;
	}

	public MethodDeclaration getDeclaration() {
		return this.declaration;
	}

	public void setDeclaration(MethodDeclaration declaration) {
		this.declaration = declaration;
	}

	public Feature getFeature(){
		return this.feature;
	}

	public void setFeature(Feature feature){
		this.feature=feature;
	}

	public ClassObject getClassParent() {
		return this.classMetrics.getClassObject();
	}

	public ClassMetrics getClassMetrics() {
		return this.classMetrics;
	}


}
