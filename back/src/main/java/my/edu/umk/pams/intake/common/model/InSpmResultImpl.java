package my.edu.umk.pams.intake.common.model;

import javax.persistence.*;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.model.InResult;
import my.edu.umk.pams.intake.application.model.InResultImpl;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.core.InMetadata;
import my.edu.umk.pams.intake.policy.model.InIntakeImpl;

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
		
	@OneToOne(targetEntity = InGradeCodeImpl.class)
	@JoinColumn(name = "GRADE_CODE_ID")
	private InGradeCode gradeCode;

	@OneToOne(targetEntity = InSpmSubjectCodeImpl.class)
	@JoinColumn(name = "SPM_SUBJECT_CODE_ID")
	private InSpmSubjectCode spmSubjectCode;
	
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
	public InGradeCode getGradeCode() {
		return gradeCode;
	}

	@Override
	public void setGradeCode(InGradeCode gradeCode) {
		this.gradeCode = gradeCode;

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
	public InSpmSubjectCode getSpmSubjectCode() {
		return spmSubjectCode;
	}
	@Override
	public void setSpmSubjectCode(InSpmSubjectCode spmSubjectCode) {
		this.spmSubjectCode = spmSubjectCode;
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
	public InIntakeApplication getApplication() {
		return application;
	}

	@Override
	public void setApplication(InIntakeApplication application) {
		this.application = application;
	}

	@Override
	public String getGraduationYear() {
		return graduationYear;
	}

	@Override
	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}
	

}
