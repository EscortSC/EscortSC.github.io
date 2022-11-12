package com.Refactor.NonInheritance;

import java.io.File;
import java.util.ArrayList;

public class readPath {
	private  ArrayList<String> paths=new ArrayList<String>();
	public   readPath(String path){
	        ArrayList<String> listFileName = new ArrayList<String>();
	        getAllFileName(path,listFileName);
	    }
	  public ArrayList<String> getPath(){
		  return this.paths;
	  }
	    private void getAllFileName(String path,ArrayList<String> fileName)
	    {
	        File file = new File(path);
	        File [] files = file.listFiles();
	        for(File a:files)
	        {
	            if(a.isDirectory())
	            {
	                getAllFileName(a.getAbsolutePath(),fileName);
	            }
	            else if(!a.isDirectory()&&a.getName()!=null&&a.getName().endsWith(".java")){
	            	paths.add(a.getAbsolutePath());
	            	fileName.add(a.getName());
	            }
	        }
	    }
}

