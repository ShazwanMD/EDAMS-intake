package my.edu.umk.pams.intake.registration.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.admission.model.InCandidate;
import my.edu.umk.pams.intake.admission.model.InCandidateStatus;
import my.edu.umk.pams.intake.admission.service.AdmissionService;
import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.service.PolicyService;


@JGivenStage
public class WhenCandidateAcceptOffer extends Stage<WhenCandidateAcceptOffer>{

	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private AdmissionService admissionService;
	
	@Autowired
	ApplicationService applicationService;
	
	@ExpectedScenarioState
    private InIntake intake; 

	@ProvidedScenarioState
	private InCandidate candidate;
	
	@ExpectedScenarioState
	List<InIntakeApplication>  applications;
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenCandidateAcceptOffer.class);
	
	public WhenCandidateAcceptOffer candidate_is_offered_in_intake_session_$(String identityNo, String intakeSession) {
		
			intake = policyService.findIntakeByReferenceNo(intakeSession);
			Assert.notNull(intake, "intake cannot be null");
			
			applications = applicationService.findIntakeApplications(intake, InBidStatus.SUBMITTED);
			Assert.notEmpty(applications, "applications cannot be empty");

			//dapatkan senarai pemohon yang telah dipilih
			for (InIntakeApplication application : applications) {
				application.setBidStatus(InBidStatus.SELECTED);
				admissionService.preselectIntakeApplication(application);
				 //test preselectIntakeApplication function
				candidate = admissionService.findCandidateByIdentityNo(identityNo);
			}

			return self();
	}

	
	public WhenCandidateAcceptOffer i_accept_offer_$(String identityNo, String intakeSession) {
		
		candidate = admissionService.findCandidateByIdentityNo(identityNo);
		candidate.setStatus(InCandidateStatus.APPROVED);
		admissionService.updateSelectedCandidate(candidate);
		
		return self();
	}
}