/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.analizer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import spirit.metrics.calculate.AccessToData;
import spirit.metrics.calculate.AverageMethodWeight;
import spirit.metrics.calculate.BaseClassUsageRatio;
import spirit.metrics.calculate.ChangingClasses;
import spirit.metrics.calculate.ChangingMethods;
import spirit.metrics.calculate.CouplingDispersion;
import spirit.metrics.calculate.CouplingIntensity;
import spirit.metrics.calculate.ForeignDataProviders;
import spirit.metrics.calculate.IAttribute;
import spirit.metrics.calculate.LocalityOfAttributesAccesses;
import spirit.metrics.calculate.MaximumNestingLevel;
import spirit.metrics.calculate.McCabe;
import spirit.metrics.calculate.NameOfFields;
import spirit.metrics.calculate.NamesOfInvokedClasses;
import spirit.metrics.calculate.NamesOfInvokingClasses;
import spirit.metrics.calculate.NumOverrideMethods;
import spirit.metrics.calculate.NumProtMembersInParent;
import spirit.metrics.calculate.NumberOfAccessorMethods;
import spirit.metrics.calculate.NumberOfAddedServices;
import spirit.metrics.calculate.NumberOfFields;
import spirit.metrics.calculate.NumberOfLinesOfCode;
import spirit.metrics.calculate.NumberOfMethods;
import spirit.metrics.calculate.NumberOfPublicFields;
import spirit.metrics.calculate.NumberOfPublicMethods;
import spirit.metrics.calculate.PercentageOfAddedServices;
import spirit.metrics.calculate.SuperClassHierarchy;
import spirit.metrics.calculate.TightClassCohesion;
import spirit.metrics.calculate.WeightOfClass;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;
import com.neu.calculate.EffectiveClasses;
import com.neu.calculate.PackageObject;
import com.neu.invocation.matrics.GetAllMethods;

import DataProcess.ReadPath;



public class SpiritVisitor extends ASTVisitor {
	private  List<ClassObject> classesArrList=SaveFileAction.classesArrList;
	private String classFullName;
	public static HashMap<TypeDeclaration, ClassMetrics> classesMetrics = new HashMap();
	private PackageObject po=new PackageObject();


	List<IAttribute> metricForClasses = new ArrayList();
	List<IAttribute> metricForMethods = new ArrayList();
	List<IAttribute> couplingMetrics = new ArrayList();
	List<IAttribute> changingMetrics = new ArrayList();
	List<IAttribute> invokingAttributes = new ArrayList();
	List<String> nameOfClasses = new ArrayList();
	

	public SpiritVisitor() {
		this.metricForClasses.add(new NameOfFields());
		this.metricForClasses.add(new McCabe());
		this.metricForClasses.add(new NumberOfMethods());
		this.metricForClasses.add(new NumberOfFields());
		this.metricForClasses.add(new AccessToData());
		this.metricForClasses.add(new TightClassCohesion());
		this.metricForClasses.add(new NumberOfLinesOfCode());
		this.metricForClasses.add(new NumberOfPublicFields());
		this.metricForClasses.add(new NumberOfPublicMethods());
		this.metricForClasses.add(new WeightOfClass());
		this.metricForClasses.add(new NumberOfAccessorMethods());
		this.metricForClasses.add(new AverageMethodWeight());
		this.metricForClasses
				.add(new NumProtMembersInParent(this.nameOfClasses));
		this.metricForClasses.add(new NumOverrideMethods(this.nameOfClasses));
		this.metricForClasses.add(new BaseClassUsageRatio());
		this.metricForClasses.add(new NumberOfAddedServices());
		this.metricForClasses.add(new PercentageOfAddedServices());
		this.metricForMethods.add(new NameOfFields());
		this.metricForMethods.add(new McCabe());
		this.metricForMethods.add(new AccessToData());
		this.metricForMethods.add(new NumberOfFields());
		this.metricForMethods.add(new TightClassCohesion());
		this.metricForMethods.add(new NumberOfLinesOfCode());
		this.metricForMethods.add(new MaximumNestingLevel());
		this.metricForMethods.add(new LocalityOfAttributesAccesses());
		this.metricForMethods.add(new ForeignDataProviders());
		this.couplingMetrics.add(new CouplingIntensity());
		this.couplingMetrics.add(new CouplingDispersion());
		this.invokingAttributes.add(new NamesOfInvokedClasses(
				this.nameOfClasses));
		this.invokingAttributes.add(new NamesOfInvokingClasses());
		this.changingMetrics.add(new ChangingClasses());
		this.changingMetrics.add(new ChangingMethods());
	}

	public boolean visit(TypeDeclaration clazz) {
		clazz.getParent().accept(po);
	    classFullName=po.getPackageName()+"."+clazz.getName();
		for( int i=0;i<this.classesArrList.size();i++){
			if(classesArrList.get(i).getName().equals(classFullName)){
				this.nameOfClasses.add(clazz.resolveBinding().getBinaryName());
				ClassMetrics classMetrics=new ClassMetrics(clazz,this.classesArrList.get(i));
				this.classesMetrics.put(clazz, classMetrics);
				break;
			}
		}
		return super.visit(clazz);
	}


