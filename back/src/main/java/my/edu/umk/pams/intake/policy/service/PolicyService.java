package my.edu.umk.pams.intake.policy.service;

import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import org.activiti.engine.task.Task;

import java.util.List;

public interface PolicyService {

    //====================================================================================================
    // WORKFLOW
    //====================================================================================================

    void completeTask(Task task);

    void assignTask(Task task, InUser user);

    //====================================================================================================
    // INTAKE SESSION
    //====================================================================================================

    InIntakeSession findCurrentIntakeSession();

    InIntakeSession findIntakeSessionById(Long id);

    InIntakeSession findIntakeSessionByCode(String code);

    List<InIntakeSession> findIntakeSessions(Integer offset, Integer limit);

    List<InIntakeSession> findIntakeSessions(String filter, Integer offset, Integer limit);

    Integer countIntakeSession();

    Integer countIntakeSession(String filter);

    void saveIntakeSession(InIntakeSession session);

    void updateIntakeSession(InIntakeSession session);

    void removeIntakeSession(InIntakeSession session);

    //====================================================================================================
    // INTAKE LEVEL
    //====================================================================================================

    InProgramLevel findProgramLevelById(Long id);

    InProgramLevel findProgramLevelByCode(String code);

    List<InProgramLevel> findProgramLevels();

    List<InProgramLevel> findProgramLevels(Integer offset, Integer limit);

    List<InProgramLevel> findProgramLevels(String filter, Integer offset, Integer limit);

    Integer countProgramLevel();

    Integer countProgramLevel(String filter);

    void saveProgramLevel(InProgramLevel level);

    void updateProgramLevel(InProgramLevel level);

    void removeProgramLevel(InProgramLevel level);

    //====================================================================================================
    // INTAKE
    //====================================================================================================

    InIntake findIntakeByTaskId(String taskId);

    List<Task> findAssignedIntakeTasks(Integer offset, Integer limit);

    List<Task> findPooledIntakeTasks(Integer offset, Integer limit);

    void startIntakeTask(InIntake intake);

    void updateIntake(InIntake intake);

    void cancelIntake(InIntake intake);

    void addProgramOffering(InIntake intake, InProgramOffering offering);

    //====================================================================================================
    // INTAKE
    //====================================================================================================

    InIntake findIntakeById(Long id);

    InIntake findIntakeByReferenceNo(String referenceNo);

    InIntake findIntakeBySessionAndCategory(InIntakeSession session, InProgramLevel category);

    List<InIntake> findIntakes(InIntakeSession session);

    List<InIntake> findIntakes(InIntakeSession session, Integer offset, Integer limit);

    List<InIntake> findIntakes(String filter, InIntakeSession session, Integer offset, Integer limit);

    Integer countIntake(InIntakeSession session);

    Integer countIntake(String filter, InIntakeSession session);

    //====================================================================================================
    // PROGRAM OFFERING
    //====================================================================================================

    InProgramOffering findProgramOfferingById(Long id);

    InProgramOffering findProgramOfferingByIntakeAndProgramCode(InIntake intake, InProgramCode programCode);

    List<InProgramOffering> findProgramOfferings(InIntake intake);

}
