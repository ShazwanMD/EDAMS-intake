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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.core.InMetadata;

@Entity(name = "InDiplomaResult")
@Table(name = "IN_DIP_RSLT")
public class InDiplomaResultImpl implements InDiplomaResult {
	
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_IN_DIP_RSLT")
	@SequenceGenerator(name = "SQ_IN_DIP_RSLT", sequenceName = "SQ_IN_DIP_RSLT", allocationSize = 1)
	private Long id;

	@Column(name = "GRADUATION_YEAR")
	private String graduationYear;
	
	@Column(name = "CGPA")
	private String cgpa;
	
	@Column(name = "INSTITUTION")
	private String institution;
	
	@Column(name = "PROGRAM")
	private String program;
			
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
		return InDiplomaResult.class;
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
	public String getCgpa() {
		return cgpa;
	}

	@Override
	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}

	@Override
	public String getInstitution() {
		return institution;
	}

	@Override
	public void setInstitution(String institution) {
		this.institution = institution;
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
	public String getProgram() {
		return program;
	}
	@Override
	public void setProgram(String program) {
		this.program = program;
	}


}
