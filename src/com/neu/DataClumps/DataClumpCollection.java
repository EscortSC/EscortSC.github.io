package com.neu.DataClumps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.MethodDeclaration;


public class DataClumpCollection {
	private Map<ClumpSignature, ClumpGroup> clumps = new HashMap();

	public void addClump(MethodDeclaration m) throws JavaModelException {
		Iterator it=m.parameters().iterator();
		List<String> names= new ArrayList<String>();
		while(it.hasNext()) {
			String parameter=it.next().toString();
			int point=parameter.lastIndexOf(" ");
		//	System.out.println(parameter.substring(0,point));
			names.add(parameter.substring(0,point));
		}
	    List<ClumpSignature> sigs = ClumpSignature.from(names);
		for (ClumpSignature sig : sigs) {
			ClumpGroup existingGroup = (ClumpGroup) this.clumps.get(sig);
			if (existingGroup == null) {
				 existingGroup = new ClumpGroup(sig);
				 existingGroup .add(m);
			} else {
				existingGroup.add(m);
			}
			this.clumps.put(sig, existingGroup);
		}
	}

	public List<ClumpGroup> inGroupOf(MethodDeclaration method) {
		List<ClumpSignature> sigs = ClumpSignature.from(method.parameters());
		List groups = new LinkedList();
		for (ClumpSignature sig : sigs) {
			groups.add((ClumpGroup) this.clumps.get(sig));
		}
		return groups;
		
	}
	
	public Map<ClumpSignature,ClumpGroup> getClumps(){
		return this.clumps;
	}

}
