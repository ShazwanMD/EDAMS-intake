package my.edu.umk.pams.intake.application.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.admission.selection.SelectionStrategyHelper;
import my.edu.umk.pams.intake.admission.selection.StandardSelectionStrategy;
import my.edu.umk.pams.intake.admission.service.AdmissionService;
import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.service.PolicyService;

@JGivenStage
public class WhenIAmSelectedApplicant extends Stage<WhenIAmSelectedApplicant>{
	private static final Logger LOG = LoggerFactory.getLogger(WhenIAmSelectedApplicant.class);
	
	@Autowired
    private ApplicationService applicationService;
	
	@Autowired
	private PolicyService policyService;

	@ExpectedScenarioState
    private InIntake intake;

	@ExpectedScenarioState
    private InIntakeSession intakeSession;
	
    @ExpectedScenarioState
    private InApplicant applicant;
	
    @ExpectedScenarioState
    private InIntakeApplication intakeApplication;
    
    @Autowired
    private AdmissionService admissionService;

    @Autowired
    private StandardSelectionStrategy standardSelectionStrategy;

    @Autowired
    private SelectionStrategyHelper selectionStrategyHelper;

    
    @ProvidedScenarioState
    private InResultType resultType;
	
	public WhenIAmSelectedApplicant I_am_selected_applicant_in_current_intake_session_$(String intakeSession){
		
		
		
		InIntake intake = policyService.findIntakeByReferenceNo(intakeSession);
		LOG.debug("intake {}", intake);
		List<InIntakeApplication> applicants = applicationService.findIntakeApplications(intake,InBidStatus.SELECTED);
		LOG.debug("applicants {}", applicants);
		for (InIntakeApplication inIntakeApplication : applicants) {
			Assert.notNull(inIntakeApplication, "list is null");
			//inIntakeApplication.setBidStatus(InBidStatus.SELECTED);
		}
		
		 
/*	//  intakeApplication selection
		standardSelectionStrategy.select(intake);
		admissionService.preselectIntakeApplication(intakeApplication);
	*/	 
	//	intakeApplication status SELECTED
//		Assert.notNull(InBidStatus.SELECTED, "intake application is not selected");
//		LOG.debug("intake application status: {}", intakeApplication.getBidStatus());
		 
		 return self();
	 }

}
