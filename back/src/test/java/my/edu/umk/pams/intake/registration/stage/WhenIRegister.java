package my.edu.umk.pams.intake.registration.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import groovy.util.logging.Log;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InApplicantImpl;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.model.InUserImpl;
import my.edu.umk.pams.intake.registration.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenIRegister extends Stage<WhenIRegister> {

    @Autowired
    private RegistrationService registrationService;
    
    @Autowired
    private ApplicationService applicationService;

    @ProvidedScenarioState
    private InUser user;

    public WhenIRegister I_register() {

//        user = new InUserImpl();
//        user.setUsername("ahmad.razif@gmail.com");
//        user.setPassword("abc123");
//        user.setRealName("Ahmad Radzif Radzol");
//        user.setEmail("Ahmad Radzif Radzol");
//
//        InApplicant applicant = new InApplicantImpl();
//        applicant.setApplicationNo("ahmad.razif@gmail.com");
//        applicant.setName("Ahmad Radzif Radzol");
//        applicant.setEmail("ahmad.razif@gmail.com");
//        applicant.setMobile("123456789");
//        applicant.setPhone("123456789");
//        applicant.setFax("123456789");
//
//        registrationService.registerUser(user, applicant);

        InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo("201720181-DEGREE-001");

        applicationService.submitNotification(application);
        return self();
    }
}
