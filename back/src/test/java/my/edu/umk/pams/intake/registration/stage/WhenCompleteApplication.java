
package my.edu.umk.pams.intake.registration.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.application.model.*;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InResidencyCode;
import my.edu.umk.pams.intake.common.model.InResidencyCodeImpl;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InApplicantImpl;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenCompleteApplication extends Stage<WhenCompleteApplication> {
	
    private static final Logger LOG = LoggerFactory.getLogger(WhenCompleteApplication.class);

    
    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private PolicyService policyService;
    
    @Autowired
    private IdentityService identityService;

    @ExpectedScenarioState
    private InIntake intake;
    
    @ProvidedScenarioState
    private InIntakeApplication application;



    public WhenCompleteApplication i_complete_my_application() {

    	//test if there is an intake or not
    	Assert.notNull(intake, "intake is null");
         
    	//create offering    
        InProgramOffering offering = new InProgramOfferingImpl();
        offering.setProjection(10);
        offering.setGeneralCriteria("TODO ADD GEN CRIT");
        offering.setSpecificCriteria("TODO ADD SPECIFIC CRIT");
        offering.setInterview(true);
        offering.setStudyCenterCode(commonService.findStudyCenterCodeByCode("A")); 
//        offering.setProgramFieldCode(null); 
        policyService.addProgramOffering(intake, offering);
        
        
        //create a residency code
    	InResidencyCode resident = new InResidencyCodeImpl();
    	resident.setCode("101");
    	resident.setDescriptionMs("test resident");
    	resident.setDescriptionEn("test resident");
    	commonService.saveResidencyCode(resident);
    	Assert.notNull(resident, "resident does not exists");
    	
    	//create applicant
        InApplicant applicant = new InApplicantImpl();
        applicant.setApplicationNo("9999990");
        applicant.setName("Ali bin Abu");
        applicant.setEmail("ali_abi@gmail.com");
        applicant.setPhone("0111020542");
        identityService.saveApplicant(applicant);

         application = new InIntakeApplicationImpl();
         application.setIntake(intake);
         application.setReferenceNo("INTAKE/10005");
         application.setName(applicant.getName());
         application.setCredentialNo("910607147644");
         application.setEmail(applicant.getEmail());
         application.setPhone(applicant.getPhone());
         application.setAge(26);
         application.setRank(3);
         application.setPaid(true);
         application.setVerified(true);
         application.setMerit(new BigDecimal("2.85"));
         application.setPaymentSourceNo("0024188");
         application.setBidType(InBidType.FIRST);
         application.setBidStatus(InBidStatus.NEW);
         application.setBidResponse(InBidResponse.NEW);
         application.setOkuNo("S12223214");
         application.setProgramSelection(offering);
         // todo: change to study mode selection
         // application.setStudyMode(commonService.findStudyModeByCode("1")); //Full time
		 application.setGenderCode(commonService.findGenderCodeByCode("1")); // Male
		 application.setReligionCode(commonService.findReligionCodeByCode("1")); // Islam
		 application.setNationalityCode(commonService.findNationalityCodeByCode("1")); // Warganegara
		 application.setRaceCode(commonService.findRaceCodeByCode("0100")); // Melayu
		 application.setEthnicityCode(commonService.findEthnicityCodeByCode("0100")); // Melayu
		 application.setMaritalCode(commonService.findMaritalCodeByCode("1")); // Bujang
		 application.setDisabilityCode(commonService.findDisabilityCodeByCode("12")); // Tidak
		 application.setResidencyCode(commonService.findResidencyCodeByCode("101")); // no data in seed, created test code for residency in unit
		 application.setApplicant(applicant);
		 LOG.debug("intake status : {} ", application.getBidStatus());

        try {
            applicationService.applyIntake(intake, application);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.notNull(application, "application 1 is not drafted");
		 LOG.debug("intake status : {} ", application.getBidStatus());
         
        return self();
    }


}

