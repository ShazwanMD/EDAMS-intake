package my.edu.umk.pams.intake.policy.service;

import my.edu.umk.pams.intake.IntakeConstants;
import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.model.InStudyMode;
import my.edu.umk.pams.intake.common.model.InSupervisorCode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.core.InFlowState;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.policy.dao.InIntakeDao;
import my.edu.umk.pams.intake.policy.dao.InIntakeSessionDao;
import my.edu.umk.pams.intake.policy.dao.InProgramLevelDao;
import my.edu.umk.pams.intake.policy.model.*;
import my.edu.umk.pams.intake.security.service.SecurityService;
import my.edu.umk.pams.intake.system.service.SystemService;
import my.edu.umk.pams.intake.workflow.service.WorkflowConstants;
import my.edu.umk.pams.intake.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.Validate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static my.edu.umk.pams.intake.IntakeConstants.INTAKE_REFERENCE_NO;
import static my.edu.umk.pams.intake.core.InFlowState.DRAFTED;
import static my.edu.umk.pams.intake.core.InFlowState.VERIFIED;

@Transactional
@Service("policyService")
public class PolicyServiceImpl implements PolicyService {

    private static final Logger LOG = LoggerFactory.getLogger(PolicyServiceImpl.class);

    @Autowired
    private InIntakeSessionDao intakeSessionDao;

    @Autowired
    private InProgramLevelDao programLevelDao;

    @Autowired
    private InIntakeDao intakeDao;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationContext applicationContext;

    //====================================================================================================
    // WORKFLOW
    //====================================================================================================

    @Override
    public void completeTask(Task task) {
        workflowService.completeTask(task);
    }

    public void assignTask(Task task, InUser user) {
        workflowService.assignTask(task, user.getName());
    }

    //====================================================================================================
    // INTAKE SESSION
    //====================================================================================================

    @Override
    public InIntakeSession findCurrentIntakeSession() {
        return intakeSessionDao.findCurrent();
    }

    @Override
    public InIntakeSession findIntakeSessionById(Long id) {
        return intakeSessionDao.findById(id);
    }

    @Override
    public InIntakeSession findIntakeSessionByCode(String code) {
        return intakeSessionDao.findByCode(code);
    }
    
    @Override
    public List<InIntakeSession> findIntakeSessionsByCurrent(Boolean current) {
        return intakeSessionDao.findInIntakeSessionsByCurrent(current);
    }

    @Override
    public List<InIntakeSession> findIntakeSessions(Integer offset, Integer limit) {
        return intakeSessionDao.find(offset, limit);
    }

    @Override
    public List<InIntakeSession> findIntakeSessions(String filter, Integer offset, Integer limit) {
        return intakeSessionDao.find(filter, offset, limit);
    }

    @Override
    public Integer countIntakeSession() {
        return intakeSessionDao.count();
    }

    @Override
    public Integer countIntakeSession(String filter) {
        return intakeSessionDao.count(filter);
    }


