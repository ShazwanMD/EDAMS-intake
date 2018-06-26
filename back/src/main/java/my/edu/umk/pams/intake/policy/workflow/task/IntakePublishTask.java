package my.edu.umk.pams.intake.policy.workflow.task;

import my.edu.umk.pams.connector.payload.CohortCodePayload;
import my.edu.umk.pams.connector.payload.FacultyCodePayload;
import my.edu.umk.pams.connector.payload.IntakePayload;
import my.edu.umk.pams.connector.payload.IntakeSessionCodePayload;
import my.edu.umk.pams.connector.payload.ProgramCodePayload;
import my.edu.umk.pams.connector.payload.ProgramLevelPayload;
import my.edu.umk.pams.intake.IntakeConstants;
import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.model.InProgramFieldCode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.policy.event.PolicyIntakeEvent;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.security.service.SecurityService;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static my.edu.umk.pams.intake.core.InFlowState.PUBLISHED;

@Component("intake_publish_ST")
public class IntakePublishTask extends BpmnActivityBehavior
        implements ActivityBehavior {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(IntakePublishTask.class);

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private ApplicationContext applicationContext;
    
    @Autowired
    private CommonService commonService;
    
    /**
     * @param execution
     * @throws Exception
     */
    public void execute(ActivityExecution execution) throws Exception {
        LOG.debug("publishing intake");

        // retrieve intake from variable
        Long intakeId = (Long) execution.getVariable(IntakeConstants.INTAKE_ID);
        LOG.debug("publish1");
        InIntake intake = policyService.findIntakeById(intakeId);
        LOG.debug("publish2:{}",intakeId);
        
        // update flow state
        intake.getFlowdata().setState(PUBLISHED);
        intake.getFlowdata().setPublishedDate(new Timestamp(currentTimeMillis()));
        intake.getFlowdata().setPublisherId(securityService.getCurrentUser().getId());
        policyService.updateIntake(intake);
        LOG.debug("update publish3");
//        
//        // <program_code>-CHRT-<academic_session_code>
//        List<ProgramCodePayload> programCodePayloadList = new ArrayList<ProgramCodePayload>();
//        List<InProgramOffering> prgrmOffering =  intake.getProgramOfferings();
//        for (InProgramOffering inProgramOffering : prgrmOffering) {
//    	   
//    	   InProgramCode programCode = inProgramOffering.getProgramCode();
//    	   String cohortCode = programCode.getFacultyCode().getCode()
//                   + "-" + programCode.getProgramLevel().getCode()
//                   + "-" + programCode.getCode()
//                   + "-CHRT-"
//                   + intake.getSession().getCode();
//           CohortCodePayload chrtPayload = new CohortCodePayload();
//           
//           ProgramCodePayload programCodePayload = new ProgramCodePayload();
//           
//           programCodePayload.setCode(programCode.getCode());
//           programCodePayload.setDescriptionEn(programCode.getDescriptionEn());
//           programCodePayload.setDescriptionMs(programCode.getDescriptionMs());
//           
//           FacultyCodePayload facultyCodePayload = new FacultyCodePayload();
//           facultyCodePayload.setCode(programCode.getFacultyCode().getCode());
//           facultyCodePayload.setDescription(programCode.getFacultyCode().getDescriptionEn());
//           programCodePayload.setFacultyCode(facultyCodePayload);
//           
//           ProgramLevelPayload prgrmLvlPayload = new ProgramLevelPayload();
//           prgrmLvlPayload.setCode(intake.getProgramLevel().getCode());
//           prgrmLvlPayload.setDescription(intake.getProgramLevel().getDescription());
//           programCodePayload.setProgramLevel(prgrmLvlPayload);
//           
//           chrtPayload.setCode(cohortCode);
//           chrtPayload.setDescription(intake.getDescriptionEn());
//           chrtPayload.setProgramCode(programCodePayload);
//           
//           IntakePayload intakePayload = new IntakePayload();
////           intakePayload.setCohort(cohortCode);
//           
//           LOG.debug("Cohort:{}",chrtPayload.getCode());
//           
//           IntakeSessionCodePayload intakeSessionPayload = new IntakeSessionCodePayload();
//           intakeSessionPayload.setCode(intake.getSession().getCode());
//           intakeSessionPayload.setDescription(intake.getSession().getDescriptionEn());
//           intakePayload.setIntakeSession(intakeSessionPayload);
//           
//           programCodePayloadList.add(programCodePayload);
//           
//           intakePayload.setOfferedProgramCodes(programCodePayloadList);
//           PolicyIntakeEvent event = new PolicyIntakeEvent(intakePayload);
//           applicationContext.publishEvent(event);
//	   }
//        
    }
}
