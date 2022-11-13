/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class BrainMethodDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double LOC_Greater_VeryHigh;
	private Double WMC_GreaterEqual_Many;
	private Double MAXNESTING_GreaterEqual_DEEP;
	private Double NOF_GreaterEqual_SMemCap;

	public BrainMethodDetectionConfiguration(String projectName) {
		super(projectName);
		this.LOC_Greater_VeryHigh = Double.valueOf(58.5D);
		this.WMC_GreaterEqual_Many = Double.valueOf(4.0D);
		this.MAXNESTING_GreaterEqual_DEEP = Double.valueOf(3.0D);
		this.NOF_GreaterEqual_SMemCap = Double.valueOf(7.0D);
	}

	public BrainMethodDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getLOC_Greater_VeryHigh() {
		return this.LOC_Greater_VeryHigh;
	}

	public void setLOC_Greater_VeryHigh(Double lOC_Greater_VeryHigh) {
		this.LOC_Greater_VeryHigh = lOC_Greater_VeryHigh;
	}

	public Double getWMC_GreaterEqual_Many() {
		return this.WMC_GreaterEqual_Many;
	}

	public void setWMC_GreaterEqual_Many(Double wMC_GreaterEqual_Many) {
		this.WMC_GreaterEqual_Many = wMC_GreaterEqual_Many;
	}

	public Double getMAXNESTING_GreaterEqual_DEEP() {
		return this.MAXNESTING_GreaterEqual_DEEP;
	}

	public void setMAXNESTING_GreaterEqual_DEEP(
			Double mAXNESTING_GreaterEqual_DEEP) {
		this.MAXNESTING_GreaterEqual_DEEP = mAXNESTING_GreaterEqual_DEEP;
	}

	public Double getNOF_GreaterEqual_SMemCap() {
		return this.NOF_GreaterEqual_SMemCap;
	}

	public void setNOF_GreaterEqual_SMemCap(Double nOF_GreaterEqual_SMemCap) {
		this.NOF_GreaterEqual_SMemCap = nOF_GreaterEqual_SMemCap;
	}
}