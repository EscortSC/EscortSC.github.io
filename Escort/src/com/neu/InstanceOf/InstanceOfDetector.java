package com.neu.InstanceOf;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.neu.Detetors.ClassSmellRating;
import com.neu.Detetors.MethodSmellRating;
import com.neu.Detetors.SmellInstance;

public class InstanceOfDetector {

	private ClassInstanceOfRating ratings = new ClassInstanceOfRating();
	
	public double obviousness() {	return 0.945;		}
	
	public InstanceOfInstance calculateComplexity(List<MethodDeclaration> visibleMethods) {
		
		for (MethodDeclaration m : visibleMethods)
			ratings.cache(m);

		return new InstanceOfInstance(ratings,visibleMethods);
	}

	public String getName() {
		return "Instance Of";
	}
	
	public void showDetails() {
		//new FeatureEnvyExplanationWindow(currentSmell(),sourceViewer());
	}
}

class InstanceOfInstance extends ClassInstanceOfRating implements SmellInstance{
	
	private Collection<MethodDeclaration> visibleMethods;
	
	public InstanceOfInstance(ClassInstanceOfRating rating, Collection<MethodDeclaration> visibleMethods){
		super.rs = rating.rs;
		this.visibleMethods = visibleMethods;
	}

	public double calculateMagnitude() {
		int instOfExprCount = 0;
		for (MethodInstanceOfRating methodRating : ratings()) {
			instOfExprCount += methodRating.smells().size();
		}

		//we add 0.5 because 1 instanceof is worth pointing out
		//we multiply by 0.5 to extend the curve: about 7 or 8 
		//			instanceof expressions produces maximum wedge size
		return 0.5 * Math.log(instOfExprCount+0.5);
	}

	@Override
	protected Collection<MethodInstanceOfRating> ratings() {
		
		List<MethodInstanceOfRating> ratings = new LinkedList<MethodInstanceOfRating>();
		//collect values on key predicates
		for(Map.Entry<MethodDeclaration, MethodInstanceOfRating> r : rs.entrySet())
			if(visibleMethods.contains(r.getKey()))
				ratings.add(r.getValue());
		
		return ratings;
	}
}

class ClassInstanceOfRating extends ClassSmellRating<MethodInstanceOfRating, InstanceofExpression>{

	protected MethodInstanceOfRating createMethodRating(MethodDeclaration m) {
		return new MethodInstanceOfRating(m);
	}

	@Override
	public double calculateMagnitude() {
		return 0;
	}
}

class MethodInstanceOfRating extends MethodSmellRating<InstanceofExpression>{

	public MethodInstanceOfRating(MethodDeclaration m) {
		super(m);
	}

	protected ASTVisitor getVisitor() {
		return new ASTVisitor(){
			public boolean visit(InstanceofExpression expr){
				process(expr.resolveTypeBinding(),expr);
				return true;
			}
		};
	}
}