package my.edu.umk.pams.intake.policy.model;

import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.model.InProgramCodeImpl;
import my.edu.umk.pams.intake.common.model.InProgramFieldCode;
import my.edu.umk.pams.intake.common.model.InProgramFieldCodeImpl;
import my.edu.umk.pams.intake.common.model.InStudyCenterCode;
import my.edu.umk.pams.intake.common.model.InStudyCenterCodeImpl;
import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "InProgramOffering")
@Table(name = "IN_PRGM_OFRG")
public class InProgramOfferingImpl implements InProgramOffering {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_PRGM_OFRG")
    @SequenceGenerator(name = "SQ_IN_PRGM_OFRG", sequenceName = "SQ_IN_PRGM_OFRG", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "PROJECTION")
    private Integer projection;

    @NotNull
    @Column(name = "GENERAL_CRITERIA")
    private String generalCriteria;

    @NotNull
    @Column(name = "SPECIFIC_CRITERIA")
    private String specificCriteria;

    @Column(name = "INTERVIEW", nullable = false)
    private boolean interview = Boolean.FALSE;

    @ManyToOne(targetEntity = InIntakeImpl.class)
    @JoinColumn(name = "INTAKE_ID")
    private InIntake intake;

    @ManyToOne(targetEntity = InProgramCodeImpl.class)
    @JoinColumn(name = "PRGM_CODE_ID", nullable = false)
    private InProgramCode programCode;

    @ManyToOne(targetEntity = InStudyCenterCodeImpl.class)
    @JoinColumn(name = "STUDY_CENTER_CODE_ID") // nullable?
    private InStudyCenterCode studyCenterCode;

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
    public Integer getProjection() {
        return projection;
    }

    @Override
    public boolean isInterview() {
        return interview;
    }

    @Override
    public void setInterview(boolean interview) {
        this.interview = interview;
    }

    @Override
    public void setProjection(Integer projection) {
        this.projection = projection;
    }

    @Override
    public String getGeneralCriteria() {
        return generalCriteria;
    }

    @Override
    public void setGeneralCriteria(String generalCriteria) {
        this.generalCriteria = generalCriteria;
    }

    @Override
    public String getSpecificCriteria() {
        return specificCriteria;
    }

    @Override
    public void setSpecificCriteria(String specificCriteria) {
        this.specificCriteria = specificCriteria;
    }

    @Override
    public InIntake getIntake() {
        return intake;
    }

    @Override
    public void setIntake(InIntake intake) {
        this.intake = intake;
    }

    @Override
    public InProgramCode getProgramCode() {
		return programCode;
	}

    @Override
	public void setProgramCode(InProgramCode programCode) {
		this.programCode = programCode;
	}

	@Override
    public InStudyCenterCode getStudyCenterCode() {
        return studyCenterCode;
    }

    @Override
    public void setStudyCenterCode(InStudyCenterCode studyCenterCode) {
        this.studyCenterCode = studyCenterCode;
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
        return InProgramOffering.class;
    }
}
