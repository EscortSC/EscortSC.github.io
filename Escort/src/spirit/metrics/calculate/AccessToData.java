package spirit.metrics.calculate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;
import com.jeantessier.escort.gui.SaveFileAction;
import com.neu.calculate.EffectiveClasses;
import com.neu.calculate.PackageObject;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class AccessToData implements IAttribute {
	    private List<ClassObject> classesArrlist=SaveFileAction.classesArrList;
		private static final String GET = "get";
		private static final String SET = "set";
		private List<String> ListOfATFD = new ArrayList();
		private List<String> ListOfATLD = new ArrayList();
		private List<String> ListOfClassInvoked = new ArrayList();
		private List<String> ListOfForeignMethodInvoked = new ArrayList();
		private List<String> ListOfLocalMethodInvoked = new ArrayList();
		private List<String> ListOfLocalFieldUsed = new ArrayList();
		private String nameOfParentClass;
		private List<String> namesOfSuperClasses;

		public void calculate(ClassMetrics node) {
			float sumOfATFD = 0.0F;
			MethodMetrics methodMetrics;
			for (Iterator arg3 = node.getMethodsMetrics().iterator(); arg3
					.hasNext(); sumOfATFD += methodMetrics
					.getMetric(this.getName()).floatValue()) {
				methodMetrics = (MethodMetrics) arg3.next();
			}

			node.setMetric(this.getName(), sumOfATFD);
		}

		@Override
		public void calculate(MethodMetrics node) {
			// TODO Auto-generated method stub
			this.ListOfATFD = new ArrayList();
			this.ListOfATLD = new ArrayList();
			this.ListOfClassInvoked = new ArrayList();
			this.ListOfForeignMethodInvoked = new ArrayList();
			this.ListOfLocalMethodInvoked = new ArrayList();
			this.ListOfLocalFieldUsed = new ArrayList();
			this.nameOfParentClass =node.getClassParent().getName();
		    this.namesOfSuperClasses = (List) node.getClassMetrics().getAttribute(
					"SC");
			this.calculation(node.getFeature());
			node.setMetric(this.getName(), (float) this.ListOfATFD.size());
			node.setAttribute("ListOfATFD", this.ListOfATFD);
			node.setMetric("ATLD", (float) this.ListOfATLD.size());
			node.setAttribute("ListOfATLD", this.ListOfATLD);
			node.setAttribute("ListOfClassInvoked", this.ListOfClassInvoked);
			node.setAttribute("ListOfMethodInvoked",
					this.ListOfForeignMethodInvoked);
			node.setAttribute("ListOfLocalFieldUsed", this.ListOfLocalFieldUsed);
			node.setAttribute("ListOfLocalMethodInvoked",
					this.ListOfLocalMethodInvoked);

		}

		public String getName() {
			return "ATFD";
		}

		public void calculation(Feature feature){
			for(String outBound:feature.getOutboundFeatureList()){
				String []str=outBound.split("\\(");
				String []str1=str[0].split("\\.");
				String mName="";
				for(int k=0;k<str1.length-1;k++){
					mName=mName+str1[k]+".";
				}
				mName=mName.substring(0,mName.length()-1);//className
				String shortName=outBound.substring(mName.length()+1, outBound.length());//method or field name
				if(this.isForeignClass(mName)){
					if (!this.ListOfClassInvoked.contains(mName)) {
						this.ListOfClassInvoked.add(mName);
					}
					if(shortName.contains("(")){
						if(!this.ListOfForeignMethodInvoked.contains(outBound)){
							this.ListOfForeignMethodInvoked.add(outBound);
						}
						if(shortName.startsWith(GET)||shortName.startsWith(SET)){
							this.ListOfATFD.add(outBound);
						}
					}
					else	{
						this.ListOfATFD.add(outBound);
						}
				}
				else{
					if(shortName.contains("(")){
						if(!this.ListOfLocalMethodInvoked.contains(shortName)){
							this.ListOfLocalMethodInvoked.add(shortName);
						}
						if (shortName.startsWith("get")
								|| shortName.startsWith("set")) {
							this.ListOfATLD.add(outBound);
						}
					}
					else{
						if(!this.ListOfLocalFieldUsed.contains(shortName)){
							this.ListOfLocalFieldUsed.add(shortName);
						}
						this.ListOfATLD.add(outBound);

					}
				}
			}
		}

		public boolean isForeignClass(String nameOfClass){
			if(nameOfClass!=null&&(this.nameOfParentClass!=null)&&(this.namesOfSuperClasses!=null)){
				if(!nameOfClass.equals(this.nameOfParentClass) && !this.namesOfSuperClasses.contains(nameOfClass))
					return true;
			}
			 return false;

		}


}
