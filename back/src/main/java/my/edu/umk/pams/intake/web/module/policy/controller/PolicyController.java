package my.edu.umk.pams.intake.web.module.policy.controller;

import org.activiti.engine.task.Task;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InFacultyCode;
import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.model.InProgramFieldCode;
import my.edu.umk.pams.intake.common.model.InStudyMode;
import my.edu.umk.pams.intake.common.model.InSupervisorCode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.core.InFlowState;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeImpl;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.model.InIntakeSessionImpl;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.model.InStudyModeOffering;
import my.edu.umk.pams.intake.policy.model.InStudyModeOfferingImpl;
import my.edu.umk.pams.intake.policy.model.InSupervisorOffering;
import my.edu.umk.pams.intake.policy.model.InSupervisorOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.web.module.application.controller.ApplicationTransformer;
import my.edu.umk.pams.intake.web.module.application.vo.IntakeApplication;
import my.edu.umk.pams.intake.web.module.common.controller.CommonTransformer;
import my.edu.umk.pams.intake.web.module.common.vo.ProgramCode;
import my.edu.umk.pams.intake.web.module.common.vo.SupervisorCode;
import my.edu.umk.pams.intake.web.module.policy.vo.Intake;
import my.edu.umk.pams.intake.web.module.policy.vo.IntakeSession;
import my.edu.umk.pams.intake.web.module.policy.vo.IntakeTask;
import my.edu.umk.pams.intake.web.module.policy.vo.ProgramLevel;
import my.edu.umk.pams.intake.web.module.policy.vo.ProgramOffering;
import my.edu.umk.pams.intake.web.module.policy.vo.StudyModeOffering;
import my.edu.umk.pams.intake.web.module.policy.vo.SupervisorOffering;
import my.edu.umk.pams.intake.workflow.service.WorkflowService;

import static java.lang.Boolean.TRUE;
import static my.edu.umk.pams.intake.core.InFlowState.DRAFTED;
import static my.edu.umk.pams.intake.core.InFlowState.VERIFIED;
import static my.edu.umk.pams.intake.core.InFlowState.PUBLISHED;
import static my.edu.umk.pams.intake.workflow.service.WorkflowConstants.REMOVE_DECISION;

@RestController
@RequestMapping("/api/policy")
public class PolicyController {

    private static final Logger LOG = LoggerFactory.getLogger(PolicyController.class);

    @Autowired
    private PolicyService policyService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private PolicyTransformer policyTransformer;

    @Autowired
    private ApplicationTransformer applicationTransformer;

    @Autowired
    private CommonTransformer commonTransformer;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ==================================================================================================== //
    //  INTAKE SESSION
    // ==================================================================================================== //

