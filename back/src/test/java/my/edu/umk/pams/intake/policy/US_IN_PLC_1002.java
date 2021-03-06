package my.edu.umk.pams.intake.policy;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmMGSEBAdministrator;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.policy.stage.ThenIntakeIsReadyForSetup;
import my.edu.umk.pams.intake.policy.stage.WhenIAddIntake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Policy")
@As("As MGSEB Academic Administrator I want to configure initiated intake, so that the applicant can apply the application")
public class US_IN_PLC_1002 extends SpringScenarioTest<GivenIAmMGSEBAdministrator,
															WhenIAddIntake, 
																ThenIntakeIsReadyForSetup> {

    private static final Logger LOG = LoggerFactory.getLogger(US_IN_PLC_1002.class);
    @Test
    @Rollback
    @Issue("PAMI-2")
    public void scenario1() {
        given().I_am_a_MGSEB_administrator_in_current_intake_session();
        when().I_add_intake();
        then().intake_is_ready_for_setup();
    }
}

