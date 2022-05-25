package com.neu.MessageChains;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.tools.ant.taskdefs.Get;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ThisExpression;

import DataProcess.JdtAstUtil;
import DataProcess.ReadPath;

import com.neu.codeSmells.*;
import com.neu.invocation.matrics.GetAllMethods; 


public class Main {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ReadPath rp = new ReadPath("C:\\Fanyi\\dataset\\unzip\\cxf");
		ArrayList<String> paths = rp.getPath();
		
//		ArrayList<CompilationUnit> units = new ArrayList<>(); 
//		for (String path :paths) { 
//			 CompilationUnit com = JdtAstUtil.getCompilationUnit(path);
//			 units.add(com); 
//	    }
		LongParameterList LPL = new LongParameterList();
		ArrayList<String> classname = new ArrayList<>();
		ArrayList<String> methodname = new ArrayList<>();
		classname = LPL.getAllClassName(paths);
		methodname = LPL.searchLongParamenterListAntipattern(paths);
		
//		try { 
//             /* Ð´ÈëTxtÎÄ¼þ */  
//             File writename = new File("D:\\Source Data\\activemq file\\activemq-activemq-5.13.4.txt"); 
//             writename.createNewFile(); 
//             BufferedWriter out = new BufferedWriter(new FileWriter(writename)); 
//             for (int i = 0; i < classname.size(); i++) {
//            	 out.write(classname.get(i)+"\r\n"); 
//                 
//             }
//             out.flush(); // 
//             out.close(); // 
//         } catch (Exception e) {  
//             e.printStackTrace();
//
//       }
		try { 
            /* read txt file */  
			
            File writename = new File("C:\\Fanyi\\dataset\\file\\cxf"); 
            writename.createNewFile(); 
            BufferedWriter out = new BufferedWriter(new FileWriter(writename)); 
            for (int i = 0; i < methodname.size(); i++) {
            		out.write(methodname.get(i)+"\r\n");
            		System.out.println(i);
            }
            out.flush(); 
            out.close(); 
        } catch (Exception e) {  
            e.printStackTrace();

       }
		System.out.println(0);
	}
	
	
	

 }

