package my.edu.umk.pams.intake.policy;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.policy.stage.ThenIntakeHasOffering;
import my.edu.umk.pams.intake.policy.stage.WhenIAddOffering;
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
@As("As a CPS Administrator, I want to start an intake, so that intake is ready for setup")
public class US_IN_PLC_1001 extends SpringScenarioTest<GivenIAmCPSAdministrator, WhenIAddOffering, ThenIntakeHasOffering> {

    private static final Logger LOG = LoggerFactory.getLogger(US_IN_PLC_1001.class);

    @Test
    @Rollback
    @Issue("PAMI-4")
    public void scenario1() {
        given().I_am_a_CPS_administrator_in_current_intake_session();
        when().I_add_a_offering_for_intake();
        then().intake_has_offering();
    }
}
