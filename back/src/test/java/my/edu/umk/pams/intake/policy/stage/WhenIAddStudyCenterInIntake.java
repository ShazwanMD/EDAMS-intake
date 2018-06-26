package my.edu.umk.pams.intake.policy.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.common.model.InStudyCenterCode;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import static my.edu.umk.pams.intake.IntakeTestConstants.INTAKE_REFERENCE_NO_MGSSEB;

/**
 * @author PAMS
 **/
@JGivenStage
public class WhenIAddStudyCenterInIntake extends Stage<WhenIAddStudyCenterInIntake> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddIntake.class);

    @Autowired
    private CommonService commonService;
    
    @Autowired
    private PolicyService policyService;

    @ExpectedScenarioState
    private InIntakeSession session;

    @ProvidedScenarioState
    private InProgramLevel level;

    @ProvidedScenarioState
    private InStudyCenterCode studyCenterCode;
    
    @ProvidedScenarioState
    private InProgramOffering programOffering;

    public WhenIAddStudyCenterInIntake I_add_StudyCenter_in_intake() {
    	
    	InIntake intake = policyService.findIntakeByReferenceNo(INTAKE_REFERENCE_NO_MGSSEB);

        programOffering = new InProgramOfferingImpl();
//        programOffering.setProgramFieldCode(null);
        studyCenterCode = commonService.findStudyCenterCodeByCode("A");
        programOffering.setStudyCenterCode(studyCenterCode);
        Assert.notNull(programOffering.getStudyCenterCode(), "studycenter is null");

        policyService.addProgramOffering(intake, programOffering);

        return self();
    }
}