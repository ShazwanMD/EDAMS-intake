package my.edu.umk.pams.intake.common.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.intake.core.InMetadata;

@Entity(name = "InSpmSubjectCode")
@Table(name = "IN_SPM_SBJT_CODE")
public class InSpmSubjectCodeImpl implements InSpmSubjectCode {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_IN_SPM_SBJT_CODE")
	@SequenceGenerator(name = "SQ_IN_SPM_SBJT_CODE", sequenceName = "SQ_IN_SPM_SBJT_CODE", allocationSize = 1)
	private Long id;

	@NotNull
	@Column(name = "CODE", unique = true, nullable = false)
	private String code; // BM,BI,MATH

	@Column(name = "DESCRIPTION_MS")
	private String descriptionMs;

	@Column(name = "DESCRIPTION_EN")
	private String descriptionEn;

	@ManyToOne(targetEntity = InSubjectCodeImpl.class)
	@JoinColumn(name = "SUBJECT_CODE_ID")
	private InSubjectCode subjectCode;

	@Embedded
	private InMetadata metadata;

	@Override
	public Long getId() {
		return id;
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
	public Class<?> getInterfaceClass() {
		return InSpmSubjectCode.class;
	}

	@Override
	public String getCode() {
		return null;
	}

	@Override
	public void setCode(String code) {

	}

	@Override
	public InSubjectCode getSubjectCode() {
		return subjectCode;
	}

	@Override
	public void setSubjectCode(InSubjectCode subjectCode) {
		this.subjectCode = subjectCode;

	}

	@Override
	public String getDescriptionMs() {
		return descriptionMs;
	}

	@Override
	public void setDescriptionMs(String descriptionMs) {
		this.descriptionMs = descriptionMs;
		
	}

	@Override
	public String getDescriptionEn() {
		return descriptionEn;
	}

	@Override
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
		
	}

}
