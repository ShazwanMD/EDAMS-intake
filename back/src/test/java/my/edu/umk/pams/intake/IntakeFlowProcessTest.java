package my.edu.umk.pams.intake;

import my.edu.umk.pams.intake.application.model.InBidStatus;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.model.InStudyMode;
import my.edu.umk.pams.intake.common.model.InSupervisorCode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.config.TestAppConfiguration;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.policy.model.*;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.system.service.SystemService;
import my.edu.umk.pams.intake.workflow.service.WorkflowService;

import org.activiti.engine.task.Task;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static my.edu.umk.pams.intake.IntakeConstants.INTAKE_APPLICATION_REFERENCE_NO;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author PAMS
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestAppConfiguration.class)
public class IntakeFlowProcessTest {

    private static final Logger LOG = LoggerFactory.getLogger(IntakeFlowProcessTest.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private IdentityService identityService;

    @Before
    public void before() {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("root", "abc123");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

    @Test
    @Rollback(false)
    public void testDraftWorkflow() throws Exception {
        InProgramLevel level = policyService.findProgramLevelByCode("MASTER");
        InIntakeSession session = policyService.findIntakeSessionByCode("201720181");
        InGraduateCenter center = commonService.findGraduateCenterByCode("CPS");

        // start a new intake
        InIntake intake = new InIntakeImpl();
        intake.setAuditNo(UUID.randomUUID().toString());
        intake.setSourceNo("MASTER/201720182" + System.currentTimeMillis());
        intake.setDescriptionEn("Intake for Program Master 201720181 " + System.currentTimeMillis());
        intake.setDescriptionMs("Intake for Program Master 201720181 " + System.currentTimeMillis());
        intake.setProjection(100);
        intake.setStartDate(new Date());
        intake.setEndDate(new Date());
        intake.setProgramLevel(level);
        intake.setSession(session);
        intake.setGraduateCenter(center);
        String referenceNo = policyService.startIntakeTask(intake);

        // verify intake

        List<Task> draftedTasks = policyService.findAssignedIntakeTasks(0, 100);
        Assert.notEmpty(draftedTasks, "Tasks should not be empty");
        Task draftedTask = draftedTasks.get(0);
        InIntake draftedIntake = policyService.findIntakeByTaskId(draftedTask.getId());

        // search again
        intake = policyService.findIntakeByReferenceNo(referenceNo);

        // preload program offering
        //InProgramCode mck = commonService.findProgramFieldCodeByCode("MCK");
        InProgramOffering mckOffering = new InProgramOfferingImpl();
//        mckOffering.setProgramFieldCode(null);
        policyService.addProgramOffering(intake, mckOffering);

        //InProgramCode mcn = commonService.findProgramFieldCodeByCode("MCN");
        InProgramOffering mcnOffering = new InProgramOfferingImpl();
//        mcnOffering.setProgramFieldCode(null);
        policyService.addProgramOffering(intake, mcnOffering);

        // preload studymode offering
        InStudyMode fulltime = commonService.findStudyModeByCode("1");
        InStudyModeOffering fulltimeOffering = new InStudyModeOfferingImpl();
        fulltimeOffering.setStudyMode(fulltime);

        policyService.addStudyModeOffering(intake, fulltimeOffering);

        InStudyMode parttime = commonService.findStudyModeByCode("2");
        InStudyModeOffering parttimeOffering = new InStudyModeOfferingImpl();
        parttimeOffering.setStudyMode(parttime);
        policyService.addStudyModeOffering(intake, parttimeOffering);

        // preload supervisor offering
        InSupervisorCode supervisor1 = commonService.findSupervisorCodeByCode("00001A");
        InSupervisorOffering supervisorOffering1 = new InSupervisorOfferingImpl();
        supervisorOffering1.setSupervisorCode(supervisor1);

        policyService.addSupervisorOffering(intake, supervisorOffering1);

        InSupervisorCode supervisor2 = commonService.findSupervisorCodeByCode("00019A");
        InSupervisorOffering supervisorOffering2 = new InSupervisorOfferingImpl();
        supervisorOffering2.setSupervisorCode(supervisor2);

        policyService.addSupervisorOffering(intake, supervisorOffering2);
        workflowService.completeTask(draftedTask);

        // publish intake
        List<Task> assignedVerifiedIntakes = policyService.findAssignedIntakeTasks(0, 100);
        Assert.notEmpty(assignedVerifiedIntakes, "Tasks should not be empty");
        workflowService.completeTask(assignedVerifiedIntakes.get(0)); // TO APPROVED

        //Create application samples for citizen and on citizen application

        //application 1 (sample of drafted Citizen application)
        InIntakeApplication application1 = new InIntakeApplicationImpl();
        application1.setIntake(intake);
        application1.setReferenceNo(referenceNo);
        application1.setStudyModeSelection(parttimeOffering);
        application1.setSupervisorSelection(supervisorOffering1);
        application1.setProgramSelection(mckOffering);
        application1.setCredentialNo("234234");
        application1.setOfficialAddress1("test official address 1");
        application1.setResearchTitle("test1a");
        application1.setName("testCitizen bin ting");
        application1.setEmail("testCitizen@email.com");
        application1.setPhone("01110202022");
        application1.setNationalityCode(commonService.findNationalityCodeByCode("1"));
        application1.setBidStatus(InBidStatus.NEW);
        application1.setApplicant((InApplicant) identityService.findUserByUsername("applicant1").getActor());
        applicationService.applyIntake(intake, application1);

        
      //application 2 (sample of Submitted Citizen application)
        InIntakeApplication application2 = new InIntakeApplicationImpl();
        application2.setIntake(intake);
        application2.setReferenceNo(referenceNo);
        application2.setStudyModeSelection(parttimeOffering);
        application2.setSupervisorSelection(supervisorOffering1);
        application2.setProgramSelection(mckOffering);
        application2.setCredentialNo("234234");
        application2.setMailingAddress1("mailing address 1");
        application2.setMailingAddress2("mailing address 2");
        application2.setMailingAddress3("mailing address 3");
        application2.setMailingPostcode("mailing postcode");
        application2.setMailingCountryCode(commonService.findCountryCodeByCode("M01"));
        application2.setMailingStateCode(commonService.findStateCodeByCode("13"));
        application2.setResearchTitle("test1b");
        application2.setName("testCitizen bin ting");
        application2.setEmail("testCitizen@email.com");
        application2.setPhone("011102202022");
        application2.setNationalityCode(commonService.findNationalityCodeByCode("1"));
        application2.setBidStatus(InBidStatus.NEW);
        application2.setApplicant((InApplicant) identityService.findUserByUsername("applicant2").getActor());
        applicationService.applyIntake(intake, application2);
        applicationService.submitIntakeApplication(draftedIntake, application2);

      //application 3 (sample of Drafted non citizen application)
        InIntakeApplication application3 = new InIntakeApplicationImpl();
        application3.setIntake(intake);
        application3.setReferenceNo(referenceNo);
        application3.setStudyModeSelection(fulltimeOffering);
        application3.setSupervisorSelection(supervisorOffering2);
        application3.setProgramSelection(mcnOffering);
        application3.setCredentialNo("123123");
        application3.setOfficialAddress1("test official address 1");
        application3.setResearchTitle("test2");
        application3.setName("testNonCitizen bin ting");
        application3.setEmail("testNonCitizen@email.com");
        application3.setPhone("011102021221");
        application3.setNationalityCode(commonService.findNationalityCodeByCode("2"));
        application3.setBidStatus(InBidStatus.NEW);
        application3.setApplicant((InApplicant) identityService.findUserByUsername("applicant3").getActor());
        applicationService.applyIntake(intake, application3);

        
        //application 4 (sample of Submitted non citizen application)
        InIntakeApplication application4 = new InIntakeApplicationImpl();
        application4.setIntake(intake);
        application4.setReferenceNo(referenceNo);
        application4.setStudyModeSelection(fulltimeOffering);
        application4.setSupervisorSelection(supervisorOffering2);
        application4.setProgramSelection(mcnOffering);
        application4.setCredentialNo("123123");
        application4.setOfficialAddress1("test official address 1");
        application4.setOfficialAddress2("test official address 2");
        application4.setOfficialAddress3("test official address 3");
        application4.setOfficialPostcode("test official postcode");
        application4.setOfficialCountryCode(commonService.findCountryCodeByCode("A03"));
        application4.setOfficialStateCode(commonService.findStateCodeByCode("02"));
        application4.setResearchTitle("test2");
        application4.setName("testNonCitizen bin ting");
        application4.setEmail("testNonCitizen@email.com");
        application4.setPhone("011102021221");
        application4.setNationalityCode(commonService.findNationalityCodeByCode("2"));
        application4.setBidStatus(InBidStatus.NEW);
        application4.setApplicant((InApplicant) identityService.findUserByUsername("applicant4").getActor());
        applicationService.applyIntake(intake, application4);
        applicationService.submitIntakeApplication(draftedIntake, application4);
    }

}
