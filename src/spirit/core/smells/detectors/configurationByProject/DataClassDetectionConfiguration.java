/** <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen Chao. **/
package spirit.core.smells.detectors.configurationByProject;

import spirit.core.smells.detectors.configurationByProject.CodeSmellDetectionConfiguration;

public class DataClassDetectionConfiguration
		extends
			CodeSmellDetectionConfiguration {
	private Long id;
	private Double WOC_Less_OneThird;
	private Double NOAP_SOAP_Greater_Few;
	private Double WMC_Less_High;
	private Double NOAP_SOAP_Greater_Many;
	private Double WMC_Less_VeryHigh;

	public DataClassDetectionConfiguration(String projectName) {
		super(projectName);
		this.WOC_Less_OneThird = Double.valueOf(0.333D);
		this.NOAP_SOAP_Greater_Few = Double.valueOf(2.0D);
		this.WMC_Less_High = Double.valueOf(29.25D);
		this.NOAP_SOAP_Greater_Many = Double.valueOf(4.0D);
		this.WMC_Less_VeryHigh = Double.valueOf(43.875D);
	}

	public DataClassDetectionConfiguration() {
		super((String) null);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getWOC_Less_OneThird() {
		return this.WOC_Less_OneThird;
	}

	public void setWOC_Less_OneThird(Double wOC_Less_OneThird) {
		this.WOC_Less_OneThird = wOC_Less_OneThird;
	}

	public Double getNOAP_SOAP_Greater_Few() {
		return this.NOAP_SOAP_Greater_Few;
	}

	public void setNOAP_SOAP_Greater_Few(Double nOAP_SOAP_Greater_Few) {
		this.NOAP_SOAP_Greater_Few = nOAP_SOAP_Greater_Few;
	}

	public Double getWMC_Less_High() {
		return this.WMC_Less_High;
	}

	public void setWMC_Less_High(Double wMC_Less_High) {
		this.WMC_Less_High = wMC_Less_High;
	}

	public Double getNOAP_SOAP_Greater_Many() {
		return this.NOAP_SOAP_Greater_Many;
	}

	public void setNOAP_SOAP_Greater_Many(Double nOAP_SOAP_Greater_Many) {
		this.NOAP_SOAP_Greater_Many = nOAP_SOAP_Greater_Many;
	}

	public Double getWMC_Less_VeryHigh() {
		return this.WMC_Less_VeryHigh;
	}

	public void setWMC_Less_VeryHigh(Double wMC_Less_VeryHigh) {
		this.WMC_Less_VeryHigh = wMC_Less_VeryHigh;
	}
}