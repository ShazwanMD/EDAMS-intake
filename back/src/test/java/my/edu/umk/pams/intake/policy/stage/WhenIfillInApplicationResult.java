package my.edu.umk.pams.intake.policy.stage;

import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.model.InResult;
import my.edu.umk.pams.intake.application.model.InResultImpl;
import my.edu.umk.pams.intake.application.model.InResultType;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.admission.selection.SelectionStrategyHelper;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIfillInApplicationResult extends Stage<WhenIfillInApplicationResult> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIfillInApplicationResult.class);

    @Autowired
    private PolicyService policyService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private SelectionStrategyHelper helper;

    @ProvidedScenarioState
    private InIntake intake;

    @ProvidedScenarioState
    private InIntakeApplication intakeApplication;
    
    @ProvidedScenarioState
    private InResultType resultType;

    @ExpectedScenarioState
    private InIntakeSession intakeSession;

    public WhenIfillInApplicationResult I_fill_in_application_result() {

    	intake = policyService.findIntakeByReferenceNo("201720181/MASTER");
        // start an intakeApplication
        intakeApplication = new InIntakeApplicationImpl();
        intakeApplication.setIntake(this.intake);
        intakeApplication.setReferenceNo("testest124");
        intakeApplication.setName("dummy john bin john doe");
        intakeApplication.setEmail("dummyjohn@gmail.com");
        intakeApplication.setPhone("0111020202");
        intakeApplication.setOkuNo("S12223214");
        intakeApplication.setSchoolName("SMKZA");
        applicationService.draftIntakeApplication(intake, intakeApplication);
        
       InResult result = new InResultImpl();
        result.setResultType(resultType.DIPLOMA);
        result.setApplication(intakeApplication);
        applicationService.addResult(intakeApplication, result);
        LOG.debug("results", result);
          //    Assert.notEmpty(programOfferings, "program offering cannot be empty");

//        helper.select(intake);

        return self();
    }
}