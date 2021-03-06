package my.edu.umk.pams.intake.policy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.policy.stage.ThenICanDetermineTheLimitOfSupervisor;
import my.edu.umk.pams.intake.policy.stage.WhenISetMaximumNumberOfSupervisor;


@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Policy")
@As("As a CPS academic Administrator, I want to set the maximum number of supervisors for applicant, so that I can determine the limit for applicant's supervisor")
public class US_IN_PLC_2004 extends SpringScenarioTest<GivenIAmCPSAdministrator,
        WhenISetMaximumNumberOfSupervisor,
ThenICanDetermineTheLimitOfSupervisor> {

	private static final Logger LOG = LoggerFactory.getLogger(US_IN_PLC_2004.class);

    @Test
    @Rollback
    @Issue("PAMI-10")
    public void testScenario1() {
        given().I_am_a_CPS_administrator_in_current_intake_session();
        when().I_set_maximum_number_of_supervisor();
        then().the_applicant_supervisor_maximum_number_is_set();
    }

}

