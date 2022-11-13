/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class RefusedParentBequestDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double NProtM_Greater_Few;
	private Double BUR_Less_OneThird;
	private Double BOvR_Less_OneThird;
	private Double AMW_Greater_AMWAvg;
	private Double WMC_Greater_WMCAvg;
	private Double NOM_Greater_NOMAvg;

	public RefusedParentBequestDetectionConfiguration(String projectName) {
		super(projectName);
		this.NProtM_Greater_Few = Double.valueOf(2.0D);
		this.BUR_Less_OneThird = Double.valueOf(0.1D);
		this.BOvR_Less_OneThird = Double.valueOf(0.333D);
		this.AMW_Greater_AMWAvg = Double.valueOf(1.89D);
		this.WMC_Greater_WMCAvg = Double.valueOf(13.229999999999999D);
		this.NOM_Greater_NOMAvg = Double.valueOf(7.0D);
	}

	public RefusedParentBequestDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getNProtM_Greater_Few() {
		return this.NProtM_Greater_Few;
	}

	public void setNProtM_Greater_Few(Double nProtM_Greater_Few) {
		this.NProtM_Greater_Few = nProtM_Greater_Few;
	}

	public Double getBUR_Less_OneThird() {
		return this.BUR_Less_OneThird;
	}

	public void setBUR_Less_OneThird(Double bUR_Less_OneThird) {
		this.BUR_Less_OneThird = bUR_Less_OneThird;
	}

	public Double getBOvR_Less_OneThird() {
		return this.BOvR_Less_OneThird;
	}

	public void setBOvR_Less_OneThird(Double bOvR_Less_OneThird) {
		this.BOvR_Less_OneThird = bOvR_Less_OneThird;
	}

	public Double getAMW_Greater_AMWAvg() {
		return this.AMW_Greater_AMWAvg;
	}

	public void setAMW_Greater_AMWAvg(Double aMW_Greater_AMWAvg) {
		this.AMW_Greater_AMWAvg = aMW_Greater_AMWAvg;
	}

	public Double getWMC_Greater_WMCAvg() {
		return this.WMC_Greater_WMCAvg;
	}

	public void setWMC_Greater_WMCAvg(Double wMC_Greater_WMCAvg) {
		this.WMC_Greater_WMCAvg = wMC_Greater_WMCAvg;
	}

	public Double getNOM_Greater_NOMAvg() {
		return this.NOM_Greater_NOMAvg;
	}

	public void setNOM_Greater_NOMAvg(Double nOM_Greater_NOMAvg) {
		this.NOM_Greater_NOMAvg = nOM_Greater_NOMAvg;
	}
}