/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class BrainClassDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double LOC_GreaterEqual_VeryHigh;
	private Double LOC_GreaterEqual_2xVeryHigh;
	private Double WMC_GreaterEqual_2xVeryHigh;
	private Double WMC_GreaterEqual_VeryHigh;
	private Double TCC_Less_Half;

	public BrainClassDetectionConfiguration(String projectName) {
		super(projectName);
		this.LOC_GreaterEqual_VeryHigh = Double.valueOf(175.5D);
		this.LOC_GreaterEqual_2xVeryHigh = Double.valueOf(351.0D);
		this.WMC_GreaterEqual_2xVeryHigh = Double.valueOf(87.75D);
		this.WMC_GreaterEqual_VeryHigh = Double.valueOf(43.875D);
		this.TCC_Less_Half = Double.valueOf(0.5D);
	}

	public BrainClassDetectionConfiguration() {
		super((String) null);
	}

	public Double getLOC_GreaterEqual_VeryHigh() {
		return this.LOC_GreaterEqual_VeryHigh;
	}

	public void setLOC_GreaterEqual_VeryHigh(Double lOC_GreaterEqual_VeryHigh) {
		this.LOC_GreaterEqual_VeryHigh = lOC_GreaterEqual_VeryHigh;
	}

	public Double getLOC_GreaterEqual_2xVeryHigh() {
		return this.LOC_GreaterEqual_2xVeryHigh;
	}

	public void setLOC_GreaterEqual_2xVeryHigh(
			Double lOC_GreaterEqual_2xVeryHigh) {
		this.LOC_GreaterEqual_2xVeryHigh = lOC_GreaterEqual_2xVeryHigh;
	}

	public Double getWMC_GreaterEqual_2xVeryHigh() {
		return this.WMC_GreaterEqual_2xVeryHigh;
	}

	public void setWMC_GreaterEqual_2xVeryHigh(
			Double wMC_GreaterEqual_2xVeryHigh) {
		this.WMC_GreaterEqual_2xVeryHigh = wMC_GreaterEqual_2xVeryHigh;
	}

	public Double getWMC_GreaterEqual_VeryHigh() {
		return this.WMC_GreaterEqual_VeryHigh;
	}

	public void setWMC_GreaterEqual_VeryHigh(Double wMC_GreaterEqual_VeryHigh) {
		this.WMC_GreaterEqual_VeryHigh = wMC_GreaterEqual_VeryHigh;
	}

	public Double getTCC_Less_Half() {
		return this.TCC_Less_Half;
	}

	public void setTCC_Less_Half(Double tCC_Less_Half) {
		this.TCC_Less_Half = tCC_Less_Half;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}