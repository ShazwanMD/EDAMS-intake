package my.edu.umk.pams.intake.admission.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.admission.model.InCandidate;
import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;

import static org.junit.Assert.assertEquals;

/**
 * @author PAMS
 */
@JGivenStage
public class ThenCandidateCanProceedToNextSelectionPhase extends Stage<ThenCandidateCanProceedToNextSelectionPhase> {


    @ExpectedScenarioState
    private InCandidate candidate;
    
    @ExpectedScenarioState
    private InIntakeApplication preselectApplication;

    InBidStatus status;
    
    public ThenCandidateCanProceedToNextSelectionPhase candidate_can_proceed() {

    	preselectApplication.setBidStatus(InBidStatus.SUBMITTED);

       	//Let Compare if it equals or not
       	status = preselectApplication.getBidStatus();
       	assertEquals(InBidStatus.SUBMITTED,status);
        return self();
        }
	}
