/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.design;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import DataProcess.ReadPath;
import spirit.core.debt.CodeSmell;
import spirit.core.debt.detectors.BrainClassDetector;
import spirit.core.debt.detectors.BrainMethodDetector;
import spirit.core.debt.detectors.CodeSmellDetector;
import spirit.core.debt.detectors.DataClassDetector;
import spirit.core.debt.detectors.DispersedCouplingDetector;
import spirit.core.debt.detectors.FeatureEnvyDetector;
import spirit.core.debt.detectors.GodClassDetector;
import spirit.core.debt.detectors.IntensiveCouplingDetector;
import spirit.core.debt.detectors.RefusedParentBequestDetector;
import spirit.core.debt.detectors.ShotgunSurgeryDetector;
import spirit.core.debt.detectors.TraditionBreakerDetector;
import spirit.db.DataBaseManager;
import spirit.metrics.analizer.SpiritVisitor;
import spirit.metrics.storage.ClassMetrics;
import spirit.metrics.storage.MethodMetrics;

public class DesignFlawManager {
	private static DesignFlawManager instance;
	private Vector<CodeSmell> smells;
	private Vector<CodeSmellDetector> classDetectors;
	private Vector<CodeSmellDetector> methodDetectors;
	SpiritVisitor visitor = new SpiritVisitor();

	public static DesignFlawManager getInstance() {
		if (instance == null) {
			instance = new DesignFlawManager();
		}
		return instance;
	}

	public DesignFlawManager() {
		this.initialize();
	}

	public void initialize() {
		this.smells = new Vector();
		this.visitor = new SpiritVisitor();
	}

	public Vector<CodeSmell> getSmells() {
		return this.smells;
	}

	public void calculateMetricsCode() throws IOException {
		this.analyseProject();
		this.visitor.executeMetrics();
	}

	public void calculateAditionalMetrics() {
		this.visitor.calculateAditionalMetrics();
	}

	public List<ClassMetrics> getlClassMetircs() {
		return this.visitor.getLClassesMetrics();
	}

	public void detectCodeSmells(String selectedProject) {
		this.smells = new Vector();
		this.resetDetectors(selectedProject);
		List lclassMetrics = this.visitor.getLClassesMetrics();
		Iterator arg3 = lclassMetrics.iterator();

		while (arg3.hasNext()) {
			ClassMetrics classMetrics = (ClassMetrics) arg3.next();
			Iterator arg5 = classMetrics.getMethodsMetrics().iterator();

			while (arg5.hasNext()) {
				MethodMetrics classDetector = (MethodMetrics) arg5.next();
				Iterator arg7 = this.methodDetectors.iterator();

				while (arg7.hasNext()) {
					CodeSmellDetector methodDetector = (CodeSmellDetector) arg7.next();
					if (methodDetector.codeSmellVerify(classDetector)) {
						this.smells.add(methodDetector.codeSmellDetected(classDetector));
					}
				}
			}

			arg5 = this.classDetectors.iterator();

			while (arg5.hasNext()) {
				CodeSmellDetector classDetector1 = (CodeSmellDetector) arg5.next();
				if (classDetector1.codeSmellVerify(classMetrics)) {
					this.smells.add(classDetector1.codeSmellDetected(classMetrics));
				}
			}
		}
	}

