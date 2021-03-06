package my.edu.umk.pams.intake.application;

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

import my.edu.umk.pams.bdd.stage.GivenIAmApplicant;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
import my.edu.umk.pams.intake.application.stage.GivenIFillIncompleteApplication;
import my.edu.umk.pams.intake.application.stage.ThenApplicationIsSubmitted;
import my.edu.umk.pams.intake.application.stage.WhenIUploadDocuments;
import my.edu.umk.pams.intake.config.TestAppConfiguration;

import static my.edu.umk.pams.intake.IntakeTestConstants.INTAKE_REFERENCE_NO_MGSSEB;


@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Application")
@As("As a applicant, I want to be able to upload related documents so that I can complete my application")
public class US_IN_APN_1011 extends SpringScenarioTest<GivenIAmApplicant, GivenIFillIncompleteApplication, ThenApplicationIsSubmitted> {
	
	private static final Logger LOG = LoggerFactory.getLogger(US_IN_APN_1011.class);
	public static final String INTAKE_REFERENCE_NO = INTAKE_REFERENCE_NO_MGSSEB;

	 @Test
	 @Issue("PAMI-32")
	 @Rollback
	 public void scenario1() {
		 
		 given().I_am_an_applicant_in_current_intake_session()
         	.and().I_am_applying_for_intake_$(INTAKE_REFERENCE_NO);
		 when().i_drafted_an_application();
		 addStage(WhenIUploadDocuments.class).and().I_Upload_Documents();	    		 
	     then().application_is_submitted();
		 		 
	 }
}

