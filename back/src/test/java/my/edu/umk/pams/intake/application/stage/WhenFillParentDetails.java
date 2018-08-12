package my.edu.umk.pams.intake.application.stage;

import static my.edu.umk.pams.intake.IntakeConstants.INTAKE_APPLICATION_REFERENCE_NO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.application.model.InGuardian;
import my.edu.umk.pams.intake.application.model.InGuardianImpl;
import my.edu.umk.pams.intake.application.model.InGuardianType;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.system.service.SystemService;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenFillParentDetails extends Stage<WhenFillParentDetails> {
	
@Autowired
private CommonService commonService;

@Autowired
private SystemService systemService;

@Autowired
private ApplicationService applicationService;
	    
@ExpectedScenarioState
private InApplicant applicant;

@ExpectedScenarioState
private InIntake intake;

@ExpectedScenarioState
private InIntakeSession intakeSession;
	    
@ExpectedScenarioState
private InIntakeApplication intakeApplication;

@ProvidedScenarioState
private InGuardian guardian;
	
	public WhenFillParentDetails I_Fill_Parent_Details() {
	 
		BigDecimal salary = new BigDecimal(9000.12);
		
		InGuardian guardian = new InGuardianImpl();
		guardian.setApplication(intakeApplication);
		guardian.setIdentityNo("651102035671");
		guardian.setName("MUHAMMAD DOLLAH");
		guardian.setSalary(salary);
		
		applicationService.addGuardian(intakeApplication, guardian);
		 return self();
	 }
}
