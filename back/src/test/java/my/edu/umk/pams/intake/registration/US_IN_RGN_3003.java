package my.edu.umk.pams.intake.registration;

import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmCPSAdministrator;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.intake.application.stage.ThenICanSubmitMyApplication;
import my.edu.umk.pams.intake.application.stage.WhenIWantToFillAllRequiredInformation;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.registration.stage.ThenICanProcessTheirApplication;
import my.edu.umk.pams.intake.registration.stage.WhenApplicantHasReferee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As("As a CPS academic Administrator, I want to know who is applicant's referee, so that i can process their application")
public class US_IN_RGN_3003 extends SpringScenarioTest<GivenIAmCPSAdministrator,  WhenIWantToFillAllRequiredInformation, ThenICanSubmitMyApplication>{	
  private static final Logger LOG = LoggerFactory.getLogger(US_IN_RGN_3003.class);
    
    private String intakeReferenceNo = "201720181/MASTER";

    @Test
    @Rollback
    @Issue("76")
    public void scenario1() {
        given().I_am_a_CPS_administrator_in_current_intake_session()
		.and().I_pick_an_intake_$(intakeReferenceNo);
		when().I_fill_in_all_the_required_information_in_my_application();
		addStage(WhenApplicantHasReferee.class).and().an_applicant_has_referee();    	
		then().I_can_submit_my_application();
		addStage(ThenICanProcessTheirApplication.class).and().I_can_process_the_application();
}

    // todo(samiya + ashraf)
    @Pending
    public void testScenario1_pending() {
//		given().I_am_a_administrator_in_current_intake_session_as_$("pps", "abc123");
        // Add additional given
//        additionalStage.and().an_applicant_has_a_referee(applicant);	<-- put assertion(s) inside
        // Perform the behavior
//        when().I_process_the_student_application(); 					<-- put assertion(s) inside
        // Verify the behavior
//        then().the_student_application_is_processed(); 				<-- put assertion(s) inside
    }

}
