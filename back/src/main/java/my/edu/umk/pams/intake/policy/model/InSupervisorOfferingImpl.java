package my.edu.umk.pams.intake.policy.model;

import my.edu.umk.pams.intake.common.model.InSupervisorCode;
import my.edu.umk.pams.intake.common.model.InSupervisorCodeImpl;
import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;

@Entity(name = "InSupervisorOffering")
@Table(name = "IN_SPVR_OFRG")
public class InSupervisorOfferingImpl implements InSupervisorOffering {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_SPVR_OFRG")
    @SequenceGenerator(name = "SQ_IN_SPVR_OFRG", sequenceName = "SQ_IN_SPVR_OFRG", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = InIntakeImpl.class)
    @JoinColumn(name = "INTAKE_ID")
    private InIntake intake;

    @ManyToOne(targetEntity = InSupervisorCodeImpl.class)
    @JoinColumn(name = "SUPERVISOR_CODE_ID")
    private InSupervisorCode supervisorCode;

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
    public InIntake getIntake() {
        return intake;
    }

    @Override
    public void setIntake(InIntake intake) {
        this.intake = intake;
    }

    @Override
    public InSupervisorCode getSupervisorCode() {
        return supervisorCode;
    }

    @Override
    public void setSupervisorCode(InSupervisorCode supervisorCode) {
        this.supervisorCode = supervisorCode;
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
        return InSupervisorOffering.class;
    }
}