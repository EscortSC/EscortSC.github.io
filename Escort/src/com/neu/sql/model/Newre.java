package com.neu.sql.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Newre {
	
	private String projectname; //项目名
	private String classname; //全类名
	private String movebefore;//移动前
	private String methodname; //方法名
	private String packagename; //包名
	private int frequency; // 出现的次数
	private java.math.BigDecimal weight; //节点位置的权重
	private int evolutionpath; //演化路径
	private String majornum; // 大版本号
	private String changestate;//改变状态
	private String changeversion; // 改变版本
	
	
	public Newre(String projectname, String classname, String movebefore,String methodname, String packagename, int frequency,
			BigDecimal weight, int evolutionpath, String majornum, String changestate, String changeversion) {
		super();
		this.projectname = projectname;
		this.classname = classname;
		this.movebefore =movebefore;
		this.methodname = methodname;
		this.packagename = packagename;
		this.frequency = frequency;
		this.weight = weight;
		this.evolutionpath = evolutionpath;
		this.majornum = majornum;
		this.changestate = changestate;
		this.changeversion = changeversion;
	}


	public Newre() {
		
		// TODO Auto-generated constructor stub
	}


	public String getProjectname() {
		return projectname;
	}


	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}


	public String getClassname() {
		return classname;
	}


	public void setClassname(String classname) {
		this.classname = classname;
	}


	public String getMovebefore() {
		return movebefore;
	}


	public void setMovebefore(String movebefore) {
		this.movebefore = movebefore;
	}


	public String getMethodname() {
		return methodname;
	}


	public void setMethodname(String methodname) {
		this.methodname = methodname;
	}


	public String getPackagename() {
		return packagename;
	}


	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}


	public int getFrequency() {
		return frequency;
	}


	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}


	public java.math.BigDecimal getWeight() {
		return weight;
	}


	public void setWeight(java.math.BigDecimal weight) {
		this.weight = weight;
	}


	public int getEvolutionpath() {
		return evolutionpath;
	}


	public void setEvolutionpath(int evolutionpath) {
		this.evolutionpath = evolutionpath;
	}


	public String getMajornum() {
		return majornum;
	}


	public void setMajornum(String majornum) {
		this.majornum = majornum;
	}


	public String getChangestate() {
		return changestate;
	}


	public void setChangestate(String changestate) {
		this.changestate = changestate;
	}


	


	public String getChangeversion() {
		return changeversion;
	}


	public void setChangeversion(String changeversion) {
		this.changeversion = changeversion;
	}


	@Override
	public String toString() {
		return "Newre [projectname=" + projectname + ", classname=" + classname + ", movebefore=" + movebefore
				+ ", methodname=" + methodname + ", packagename=" + packagename + ", frequency=" + frequency
				+ ", weight=" + weight + ", evolutionpath=" + evolutionpath + ", majornum=" + majornum
				+ ", changestate=" + changestate + ", changeversion=" + changeversion + "]";
	}


	




	
	
	
	
	
	
	

}
