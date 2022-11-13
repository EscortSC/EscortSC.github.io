package com.neu.print;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jdt.core.dom.CompilationUnit;

import com.jeantessier.escort.gui.SaveFileAction;
import com.neu.DataClumps.newer.DataClumpsDetector;
import com.neu.InstanceOf.InstanceOfTest;
import com.neu.codeSmells.LongParameterList;
import com.neu.codeSmells.MessageChain;
import com.neu.codeSmells.MiddleMan;
import com.neu.codeSmells.SpaghettiCode;

import DataProcess.JdtAstUtil;
import DataProcess.ReadPath;
import spirit.core.debt.CodeSmell;
import spirit.core.design.DesignFlawManager;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class PrintSmells {

	private ReadPath rp;
	private String relativePath;

	public PrintSmells() {
		try {
			rp = new ReadPath(SaveFileAction.sourcepath);
			relativePath = SaveFileAction.relativePath;
			/*DesignFlawManager dfm = new DesignFlawManager();
			dfm.calculateMetricsCode();
			dfm.calculateAditionalMetrics();
			dfm.detectCodeSmells("D:\\workspace\\HSQLDB");*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		// DesignFlawManager dfm = this.printSmell_1();

		// this.printSmell_2();
		 this.printSmell_3();
	}
	
	public PrintSmells(String path,String relativePath) {
		 rp = new ReadPath(path);
		 this.relativePath=relativePath;
		 this.printSmell_2();
		 //this.printSmell_3();
	}

	private DesignFlawManager printSmell_1() {

		DesignFlawManager dfm = new DesignFlawManager();

		try {
			File f = new File("D:\\smell\\output\\" + relativePath + "\\smellDetail.txt");
			if (!f.exists()) {
				f.createNewFile();
			} else {
				System.out.println("file exist");
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
			PrintSpiritSmellDetail psd = new PrintSpiritSmellDetail();
			dfm.calculateMetricsCode();
			dfm.calculateAditionalMetrics();
			dfm.detectCodeSmells("D:\\workspace\\HSQLDB");
			Vector<CodeSmell> smells = dfm.getSmells();

			ClassMetrics node;
			MethodMetrics node1;
			for (CodeSmell cm : smells) {
				String methodName = cm.getElementName().replace(",", ";");
				// System.out.println(cm.getKindOfSmellName() + ";" + cm.getElementName());
				out.write(cm.getKindOfSmellName() + ";" + cm.getElementName() + ";" + '\n');
			}
			out.flush();
			out.close();
		} catch (

		IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dfm;
	}

	private void printSmell_2() {
		try {
			
			File f = new File("D:\\smell\\output\\" + relativePath + "\\smellDetail.txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
			ArrayList<String> paths = rp.paths;
			ArrayList<String> smells2 = new ArrayList<String>();

		/*	LongParameterList lpl = new LongParameterList();
			ArrayList<String> lpls = lpl.searchLongParamenterListAntipattern(paths);
			for (String str : lpls) {
				smells2.add("LongParameterList" + ";" + str);
				out.write("LongParameterList" + ";" + str + '\n');
			}

			MiddleMan mm1 = new MiddleMan();
			ArrayList<String> mms = mm1.searchMiddleMan(paths);
			for (String str : mms) {
				smells2.add("MiddleMan" + ";" + str);
				// System.out.println("MiddleMan" + ";" + str);
				out.write("MiddleMan" + ";" + str + '\n');
			}
			MessageChain mc=new MessageChain();
			List<String> mcs=mc.searchMessageChain(paths);
			for(String str:mcs) {
				out.write("MessageChains" + ";" + str + '\n');
			}
			SpaghettiCode sc=new SpaghettiCode();
			List<String> scs=sc.searchCandidateSpaghettiCodes(paths);
			for(String str:scs) {
				out.write("SpaghettiCode;"+str+'\n');
			}*/

			DataClumpsDetector dcd = new DataClumpsDetector(paths);

			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void printSmell_3() {
		List<CompilationUnit> comUnits = new ArrayList<CompilationUnit>();
		for (String path : rp.getPath()) {
			CompilationUnit com = JdtAstUtil.getCompilationUnit(path);
			comUnits.add(com);
		}
		
		/*
		 * SwitchTest st = new SwitchTest(comUnits); List<String> candidateSwitch =
		 * st.getCandidateSwitch(); for (String cswitch : candidateSwitch) {
		 * System.out.println("SwitchMore;" + cswitch); }
		 * 
		 * TypeCastTest tct = new TypeCastTest(comUnits); List<String> candidateTypeCast
		 * = tct.getCandidateTypeCast(); for (String ctypecast : candidateTypeCast) {
		 * System.out.println("TypeCast;" + ctypecast); }
		 */
		try {
			File f = new File("D:\\smell\\output\\" + relativePath + "\\smellDetail.txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			BufferedWriter out = new BufferedWriter(new FileWriter(f, true));
			InstanceOfTest iot = new InstanceOfTest(comUnits);
			List<String> candidateInstanceOf = iot.getCandidateInstanceOf();
			for (String cinstance : candidateInstanceOf) {
				out.write("InstanceOf;" + cinstance+'\n');
			}
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}
}
