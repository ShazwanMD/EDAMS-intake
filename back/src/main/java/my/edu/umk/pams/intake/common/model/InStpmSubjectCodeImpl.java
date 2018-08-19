package my.edu.umk.pams.intake.common.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import my.edu.umk.pams.intake.core.InMetadata;

@Entity(name = "InStpmSubjectCode")
@Table(name = "IN_STPM_SBJT_CODE")
public class InStpmSubjectCodeImpl implements InStpmSubjectCode {
	
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_IN_STPM_SBJT_CODE")
	@SequenceGenerator(name = "SQ_IN_STPM_SBJT_CODE", sequenceName = "SQ_IN_STPM_SBJT_CODE", allocationSize = 1)
	private Long id;
	
	@Column(name = "CODE")
	private String code; // BM,BI,MATH

	@Column(name = "DESCRIPTION")
	private String description; // BM,BI,MATH

	@Embedded
	private InMetadata metadata;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String getCode() {
		return code;
	}
	@Override
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public InMetadata getMetadata() {
		return metadata;
	}
	@Override
	public void setMetadata(InMetadata metadata) {
		this.metadata = metadata;
	}
	
	@Override
	public Class<?> getInterfaceClass(){
		return InStpmSubjectCode.class;
	}
	
	


}
