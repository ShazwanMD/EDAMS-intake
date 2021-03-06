package my.edu.umk.pams.intake.registration.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;

public class WhenIVerifyRefereeStatus extends Stage<WhenIVerifyRefereeStatus> {

    @Autowired
    private RegistrationService registrationservice;

    @Autowired
    private IdentityService identityservice;

    @ProvidedScenarioState
    private InUser user;

    @ExpectedScenarioState
    private InApplicant applicant;

    public WhenIVerifyRefereeStatus I_verify_applicant_has_referee() {

        return self();
    }

}
