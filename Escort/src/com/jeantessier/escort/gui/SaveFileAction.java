/*
 *  Copyright (c) 2001-2009, Jean Tessier
 *  All rights reserved.
 *  
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *  
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *  
 *      * Neither the name of Jean Tessier nor the names of his contributors
 *        may be used to endorse or promote products derived from this software
 *        without specific prior written permission.
 *  
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 *  "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 *  LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 *  A PARTICULAR PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE REGENTS OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 *  PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 *  LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.jeantessier.escort.gui;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

import com.Refactor.classparser.ClassObject;
import com.Refactor.classparser.filename;
import com.neu.invocation.matrics.GetAllMethods;
import com.neu.invocation.matrics.ThreeEntityList;
import com.neu.invocation.matrics.ThreeEntityList_CC;
import com.neu.invocation.matrics.ThreeEntityList_CM;
import com.neu.invocation.matrics.ThreeEntityList_MC;
import com.neu.invocation.matrics.ThreeEntityList_MM;
import com.neu.print.PrintSmells;

public class SaveFileAction extends AbstractAction implements Runnable {
	private DependencyFinder model;
	private String encoding;
	private String dtdPrefix;

	private String indentText;
	private File file;
	public static List<ClassObject> classesArrList = new ArrayList();// 创建用来保护class信息的集合
	public static ArrayList<String> classname = new ArrayList<String>();
	public static String sourcepath;
	public static String relativePath;

	public SaveFileAction(DependencyFinder model, String encoding, String dtdPrefix) {
		this.model = model;
		this.encoding = encoding;
		this.dtdPrefix = dtdPrefix;

		putValue(Action.LONG_DESCRIPTION, "Save current graph to XML file");
		putValue(Action.NAME, "Save");
		putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("icons/save.gif")));
	}

	public String getIndentText() {
		return indentText;
	}

	public void setIndentText(String indentText) {
		this.indentText = indentText;
	}

	public void actionPerformed(ActionEvent e) {
		String rootPath = "Temp.xml";
		file = new File(rootPath);
		if (file.exists()) {
			file.delete();
		}
		file = new File(rootPath);
		new Thread(this).start();
	}

	public void Tempvarible() {// wangying
		sourcepath = "D:\\smell\\Project\\Struts";
		relativePath = "Struts\\Struts-2.5.14";
		String sourcepath2 = sourcepath + "\\";
		classname=filename.getallclassnames(sourcepath2);
	   // getallclassnames()函数实现的输出
	}


	public void run() {
		try {
			model.getStatusLine().showInfo("Saving " + file.getName() + " ...");

			PrintWriter out1 = new PrintWriter(new FileWriter(file));
			com.jeantessier.dependency.Printer printer = new com.jeantessier.dependency.XMLPrinter(out1, encoding,
					dtdPrefix);
			if (indentText != null) {
				printer.setIndentText(indentText);
			}
			Tempvarible();
			printer.traverseNodes(model.getPackages());

			/*for (int k = 0; k < classesArrList.size(); k++) {
				SaveFileAction.classname.add(SaveFileAction.classesArrList.get(k).getName());
			}*/

			System.out.println("classname " + SaveFileAction.classname.size());
			System.out.println("classesArrList " + SaveFileAction.classesArrList.size());
			ArrayList<String> cNames = new ArrayList<String>();
			cNames = (ArrayList<String>) this.classname.clone();
			for (String cn : cNames) {
				boolean found = false;
				for (ClassObject co : classesArrList) {
					if (cn.equals(co.getName())) {
						found = true;
						break;
					}
				}
				if (!found) {
					classname.remove(cn);
				}
			}
			GetAllMethods gam = new GetAllMethods();
			System.out.println("methods "+gam.allMethodNames.size());
			/*ThreeEntityList l1 = new ThreeEntityList_CC(); 
			ThreeEntityList l2 = new ThreeEntityList_CM(); 
			ThreeEntityList l3 = new ThreeEntityList_MC();
		    ThreeEntityList l4 = new ThreeEntityList_MM();*/
			

			
		    /* PrintCMList pcml = new PrintCMList();
			  ClassMatrics cm = new ClassMatrics(); 
			 * MClassMatrics mc=new MClassMatrics();
			 */

			PrintSmells ps = new PrintSmells();
			// GetInheritEdges gie=new GetInheritEdges(relativePath);*/

			out1.close();
			model.getStatusLine().showInfo("Saved " + file.getName());
			SaveFileAction.classname.clear();
			System.out.println("===============");

		} catch (IOException ex) {
			model.getStatusLine().showError("Cannot save: " + ex.getClass().getName() + ": " + ex.getMessage());
		}

	}
}