    @Override
    public void saveIntakeSession(InIntakeSession session) {
        intakeSessionDao.save(session, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateIntakeSession(InIntakeSession session) {
        intakeSessionDao.update(session, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeIntakeSession(InIntakeSession session) {
        intakeSessionDao.delete(session, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //====================================================================================================
    // PROGRAM LEVEL
    //====================================================================================================

    @Override
    public InProgramLevel findProgramLevelById(Long id) {
        return programLevelDao.findById(id);
    }

    @Override
    public InProgramLevel findProgramLevelByCode(String code) {
        return programLevelDao.findByCode(code);
    }

    @Override
    public List<InProgramLevel> findProgramLevels() {
        return programLevelDao.find();
    }

    @Override
    public List<InProgramLevel> findProgramLevels(Integer offset, Integer limit) {
        return programLevelDao.find(offset, limit);
    }

    @Override
    public List<InProgramLevel> findProgramLevels(String filter, Integer offset, Integer limit) {
        return programLevelDao.find(filter, offset, limit);
    }

    @Override
    public Integer countProgramLevel() {
        return programLevelDao.count();
    }

    @Override
    public Integer countProgramLevel(String filter) {
        return programLevelDao.count(filter);
    }

    @Override
    public void saveProgramLevel(InProgramLevel level) {
        programLevelDao.save(level, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateProgramLevel(InProgramLevel level) {
        programLevelDao.update(level, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeProgramLevel(InProgramLevel level) {
        programLevelDao.remove(level, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //====================================================================================================
    // INTAKE
    //====================================================================================================

    @Override
    public InIntake findIntakeByTaskId(String taskId) {
        Task task = workflowService.findTask(taskId);
        Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
        return intakeDao.findById((Long) map.get(IntakeConstants.INTAKE_ID));
    }

    @Override
    public Task findIntakeTaskByTaskId(String taskId) {
    	sessionFactory.getCurrentSession().flush();
        return workflowService.findTask(taskId);
    }

    @Override
    public List<Task> findAssignedIntakeTasks(Integer offset, Integer limit) {
        return workflowService.findAssignedTasks(InIntake.class.getName(), offset, limit);
    }

    @Override
    public List<Task> findPooledIntakeTasks(Integer offset, Integer limit) {
        return workflowService.findPooledTasks(InIntake.class.getName(), offset, limit);
    }

    @Override
    public String startIntakeTask(InIntake intake) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("programLevel", intake.getProgramLevel());
        map.put("graduateCenter", intake.getGraduateCenter());
        String refNo = systemService.generateFormattedReferenceNo(INTAKE_REFERENCE_NO, map);
        LOG.debug("User copy cannot be null " +securityService.getCurrentUser());
        // save and process
        intake.setReferenceNo(refNo);
        intakeDao.saveOrUpdate(intake, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(intake);

        // trigger workflow
        workflowService.processWorkflow(intake, prepareVariables(intake));
        LOG.debug("Processing application with refNo {}", new Object[]{refNo});
        return refNo;
    }

    @Override
    public void updateIntake(InIntake intake) {
        intakeDao.update(intake, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void cancelIntake(InIntake intake) {
        Validate.notNull(intake, "Intake cannot be null");
        Validate.isTrue(VERIFIED.equals(intake.getFlowdata().getState()) 
        		|| DRAFTED.equals(intake.getFlowdata().getState()), "Intake can only be configured in VERIFIED or DRAFTED state");
        intakeDao.update(intake, securityService.getCurrentUser());

        List<InProgramOffering> offerings = findProgramOfferings(intake);
        // todo(uda) intake cancel event

        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addSupervisorOffering(InIntake intake, InSupervisorOffering supervisorOffering) {
        Validate.notNull(intake, "Intake cannot be null");
        Validate.notNull(supervisorOffering, "Offering cannot be null");
        Validate.isTrue(VERIFIED.equals(intake.getFlowdata().getState()) 
        		|| DRAFTED.equals(intake.getFlowdata().getState()), "Intake can only be configured in VERIFIED or DRAFTED state");
        intakeDao.addSupervisorOffering(intake, supervisorOffering, securityService.getCurrentUser());
    }

    @Override
    public void deleteSupervisorOffering(InIntake intake, InSupervisorOffering supervisorOffering) {
        Validate.notNull(intake, "Intake cannot be null");
        Validate.notNull(supervisorOffering, "Offering cannot be null");
        Validate.isTrue(VERIFIED.equals(intake.getFlowdata().getState()) 
        		|| DRAFTED.equals(intake.getFlowdata().getState()), "Intake can only be configured in VERIFIED or DRAFTED state");
        intakeDao.deleteSupervisorOffering(intake, supervisorOffering, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addProgramOffering(InIntake intake, InProgramOffering programOffering) {
        Validate.notNull(intake, "Intake cannot be null");
        Validate.notNull(programOffering, "Offering cannot be null");
        Validate.isTrue(VERIFIED.equals(intake.getFlowdata().getState()) 
        		|| DRAFTED.equals(intake.getFlowdata().getState()), "Intake can only be configured in VERIFIED or DRAFTED state");
        programOffering.setGeneralCriteria(programOffering.getGeneralCriteria());
        programOffering.setSpecificCriteria(programOffering.getSpecificCriteria());
        intakeDao.addProgramOffering(intake, programOffering, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateProgramOfferings(InIntake intake, InProgramOffering programOffering) {
        Validate.notNull(intake, "Intake cannot be null");
        Validate.notNull(programOffering, "Offering cannot be null");
        Validate.isTrue(VERIFIED.equals(intake.getFlowdata().getState()) 
        		|| DRAFTED.equals(intake.getFlowdata().getState()), "Intake can only be configured in VERIFIED or DRAFTED state");
        intakeDao.updateProgramOfferings(intake, programOffering, securityService.getCurrentUser());
    }

    @Override
    public void deleteProgramOffering(InIntake intake, InProgramOffering programOffering) {
        Validate.notNull(intake, "Intake cannot be null");
        Validate.notNull(programOffering, "Offering cannot be null");
        Validate.isTrue(VERIFIED.equals(intake.getFlowdata().getState()) 
        		|| DRAFTED.equals(intake.getFlowdata().getState()), "Intake can only be configured in VERIFIED or DRAFTED state");
        intakeDao.deleteProgramOffering(intake, programOffering, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addStudyModeOffering(InIntake intake, InStudyModeOffering modeOffering) {
        Validate.notNull(intake, "Intake cannot be null");
        Validate.notNull(modeOffering, "Offering cannot be null");
        Validate.isTrue(VERIFIED.equals(intake.getFlowdata().getState()) 
        		|| DRAFTED.equals(intake.getFlowdata().getState()), "Intake can only be configured in VERIFIED or DRAFTED state");
        intakeDao.addModeOffering(intake, modeOffering, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteStudyModeOffering(InIntake intake, InStudyModeOffering modeOffering) {
        Validate.notNull(intake, "Intake cannot be null");
        Validate.notNull(modeOffering, "Offering cannot be null");
        Validate.isTrue(VERIFIED.equals(intake.getFlowdata().getState()) 
        		|| DRAFTED.equals(intake.getFlowdata().getState()), "Intake can only be configured in VERIFIED or DRAFTED state");
        intakeDao.deleteModeOffering(intake, modeOffering, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    //    public InGroup getAdminGroup() {
//        return groupDao.findByName();
//    }

    //====================================================================================================
    // INTAKE
    //====================================================================================================

    @Override
    public InIntake findIntakeById(Long id) {
        return intakeDao.findById(id);
    }

    @Override
    public InIntake findIntakeByReferenceNo(String referenceNo) {
        return intakeDao.findByReferenceNo(referenceNo);
    }

    @Override
    public InIntake findIntakeBySessionAndCategory(InIntakeSession session, InProgramLevel category) {
        return intakeDao.findBySessionAndCategory(session, category);
    }

    @Override
    public List<InIntake> findIntakes() {
        return intakeDao.find();
    }
    
    @Override
    public List<InIntake> findIntakesByEndDate() {
        return intakeDao.findByEndDate();
    }

    @Override
    public List<InIntake> findIntakesByFlowState(InFlowState flowState) {
        return intakeDao.findByFlowState(flowState);
    }

    @Override
    public List<InIntake> findIntakesByFlowStates(InFlowState... flowStates) {
        return intakeDao.findByFlowStates(flowStates);
    }

    @Override
    public List<InIntake> findIntakes(InGraduateCenter graduateCenter) {
        return intakeDao.find(graduateCenter);
    }

    @Override
    public List<InIntake> findIntakes(InIntakeSession session) {
        return intakeDao.find(session);
    }

    @Override
    public List<InIntake> findIntakes(InIntakeSession session, InGraduateCenter graduateCenter) {
        return intakeDao.find(session, graduateCenter);
    }

    @Override
    public List<InIntake> findIntakes(InIntakeSession session, Integer offset, Integer limit) {
        return intakeDao.find(session, offset, limit);
    }

    @Override
    public List<InIntake> findIntakes(InIntakeSession session, InGraduateCenter graduateCenter, Integer offset, Integer limit) {
        return intakeDao.find(session, graduateCenter, offset, limit);
    }

    @Override
    public List<InIntake> findIntakes(String filter, InIntakeSession session, Integer offset, Integer limit) {
        return intakeDao.find(filter, session, offset, limit);
    }

    @Override
    public List<InIntake> findIntakes(String filter, InIntakeSession session, InGraduateCenter graduateCenter, Integer offset, Integer limit) {
        return intakeDao.find(filter, session, graduateCenter, offset, limit);
    }

    @Override
    public Integer countIntake(InIntakeSession session) {
        return intakeDao.count(session);
    }

    @Override
    public Integer countIntake(InIntakeSession session, InGraduateCenter graduateCenter) {
        return intakeDao.count(session, graduateCenter);
    }

    @Override
    public Integer countIntake(String filter, InIntakeSession session) {
        return intakeDao.count(filter, session);
    }

    @Override
    public Integer countIntake(String filter, InIntakeSession session, InGraduateCenter graduateCenter) {
        return intakeDao.count(filter, session, graduateCenter);
    }

    @Override
    public String copyIntake(InIntake oldIntake) {
        InIntake intake = new InIntakeImpl();
        intake.setSourceNo(UUID.randomUUID().toString());
        intake.setAuditNo(UUID.randomUUID().toString());
        intake.setDescriptionEn(oldIntake.getDescriptionEn());
        intake.setDescriptionMs(oldIntake.getDescriptionMs());
        intake.setStartDate(oldIntake.getStartDate());
        intake.setEndDate(oldIntake.getEndDate());
        intake.setProjection(oldIntake.getProjection());
        intake.setProgramLevel(oldIntake.getProgramLevel());
        intake.setSession(oldIntake.getSession());
        intake.setGraduateCenter(oldIntake.getGraduateCenter());

        // start intake
        String referenceNo = startIntakeTask(intake);
        InIntake copied = findIntakeByReferenceNo(referenceNo);
        // add programOffering, supervisOffering, studyModeOffering
        List<InStudyModeOffering> modeOfferings = intakeDao.findModeOfferings(oldIntake);
        for (InStudyModeOffering o : modeOfferings) {
            InStudyModeOffering offering = new InStudyModeOfferingImpl();
            offering.setStudyMode(o.getStudyMode());
            addStudyModeOffering(copied, offering);
        }
        List<InProgramOffering> programOfferings = intakeDao.findProgramOfferings(oldIntake);
        for (InProgramOffering o : programOfferings) {
            InProgramOffering offering = new InProgramOfferingImpl();
            offering.setProgramCode(o.getProgramCode());
            offering.setProjection(o.getProjection());
            offering.setSpecificCriteria(o.getSpecificCriteria());
            offering.setGeneralCriteria(o.getGeneralCriteria());
            offering.setStudyCenterCode(o.getStudyCenterCode());
            offering.setInterview(o.isInterview());
            addProgramOffering(copied, offering);
        }
        List<InSupervisorOffering> supervisorOfferings = intakeDao.findSupervisorOfferings(oldIntake);
        for (InSupervisorOffering o : supervisorOfferings) {
            InSupervisorOffering offering = new InSupervisorOfferingImpl();
            offering.setSupervisorCode(o.getSupervisorCode());
            addSupervisorOffering(copied, offering);
        }
        return referenceNo;
    }

    //====================================================================================================
    // SUPERVISOR OFFERING
    //====================================================================================================

    @Override
    public InSupervisorOffering findSupervisorOfferingById(Long id) {
        return intakeDao.findSupervisorOfferingById(id);
    }

    @Override
    public InSupervisorOffering findSupervisorOfferingByIntakeAndSupervisorCode(InIntake intake, InSupervisorCode supervisorCode) {
        return intakeDao.findSupervisorOfferingByIntakeAndSupervisorCode(intake, supervisorCode);
    }

    @Override
    public List<InSupervisorOffering> findSupervisorOfferings(InIntake intake) {
        return intakeDao.findSupervisorOfferings(intake);
    }


    //====================================================================================================
    // PROGRAM OFFERING
    //====================================================================================================

    @Override
    public InProgramOffering findProgramOfferingById(Long id) {
        return intakeDao.findProgramOfferingById(id);
    }

    @Override
    public InProgramOffering findProgramOfferingByIntakeAndProgramCode(InIntake intake, InProgramCode programCode) {
        return intakeDao.findProgramOfferingByIntakeAndProgramCode(intake, programCode);
    }

    @Override
    public List<InProgramOffering> findProgramOfferings(InIntake intake) {
        return intakeDao.findProgramOfferings(intake);
    }


    //====================================================================================================
    // STUDY MODE OFFERING
    //====================================================================================================

    @Override
    public InStudyModeOffering findStudyModeOfferingById(Long id) {
        return intakeDao.findModeOfferingById(id);
    }

    @Override
    public InStudyModeOffering findStudyModeOfferingByIntakeAndStudyMode(InIntake intake, InStudyMode studyMode) {
        return intakeDao.findModeOfferingByIntakeAndMode(intake, studyMode);
    }

    @Override
    public List<InStudyModeOffering> findStudyModeOfferings(InIntake intake) {
        return intakeDao.findModeOfferings(intake);
    }

    //====================================================================================================
    // PRIVATE METHODS
    //====================================================================================================

    private Map<String, Object> prepareVariables(InIntake intake) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(IntakeConstants.INTAKE_ID, intake.getId());
        map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
        map.put(WorkflowConstants.REFERENCE_NO, intake.getReferenceNo());
        map.put(WorkflowConstants.REMOVE_DECISION, false);
        map.put(WorkflowConstants.CANCEL_DECISION, false);
        return map;
    }

}
