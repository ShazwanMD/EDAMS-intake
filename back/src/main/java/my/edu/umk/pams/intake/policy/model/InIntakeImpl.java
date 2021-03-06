package my.edu.umk.pams.intake.policy.model;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.model.InGraduateCenterImpl;
import my.edu.umk.pams.intake.core.InFlowdata;
import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity(name = "InIntake")
@Table(name = "IN_INTK")

public class InIntakeImpl implements InIntake {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_INTK")
    @SequenceGenerator(name = "SQ_IN_INTK", sequenceName = "SQ_IN_INTK", allocationSize = 1)
    private Long id;

    @Column(name = "REFERENCE_NO", unique = true, nullable = false)
    private String referenceNo; // programLevel + session  DIPLOMA/201420151

    @Column(name = "SOURCE_NO", nullable = false)
    private String sourceNo;

    @Column(name = "AUDIT_NO", unique = true, nullable = false)
    private String auditNo;

    @Column(name = "DESCRIPTION_EN", nullable = false)
    private String descriptionEn;
    
    @Column(name = "DESCRIPTION_MS", nullable = false)
    private String descriptionMs;

    @NotNull
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @NotNull
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @NotNull
    @Column(name = "PROJECTION")
    private Integer projection = 0;

    @Column(name = "CANCEL_COMMENT", unique = true)
    private String cancelComment;

    @Column(name = "REMOVE_COMMENT", unique = true)
    private String removeComment;

    @OneToOne(targetEntity = InIntakeSessionImpl.class)
    @JoinColumn(name = "SESSION_ID", nullable = false)
    private InIntakeSession session;

    @OneToOne(targetEntity = InProgramLevelImpl.class)
    @JoinColumn(name = "PROGRAM_LEVEL_ID", nullable = false)
    private InProgramLevel programLevel;

    @OneToOne(targetEntity = InGraduateCenterImpl.class)
    @JoinColumn(name = "GRADUATE_CENTER_ID", nullable = false)
    private InGraduateCenter graduateCenter;

    @OneToMany(targetEntity = InStudyModeOfferingImpl.class, mappedBy = "intake")
    private List<InStudyModeOffering> modeOfferings;
    
    @OneToMany(targetEntity = InProgramOfferingImpl.class, mappedBy = "intake")
    private List<InProgramOffering> programOfferings;
   
    @OneToMany(targetEntity = InIntakeApplicationImpl.class, mappedBy = "intake")
    private List<InIntakeApplication> applications;

    @Embedded
    private InMetadata metadata;

    @Embedded
    private InFlowdata flowdata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getReferenceNo() {
        return referenceNo;
    }

    @Override
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    @Override
    public String getSourceNo() {
        return sourceNo;
    }

    @Override
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    @Override
    public String getAuditNo() {
        return auditNo;
    }

    @Override
    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
    }

    public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionMs() {
		return descriptionMs;
	}

	public void setDescriptionMs(String descriptionMs) {
		this.descriptionMs = descriptionMs;
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
    public Integer getProjection() {
        return projection;
    }

    @Override
    public void setProjection(Integer projection) {
        this.projection = projection;
    }

    @Override
    public String getCancelComment() {
        return cancelComment;
    }

    @Override
    public void setCancelComment(String cancelComment) {
        this.cancelComment = cancelComment;
    }

    @Override
    public String getRemoveComment() {
        return removeComment;
    }

    @Override
    public void setRemoveComment(String removeComment) {
        this.removeComment = removeComment;
    }

    @Override
    public InIntakeSession getSession() {
        return session;
    }

    @Override
    public void setSession(InIntakeSession session) {
        this.session = session;
    }

    @Override
    public InProgramLevel getProgramLevel() {
        return programLevel;
    }

    @Override
    public void setProgramLevel(InProgramLevel programLevel) {
        this.programLevel = programLevel;
    }

    @Override
    public InGraduateCenter getGraduateCenter() {
        return graduateCenter;
    }

    @Override
    public void setGraduateCenter(InGraduateCenter graduateCenter) {
        this.graduateCenter = graduateCenter;
    }

    @Override
    public List<InProgramOffering> getProgramOfferings() {
        return programOfferings;
    }

    @Override
    public void setProgramOfferings(List<InProgramOffering> programOfferings) {
        this.programOfferings = programOfferings;
    }

    @Override
    public List<InStudyModeOffering> getModeOfferings() {
        return modeOfferings;
    }

    @Override
    public void setModeOfferings(List<InStudyModeOffering> modeOfferings) {
        this.modeOfferings = modeOfferings;
    }

    @Override
    public List<InIntakeApplication> getApplications() {
        return applications;
    }

    @Override
    public void setApplications(List<InIntakeApplication> applications) {
        this.applications = applications;
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
    public InFlowdata getFlowdata() {
        return flowdata;
    }

    @Override
    public void setFlowdata(InFlowdata flowdata) {
        this.flowdata = flowdata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return InIntake.class;
    }

}
