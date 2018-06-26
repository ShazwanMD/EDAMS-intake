package my.edu.umk.pams.intake.registration.stage;

import com.tngtech.jgiven.Stage;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
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
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static my.edu.umk.pams.intake.IntakeConstants.INTAKE_APPLICATION_REFERENCE_NO;


@JGivenStage
public class WhenPrepareApplicationSubmission extends Stage<WhenPrepareApplicationSubmission> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenPrepareApplicationSubmission.class);

    @Autowired
    private ApplicationService applicationService;
    
    @Autowired
    private PolicyService policyService;

    @Autowired
    private CommonService commonService;
    
    @Autowired
    private IdentityService identityService;

    @ExpectedScenarioState
    private InIntake intake;

    @ExpectedScenarioState
    private InIntakeSession intakeSession;

    @ProvidedScenarioState
    private InApplicant applicant; 
    
    @ProvidedScenarioState
    private List<InIntakeApplication> paidApplications = new ArrayList<>();

    @ProvidedScenarioState
    private List<InIntakeApplication> unpaidApplications = new ArrayList<>();

    @ExpectedScenarioState
    private InUser user;


    public WhenPrepareApplicationSubmission I_prepare_3_applications() {
        
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
    	resident.setDescriptionEn("test resident");
    	resident.setDescriptionMs("test resident");
    	commonService.saveResidencyCode(resident);
    	Assert.notNull(resident, "resident does not exists");
    	
    	//create applicant1
        InApplicant applicant1 = new InApplicantImpl();
        applicant1.setApplicationNo("9999990");
        applicant1.setName("Ahmad Kharizmi bin Khaldun");
        applicant1.setEmail("ibnu_khaldun@gmail.com");
        applicant1.setPhone("0111020202");
        identityService.saveApplicant(applicant1);

    	//create applicant2
        InApplicant applicant2 = new InApplicantImpl();
        applicant2.setApplicationNo("9999991");
        applicant2.setName("Ahmad Sam bin Khaldun Sam");
        applicant2.setEmail("ahmad_sam@gmail.com");
        applicant2.setPhone("0111020203");
        identityService.saveApplicant(applicant2);

    	//create applicant3
        InApplicant applicant3 = new InApplicantImpl();
        applicant3.setApplicationNo("9999992");
        applicant3.setName("Siti Samiya bin Khaldun Sam");
        applicant3.setEmail("siti_samiya@gmail.com");
        applicant3.setPhone("0111020204");
        identityService.saveApplicant(applicant3);

         //Application 1
         InIntakeApplication application1 = new InIntakeApplicationImpl();
         application1.setIntake(intake);
         application1.setReferenceNo("INTAKE/10001");
         application1.setName(applicant1.getName());
         application1.setCredentialNo("910607145581");
         application1.setEmail(applicant1.getEmail());
         application1.setPhone(applicant1.getPhone());
         application1.setAge(26);
         application1.setRank(3);
         application1.setPaid(true);
         application1.setVerified(false);
         application1.setMerit(new BigDecimal("2.85"));
         application1.setPaymentSourceNo("0024188");
         application1.setBidType(InBidType.FIRST);
         application1.setBidStatus(InBidStatus.NEW);
         application1.setBidResponse(InBidResponse.NEW);
         application1.setOkuNo("S12223214");
         application1.setProgramSelection(offering);
         // todo: change to study mode
         // application1.setStudyModeSelection(commonService.findStudyModeByCode("1")); //Full time
		 application1.setGenderCode(commonService.findGenderCodeByCode("1")); // Male
		 application1.setReligionCode(commonService.findReligionCodeByCode("1")); // Islam
		 application1.setNationalityCode(commonService.findNationalityCodeByCode("1")); // Warganegara
		 application1.setRaceCode(commonService.findRaceCodeByCode("0100")); // Melayu
		 application1.setEthnicityCode(commonService.findEthnicityCodeByCode("0100")); // Melayu
		 application1.setMaritalCode(commonService.findMaritalCodeByCode("1")); // Bujang
		 application1.setDisabilityCode(commonService.findDisabilityCodeByCode("12")); // Tidak
		 application1.setResidencyCode(commonService.findResidencyCodeByCode("101")); // no data in seed, created test code for residency in unit
		 application1.setApplicant(applicant1);
		 LOG.debug("intake status : {} ", application1.getBidStatus());

        try {
            applicationService.applyIntake(intake, application1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.notNull(application1, "application 1 is not drafted");
		 LOG.debug("intake status : {} ", application1.getBidStatus());
         

		  //Application 2
          InIntakeApplication  application2 = new InIntakeApplicationImpl();
          application2.setReferenceNo(INTAKE_APPLICATION_REFERENCE_NO); 
          
          application2.setIntake(intake);
          application2.setReferenceNo("INTAKE/10002");
          application2.setName(applicant2.getName());
          application2.setCredentialNo("910607149913");
          application2.setEmail(applicant2.getEmail());
          application2.setPhone(applicant2.getPhone());
          application2.setAge(25);
          application2.setRank(2);
          application2.setPaid(false);
          application1.setVerified(true);
          application2.setMerit(new BigDecimal("2.80"));
          application2.setPaymentSourceNo("002268sd");
          application2.setBidType(InBidType.FIRST);
          application2.setBidStatus(InBidStatus.NEW);
          application2.setBidResponse(InBidResponse.NEW);
          application2.setOkuNo("S17453214");
          // todo: studymodeselection
          // application2.setStudyMode(commonService.findStudyModeByCode("1")); //Full time
          application2.setGenderCode(commonService.findGenderCodeByCode("1")); //Male
          application2.setReligionCode(commonService.findReligionCodeByCode("1")); //Islam    
          application2.setNationalityCode(commonService.findNationalityCodeByCode("1")); //Warganegara  
          application2.setRaceCode(commonService.findRaceCodeByCode("0100")); //Melayu
          application2.setEthnicityCode(commonService.findEthnicityCodeByCode("0100")); //Melayu
          application2.setMaritalCode(commonService.findMaritalCodeByCode("1")); //Bujang
          application2.setDisabilityCode(commonService.findDisabilityCodeByCode("12")); //Tidak cacat
          application2.setResidencyCode(commonService.findResidencyCodeByCode("101")); // no data in seed, created test code for residency in unit
          application2.setApplicant(applicant2);
          application2.setProgramSelection(offering);
          LOG.debug("intake status : {} ", application2.getBidStatus());

        try {
            applicationService.applyIntake(intake, application2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.notNull(application2, "applicantion2 is not drafted");
          LOG.debug("intake status : {} ", application2.getBidStatus());
          
          //Application 3
          InIntakeApplication  application3 = new InIntakeApplicationImpl();
          application3.setIntake(intake);
          application3.setReferenceNo("INTAKE/10003");
          application3.setName(applicant3.getName());
          application3.setCredentialNo("870607149913");
          application3.setEmail(applicant3.getEmail());
          application3.setPhone(applicant3.getPhone());
          application3.setPaid(true);
          application1.setVerified(true);
          application3.setAge(30);
          application3.setRank(1);
          application3.setMerit(new BigDecimal("3.80"));
          application3.setPaymentSourceNo("767268sd");
          application3.setBidType(InBidType.FIRST);
          application3.setBidStatus(InBidStatus.NEW);
          application3.setBidResponse(InBidResponse.NEW);
          application3.setOkuNo("S17453217874");  
          // application3.setStudyMode(commonService.findStudyModeByCode("2")); //Part time
          application3.setGenderCode(commonService.findGenderCodeByCode("2")); //Female
          application3.setReligionCode(commonService.findReligionCodeByCode("1")); //Islam  
          application3.setNationalityCode(commonService.findNationalityCodeByCode("1")); //Warganegara                
          application3.setRaceCode(commonService.findRaceCodeByCode("0100")); //Melayu   
          application3.setEthnicityCode(commonService.findEthnicityCodeByCode("0403")); //Minangkabau       
          application3.setMaritalCode(commonService.findMaritalCodeByCode("1")); //Bujang
          application3.setDisabilityCode(commonService.findDisabilityCodeByCode("12")); //Tidak cacat
          application3.setResidencyCode(commonService.findResidencyCodeByCode("101")); // no data in seed, created test code for residency in unit
          application3.setApplicant(applicant3);
          application3.setProgramSelection(offering);
          LOG.debug("intake status : {} ", application3.getBidStatus());

        try {
            applicationService.applyIntake(intake, application3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.notNull(application3, "applicantion3 is not drafted");
          LOG.debug("intake status : {} ", application3.getBidStatus());
         

         return self();
    }
    
    
    
    public WhenPrepareApplicationSubmission I_submit_3_applications() {

        List<String> referenceNos = Arrays.asList("INTAKE/10001", "INTAKE/10002", "INTAKE/10003");

        //submit each application
        for (String refNo : referenceNos) {
            InIntakeApplication application = applicationService.findIntakeApplicationByReferenceNo(refNo);
            applicationService.submitIntakeApplication(intake, application);
            Assert.notNull(application, refNo + " application is null");

            InBidStatus expected = InBidStatus.SUBMITTED;
            InBidStatus found = application.getBidStatus();
            String message = refNo + "application expected " + expected + ", found " + found;
            Assert.isTrue(expected.equals(found), message);
            LOG.debug("intake status : {} ", application.getBidStatus());

            if (application.isPaid())
                paidApplications.add(application);
            else
                unpaidApplications.add(application);
        }

        return self() ;
    }
}

