package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@JGivenStage
public class GivenIAmMGSEBAdministrator extends Stage<GivenIAmMGSEBAdministrator> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmMGSEBAdministrator.class);
    public static final String GRADUATE_CENTER = "MGSEB";

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CommonService commonService;

    @ProvidedScenarioState
    InIntakeSession intakeSession;

    @ProvidedScenarioState
    InIntake intake;
    
    @ProvidedScenarioState
    InGraduateCenter graduateCenter;

    public void I_am_a_MGSEB_administrator_in_$_academic_session(String academicSessionCode){
        loginAsMGSEB();
        intakeSession = policyService.findIntakeSessionByCode(academicSessionCode);
    }

    public GivenIAmMGSEBAdministrator I_am_a_MGSEB_administrator_in_current_intake_session(){
        loginAsMGSEB();
        intakeSession = policyService.findCurrentIntakeSession();
        return self();
    }
    
    public GivenIAmMGSEBAdministrator I_pick_an_intake_$(String intakeReferenceNo){
        intake = policyService.findIntakeByReferenceNo(intakeReferenceNo);
        return self();
    }
    

    private void loginAsMGSEB() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("mgseb", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);

        graduateCenter = commonService.findGraduateCenterByCode(GRADUATE_CENTER);
    }
}
