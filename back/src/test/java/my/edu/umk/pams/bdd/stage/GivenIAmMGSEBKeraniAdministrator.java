package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.identity.model.InStaff;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.security.integration.InUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@JGivenStage
public class GivenIAmMGSEBKeraniAdministrator extends Stage<GivenIAmMGSEBKeraniAdministrator> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmMGSEBKeraniAdministrator.class);
    public static final String GRADUATE_CENTER = "MGSEB";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CommonService commonService;

    @ProvidedScenarioState
    private InIntakeSession intakeSession;

    @ProvidedScenarioState
    private InStaff staff;

    @ProvidedScenarioState
    InGraduateCenter graduateCenter;

    public void I_am_a_MGSEB_administrator_in_$_intake_session(String intakeSessionCode){
        loginAsMGSEB();
        intakeSession = policyService.findIntakeSessionByCode(intakeSessionCode);
    }

    public void I_am_a_MGSEB_administrator_in_current_intake_session(){
        loginAsMGSEB();
        intakeSession = policyService.findCurrentIntakeSession();
    }

    private void loginAsMGSEB() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("mgseb-kerani", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);

        // retrieve staff from user
        InUser user = ((InUserDetails) authed.getPrincipal()).getUser();
        staff = (InStaff) user.getActor();

        graduateCenter = commonService.findGraduateCenterByCode(GRADUATE_CENTER);
    }
}
