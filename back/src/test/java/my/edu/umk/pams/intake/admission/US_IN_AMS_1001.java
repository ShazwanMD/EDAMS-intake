package my.edu.umk.pams.intake.admission;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;
import my.edu.umk.pams.intake.admission.stage.ThenICanFilterTheTopApplicant;
import my.edu.umk.pams.intake.admission.stage.WhenISliceAndDiceTopApplicant;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As CPS administrator, I want to slide and dice top applicant applications for an intake so that I can filter the top applicants")
public class US_IN_AMS_1001 extends
        SpringScenarioTest<GivenIAmCPSAdministrator,
                WhenISliceAndDiceTopApplicant,
                ThenICanFilterTheTopApplicant> {

    @Test
    @Rollback
    public void scenario1() {
        given().I_am_a_CPS_administrator_in_current_intake_session()
        .and().I_pick_an_intake_$("201720181/MASTER");
        when().I_slide_and_dice_top_applicant_for_intake();
        then().I_can_filter_the_top_applicant();
    }
}

