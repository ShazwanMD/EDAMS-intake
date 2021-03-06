package my.edu.umk.pams.intake.registration;

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
import my.edu.umk.pams.intake.registration.stage.ThenICanCompleteStudentRegistration;
import my.edu.umk.pams.intake.registration.stage.WhenActivateStudentAdmission;
import my.edu.umk.pams.intake.registration.stage.WhenCandidateAcceptOffer;
import my.edu.umk.pams.intake.registration.stage.WhenIOfferToCandidate;
import my.edu.umk.pams.intake.registration.stage.WhenPrepareApplicationSubmission;
import my.edu.umk.pams.intake.registration.stage.WhenPreselectApplicant;

import static my.edu.umk.pams.intake.IntakeTestConstants.INTAKE_REFERENCE_NO_MGSSEB;


@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Registration")
@As("As an academic administrator, I want to scan the applicant's offer letter barcode for matriculation so that I can complete the student's registration")
public class US_IN_RGN_3007 extends SpringScenarioTest <GivenIAmCPSAdministrator,
														WhenPrepareApplicationSubmission,
														ThenICanCompleteStudentRegistration> {
	
	private static final Logger LOG = LoggerFactory.getLogger(US_IN_RGN_3007.class);
	
	public static final String INTAKE_REFERENCE_NO = INTAKE_REFERENCE_NO_MGSSEB;
	public static final String IDENTITY_NO = "248674";

    @Test
    @Rollback
    @Issue("PAMI-80")
    public void scenario1() {
    	
        given().I_am_a_CPS_administrator_in_current_intake_session()
        .and().I_pick_an_intake_$(INTAKE_REFERENCE_NO);
        when().I_prepare_3_applications().and().I_submit_3_applications();
        addStage(WhenPreselectApplicant.class).and().I_preselect_applicant_$(IDENTITY_NO);
        addStage(WhenIOfferToCandidate.class).and().I_offer_to_candidate_in_intake_session_$(INTAKE_REFERENCE_NO);
        addStage(WhenCandidateAcceptOffer.class).and().a_candidate_accept_offer_$(IDENTITY_NO,INTAKE_REFERENCE_NO);
        addStage(WhenActivateStudentAdmission.class).and().I_scan_offer_letter_barcode_for_a_candidate_$(IDENTITY_NO,INTAKE_REFERENCE_NO);
        then().I_can_complete_student_registration(); 
    }
}

	
