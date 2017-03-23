package my.edu.umk.pams.intake.policy;

import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.policy.stage.ThenICanProceedTheIntakeProcess;
import my.edu.umk.pams.intake.policy.stage.WhenIWantToSetProjectionForCurrentIntake;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author PAMS
 *         As a PPS Academic Administrator,
 *         I want to set the projection for current intake session,
 *         so that i can proceed the intake process
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_IN_PLC_2003 extends SpringScenarioTest<GivenIAmCPSAdministrator,
        WhenIWantToSetProjectionForCurrentIntake,
        ThenICanProceedTheIntakeProcess> {

    private static final Logger LOG = LoggerFactory.getLogger(US_IN_PLC_2003.class);

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @Test
    @Rollback(true)
    @Issue("PAMI-9")
    public void testScenario1() {
        given().I_am_a_CPS_administrator_in_current_intake_session();
        when().I_Want_To_Set_Projection_For_Current_Intake();
        then().I_Can_Proceed_The_Intake_Process();
    }

}
