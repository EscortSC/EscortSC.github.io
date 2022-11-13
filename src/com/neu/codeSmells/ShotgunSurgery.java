package com.neu.codeSmells;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.neu.codeSmell.calculate.ClaObject;
import com.neu.codeSmell.calculate.MethodInvoking;
import com.neu.codeSmell.calculate.PackageObject;

import DataProcess.JdtAstUtil;

public class ShotgunSurgery {
	private ArrayList<String> candidateShotgunSurgeries;
	private ArrayList<MethodInvocation>InvokingMethods;
	private ArrayList<TypeDeclaration> allTypes;


	public ShotgunSurgery(){
		candidateShotgunSurgeries=new ArrayList<String>();
		allTypes=new ArrayList<TypeDeclaration>();
	}

	public ArrayList<String> searchShotgunSurgery(ArrayList<String> paths){
		for(String path: paths){
			CompilationUnit comp=JdtAstUtil.getCompilationUnit(path);
			ClaObject co=new ClaObject();
			comp.accept(co);
			if(co.getType()!=null){
				TypeDeclaration type=co.getType();
				PackageObject po=new PackageObject();
				 type.getParent().accept(po);
				 String className=po.getPackageName()+"."+type.getName();
				 allTypes.add(co.getType());
					ArrayList<MethodDeclaration> methods=co.getMethods();
					for(MethodDeclaration method:methods){
						if(this.isNonConstructorAndNonStatic(method)&&(this.getNumOfInvokingClasses(method)>=4)&&
								this.InvokingMethods.size()>=7){
							candidateShotgunSurgeries.add(className+";"+this.InvokingMethods.size());
						}
					}
			}
		}
		return this.candidateShotgunSurgeries;
	}

	private boolean isNonConstructorAndNonStatic(MethodDeclaration method) {
		List modifiers = method.modifiers();
		boolean isStatic = false;
		Iterator arg4 = modifiers.iterator();

		while (arg4.hasNext()) {
			IExtendedModifier modifier = (IExtendedModifier) arg4.next();
			if (modifier.isModifier() && ((Modifier) modifier).isStatic()) {
				isStatic = true;
			}
		}

		if (!method.isConstructor() && !isStatic) {
			return true;
		} else {
			return false;
		}
	}

	private int getNumOfInvokingClasses(MethodDeclaration method){
		ArrayList<TypeDeclaration> InvokingClasses=new ArrayList<TypeDeclaration>();
		MethodInvoking mi=new MethodInvoking();
		method.accept(mi);
		InvokingMethods=mi.getList();
		for(MethodInvocation InvokingMethod:InvokingMethods){
			System.out.println(InvokingMethod.getExpression());
			ClaObject co=new ClaObject();
			InvokingMethod.accept(co);
			TypeDeclaration type=co.getType();
			boolean found=false;
			for(TypeDeclaration ty:InvokingClasses){
				if(ty.equals(type)) {
					found=true;
					break;
				}
			}
			if(!found)InvokingClasses.add(type);

		}
		return  InvokingClasses.size();
	}



}
