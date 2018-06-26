package my.edu.umk.pams.intake.policy.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PAMS
 */
@Pending
@JGivenStage
public class WhenIOfferMGSEBProgramsInIntake extends Stage<WhenIOfferMGSEBProgramsInIntake> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIOfferMGSEBProgramsInIntake.class);

    @Autowired
    private CommonService commonService;

    @ProvidedScenarioState
    private InIntake intake;

    @ExpectedScenarioState
    private InIntakeSession intakeSession;

    @Autowired
    private PolicyService policyService;

    @ProvidedScenarioState
    private List<InProgramCode> programCodes;
    
    @ProvidedScenarioState
    private List<InProgramOffering> offerings;

    public WhenIOfferMGSEBProgramsInIntake i_list_and_offer_programs_under_MGSEB_to_intake(String referenceNo) {
        
        intake = policyService.findIntakeByReferenceNo(referenceNo);
        Assert.notNull(intake, "intake is null");
    	InGraduateCenter graduateCenter = commonService.findGraduateCenterByCode("MGSEB");
    	
    	programCodes = commonService.findProgramCodes(graduateCenter); //todo, no seed data for MGSEB programs
    	
    	Assert.notEmpty(programCodes, "There are no programs in MGSEB");
    	
    	for (InProgramCode programCode : programCodes) {
    	
    		
    		Assert.notNull(programCode, "There are no program codes in MGSEB");
    		LOG.debug("Programs under {} are : {}",programCode.getGraduateCenter().getCode(), programCode.getDescriptionEn());
    		LOG.debug("Programs under {} are : {}",programCode.getGraduateCenter().getCode(), programCode.getDescriptionMs());
       	 
        	 offerings = new ArrayList<>();

        		 InProgramOffering offering = new InProgramOfferingImpl();
//                 offering.setProgramFieldCode(null);
                 policyService.addProgramOffering(intake, offering);
                 offerings.add(offering); 	 
			
		}
        
        return self();
    }
}