package com.neu.DataClumps;

import java.util.List;

import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.dom.MethodDeclaration;



public class DataClumpDetetion {
	private ClumpSpider spider = new ClumpSpider();
	
	public DataClumpDetetion() {
		
	}
	
	public double obviousness() {
		return 0.2D;
	}

	public ClumpSpider calculateComplexity(List<MethodDeclaration> ms) {
		this.spider.spiderFrom(ms);

		return this.spider;
	}

	public String getName() {
		return "Data Clumps";
	}

}
