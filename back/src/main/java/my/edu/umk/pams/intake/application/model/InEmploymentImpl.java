package my.edu.umk.pams.intake.application.model;


import my.edu.umk.pams.intake.common.model.*;
import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity(name = "InEmployment")
@Table(name = "IN_EMPT")
public class InEmploymentImpl implements InEmployment {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_EMPT")
    @SequenceGenerator(name = "SQ_IN_EMPT", sequenceName = "SQ_IN_EMPT", allocationSize = 1)
    private Long id;
    
    @NotNull
    @Column(name = "DESIGNATION", nullable = false)
    private String designation;


    @NotNull
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    
    @Column(name = "END_DATE")
    private Date endDate;

    @NotNull
    @Column(name = "EMPLOYER", nullable = false)
    private String employer;
    
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "EMPLOYMENY_TYPE")
    private InEmploymentType employmentType;
    
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "WORKING_DURATION")
    private InEmploymentWorkingDuration workingDuration;

    @NotNull
    @Column(name = "ACTIVE", nullable = false)
    private Boolean current = Boolean.FALSE;

    @ManyToOne(targetEntity = InEmploymentSectorCodeImpl.class)
    @JoinColumn(name = "SECTOR_CODE_ID")
    private InEmploymentSectorCode sectorCode;

    @ManyToOne(targetEntity = InEmploymentFieldCodeImpl.class)
    @JoinColumn(name = "FIELD_CODE_ID")
    private InEmploymentFieldCode fieldCode;

    @ManyToOne(targetEntity = InEmploymentLevelCodeImpl.class)
    @JoinColumn(name = "LEVEL_CODE_ID")
    private InEmploymentLevelCode levelCode;

    @ManyToOne(targetEntity = InIntakeApplicationImpl.class)
    @JoinColumn(name = "APPLICATION_ID")
    private InIntakeApplication application;

    @Embedded
    private InMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    @Override
    public String getDesignation() {
        return designation;
    }

    @Override
    public void setDesignation(String designation) {
        this.designation = designation;
    }
    
    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getEmployer() {
        return employer;
    }

    @Override
    public void setEmployer(String employer) {
        this.employer = employer;
    }

    @Override
    public Boolean isCurrent() {
        return current;
    }

    @Override
    public void setCurrent(Boolean current) {
        this.current = current;
    }

    @Override
    public InEmploymentSectorCode getSectorCode() {
        return sectorCode;
    }

    public void setSectorCode(InEmploymentSectorCode sectorCode) {
        this.sectorCode = sectorCode;
    }

    public Boolean getCurrent() {
        return current;
    }

    @Override
    public InEmploymentFieldCode getFieldCode() {
        return fieldCode;
    }

    @Override
    public void setFieldCode(InEmploymentFieldCode fieldCode) {
        this.fieldCode = fieldCode;
    }

    @Override
    public InEmploymentLevelCode getLevelCode() {
        return levelCode;
    }

    @Override
    public void setLevelCode(InEmploymentLevelCode levelCode) {
        this.levelCode = levelCode;
    }
    
    
    @Override
    public InEmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(InEmploymentType employmentType) {
		this.employmentType = employmentType;
	}
	
	  
    @Override
    public InEmploymentWorkingDuration getWorkingDuration() {
		return workingDuration;
	}

	public void setWorkingDuration(InEmploymentWorkingDuration workingDuration) {
		this.workingDuration = workingDuration;
	}

	@Override
    public InIntakeApplication getApplication() {
        return application;
    }

    @Override
    public void setApplication(InIntakeApplication application) {
        this.application = application;
    }

    public InMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(InMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return InEmployment.class;
    }

}
