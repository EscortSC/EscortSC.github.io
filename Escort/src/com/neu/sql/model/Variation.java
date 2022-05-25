package com.neu.sql.model;

import java.sql.Date;

public class Variation {
	
	private String compareversion;//对比版本号
	private String classname; //全类名
	private String versionnnumber; //版本号
	private String methodname; //方法名
	private String packagename; //包名
	private String changestate;//改变状态（4：重命名,5: 拆包）
	private int evolutionpath; //演化路径
	private String majornum; // 大版本号
	private java.sql.Date releasetime; // 发布时间
	
	public Variation(String compareversion, String classname, String versionnnumber, String methodname,
			String packagename, String changestate, int evolutionpath, String majornum, Date releasetime) {
		
		this.compareversion = compareversion;
		this.classname = classname;
		this.versionnnumber = versionnnumber;
		this.methodname = methodname;
		this.packagename = packagename;
		this.changestate = changestate;
		this.evolutionpath = evolutionpath;
		this.majornum = majornum;
		this.releasetime = releasetime;
	}

	public Variation() {
		// TODO Auto-generated constructor stub
	}

	public String getCompareversion() {
		return compareversion;
	}

	public void setCompareversion(String compareversion) {
		this.compareversion = compareversion;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public String getVersionnnumber() {
		return versionnnumber;
	}

	public void setVersionnnumber(String versionnnumber) {
		this.versionnnumber = versionnnumber;
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

	public String getChangestate() {
		return changestate;
	}

	public void setChangestate(String changestate) {
		this.changestate = changestate;
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

	public java.sql.Date getReleasetime() {
		return releasetime;
	}

	public void setReleasetime(java.sql.Date releasetime) {
		this.releasetime = releasetime;
	}

	@Override
	public String toString() {
		return "Variation [compareversion=" + compareversion + ", classname=" + classname + ", versionnnumber="
				+ versionnnumber + ", methodname=" + methodname + ", packagename=" + packagename + ", changestate="
				+ changestate + ", evolutionpath=" + evolutionpath + ", majornum=" + majornum + ", releasetime="
				+ releasetime + "]";
	}

	
	
	
	
	
	
	

}