    @RequestMapping(value = "/intakeSessions", method = RequestMethod.GET)
    public ResponseEntity<List<IntakeSession>> findIntakeSessions() {
        List<InIntakeSession> sessions = policyService.findIntakeSessions(0, 100);
        return new ResponseEntity<List<IntakeSession>>(policyTransformer.toIntakeSessionVos(sessions), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/intakeSessionsCurrent", method = RequestMethod.GET)
    public ResponseEntity<List<IntakeSession>> findIntakeSessionsByCurrent() {
        List<InIntakeSession> sessions = policyService.findIntakeSessionsByCurrent(TRUE);
        return new ResponseEntity<List<IntakeSession>>(policyTransformer.toIntakeSessionVos(sessions), HttpStatus.OK);
    }

    @RequestMapping(value = "/intake-sessions", method = RequestMethod.POST)
    public ResponseEntity<String> saveIntakeSession(@RequestBody IntakeSession vo) {
        InIntakeSession session = new InIntakeSessionImpl();
        session.setCode(vo.getCode());
        session.setLabel(vo.getLabel());
        session.setDescriptionMs(vo.getDescriptionMs());
        session.setDescriptionEn(vo.getDescriptionEn());
        session.setCurrent(vo.isCurrent());
        session.setYear(vo.getYear());
        policyService.saveIntakeSession(session);

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/intake-sessions/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateIntakeSession(@PathVariable String code, @RequestBody IntakeSession vo) {
        InIntakeSession session = policyService.findIntakeSessionByCode(code);
        session.setCode(vo.getCode());
        session.setLabel(vo.getLabel());
        session.setDescriptionMs(vo.getDescriptionMs());
        session.setDescriptionEn(vo.getDescriptionEn());
        session.setCurrent(vo.isCurrent());
        session.setYear(vo.getYear());
        policyService.updateIntakeSession(session);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/intake-sessions/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeIntakeSession(@PathVariable Long id) {
        InIntakeSession session = policyService.findIntakeSessionById(id);
        policyService.removeIntakeSession(session);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  PROGRAM LEVEL
    // ==================================================================================================== //

    @RequestMapping(value = "/programLevels", method = RequestMethod.GET)
    public ResponseEntity<List<ProgramLevel>> findProgramLevels() {
        List<InProgramLevel> levels = policyService.findProgramLevels(0, 100);
        return new ResponseEntity<List<ProgramLevel>>(policyTransformer.toProgramLevelVos(levels), HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  INTAKE
    // ==================================================================================================== //

    @RequestMapping(value = "/intakes/", method = RequestMethod.GET)
    public ResponseEntity<List<Intake>> findIntakes() {
        List<InIntake> intakes = policyService.findIntakesByEndDate(); // todo(uda): pagination
        return new ResponseEntity<List<Intake>>(policyTransformer.toIntakeVos(intakes), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/current", method = RequestMethod.GET)
    public ResponseEntity<List<Intake>> findCurrentIntakes() {
        List<InIntake> intakes = policyService.findIntakes(policyService.findCurrentIntakeSession());
        return new ResponseEntity<List<Intake>>(policyTransformer.toIntakeVos(intakes), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<Intake> findIntakeByReferenceNo(@PathVariable String referenceNo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        return new ResponseEntity<Intake>(policyTransformer.toIntakeVo(intake), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateIntake(@PathVariable String referenceNo, @RequestBody Intake vo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        intake.setDescriptionEn(vo.getDescriptionEn());
        intake.setDescriptionMs(vo.getDescriptionMs());
        intake.setStartDate(vo.getStartDate());
        intake.setEndDate(vo.getEndDate());
        intake.setProjection(vo.getProjection());
        intake.setProgramLevel(policyService.findProgramLevelById(vo.getProgramLevel().getId()));
        intake.setSession(policyService.findIntakeSessionById(vo.getIntakeSession().getId()));
        intake.setGraduateCenter(commonService.findGraduateCenterById(vo.getGraduateCenter().getId()));
        policyService.updateIntake(intake);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/copy", method = RequestMethod.POST)
    public ResponseEntity<String> copyIntake(@PathVariable String referenceNo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        String generatedReferenceNo = policyService.copyIntake(intake);
        return new ResponseEntity<String>(generatedReferenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/archived", method = RequestMethod.GET)
    public ResponseEntity<List<Intake>> findArchivedIntakes() {
        List<InIntake> intakes = policyService.findIntakesByFlowStates(InFlowState.CANCELLED, InFlowState.REMOVED, InFlowState.COMPLETED);
        return new ResponseEntity<List<Intake>>(policyTransformer.toIntakeVos(intakes), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<IntakeTask>> findAssignedIntakes() {
        List<Task> tasks = policyService.findAssignedIntakeTasks(0, 100);
        return new ResponseEntity<List<IntakeTask>>(policyTransformer.toIntakeTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<IntakeTask>> findPooledIntakes() {
        List<Task> tasks = policyService.findPooledIntakeTasks(0, 100);
        return new ResponseEntity<List<IntakeTask>>(policyTransformer.toIntakeTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startIntakeTask(@RequestBody Intake vo) throws Exception {
        InIntake intake = new InIntakeImpl();
        intake.setSourceNo(UUID.randomUUID().toString());
        intake.setAuditNo(UUID.randomUUID().toString());
        intake.setDescriptionEn(vo.getDescriptionEn());
        intake.setDescriptionMs(vo.getDescriptionMs());
        intake.setStartDate(vo.getStartDate());
        intake.setEndDate(vo.getEndDate());
        intake.setProjection(vo.getProjection());
        intake.setProgramLevel(policyService.findProgramLevelById(vo.getProgramLevel().getId()));
        intake.setSession(policyService.findIntakeSessionById(vo.getIntakeSession().getId()));
        intake.setGraduateCenter(commonService.findGraduateCenterById(vo.getGraduateCenter().getId()));
        return new ResponseEntity<String>(policyService.startIntakeTask(intake), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<IntakeTask> findIntakeTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<IntakeTask>(policyTransformer
                .toIntakeTaskVo(policyService.findIntakeTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/claimTask", method = RequestMethod.POST)
    public ResponseEntity<String> claimIntakeTask(@RequestBody IntakeTask vo) {
    	
        Task task = policyService.findIntakeTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
    
    @RequestMapping(value = "/intakes/completeTask", method = RequestMethod.POST)
    public void completeIntakeTask(@RequestBody IntakeTask vo) {
        Task task = policyService.findIntakeTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
    }

    @RequestMapping(value = "/intakes/removeTask", method = RequestMethod.POST)
    public void removeIntakeTask(@RequestBody IntakeTask vo) {
        Task task = policyService.findIntakeTaskByTaskId(vo.getTaskId());
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put(REMOVE_DECISION, TRUE);
        workflowService.completeTask(task, variables);
    }

    @RequestMapping(value = "/intakes/releaseTask", method = RequestMethod.POST)
    public void releaseIntakeTask(@RequestBody IntakeTask vo) {
        Task task = policyService.findIntakeTaskByTaskId(vo.getTaskId());
        workflowService.releaseTask(task);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/programOfferings", method = RequestMethod.GET)
    public ResponseEntity<List<ProgramOffering>> findProgramOfferings(@PathVariable String referenceNo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        return new ResponseEntity<List<ProgramOffering>>(policyTransformer
                .toProgramOfferingVos(policyService.findProgramOfferings(intake)), HttpStatus.OK);
    }

    @RequestMapping(value = "/programOfferings/{id}", method = RequestMethod.GET)
    public ResponseEntity<ProgramOffering> findProgramOfferingsById(@PathVariable Long id) {
        return new ResponseEntity<ProgramOffering>(policyTransformer.toProgramOfferingVo(policyService.findProgramOfferingById(id)), HttpStatus.OK);
    }
 
    @RequestMapping(value = "/intakes/{referenceNo}/programOfferings", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addProgramOfferings(@PathVariable String referenceNo,
                                                       @RequestBody ProgramOffering vo) {
//        try {
            LOG.debug("addProgramOfferings");
            InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
            
            InProgramCode programCode = commonService.findProgramCodeById(vo.getProgramCode().getId());
            InProgramOffering offering = new InProgramOfferingImpl();
            offering.setProjection(vo.getProjection());
            offering.setInterview(vo.getInterview());
            offering.setGeneralCriteria("TODO");
            offering.setSpecificCriteria("TODO");
            offering.setProgramCode(programCode);
            // todo: offering.setStudyCenterCode();
            policyService.addProgramOffering(intake, offering);
            
            if((intake.getFlowdata().getState()).equals(PUBLISHED)) {
         		throw new IllegalArgumentException( "Intake can only be edited in VERIFIED or DRAFTED state");
        }     
//        } catch (Exception e) {
//            LOG.debug(e.getMessage());
//        }
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/programOfferings/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateProgramOfferings(@PathVariable String referenceNo, @RequestBody ProgramOffering vo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        InProgramOffering offering = policyService.findProgramOfferingById(vo.getId());
        InProgramCode programCode = commonService.findProgramCodeById(vo.getProgramCode().getId());
        System.out.println("vo.getInterview() :" + vo.getInterview());
        System.out.println("vo.getGeneralCriteria() :" + vo.getGeneralCriteria());
        System.out.println("vo.getSpecificCriteria() :" + vo.getSpecificCriteria());
        offering.setProjection(vo.getProjection());
        offering.setInterview(vo.getInterview());
        offering.setGeneralCriteria(vo.getGeneralCriteria());
        offering.setSpecificCriteria(vo.getSpecificCriteria());
        offering.setProgramCode(programCode);

        policyService.updateProgramOfferings(intake, offering);
        
        if((intake.getFlowdata().getState()).equals(PUBLISHED)) {
     		throw new IllegalArgumentException( "Intake can only be edited in VERIFIED or DRAFTED state");
    }     
        
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/programOfferings/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteProgramOfferings(@PathVariable String referenceNo,
                                                          @PathVariable Long id) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        InProgramOffering offering = policyService.findProgramOfferingById(id);
        policyService.deleteProgramOffering(intake, offering);
        
        if((intake.getFlowdata().getState()).equals(PUBLISHED)) {
     		throw new IllegalArgumentException( "Intake can only be edited in VERIFIED or DRAFTED state");
    }     
        
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/studyModeOfferings", method = RequestMethod.GET)
    public ResponseEntity<List<StudyModeOffering>> findstudyModeOfferings(@PathVariable String referenceNo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        return new ResponseEntity<List<StudyModeOffering>>(policyTransformer
                .toStudyModeOfferingVos(policyService.findStudyModeOfferings(intake)), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/studyModeOfferings", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addStudyModeOfferings(@PathVariable String referenceNo,
                                                         @RequestBody StudyModeOffering vo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        InStudyMode studyMode = commonService.findStudyModeById(vo.getStudyMode().getId());
        InStudyModeOffering offering = new InStudyModeOfferingImpl();
        offering.setStudyMode(studyMode);
        policyService.addStudyModeOffering(intake, offering);
        
        if((intake.getFlowdata().getState()).equals(PUBLISHED)) {
     		throw new IllegalArgumentException( "Intake can only be edited in VERIFIED or DRAFTED state");
    }     
        
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/studyModeOfferings/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteStudyModeOfferings(@PathVariable String referenceNo,
                                                            @PathVariable Long id) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        InStudyModeOffering offering = policyService.findStudyModeOfferingById(id);
        policyService.deleteStudyModeOffering(intake, offering);
        
        if((intake.getFlowdata().getState()).equals(PUBLISHED)) {
     		throw new IllegalArgumentException( "Intake can only be edited in VERIFIED or DRAFTED state");
    }     
        
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/intakeApplications", method = RequestMethod.GET)
    public ResponseEntity<List<IntakeApplication>> findIntakeApplications(@PathVariable String referenceNo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        return new ResponseEntity<List<IntakeApplication>>(applicationTransformer
                .toSimpleIntakeApplicationVos(applicationService.findIntakeApplications(intake)), HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  GRADUATE CENTER
    // ==================================================================================================== //

    @RequestMapping(value = "/graduateCenter/{code}/intakes", method = RequestMethod.GET)
    public ResponseEntity<List<Intake>> findIntakesByGraduateCenter(@PathVariable String code) {
        InGraduateCenter graduateCenter = commonService.findGraduateCenterByCode(code);
        List<InIntake> intakes = policyService.findIntakes(graduateCenter); // todo(uda): pagination
        return new ResponseEntity<List<Intake>>(policyTransformer.toIntakeVos(intakes), HttpStatus.OK);
    }

    @RequestMapping(value = "/graduateCenter/{code}/intakes/current", method = RequestMethod.GET)
    public ResponseEntity<List<Intake>> findCurrentIntakesByGraduateCenter(@PathVariable String code) {
        InGraduateCenter graduateCenter = commonService.findGraduateCenterByCode(code);
        InIntakeSession intakeSession = policyService.findCurrentIntakeSession();
        List<InIntake> intakes = policyService.findIntakes(intakeSession, graduateCenter); // todo(uda): pagination
        return new ResponseEntity<List<Intake>>(policyTransformer.toIntakeVos(intakes), HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  PROGRAM CODES
    // ==================================================================================================== //

    @RequestMapping(value = "/faculty/{code}/programCodes", method = RequestMethod.GET)
    public ResponseEntity<List<ProgramCode>> findProgramCodes(@PathVariable String code) {
        InFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
        return new ResponseEntity<List<ProgramCode>>(commonTransformer
                .toProgramCodeVos(commonService.findProgramCodes(facultyCode)), HttpStatus.OK);
    }

    // ==================================================================================================== //
    //  SUPERVISOR CODES
    // ==================================================================================================== //

    @RequestMapping(value = "/faculty/{code}/supervisorCodes", method = RequestMethod.GET)
    public ResponseEntity<List<SupervisorCode>> findSupervisorCodes(@PathVariable String code) {
        InFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
        return new ResponseEntity<List<SupervisorCode>>(commonTransformer
                .toSupervisorCodeVos(commonService.findSupervisorCodes(facultyCode)), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/supervisorOfferings", method = RequestMethod.GET)
    public ResponseEntity<List<SupervisorOffering>> findSupervisorOfferings(@PathVariable String referenceNo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        return new ResponseEntity<List<SupervisorOffering>>(policyTransformer
                .toSupervisorOfferingVos(policyService.findSupervisorOfferings(intake)), HttpStatus.OK);
    }

    @RequestMapping(value = "/intakes/{referenceNo}/supervisorOfferings", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addSupervisorOfferings(@PathVariable String referenceNo,
                                                          @RequestBody SupervisorOffering vo) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        InSupervisorCode supervisorCode = commonService.findSupervisorCodeById(vo.getSupervisorCode().getId());
        InSupervisorOffering offering = new InSupervisorOfferingImpl();
        offering.setSupervisorCode(supervisorCode);
        policyService.addSupervisorOffering(intake, offering);
        
        if((intake.getFlowdata().getState()).equals(PUBLISHED)) {
         		throw new IllegalArgumentException( "Intake can only be edited in VERIFIED or DRAFTED state");
        }
        
        return new ResponseEntity<Boolean>(true, HttpStatus.OK){};
    }

    @RequestMapping(value = "/intakes/{referenceNo}/supervisorOfferings/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> deleteSupervisorOfferings(@PathVariable String referenceNo,
                                                             @PathVariable Long id) {
        InIntake intake = policyService.findIntakeByReferenceNo(referenceNo);
        InSupervisorOffering offering = policyService.findSupervisorOfferingById(id);
        policyService.deleteSupervisorOffering(intake, offering);
        
        if((intake.getFlowdata().getState()).equals(PUBLISHED)) {
     		throw new IllegalArgumentException( "Intake can only be edited in VERIFIED or DRAFTED state");
    } 
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================
}
