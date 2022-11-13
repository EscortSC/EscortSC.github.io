/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class GodClassDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double ATFD_Greater_FEW;
	private Double TCC_Less_OneThird;
	private Double WMC_GreaterEqual_VeryHigh;

	public GodClassDetectionConfiguration(String projectName) {
		super(projectName);
		this.ATFD_Greater_FEW = Double.valueOf(2.0D);
		this.TCC_Less_OneThird = Double.valueOf(0.333D);
		this.WMC_GreaterEqual_VeryHigh = Double.valueOf(43.875D);
	}

	public GodClassDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getATFD_Greater_FEW() {
		return this.ATFD_Greater_FEW;
	}

	public void setATFD_Greater_FEW(Double aTFD_Greater_FEW) {
		this.ATFD_Greater_FEW = aTFD_Greater_FEW;
	}

	public Double getTCC_Less_OneThird() {
		return this.TCC_Less_OneThird;
	}

	public void setTCC_Less_OneThird(Double tCC_Less_OneThird) {
		this.TCC_Less_OneThird = tCC_Less_OneThird;
	}

	public Double getWMC_GreaterEqual_VeryHigh() {
		return this.WMC_GreaterEqual_VeryHigh;
	}

	public void setWMC_GreaterEqual_VeryHigh(Double wMC_GreaterEqual_VeryHigh) {
		this.WMC_GreaterEqual_VeryHigh = wMC_GreaterEqual_VeryHigh;
	}
}