	public void executeMetrics() {
		Iterator arg1 = this.classesMetrics.keySet().iterator();
		while (arg1.hasNext()) {
			TypeDeclaration clazz = (TypeDeclaration) arg1.next();
			clazz.getParent().accept(po);
			classFullName=po.getPackageName()+"."+clazz.getName().toString();
			ClassMetrics classMetrics = (ClassMetrics) this.classesMetrics
					.get(clazz);
			classMetrics.setAttribute("nameOfClasses", this.nameOfClasses);
			SuperClassHierarchy superClasses = new SuperClassHierarchy(
					this.nameOfClasses);
			superClasses.calculate(classMetrics);
			MethodDeclaration[] methods = clazz.getMethods();
			ClassObject classObject=classMetrics.getClassObject();
			List<Feature> features=classObject.getFeatureList();
			MethodDeclaration[] arg8 = methods;
			int arg7 = methods.length;

			for (int arg6 = 0; arg6 < arg7; ++arg6) {
				MethodDeclaration metric = arg8[arg6];
				 Iterator it=metric.parameters().iterator();
	    		 String Parameters="(";
	    		 while(it.hasNext()){
	    			 String name=it.next().toString();
					String[] str = name.split(" ");
	    			 Parameters=Parameters+str[0]+",";
	    		 }
	    		 if(Parameters.length()>1) Parameters=Parameters.substring(0,Parameters.length()-1);
	    		 Parameters=Parameters+")";
	    		 String declarationMName=this.classFullName+"."+metric.getName()+Parameters;

				for(Feature fe:features){
					if(fe.getName().contains("(")&&fe.getName().contains(")")){
						String paramenters="";
						String str1[]=fe.getName().split("\\(");
						String str2[]=str1[1].split("\\)");
						if(str2.length>0){
							String str3[]=str2[0].split("\\, ");
							for(int k=0;k<str3.length;k++){
								String str4[]=str3[k].split("\\.");
								paramenters=paramenters+str4[str4.length-1]+",";
							}
							paramenters=paramenters.substring(0,paramenters.length()-1);
						}
						String methodNames=str1[0]+"("+paramenters+")";
						if(methodNames.equals(declarationMName)){
							MethodMetrics methodMetrics = new MethodMetrics(fe,classMetrics,metric);
							Iterator arg11 = this.metricForMethods.iterator();

							while (arg11.hasNext()) {
								IAttribute metric1 = (IAttribute) arg11.next();
								metric1.calculate(methodMetrics);
							}
						    classMetrics.getMethodsMetrics().add(methodMetrics);
							 break;
							}
						}
				}

			}
			Iterator arg13 = this.metricForClasses.iterator();
			while (arg13.hasNext()) {
				IAttribute arg12 = (IAttribute) arg13.next();
				arg12.calculate(classMetrics);
			}
		}

	}


	public List<ClassMetrics> getLClassesMetrics() {
		ArrayList lclassMetrics = new ArrayList();
		Iterator arg2 = this.classesMetrics.keySet().iterator();

		while (arg2.hasNext()) {
			TypeDeclaration clazz = (TypeDeclaration) arg2.next();
			lclassMetrics.add((ClassMetrics) this.classesMetrics.get(clazz));
		}

		return lclassMetrics;
	}

	private void executeInvokingAttributes() {
		Iterator arg1 = this.invokingAttributes.iterator();

		while (arg1.hasNext()) {
			IAttribute invokingAttribute = (IAttribute) arg1.next();
			Iterator arg3 = this.getLClassesMetrics().iterator();

			while (arg3.hasNext()) {
				ClassMetrics classMetrics = (ClassMetrics) arg3.next();
				Iterator arg5 = classMetrics.getMethodsMetrics().iterator();

				while (arg5.hasNext()) {
					MethodMetrics methodMetric = (MethodMetrics) arg5.next();
					invokingAttribute.calculate(methodMetric);
				}

				invokingAttribute.calculate(classMetrics);
			}
		}

	}

	public void calculateAditionalMetrics() {
		Iterator arg1 = this.couplingMetrics.iterator();

		IAttribute changingMetric;
		ClassMetrics classMetric;
		Iterator arg3;
		MethodMetrics methodMetric;
		Iterator arg5;
		while (arg1.hasNext()) {
			changingMetric = (IAttribute) arg1.next();
			arg3 = this.getLClassesMetrics().iterator();

			while (arg3.hasNext()) {
				classMetric = (ClassMetrics) arg3.next();
				arg5 = classMetric.getMethodsMetrics().iterator();

				while (arg5.hasNext()) {
					methodMetric = (MethodMetrics) arg5.next();
					changingMetric.calculate(methodMetric);
				}

				changingMetric.calculate(classMetric);
			}
		}

		this.executeInvokingAttributes();
		arg1 = this.changingMetrics.iterator();

		while (arg1.hasNext()) {
			changingMetric = (IAttribute) arg1.next();
			arg3 = this.getLClassesMetrics().iterator();

			while (arg3.hasNext()) {
				classMetric = (ClassMetrics) arg3.next();
				arg5 = classMetric.getMethodsMetrics().iterator();

				while (arg5.hasNext()) {
					methodMetric = (MethodMetrics) arg5.next();
					changingMetric.calculate(methodMetric);
				}

				changingMetric.calculate(classMetric);
			}
		}
	}
}