package my.edu.umk.pams.intake.common.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.model.InResult;
import my.edu.umk.pams.intake.application.model.InResultImpl;
import my.edu.umk.pams.intake.core.InMetadata;

@Entity(name = "InSpmResult")
@Table(name = "IN_SPM_RSLT")
@Inheritance(strategy = InheritanceType.JOINED)
public class InSpmResultImpl implements InSpmResult {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_IN_SPM_RSLT")
	@SequenceGenerator(name = "SQ_IN_SPM_RSLT", sequenceName = "SQ_IN_SPM_RSLT", allocationSize = 1)
	private Long id;

	@Column(name = "GRADUATION_YEAR")
	private String graduationYear;

	@Column(name = "AGGREGATE")
	private String aggregate;
	
	@Column(name = "CODE")
	private String code;
	
	@ManyToOne(targetEntity = InSubjectCodeImpl.class)
	@JoinColumn(name = "SUBJECT_CODE_ID")
	private InSubjectCode subjectCode;

	@ManyToOne(targetEntity = InGradeCodeImpl.class)
	@JoinColumn(name = "GRADE_CODE_ID")
	private InGradeCode gradeCode;

	@Embedded
	private InMetadata metadata;

	@ManyToOne(targetEntity = InResultImpl.class)
	@JoinColumn(name = "RESULT_ID")
	private InResult result;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return InSpmResult.class;
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
	public InGradeCode getGradeCode() {
		return gradeCode;
	}

	@Override
	public void setGradeCode(InGradeCode gradeCode) {
		this.gradeCode = gradeCode;

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

}
