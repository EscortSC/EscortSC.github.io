package spirit.metrics.storage;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.Refactor.classparser.ClassObject;

public class ClassMetrics extends NodeMetrics{
	private ClassObject classObject;
	private TypeDeclaration declaration;
	private List<MethodMetrics> methodsMetrics;

	public ClassMetrics(TypeDeclaration declaration,ClassObject classObject) {
		this.classObject=classObject;
		this.declaration = declaration;
		this.methodsMetrics = new ArrayList();
	}

	public ClassObject getClassObject(){
		return this.classObject;
	}

	public void setClassObject(ClassObject classObject){
		this.classObject=classObject;
	}

	public TypeDeclaration getDeclaration() {
		return this.declaration;
	}

	public void setDeclaration(TypeDeclaration declaration) {
		this.declaration = declaration;
	}

	public List<MethodMetrics> getMethodsMetrics() {
		return this.methodsMetrics;
	}

	public void setMethodsMetrics(List<MethodMetrics> methodsMetrics) {
		this.methodsMetrics = methodsMetrics;
	}


}
