/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class IntensiveCouplingDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double MAXNESTING_Greater_SHALLOW;
	private Double CDISP_Less_OneQuarter;
	private Double CINT_Greater_Few;
	private Double CDISP_Less_Half;
	private Double CINT_Greater_SMemCap;

	public IntensiveCouplingDetectionConfiguration(String projectName) {
		super(projectName);
		this.MAXNESTING_Greater_SHALLOW = Double.valueOf(1.0D);
		this.CDISP_Less_OneQuarter = Double.valueOf(0.25D);
		this.CINT_Greater_Few = Double.valueOf(2.0D);
		this.CDISP_Less_Half = Double.valueOf(0.5D);
		this.CINT_Greater_SMemCap = Double.valueOf(7.0D);
	}

	public IntensiveCouplingDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMAXNESTING_Greater_SHALLOW() {
		return this.MAXNESTING_Greater_SHALLOW;
	}

	public void setMAXNESTING_Greater_SHALLOW(Double mAXNESTING_Greater_SHALLOW) {
		this.MAXNESTING_Greater_SHALLOW = mAXNESTING_Greater_SHALLOW;
	}

	public Double getCDISP_Less_OneQuarter() {
		return this.CDISP_Less_OneQuarter;
	}

	public void setCDISP_Less_OneQuarter(Double cDISP_Less_OneQuarter) {
		this.CDISP_Less_OneQuarter = cDISP_Less_OneQuarter;
	}

	public Double getCINT_Greater_Few() {
		return this.CINT_Greater_Few;
	}

	public void setCINT_Greater_Few(Double cINT_Greater_Few) {
		this.CINT_Greater_Few = cINT_Greater_Few;
	}

	public Double getCDISP_Less_Half() {
		return this.CDISP_Less_Half;
	}

	public void setCDISP_Less_Half(Double cDISP_Less_Half) {
		this.CDISP_Less_Half = cDISP_Less_Half;
	}

	public Double getCINT_Greater_SMemCap() {
		return this.CINT_Greater_SMemCap;
	}

	public void setCINT_Greater_SMemCap(Double cINT_Greater_SMemCap) {
		this.CINT_Greater_SMemCap = cINT_Greater_SMemCap;
	}
}