	private void resetDetectors(String projectName) {
		this.classDetectors = new Vector();
		this.methodDetectors = new Vector();
		this.methodDetectors.add(new BrainMethodDetector(
				DataBaseManager.getInstance().getBrainMethodDetectionConfiguration(projectName)));
		this.methodDetectors.add(new FeatureEnvyDetector(
				DataBaseManager.getInstance().getFeatureEnvyDetectionConfiguration(projectName)));
		this.methodDetectors.add(new DispersedCouplingDetector(
				DataBaseManager.getInstance().getDispersedCouplingDetectionConfiguration(projectName)));
		this.methodDetectors.add(new IntensiveCouplingDetector(
				DataBaseManager.getInstance().getIntensiveCouplingDetectionConfiguration(projectName)));
		this.methodDetectors.add(new ShotgunSurgeryDetector(
				DataBaseManager.getInstance().getShotgunSurgeryDetectionConfiguration(projectName)));

		this.classDetectors.add(
				new DataClassDetector(DataBaseManager.getInstance().getDataClassDetectionConfiguration(projectName)));
		this.classDetectors.add(new RefusedParentBequestDetector(
				DataBaseManager.getInstance().getRefusedParentBequestDetectionConfiguration(projectName)));
		this.classDetectors.add(new TraditionBreakerDetector(
				DataBaseManager.getInstance().getTraditionBreakerDetectionConfiguration(projectName)));
		this.classDetectors.add(
				new GodClassDetector(DataBaseManager.getInstance().getGodClassDetectionConfiguration(projectName)));
	}

	public ASTNode getCompilationUnit(ASTNode node) {
		return node.getNodeType() == 15 ? node : this.getAncestorCU(node);
	}

	public ASTNode getAncestorCU(ASTNode element) {
		while (element != null) {
			if (element.getNodeType() == 15) {
				return element;
			}

			element = element.getParent();
		}

		return null;
	}

	private void analyseProject() {
		ArrayList<String> paths = ReadPath.paths;
		for (String path : paths) {
			CompilationUnit comp = this.parse(path);
			comp.accept(this.visitor);
		}
	}

	private CompilationUnit parse(String javaFilePath) {
		byte[] input = null;
		try {
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
			input = new byte[bufferedInputStream.available()];
			bufferedInputStream.read(input);
			bufferedInputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setEnvironment(null, null, null, true);
		int index = javaFilePath.lastIndexOf("\\");
		String name = javaFilePath.substring(index, javaFilePath.length() - 5);
		parser.setUnitName(name);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setSource(new String(input).toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		CompilationUnit result = (CompilationUnit) (parser.createAST(null));
		return result;

	}

	/*
	 * public void getIndex(){ List<ClassMetrics> lclassMetrics =
	 * this.visitor.getLClassesMetrics(); Iterator arg3 = lclassMetrics.iterator();
	 * while (arg3.hasNext()) { ClassMetrics classMetrics = (ClassMetrics)
	 * arg3.next(); TypeDeclaration type=classMetrics.getDeclaration();
	 * if(type.getName().toString().equals("Column")){
	 * System.out.println("ATFD"+","+classMetrics.getMetric("ATFD")+",TCC,"+
	 * classMetrics.getMetric("TCC")+",WMC,"+classMetrics.getMetric("WMC"));
	 * //System.out.println("WOC,"+classMetrics.getMetric("WOC")+",NOPA,"+
	 * classMetrics.getMetric("NOPA").floatValue()+",NOAM," //
	 * +classMetrics.getMetric("NOAM")+",WMC,"+classMetrics.getMetric("WMC"));
	 * Iterator arg5 = classMetrics.getMethodsMetrics().iterator();
	 * 
	 * while (arg5.hasNext()) { MethodMetrics classDetector = (MethodMetrics)
	 * arg5.next(); List<String> atfds=(List<String>)
	 * classDetector.getAttribute("ListOfATFD"); if(atfds.size()>0){ Iterator
	 * it=classDetector.getDeclaration().parameters().iterator(); String
	 * Parameters="("; while(it.hasNext()){ String name=it.next().toString(); String
	 * []str=name.split(" "); Parameters=Parameters+str[0]+","; }
	 * if(Parameters.length()>1)
	 * Parameters=Parameters.substring(0,Parameters.length()-1);
	 * Parameters=Parameters+")";
	 * //System.out.println(classDetector.getDeclaration().getName()+Parameters+","+
	 * classDetector.getMetric("WMC"));
	 * System.out.println(classDetector.getDeclaration().getName()+Parameters+","+
	 * classDetector.getAttribute("ListOfATFD")); }
	 * 
	 * } } }
	 * 
	 * }
	 */

}