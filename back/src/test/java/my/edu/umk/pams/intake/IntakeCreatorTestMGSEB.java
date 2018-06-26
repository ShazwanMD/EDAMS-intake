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
public class IntakeCreatorTestMGSEB {

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
		InGraduateCenter center = commonService.findGraduateCenterByCode("MGSEB");

		// start a new intake
		InIntake intake = new InIntakeImpl();
		intake.setAuditNo(UUID.randomUUID().toString());
		intake.setSourceNo("MASTER/201720182" + System.currentTimeMillis());
		intake.setDescriptionEn("Intake for Program Master 201720181 " + System.currentTimeMillis());
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
//		mckOffering.setProgramFieldCode(null);
		policyService.addProgramOffering(intake, mckOffering);

		//InProgramCode mcn = commonService.findProgramFieldCodeByCode("MCN");
		InProgramOffering mcnOffering = new InProgramOfferingImpl();
//		mcnOffering.setProgramFieldCode(null);
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
		workflowService.completeTask(assignedVerifiedIntakes.get(0)); // TO
																		// APPROVED
		

	}
}
