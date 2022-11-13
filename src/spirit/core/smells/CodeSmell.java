/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.PackageObject;

import spirit.core.design.DesignFlaw;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.NodeMetrics;

public abstract class CodeSmell extends DesignFlaw {
	private String kindOfSmellName;
	protected ASTNode element;
	protected NodeMetrics node;


	public CodeSmell(String kindOfSmellName) {
		this.kindOfSmellName = kindOfSmellName;
	}

	public ASTNode getElement() {
		return this.element;
	}

	public NodeMetrics getNode(){
		return this.node;
	}

	public String getKindOfSmellName() {
		return this.kindOfSmellName;
	}

	public String getMainClassName() {
		return this.getMainClass().resolveBinding().getQualifiedName();
	}

	public abstract TypeDeclaration getMainClass();

	public abstract Set<String> getAffectedClasses();

	public String getElementName() {
		if(this.element instanceof TypeDeclaration) {
			PackageObject vn=new PackageObject();
			this.element.getParent().accept(vn);
			String fullName=vn.getPackageName()+"."+((TypeDeclaration) this.element).getName().getFullyQualifiedName();
			return fullName;
		}
		else if(this.element instanceof MethodDeclaration){
			TypeDeclaration	td=(TypeDeclaration) ((MethodDeclaration) this.element).getParent();
		  	PackageObject vn=new PackageObject();
		  	td.getParent().accept(vn);
		  	 Iterator it=((MethodDeclaration) element).parameters().iterator();
    		 String parameters="(";
    		 while(it.hasNext()){
    			 String name=it.next().toString();
    			 String []str=name.split(" ");
    			 parameters=parameters+str[0]+",";
    		 }
    		 if(parameters.length()>1) parameters=parameters.substring(0,parameters.length()-1);
    		 parameters=parameters+")";
		    String fullName=vn.getPackageName()+"."+td.getName().toString()+"."
		  	+((MethodDeclaration) this.element).getName().toString()+parameters;

		    return fullName;
			}
		else return this.element.toString();
	}

	public int getLine() {
		IJavaElement javaElement = this.getMainClass().resolveBinding()
				.getJavaElement();
		ICompilationUnit cu = (ICompilationUnit) javaElement.getAncestor(5);
		return this.getLineNumFromOffset(cu, this.element.getStartPosition());
	}

	private int getLineNumFromOffset(ICompilationUnit cUnit, int offSet) {
		try {
			String source = cUnit.getSource();
			IType type = cUnit.findPrimaryType();
			if (type != null) {
				String sourcetodeclaration = source.substring(0, offSet);
				int lines = 0;
				char[] chars = new char[sourcetodeclaration.length()];
				sourcetodeclaration.getChars(0, sourcetodeclaration.length(),
						chars, 0);

				for (int i = 0; i < chars.length; ++i) {
					if (chars[i] == 10) {
						++lines;
					}
				}

				return lines + 1;
			}
		} catch (JavaModelException arg8) {
			;
		}

		return 0;
	}
}