package my.edu.umk.pams.intake.common.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.core.InMetadata;

@Entity(name = "InStpmResult")
@Table(name = "IN_STPM_RSLT")
public class InStpmResultImpl implements InStpmResult {
	
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_IN_STPM_RSLT")
	@SequenceGenerator(name = "SQ_IN_STPM_RSLT", sequenceName = "SQ_IN_STPM_RSLT", allocationSize = 1)
	private Long id;
	
	@Column(name = "GRADUATION_YEAR")
	private String graduationYear;
	
	@Column(name = "SCHOOL_NAME")
	private String schoolName;

	@Column(name = "AGGREGATE")
	private String aggregate;
		
	@OneToOne(targetEntity = InGradeCodeImpl.class)
	@JoinColumn(name = "GRADE_CODE_ID")
	private InGradeCode gradeCode;

	@OneToOne(targetEntity = InStpmSubjectCodeImpl.class)
	@JoinColumn(name = "STPM_SUBJECT_CODE_ID")
	private InStpmSubjectCode stpmSubjectCode;
	
    @ManyToOne(targetEntity = InIntakeApplicationImpl.class)
    @JoinColumn(name = "APPLICATION_ID")
    private InIntakeApplication application;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "RESULT_TYPE")
    private InResultType resultType;

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
	public String getGraduationYear() {
		return graduationYear;
	}
	@Override
	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}
	@Override
	public String getAggregate() {
		return aggregate;
	}
	@Override
	public void setAggregate(String aggregate) {
		this.aggregate = aggregate;
	}
	@Override
	public InGradeCode getGradeCode() {
		return gradeCode;
	}
	@Override
	public void setGradeCode(InGradeCode gradeCode) {
		this.gradeCode = gradeCode;
	}
	@Override
	public InStpmSubjectCode getStpmSubjectCode() {
		return stpmSubjectCode;
	}
	@Override
	public void setStpmSubjectCode(InStpmSubjectCode stpmSubjectCode) {
		this.stpmSubjectCode = stpmSubjectCode;
	}
	@Override
	public InIntakeApplication getApplication() {
		return application;
	}
	@Override
	public void setApplication(InIntakeApplication application) {
		this.application = application;
	}
	@Override
	public InResultType getResultType() {
		return resultType;
	}
	@Override
	public void setResultType(InResultType resultType) {
		this.resultType = resultType;
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
		return InStpmResult.class;
	}




}
