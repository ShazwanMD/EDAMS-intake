package my.edu.umk.pams.intake.policy.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import static my.edu.umk.pams.intake.IntakeTestConstants.INTAKE_REFERENCE_NO_MGSSEB;

@JGivenStage
public class WhenISetupCompetenciesMatrix extends Stage<WhenISetupCompetenciesMatrix> {
    private static final Logger LOG = LoggerFactory.getLogger(WhenISetupCompetenciesMatrix.class);

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CommonService commonService;

    @ProvidedScenarioState
    private InProgramOffering programOffering;

    @ExpectedScenarioState
    private InIntake intake;

    public WhenISetupCompetenciesMatrix I_setup_competencies_matrix() {

        intake = policyService.findIntakeByReferenceNo(INTAKE_REFERENCE_NO_MGSSEB);

        programOffering = new InProgramOfferingImpl();
//        programOffering.setProgramFieldCode(null);
        policyService.addProgramOffering(intake, programOffering);

        Assert.notNull(programOffering, "Program Offering is null");

        return self();
    }

}
