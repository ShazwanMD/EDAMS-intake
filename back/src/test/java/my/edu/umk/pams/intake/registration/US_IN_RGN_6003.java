package my.edu.umk.pams.intake.registration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;

import my.edu.umk.pams.bdd.stage.GivenIAmRegistrar;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.registration.stage.ThenProceedRegistrationForSelectedApplicants;
import my.edu.umk.pams.intake.registration.stage.WhenApplicantFillAndSubmitApplication;
import my.edu.umk.pams.intake.registration.stage.WhenIPreselectApplicant;
import my.edu.umk.pams.intake.registration.stage.WhenIPullSelectedApplicantsDetails;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@As( "As a registrar, I want to pull up information on selected applicant's given name or ic number or bill presentment number "
	+ "so that I can proceed with the selected applicants registration progress")
public class US_IN_RGN_6003 extends
		SpringScenarioTest<GivenIAmRegistrar, WhenApplicantFillAndSubmitApplication, ThenProceedRegistrationForSelectedApplicants> {
	
	 public static final String INTAKE_REFERENCE_NO = "201720181/MASTER";
	 private static final String IDENTITY_NO = "248674";
		
	 @Issue ("PAMI-104")
	 @Test
	 @Rollback
	 public void scenario1() {
	     given().I_am_a_Registrar_in_current_intake_session()
	     .and().I_pick_an_intake_$(INTAKE_REFERENCE_NO);
	     when().I_fill_in_application().and().applicant_submit_application();
	     addStage(WhenIPreselectApplicant.class).and().I_preselect_applicant_in_intake_session_$(IDENTITY_NO, INTAKE_REFERENCE_NO);
	     addStage(WhenIPullSelectedApplicantsDetails.class).and().I_pull_selected_applicant_details();
	     then().Proceed_Registration_For_Selected_Applicants();
	}




}
