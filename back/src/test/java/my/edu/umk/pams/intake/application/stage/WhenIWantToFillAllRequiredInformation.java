package my.edu.umk.pams.intake.application.stage;

import com.tngtech.jgiven.Stage;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

import static my.edu.umk.pams.intake.IntakeConstants.INTAKE_APPLICATION_REFERENCE_NO;

@JGivenStage
public class WhenIWantToFillAllRequiredInformation extends Stage<WhenIWantToFillAllRequiredInformation> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToFillAllRequiredInformation.class);

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private SystemService systemService;

    @ExpectedScenarioState
    private InIntake intake;

    @ExpectedScenarioState
    private InIntakeSession intakeSession;

    @ExpectedScenarioState
    private InApplicant applicant;

    @ProvidedScenarioState
    private InIntakeApplication application;

    public WhenIWantToFillAllRequiredInformation I_fill_in_all_the_required_information_in_my_application() {
        // generate intake reference no
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("intakeSession", intakeSession);
        map.put("programLevel", intake.getProgramLevel());
        String referenceNo = systemService.generateFormattedReferenceNo(INTAKE_APPLICATION_REFERENCE_NO, map);

        // start an intakeApplication
        application = new InIntakeApplicationImpl();
        application.setIntake(this.intake);
        application.setReferenceNo(referenceNo);
        application.setName("dummy john bin john doe");
        application.setEmail("dummyjohn@gmail.com");
        application.setPhone("0111020202");
        application.setBidStatus(InBidStatus.NEW);
        try {
            applicationService.applyIntake(intake, application);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return self();
    }
}
