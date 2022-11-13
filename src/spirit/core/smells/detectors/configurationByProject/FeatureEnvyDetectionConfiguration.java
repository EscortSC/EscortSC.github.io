/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class FeatureEnvyDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double ATFD_Greater_Few;
	private Double LAA_Less_OneThird;
	private Double FDP_LessEqual_FEW;

	public FeatureEnvyDetectionConfiguration(String projectName) {
		super(projectName);
		this.ATFD_Greater_Few = Double.valueOf(2.0D);
		this.LAA_Less_OneThird = Double.valueOf(0.333D);
		this.FDP_LessEqual_FEW = Double.valueOf(2.0D);
	}

	public FeatureEnvyDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getATFD_Greater_Few() {
		return this.ATFD_Greater_Few;
	}

	public void setATFD_Greater_Few(Double aTFD_Greater_Few) {
		this.ATFD_Greater_Few = aTFD_Greater_Few;
	}

	public Double getLAA_Less_OneThird() {
		return this.LAA_Less_OneThird;
	}

	public void setLAA_Less_OneThird(Double lAA_Less_OneThird) {
		this.LAA_Less_OneThird = lAA_Less_OneThird;
	}

	public Double getFDP_LessEqual_FEW() {
		return this.FDP_LessEqual_FEW;
	}

	public void setFDP_LessEqual_FEW(Double fDP_LessEqual_FEW) {
		this.FDP_LessEqual_FEW = fDP_LessEqual_FEW;
	}
}