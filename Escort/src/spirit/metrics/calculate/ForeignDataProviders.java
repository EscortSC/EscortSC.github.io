/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.metrics.calculate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;


public class ForeignDataProviders implements IAttribute {
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

	public String getName() {
		return "FDP";
	}

	public void calculate(MethodMetrics node) {

		List listOfATFD = (List) node.getAttribute("ListOfATFD");
		ArrayList listFDP = new ArrayList();
		Iterator arg4 = listOfATFD.iterator();

		while (arg4.hasNext()) {
			String outBound=(String) arg4.next();
			String []str=outBound.split("\\(");
			String []str1=str[0].split("\\.");
			String mName="";
			for(int k=0;k<str1.length-1;k++){
				mName=mName+str1[k]+".";
			}
			mName=mName.substring(0,mName.length()-1);
			if (!listFDP.contains(mName)) {
				listFDP.add(mName);
			}
		}

		node.setMetric(this.getName(), (float) listFDP.size());

	}

}