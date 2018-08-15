package my.edu.umk.pams.intake.application;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.bdd.stage.GivenIAmAdministrator;
import my.edu.umk.pams.bdd.stage.GivenIAmRegisteredUser;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
import my.edu.umk.pams.intake.application.stage.ThenICanLoginAgain;
import my.edu.umk.pams.intake.application.stage.WhenIWantToResetForgetPassword;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
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
@As("As a applicant, I want to reset my forget password so that I can login again")
@Submodule("Application")
public class US_IN_APN_1000 extends SpringScenarioTest<GivenIAmAdministrator, WhenIWantToResetForgetPassword, ThenICanLoginAgain> {

    private static final Logger LOG = LoggerFactory.getLogger(US_IN_APN_1000.class);

    @Test
    @Rollback(false)
    @Issue("PAMI-21")
    public void scenario1() {
        given().I_am_a_administrator_in_current_intake_session_as_$("root", "abc123");
        when().I_want_to_reset_forget_password();
        //then().I_can_login_again();
    }
}

