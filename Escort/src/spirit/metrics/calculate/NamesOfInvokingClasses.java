package spirit.metrics.calculate;

import java.util.ArrayList;
import java.util.List;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.Feature;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class NamesOfInvokingClasses  implements IAttribute{

	public String getName() {
		return "ListOfClassInvoking";
	}

	public void calculate(MethodMetrics node){
		ArrayList linvoking = new ArrayList();
		ArrayList lmethodinvoking = new ArrayList();
		Feature fe=node.getFeature();
		for(String inBound:fe.getInboundFeatureList()){
			if(inBound.contains("(")&&!lmethodinvoking.contains(inBound)){
				lmethodinvoking.add(inBound);
			}
			String []str=inBound.split("\\(");
			String []str1=str[0].split("\\.");
			String mName="";
			for(int k=0;k<str1.length-1;k++){
				mName=mName+str1[k]+".";
			}
			mName=mName.substring(0,mName.length()-1);
			if(!linvoking.contains(mName)){
				linvoking.add(mName);
			}
		}
		node.setAttribute(this.getName(), linvoking);
		node.setAttribute("ListOfMethodInvoking", lmethodinvoking);

	}
	@Override
	public void calculate(ClassMetrics node) {
		// TODO Auto-generated method stub
		ArrayList invoking = new ArrayList();
		List<MethodMetrics> methods=node.getMethodsMetrics();
		for(MethodMetrics method:methods){
			List linvoking=(List) method.getAttribute(this.getName());
			for(Object inv:linvoking){
				if(!invoking.contains(inv)){
					invoking.add(inv);
				}
			}
		}
		node.setAttribute(this.getName(), invoking);


	}

}
