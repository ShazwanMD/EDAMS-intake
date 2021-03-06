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
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmCandidate;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.bdd.tags.Submodule;
import my.edu.umk.pams.intake.application.stage.WhenIWantToFillAllRequiredInformation;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.registration.stage.ThenSubmitPaymentInfo;
import my.edu.umk.pams.intake.registration.stage.WhenMakePaymentForStudyFee;

import static my.edu.umk.pams.intake.IntakeTestConstants.INTAKE_REFERENCE_NO_MGSSEB;

@RunWith(SpringRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
@Submodule("Registration")
@As(" As a selected applicant, I want to make a payment for study fees, so that I can submit the payment info to the bursary")
public class US_IN_RGN_5004  extends SpringScenarioTest<GivenIAmCandidate,
WhenIWantToFillAllRequiredInformation,ThenSubmitPaymentInfo> {
	
	private static final Logger LOG = LoggerFactory.getLogger(US_IN_RGN_5004.class);
	
	public static final String INTAKE_REFERENCE_NO = INTAKE_REFERENCE_NO_MGSSEB;
	public static final String MATRIC_NO = "248674";
	
	@Test
    @Issue("PAMI-100")
    @Rollback
    @Pending
    public void scenario1() {
		given().I_am_candidate_in_current_intake_session()
		 .and().I_applied_for_intake_$(INTAKE_REFERENCE_NO);
		when().I_fill_in_all_the_required_information_in_my_application();
		addStage(WhenMakePaymentForStudyFee.class).and().I_want_to_make_payment_for_my_study_fee_$(MATRIC_NO,INTAKE_REFERENCE_NO);
		then().I_can_submit_the_payment_info_to_bursary(MATRIC_NO);
	}
	

}



