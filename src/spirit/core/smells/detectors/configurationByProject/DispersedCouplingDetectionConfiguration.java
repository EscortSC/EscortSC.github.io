/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class DispersedCouplingDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double MAXNESTING_Greater_Shallow;
	private Double CINT_Greater_SMemCap;
	private Double CDISP_GreaterEqual_Half;

	public DispersedCouplingDetectionConfiguration(String projectName) {
		super(projectName);
		this.MAXNESTING_Greater_Shallow = Double.valueOf(1.0D);
		this.CINT_Greater_SMemCap = Double.valueOf(7.0D);
		this.CDISP_GreaterEqual_Half = Double.valueOf(0.5D);
	}

	public DispersedCouplingDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMAXNESTING_Greater_Shallow() {
		return this.MAXNESTING_Greater_Shallow;
	}

	public void setMAXNESTING_Greater_Shallow(Double mAXNESTING_Greater_Shallow) {
		this.MAXNESTING_Greater_Shallow = mAXNESTING_Greater_Shallow;
	}

	public Double getCINT_Greater_SMemCap() {
		return this.CINT_Greater_SMemCap;
	}

	public void setCINT_Greater_SMemCap(Double cINT_Greater_SMemCap) {
		this.CINT_Greater_SMemCap = cINT_Greater_SMemCap;
	}

	public Double getCDISP_GreaterEqual_Half() {
		return this.CDISP_GreaterEqual_Half;
	}

	public void setCDISP_GreaterEqual_Half(Double cDISP_GreaterEqual_Half) {
		this.CDISP_GreaterEqual_Half = cDISP_GreaterEqual_Half;
	}
}