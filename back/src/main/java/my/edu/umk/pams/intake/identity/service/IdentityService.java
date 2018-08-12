package my.edu.umk.pams.intake.identity.service;

import my.edu.umk.pams.connector.payload.StaffPayload;
import my.edu.umk.pams.connector.payload.StudyCenterPayload;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.identity.dao.RecursiveGroupException;
import my.edu.umk.pams.intake.identity.model.*;

import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface IdentityService {

    //====================================================================================================
    // PRINCIPAL
    //====================================================================================================

    InPrincipal findPrincipalById(Long id);

    InPrincipal findPrincipalByName(String name);

    List<InPrincipal> findPrincipals(String filter, Integer offset, Integer limit);

    Set<String> findSids(InPrincipal principal);

    Integer countPrincipal(String filter);

    void addPrincipalRole(InPrincipal principal, InPrincipalRole principalRole);

    void deletePrincipalRole(InPrincipal principal, InPrincipalRole principalRole);
    
    InPrincipalRole findByPrincipal(InPrincipal principal);

    //====================================================================================================
    // USER
    //====================================================================================================

    InUser findUserByEmail(String email);

    InUser findUserByUsername(String username);

    InUser findUserById(Long id);

    InUser findUserByActor(InActor actor);

    List<InUser> findUsers(Integer offset, Integer limit);

    List<InUser> findUsers(String filter, Integer offset, Integer limit);

    Integer countUser();

    Integer countUser(String filter);

    boolean hasUser(InActor actor);

    boolean isUserExists(String username);

    void saveUser(InUser user);

    void updateUser(InUser user);

    void removeUser(InUser user);

    void changePassword(InUser user, String newPassword);
    
//    void changeEmail(InUser user, String newEmail);
    
    void changeEmail(InApplicant applicant, String newEmail);
    

    //====================================================================================================
    // GROUP
    //====================================================================================================

    InGroup getRootGroup();

    InGroup findGroupByName(String name);

    InGroup findOrCreateGroupByName(String name);

    InGroup findGroupById(Long id);

    List<InGroup> findGroups(Integer offset, Integer limit);

    List<InGroup> findImmediateGroups(InPrincipal principal);

    List<InGroup> findImmediateGroups(InPrincipal principal, Integer offset, Integer limit);

    Set<InGroup> findEffectiveGroups(InPrincipal principal);

    Set<String> findEffectiveGroupsAsString(InPrincipal principal);

    List<InGroup> findAvailableUserGroups(InUser user);

    List<InPrincipal> findAvailableGroupMembers(InGroup group);

    List<InPrincipal> findGroupMembers(InGroup group);

    List<InPrincipal> findGroupMembers(InGroup group, Integer offset, Integer limit);

    Integer countGroup();

    Integer countGroupMember(InGroup group);

    boolean isGroupExists(String name);

    boolean hasMembership(InGroup group, InPrincipal principal);

    InGroup createGroupWithRole(String groupName, InRoleType... types);

    void saveGroup(InGroup group);

    void updateGroup(InGroup group);

    void removeGroup(InGroup group);

    void addGroupMember(InGroup group, InPrincipal principal) throws RecursiveGroupException;

    void deleteGroupMember(InGroup group, InPrincipal principal);

    InGroupMember findMemberByPrincipal(InPrincipal principal);
    //====================================================================================================
    // ACTOR
    //====================================================================================================

    InActor findActorById(Long id);

    InActor findActorByIdentityNo(String identityNo);

    List<InActor> findActors(Integer offset, Integer limit);

    List<InActor> findActors(InActorType type, Integer offset, Integer limit);

    List<InActor> findActors(String filter, Integer offset, Integer limit);

    List<InActor> findActors(String filter, InActorType type, Integer offset, Integer limit);

    Integer countActor();

    Integer countActor(InActorType type);

    Integer countActor(String filter);

    Integer countActor(String filter, InActorType type);
    
    boolean isActorEmailExists(String email);
    
    boolean isActorIdentityNoExists(String identityNo);

    //====================================================================================================
    // STAFF
    //====================================================================================================

    InStaff findStaffById(Long id);

    InStaff findStaffByStaffNo(String StaffNo);

    InStaff findStaffByNricNo(String nricNo);

    List<InStaff> findStaffs(Integer offset, Integer limit);

    List<InStaff> findStaffs(String filter, Integer offset, Integer limit);

    Integer countStaff();

    Integer countStaff(String filter);

    boolean isStaffEmailExists(String email);

    boolean isStaffNoExists(String StaffNo);

    void saveStaff(InStaff Staff);

    void updateStaff(InStaff Staff);

    void deleteStaff(InStaff Staff);

    void broadcastCreated(InStaff Staff);

    void broadcastUpdated(InStaff Staff);

    //====================================================================================================
    // APPLICANT
    //====================================================================================================

    InApplicant findApplicantById(Long id);

    InApplicant findApplicantByEmail(String email);
 
    InApplicant findApplicantByApplicantNo(String ApplicantNo);

    List<InApplicant> findApplicants(Integer offset, Integer limit);

    List<InApplicant> findApplicants(String filter, Integer offset, Integer limit);

    Integer countApplicant();

    Integer countApplicant(String filter);

    void saveApplicant(InApplicant Applicant);

    void updateApplicant(InApplicant Applicant);

	void updatePrincipal(InPrincipal principal);
	
	void changeAddress(InIntakeApplication application, String newAddress);

	void updateMyIntakeApplication(InIntakeApplication application);

	void saveStaffIMSNonAcademicActive(InStaff staff);

	InGroup findGroupByUser(InUser user);

	void saveStaffIMSNonAcademicInActive(InStaff staff);
	
	void saveUMKCEEnonAcademicStaff(InStaff staff);
	
	void updateUMKCEEnonAcademicStaff(InStaff staff);
	

//	void changeAddress(InIntakeApplication intakeApplication, String newAddress);
}




