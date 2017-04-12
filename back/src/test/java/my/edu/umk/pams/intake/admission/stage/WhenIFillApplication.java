package my.edu.umk.pams.intake.admission.stage;

import com.tngtech.jgiven.Stage;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.application.model.InBidResponse;
import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InBidType;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InResidencyCode;
import my.edu.umk.pams.intake.common.model.InResidencyCodeImpl;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InApplicantImpl;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.security.service.SecurityService;
import my.edu.umk.pams.intake.system.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static my.edu.umk.pams.intake.IntakeConstants.INTAKE_APPLICATION_REFERENCE_NO;


@JGivenStage
public class WhenIFillApplication extends Stage<WhenIFillApplication> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIFillApplication.class);

    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private PolicyService policyService;

    @Autowired
    private SystemService systemService;
    
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private IdentityService identityService;

    @ExpectedScenarioState
    private InIntake intake;

    @ExpectedScenarioState
    private InIntakeSession intakeSession;

    @ProvidedScenarioState
    private InApplicant applicant; 

    @ExpectedScenarioState
    private InUser user;

    @ProvidedScenarioState
    private InIntakeApplication application;

    public WhenIFillApplication I_fill_in_application() {
       
    	InIntake intake = policyService.findIntakeByReferenceNo("201720181/MASTER");
        Assert.notNull(intake, "intake is null");
         
        Map<String, Object> map = new HashMap<String, Object>();
    	map.put("intakeSession", intakeSession);
    	map.put("programLevel", intake.getProgramLevel());
    	String referenceNo = systemService.generateFormattedReferenceNo(INTAKE_APPLICATION_REFERENCE_NO, map);
    	
    	
    	InResidencyCode resident = new InResidencyCodeImpl();
    	resident.setCode("101");
    	resident.setDescription("test resident");
    	commonService.saveResidencyCode(resident);
    	Assert.notNull(resident, "resident is null");
    	LOG.debug("test resident {} :", resident);
    	
    	
    	
        InApplicant applicant = new InApplicantImpl();
        applicant.setApplicationNo("9999999");
        applicant.setName("dummy john bin john doe");
        applicant.setEmail("dummyjohn@gmail.com");
        applicant.setPhone("0111020202");
        identityService.saveApplicant(applicant);
        Assert.notNull(applicant, "applicant is null");
    	
         application = new InIntakeApplicationImpl();
         application.setReferenceNo(INTAKE_APPLICATION_REFERENCE_NO); 
         
         BigDecimal merit = new BigDecimal("2.75");
        
         application.setIntake(intake);
         application.setReferenceNo(referenceNo);
         application.setName("Ahmad Kharizmi bin Khaldun");
         application.setCredentialNo("910607145581");
         application.setEmail("ibnu_khaldun@gmail.com");
         application.setPhone("0111020202");
         application.setAge(26);
         application.setRank(3);
         application.setMerit(merit);
         application.setPaymentSourceNo("0024188");
         application.setSchoolBatch(2006/2010);
         application.setSchoolName("SMK Sultan Ismail");
         application.setBidType(InBidType.FIRST);
         application.setBidStatus(InBidStatus.NEW);
         application.setBidResponse(InBidResponse.NEW);
         application.setOkuNo("S12223214");
         
         application.setStudyMode(commonService.findStudyModeByCode("1")); 
         Assert.notNull(commonService.findStudyModeByCode("1"), "studymode is null");
         
         application.setGenderCode(commonService.findGenderCodeByCode("1"));
         Assert.notNull(commonService.findGenderCodeByCode("1"), "studymode is null");
         
         application.setReligionCode(commonService.findReligionCodeByCode("1"));
         Assert.notNull(commonService.findReligionCodeByCode("1"), "studymode is null");
         
         application.setNationalityCode(commonService.findNationalityCodeByCode("1"));
         Assert.notNull(commonService.findNationalityCodeByCode("1"), "studymode is null");
         
         application.setRaceCode(commonService.findRaceCodeByCode("0100"));
         Assert.notNull(commonService.findRaceCodeByCode("0100"), "studymode is null");
   
         application.setEthnicityCode(commonService.findEthnicityCodeByCode("0100"));
         Assert.notNull(commonService.findEthnicityCodeByCode("0100"), "studymode is null");
         
         application.setMaritalCode(commonService.findMaritalCodeByCode("1"));
         Assert.notNull(commonService.findMaritalCodeByCode("1"), "studymode is null");
         
         application.setDisabilityCode(commonService.findDisabilityCodeByCode("12"));
         Assert.notNull(commonService.findDisabilityCodeByCode("12"), "studymode is null");
         
         application.setResidencyCode(commonService.findResidencyCodeByCode("101"));
         Assert.notNull(commonService.findResidencyCodeByCode("101"), "residency is null");
         
         application.setApplicant(applicant);
         Assert.notNull(applicant, "studymode is null");
         
         
       

         applicationService.draftIntakeApplication(intake, application);
         //draftIntakeApplication(intake, application);
        
    	
    	/*Map<String, Object> map = new HashMap<String, Object>();
    	map.put("intakeSession", intakeSession);
    	map.put("programLevel", intake.getProgramLevel());
    	String referenceNo = systemService.generateFormattedReferenceNo(INTAKE_APPLICATION_REFERENCE_NO, map);

    	// start an intakeApplication
    	intakeApplication = new InIntakeApplicationImpl();
    	intakeApplication.setIntake(this.intake);
    	intakeApplication.setReferenceNo(referenceNo);
    	intakeApplication.setName("dummy john bin john doe");
    	intakeApplication.setEmail("dummyjohn@gmail.com");
    	intakeApplication.setPhone("0111020202");
    	intakeApplication.setOkuNo("S12223214");
    	intakeApplication.setSchoolName("SMKZA");
    	intakeApplication.setBidStatus(InBidStatus.DRAFTED);
    	applicationService.draftIntakeApplication(intake, intakeApplication);*/
         
        return self() ;
    }
}

