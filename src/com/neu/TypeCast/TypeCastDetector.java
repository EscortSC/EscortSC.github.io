package com.neu.TypeCast;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.neu.SmellDetetors.ClassSmellRating;
import com.neu.SmellDetetors.MethodSmellRating;
import com.neu.SmellDetetors.SmellInstance;

//TODO: this class is more or lessa  copy of FeatureEnvyDetector
public class TypeCastDetector  {

	private ClassTypeCastRating ratings = new ClassTypeCastRating();

	

	public TypeCastDetector(){
	}
	public double obviousness() {
		return 0.94;
	}

	public String getName() {
		return "Typecast";
	}

	public void showDetails() {
		// new FeatureEnvyExplanationWindow(currentSmell(),sourceViewer());
	}

	public TypeCastInstance calculateComplexity(List<MethodDeclaration> visibleMethods) {
		// TODO Auto-generated method stub
		for (MethodDeclaration m : visibleMethods)
			ratings.cache(m);

		return new TypeCastInstance(ratings, visibleMethods);
	}
}

class TypeCastInstance extends ClassTypeCastRating implements SmellInstance {

	private Collection<MethodDeclaration> visibleMethods;

	public TypeCastInstance(ClassTypeCastRating rating, Collection<MethodDeclaration> visibleMethods) {
		super.rs = rating.rs;
		this.visibleMethods = visibleMethods;
	}

	public double calculateMagnitude() {
		int severity = 0;
		for (MethodTypeCastRating methodRating : ratings()) {
			severity += methodRating.classesReferenced().size();
		}

		return Math.log(severity) / (8 * Math.log(2));
	}

	@Override
	protected Collection<MethodTypeCastRating> ratings() {

		List<MethodTypeCastRating> ratings = new LinkedList<MethodTypeCastRating>();
		// collect values on key predicates
		for (Map.Entry<MethodDeclaration, MethodTypeCastRating> r : rs.entrySet())
			if (visibleMethods.contains(r.getKey()))
				ratings.add(r.getValue());

		return ratings;
	}

	
}

class ClassTypeCastRating extends ClassSmellRating<MethodTypeCastRating, CastExpression> {

	protected MethodTypeCastRating createMethodRating(MethodDeclaration m) {
		return new MethodTypeCastRating(m);
	}

	@Override
	public double calculateMagnitude() {
		return 0;
	}

}

class MethodTypeCastRating extends MethodSmellRating<CastExpression> {

	public MethodTypeCastRating(MethodDeclaration m) {
		super(m);
	}

	protected ASTVisitor getVisitor() {
		return new ASTVisitor() {
			public boolean visit(CastExpression expr) {
				process(expr.resolveTypeBinding(), expr);
				return true;
			}
		};
	}
}