package my.edu.umk.pams.intake.policy.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
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
@JGivenStage
public class WhenIOfferCPSProgramsInIntake extends Stage<WhenIOfferCPSProgramsInIntake> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIOfferCPSProgramsInIntake.class);

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

    public WhenIOfferCPSProgramsInIntake i_list_and_offer_programs_under_CPS_to_intake(String referenceNo) {
        
        intake = policyService.findIntakeByReferenceNo(referenceNo);
        Assert.notNull(intake, "intake is null");
    	InGraduateCenter graduateCenter = commonService.findGraduateCenterByCode("CPS");
    	
    	programCodes = commonService.findProgramCodes(graduateCenter);
    	
    	for (InProgramCode programCode : programCodes) {
    		
    		Assert.notNull(programCode, "There are no program codes in CPS");
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