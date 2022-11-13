package com.neu.sql.model;

import java.sql.Date;

public class Variation {
	
	private String compareversion;//�ԱȰ汾��
	private String classname; //ȫ����
	private String versionnnumber; //�汾��
	private String methodname; //������
	private String packagename; //����
	private String changestate;//�ı�״̬��4��������,5: �����
	private int evolutionpath; //�ݻ�·��
	private String majornum; // ��汾��
	private java.sql.Date releasetime; // ����ʱ��
	
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
