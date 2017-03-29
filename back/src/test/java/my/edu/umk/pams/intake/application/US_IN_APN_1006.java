package my.edu.umk.pams.intake.application;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.tngtech.jgiven.integration.spring.SpringScenarioTest;
import my.edu.umk.pams.bdd.stage.GivenIAmApplicant;
import my.edu.umk.pams.bdd.tags.Issue;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.application.stage.ThenICanSubmitMyApplication;
import my.edu.umk.pams.intake.application.stage.WhenIWantToFillAllRequiredInformation;
import my.edu.umk.pams.intake.application.stage.WhenIWantToFillinMultipleInformationOnMyExtraCurricularExperience;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.registration.stage.GivenIHaveIncompleteApplication;

import javax.faces.application.Application;

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
 *         <p>
As a applicant, 
I want to fill in multiple information on my history of extra-curricular experience 
so that I can complete my application
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_IN_APN_1006 extends SpringScenarioTest<GivenIAmApplicant, WhenIWantToFillAllRequiredInformation, ThenICanSubmitMyApplication> {

    private static final Logger LOG = LoggerFactory.getLogger(US_IN_APN_1004.class);

    public static final String INTAKE_REFERENCE_NO = "201720181/MASTER";

    @Test
    @Issue("PAMI-27")
    @Rollback
    public void scenario1() {
    	given().I_am_an_applicant_in_current_intake_session()
              .and().I_am_applying_for_intake_$(INTAKE_REFERENCE_NO);
        when().I_fill_in_all_the_required_information_in_my_application();
        addStage(WhenIWantToFillinMultipleInformationOnMyExtraCurricularExperience.class).and().I_want_to_fill_in_multiple_information_on_my_history_of_curricular_experience();
        then().I_can_submit_my_application();
        
        //InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(referenceNo:"XX");
    }
}
