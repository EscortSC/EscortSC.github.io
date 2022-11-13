/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class TraditionBreakerDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double NAS_GreaterEqual_NOMAvg;
	private Double PNAS_GreaterEqual_TWO_THIRD;
	private Double AMW_Greater_AMWAvg;
	private Double WMC_GreaterEqual_VeryHigh;
	private Double NOM_GreatherEqual_High;
	private Double AMW_Greater_AMWAvg_2;
	private Double WMC_Greater_VeryHighDiv2;
	private Double NOM_GreatherEqual_HighDiv2;

	public TraditionBreakerDetectionConfiguration(String projectName) {
		super(projectName);
		this.NAS_GreaterEqual_NOMAvg = Double.valueOf(7.0D);
		this.PNAS_GreaterEqual_TWO_THIRD = Double.valueOf(0.666D);
		this.AMW_Greater_AMWAvg = Double.valueOf(1.89D);
		this.WMC_GreaterEqual_VeryHigh = Double.valueOf(43.875D);
		this.NOM_GreatherEqual_High = Double.valueOf(9.0D);
		this.AMW_Greater_AMWAvg_2 = Double.valueOf(1.89D);
		this.WMC_Greater_VeryHighDiv2 = Double.valueOf(21.9375D);
		this.NOM_GreatherEqual_HighDiv2 = Double.valueOf(4.5D);
	}

	public TraditionBreakerDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getNAS_GreaterEqual_NOMAvg() {
		return this.NAS_GreaterEqual_NOMAvg;
	}

	public void setNAS_GreaterEqual_NOMAvg(Double nAS_GreaterEqual_NOMAvg) {
		this.NAS_GreaterEqual_NOMAvg = nAS_GreaterEqual_NOMAvg;
	}

	public Double getPNAS_GreaterEqual_TWO_THIRD() {
		return this.PNAS_GreaterEqual_TWO_THIRD;
	}

	public void setPNAS_GreaterEqual_TWO_THIRD(
			Double pNAS_GreaterEqual_TWO_THIRD) {
		this.PNAS_GreaterEqual_TWO_THIRD = pNAS_GreaterEqual_TWO_THIRD;
	}

	public Double getAMW_Greater_AMWAvg() {
		return this.AMW_Greater_AMWAvg;
	}

	public void setAMW_Greater_AMWAvg(Double aMW_Greater_AMWAvg) {
		this.AMW_Greater_AMWAvg = aMW_Greater_AMWAvg;
	}

	public Double getWMC_GreaterEqual_VeryHigh() {
		return this.WMC_GreaterEqual_VeryHigh;
	}

	public void setWMC_GreaterEqual_VeryHigh(Double wMC_GreaterEqual_VeryHigh) {
		this.WMC_GreaterEqual_VeryHigh = wMC_GreaterEqual_VeryHigh;
	}

	public Double getNOM_GreatherEqual_High() {
		return this.NOM_GreatherEqual_High;
	}

	public void setNOM_GreatherEqual_High(Double nOM_GreatherEqual_High) {
		this.NOM_GreatherEqual_High = nOM_GreatherEqual_High;
	}

	public Double getAMW_Greater_AMWAvg_2() {
		return this.AMW_Greater_AMWAvg_2;
	}

	public void setAMW_Greater_AMWAvg_2(Double aMW_Greater_AMWAvg_2) {
		this.AMW_Greater_AMWAvg_2 = aMW_Greater_AMWAvg_2;
	}

	public Double getWMC_Greater_VeryHighDiv2() {
		return this.WMC_Greater_VeryHighDiv2;
	}

	public void setWMC_Greater_VeryHighDiv2(Double wMC_Greater_VeryHighDiv2) {
		this.WMC_Greater_VeryHighDiv2 = wMC_Greater_VeryHighDiv2;
	}

	public Double getNOM_GreatherEqual_HighDiv2() {
		return this.NOM_GreatherEqual_HighDiv2;
	}

	public void setNOM_GreatherEqual_HighDiv2(Double nOM_GreatherEqual_HighDiv2) {
		this.NOM_GreatherEqual_HighDiv2 = nOM_GreatherEqual_HighDiv2;
	}
}