package spirit.metrics.calculate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodInvocation;

import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class CouplingIntensity implements IAttribute{

	@Override
	public void calculate(ClassMetrics node) {
		// TODO Auto-generated method stub


	}

	@Override
	public void calculate(MethodMetrics node) {
		// TODO Auto-generated method stub
			List ListOfMethodInvoked = (List) node
					.getAttribute("ListOfMethodInvoked");
			ArrayList distinctMethods = new ArrayList();
			if(node.getAttribute("ListOfMethodInvoked")!=null){
				Iterator arg4 = ListOfMethodInvoked.iterator();

				while (arg4.hasNext()) {
					String method = (String) arg4.next();
					if ( !this.definedInSuperClass(node, method)	) {
						distinctMethods.add(method);
					}
				}
				node.setMetric(this.getName(), (float) distinctMethods.size());	
			}
			
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "CINT";
	}

	private boolean definedInSuperClass(MethodMetrics node,
			String method) {
		List superClasses = (List) node.getClassMetrics().getAttribute("SC");
		String []str=method.split("\\(");
		String []str1=str[0].split("\\.");
		String mName="";
		for(int k=0;k<str1.length-1;k++){
			mName=mName+str1[k]+".";
		}
		mName=mName.substring(0,mName.length()-1);
		if(superClasses.contains(mName)) return true;
		return false;



	}

}
