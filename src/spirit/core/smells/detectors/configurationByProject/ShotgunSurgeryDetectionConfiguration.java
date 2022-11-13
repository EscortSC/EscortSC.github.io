/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class ShotgunSurgeryDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double CC_GreaterEqual_MANY;
	private Double CM_Greater_SMemCap;

	public ShotgunSurgeryDetectionConfiguration(String projectName) {
		super(projectName);
		this.CC_GreaterEqual_MANY = Double.valueOf(4.0D);
		this.CM_Greater_SMemCap = Double.valueOf(7.0D);
	}

	public ShotgunSurgeryDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCC_GreaterEqual_MANY() {
		return this.CC_GreaterEqual_MANY;
	}

	public void setCC_GreaterEqual_MANY(Double cC_GreaterEqual_MANY) {
		this.CC_GreaterEqual_MANY = cC_GreaterEqual_MANY;
	}

	public Double getCM_Greater_SMemCap() {
		return this.CM_Greater_SMemCap;
	}

	public void setCM_Greater_SMemCap(Double cM_Greater_SMemCap) {
		this.CM_Greater_SMemCap = cM_Greater_SMemCap;
	}
}