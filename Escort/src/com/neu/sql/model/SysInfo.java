package com.neu.sql.model;

import java.sql.Date;

public class SysInfo {
	
	private String versionnnumber; //�汾��
	private String classname; //ȫ����
	private String methodname; //������
	private String packagename; //����
	private int evolutionpath; //�ݻ�·��
	private String majornum; // ��汾��
	private String projectname; //��Ŀ��
	
	
	
	
	public SysInfo(String versionnnumber, String classname, String methodname, String packagename,
			int evolutionpath, String majornum, String projectname) {
		super();
		this.versionnnumber = versionnnumber;
		this.classname = classname;
		this.methodname = methodname;
		this.packagename = packagename;
		this.evolutionpath = evolutionpath;
		this.majornum = majornum;
		this.projectname = projectname;
		
	}
	
	
	public SysInfo() {
		
		// TODO Auto-generated constructor stub
	}


	public String getVersionnnumber() {
		return versionnnumber;
	}
	public void setVersionnnumber(String versionnnumber) {
		this.versionnnumber = versionnnumber;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
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
	public String getProjectname() {
		return projectname;
	}
	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}


	@Override
	public String toString() {
		return "SysInfo [versionnnumber=" + versionnnumber + ", classname=" + classname + ", methodname=" + methodname
				+ ", packagename=" + packagename + ", evolutionpath=" + evolutionpath + ", majornum=" + majornum
				+ ", projectname=" + projectname + "]";
	}
	


	

	
	
	

}
