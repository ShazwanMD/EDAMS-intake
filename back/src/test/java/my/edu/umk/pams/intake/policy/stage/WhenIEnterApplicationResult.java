package my.edu.umk.pams.intake.policy.stage;

import my.edu.umk.pams.intake.application.model.*;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.*;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InApplicantImpl;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.model.InSupervisorOffering;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static my.edu.umk.pams.intake.IntakeTestConstants.INTAKE_REFERENCE_NO_MGSSEB;

@JGivenStage
public class WhenIEnterApplicationResult extends Stage<WhenIEnterApplicationResult> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIEnterApplicationResult.class);

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private IdentityService identityService;

    @ProvidedScenarioState
    private InIntake intake;

    @ProvidedScenarioState
    private InIntakeApplication application1;

    @ProvidedScenarioState
    private InResultType resultType;

    @ExpectedScenarioState
    private InIntakeSession intakeSession;

    @ExpectedScenarioState
    private InProgramOffering programOffering;

    @ExpectedScenarioState
    private InSupervisorOffering supervisorOffering;

    public WhenIEnterApplicationResult I_enter_application_result() {

        //create offering
        InProgramOffering offering = new InProgramOfferingImpl();
        offering.setProjection(10);
        offering.setGeneralCriteria("2");
        offering.setSpecificCriteria("3.50");
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

        //create applicant1
        InApplicant applicant1 = new InApplicantImpl();
        applicant1.setApplicationNo("9999990");
        applicant1.setName("Ahmad Kharizmi bin Khaldun");
        applicant1.setEmail("ibnu_khaldun@gmail.com");
        applicant1.setPhone("0111020202");
        identityService.saveApplicant(applicant1);

        intake = policyService.findIntakeByReferenceNo(INTAKE_REFERENCE_NO_MGSSEB);
        // start an intakeApplication
        //Application 1
        application1 = new InIntakeApplicationImpl();
        application1.setIntake(intake);
        application1.setReferenceNo("INTAKE/10001");
        application1.setName(applicant1.getName());
        application1.setCredentialNo("910607145581");
        application1.setEmail(applicant1.getEmail());
        application1.setPhone(applicant1.getPhone());
        application1.setAge(26);
        application1.setRank(3);
        application1.setPaid(true);
        application1.setMerit(new BigDecimal("2.85"));
        application1.setPaymentSourceNo("0024188");
        application1.setBidType(InBidType.FIRST);
        application1.setBidStatus(InBidStatus.NEW);
        application1.setBidResponse(InBidResponse.NEW);
        application1.setOkuNo("S12223214");
        application1.setProgramSelection(offering);
        // todo study mode selection
        // application1.setStudyMode(commonService.findStudyModeByCode("1")); //Full time
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


        //adding result
        InResult result = new InResultImpl();
        result.setResultType(InResultType.BACHELOR);
        result.setGraduationYear(2017);
        result.setResultNumeric(new BigDecimal(3.3));
        result.setName("11111111");
        result.setApplication(application1);
        applicationService.addResult(application1, result);
        LOG.debug("results year:{}, cgpa:{}", result.getGraduationYear(), result.getName());

        //adding grade code
        InGradeCode grade = new InGradeCodeImpl();
        grade.setCode("grade123");
        grade.setDescription("test grade desc");
        grade.setOrdinal(1);
        commonService.saveGradeCode(grade);


        //adding subject code
        InSubjectCode subject = new InSubjectCodeImpl();
        subject.setCode("subject123");
        subject.setDescriptionEn("test subject description");
        subject.setDescriptionMs("test subject description");

        commonService.saveSubjectCode(subject);
        return self();
    }
}