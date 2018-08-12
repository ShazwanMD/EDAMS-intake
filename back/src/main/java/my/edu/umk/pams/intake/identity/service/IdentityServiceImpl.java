package my.edu.umk.pams.intake.identity.service;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import my.edu.umk.pams.connector.payload.StaffPayload;
import my.edu.umk.pams.connector.payload.StudyCenterPayload;
import my.edu.umk.pams.intake.admission.dao.InCandidateDao;
import my.edu.umk.pams.intake.admission.model.InCandidate;
import my.edu.umk.pams.intake.admission.service.AdmissionService;
import my.edu.umk.pams.intake.application.dao.InIntakeApplicationDao;
import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InIntakeApplicationImpl;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InFacultyCode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.identity.dao.InActorDao;
import my.edu.umk.pams.intake.identity.dao.InApplicantDao;
import my.edu.umk.pams.intake.identity.dao.InGroupDao;
import my.edu.umk.pams.intake.identity.dao.InPrincipalDao;
import my.edu.umk.pams.intake.identity.dao.InStaffDao;
import my.edu.umk.pams.intake.identity.dao.InUserDao;
import my.edu.umk.pams.intake.identity.dao.RecursiveGroupException;
import my.edu.umk.pams.intake.identity.event.StaffCreatedEvent;
import my.edu.umk.pams.intake.identity.event.StaffUpdatedEvent;
import my.edu.umk.pams.intake.identity.model.InActor;
import my.edu.umk.pams.intake.identity.model.InActorType;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InGroup;
import my.edu.umk.pams.intake.identity.model.InGroupImpl;
import my.edu.umk.pams.intake.identity.model.InGroupMember;
import my.edu.umk.pams.intake.identity.model.InGroupMemberImpl;
import my.edu.umk.pams.intake.identity.model.InPrincipal;
import my.edu.umk.pams.intake.identity.model.InPrincipalImpl;
import my.edu.umk.pams.intake.identity.model.InPrincipalRole;
import my.edu.umk.pams.intake.identity.model.InPrincipalRoleImpl;
import my.edu.umk.pams.intake.identity.model.InPrincipalType;
import my.edu.umk.pams.intake.identity.model.InRoleType;
import my.edu.umk.pams.intake.identity.model.InStaff;
import my.edu.umk.pams.intake.identity.model.InStaffImpl;
import my.edu.umk.pams.intake.identity.model.InStaffType;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.model.InUserImpl;
import my.edu.umk.pams.intake.registration.dao.InUserVerificationDao;
import my.edu.umk.pams.intake.registration.model.InUserVerification;
import my.edu.umk.pams.intake.registration.model.InUserVerificationImpl;
import my.edu.umk.pams.intake.registration.service.RegistrationService;
import my.edu.umk.pams.intake.security.integration.InAutoLoginToken;
import my.edu.umk.pams.intake.security.integration.NonSerializableSecurityContext;
import my.edu.umk.pams.intake.security.service.SecurityService;
import my.edu.umk.pams.intake.system.model.InEmailQueue;
import my.edu.umk.pams.intake.system.model.InEmailQueueImpl;
import my.edu.umk.pams.intake.system.model.InEmailQueueStatus;
import my.edu.umk.pams.intake.system.service.SystemService;
import my.edu.umk.pams.intake.system.service.SystemServiceImpl;

/**
 * @author canang technologies
 * @since 1/30/14
 */
@Transactional
@Service("inIdentityService")
public class IdentityServiceImpl implements IdentityService {

    private static final String GROUP_ROOT = "GRP_ADMN";
    private static final Logger LOG = LoggerFactory.getLogger(SystemServiceImpl.class);
    public static final int ONE_WEEK = 60 * 24 * 7;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private InPrincipalDao principalDao;

    @Autowired
    private InUserVerificationDao userVerificationDao;
    
    @Autowired
    private InUserDao userDao;

    @Autowired
    private InGroupDao groupDao;

    @Autowired
    private InActorDao actorDao;

    @Autowired
    private InStaffDao staffDao;

    @Autowired
    private InApplicantDao applicantDao;
    
    @Autowired
    private InIntakeApplicationDao intakeApplicationDao;
    
    @Autowired
    private InCandidateDao candidateDao;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private AdmissionService admissionService;
    
    @Autowired
    private ApplicationService applicationService;
       
    @Autowired
    private SystemService systemService;
    
    @Autowired
    private RegistrationService registrationService;


    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private CommonService commonService;


    //====================================================================================================
    // PRINCIPAL
    //====================================================================================================

    @Override
    public InPrincipal findPrincipalById(Long id) {
        return principalDao.findById(id);
    }

    @Override
    public InPrincipal findPrincipalByName(String name) {
        return principalDao.findByName(name);
    }

    @Override
    public List<InPrincipal> findPrincipals(String filter, Integer offset, Integer limit) {
        return principalDao.find(filter, offset, limit);
    }

    @Override
    public Set<String> findSids(InPrincipal principal) {
        Set<InGroup> groups = null;
        Set<String> principals = new HashSet<String>();
        try {
            groups = groupDao.findEffectiveAsNative(principal);
        } catch (Exception e) {
            LOG.error("Error occured loading principals", e);
        } finally {
            if (null != groups) {
                for (InGroup group : groups) {
                    principals.add(group.getName());
                }
            }
            principals.add(principal.getName());
        }
        return principals;
    }

    @Override
    public Integer countPrincipal(String filter) {
        return principalDao.count(filter);
    }

    @Override
    public void addPrincipalRole(InPrincipal principal, InPrincipalRole principalRole) {
        principalDao.addRole(principal, principalRole, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deletePrincipalRole(InPrincipal principal, InPrincipalRole principalRole) {
        principalDao.deleteRole(principal, principalRole, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public InPrincipalRole findByPrincipal(InPrincipal principal){
    	return principalDao.findByPrincipal(principal);
    }

    //====================================================================================================
    // USER
    //====================================================================================================

    @Override
    public InUser findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }
    
//    @Override
//    public InUser findUserByIdentityNo(String identityNo) {
//        return userDao.findByIdentityNo(identityNo);
//    }

    @Override
    public InUser findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public InUser findUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public InUser findUserByActor(InActor actor) {
        return userDao.findByActor(actor);
    }

    @Override
    public List<InUser> findUsers(Integer offset, Integer limit) {
        return userDao.find(offset, limit);
    }

    @Override
    public List<InUser> findUsers(String filter, Integer offset, Integer limit) {
        return userDao.find(filter, offset, limit);
    }

    @Override
    public Integer countUser() {
        return userDao.count();
    }

    @Override
    public Integer countUser(String filter) {
        return userDao.count(filter);
    }

    @Override
    public boolean hasUser(InActor actor) {
        return userDao.hasUser(actor);
    }

    @Override
    public boolean isUserExists(String username) {
        return userDao.isExists(username);
    }
    
//    @Override
//    public boolean isUserEmailExists(String email) {
//        return userDao.isEmailExists(email);
//    }
    


    @Override
    public void saveUser(InUser user) {
        userDao.save(user, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateUser(InUser user) {
        SecurityContext sc = loginAsSystem();
        userDao.update(user, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        logoutAsSystem(sc);
    }
    

    
    @Override
    public void updateMyIntakeApplication(InIntakeApplication application) {

    	application.setOfficialAddress1(application.getOfficialAddress1());
        intakeApplicationDao.update(application, securityService.getCurrentUser());


    }

    @Override
    public void removeUser(InUser user) {
        userDao.remove(user, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void changePassword(InUser user, String newPassword) {
        SecurityContext sc = loginAsSystem();
        user.setPassword(newPassword);
        userDao.update(user, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        logoutAsSystem(sc);
    }
    
    //====================================================================================================
    // GROUP
    //====================================================================================================

    @Override
    public InGroup getRootGroup() {
        return groupDao.findByName(GROUP_ROOT);
    }

    @Override
    public InGroup findGroupByName(String name) {
        return groupDao.findByName(name);
    }
    
    @Override
    public InGroup findGroupByUser(InUser user) {
        return groupDao.findGroupByUser(user);
    }

    @Override
    public InGroup findOrCreateGroupByName(String name) {
        InGroup group = null;
        if (groupDao.isExists(name))
            group = groupDao.findByName(name);
        else {
            group = new InGroupImpl();
            group.setName(name);
            group.setEnabled(true);
            group.setLocked(false);
            groupDao.save(group, securityService.getCurrentUser());
        }
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(group);
        return group;
    }

    @Override
    public InGroup findGroupById(Long id) {
        return groupDao.findById(id);
    }

    @Override
    public List<InGroup> findGroups(Integer offset, Integer limit) {
        return groupDao.find(offset, limit);
    }

    @Override
    public List<InGroup> findImmediateGroups(InPrincipal principal) {
        return groupDao.findImmediate(principal);
    }

    @Override
    public List<InGroup> findImmediateGroups(InPrincipal principal, Integer offset, Integer limit) {
        return groupDao.findImmediate(principal, offset, limit);
    }

    @Override
    public Set<InGroup> findEffectiveGroups(InPrincipal principal) {
        return groupDao.findEffectiveAsNative(principal);
    }

    @Override
    public Set<String> findEffectiveGroupsAsString(InPrincipal principal) {
        Set<String> groups = new HashSet<>();
        Set<InGroup> groupSet = groupDao.findEffectiveAsNative(principal);
        for (InGroup inGroup : groupSet) {
            groups.add(inGroup.getName());
        }
        return groups;
    }

    @Override
    public List<InGroup> findAvailableUserGroups(InUser user) {
        return groupDao.findAvailableGroups(user);
    }

    @Override
    public List<InPrincipal> findAvailableGroupMembers(InGroup group) {
        return groupDao.findAvailableMembers(group);
    }

    @Override
    public List<InPrincipal> findGroupMembers(InGroup group) {
        return groupDao.findMembers(group);
    }

    @Override
    public List<InPrincipal> findGroupMembers(InGroup group, Integer offset, Integer limit) {
        return groupDao.findMembers(group, offset, limit);
    }

    @Override
    public Integer countGroup() {
        return groupDao.count();
    }

    @Override
    public Integer countGroupMember(InGroup group) {
        return groupDao.countMember(group);
    }

    @Override
    public boolean isGroupExists(String name) {
        return groupDao.isExists(name);
    }

    @Override
    public boolean hasMembership(InGroup group, InPrincipal principal) {
        return groupDao.hasMembership(group, principal);
    }

    @Override
    public InGroup createGroupWithRole(String name, InRoleType... types) {
        InGroup group = new InGroupImpl();
        group.setName(name);
        group.setEnabled(true);
        group.setLocked(false);
        groupDao.save(group, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(group);

        for (InRoleType type : types) {
            InPrincipalRole role = new InPrincipalRoleImpl();
            role.setRole(type);
            principalDao.addRole(group, role, securityService.getCurrentUser());
            sessionFactory.getCurrentSession().flush();
        }
        sessionFactory.getCurrentSession().refresh(group);
        return group;
    }

    @Override
    public void saveGroup(InGroup group) {
        groupDao.save(group, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateGroup(InGroup group) {
        groupDao.update(group, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeGroup(InGroup group) {
        groupDao.remove(group, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addGroupMember(InGroup group, InPrincipal principal) throws RecursiveGroupException {
        groupDao.addMember(group, principal, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteGroupMember(InGroup group, InPrincipal principal) {
        groupDao.deleteMember(group, principal);
        sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public InGroupMember findMemberByPrincipal(InPrincipal principal) {
        return groupDao.findByPrincipal(principal);
    }

    //====================================================================================================
    // ACTOR
    //====================================================================================================

    @Override
    public InActor findActorById(Long id) {
        return actorDao.findById(id);
    }

    @Override
    public InActor findActorByIdentityNo(String identityNo) {
        return actorDao.findByIdentityNo(identityNo);
    }

    @Override
    public List<InActor> findActors(Integer offset, Integer limit) {
        return actorDao.find(offset, limit);
    }

    @Override
    public List<InActor> findActors(InActorType type, Integer offset, Integer limit) {
        return actorDao.find(type, offset, limit);
    }

    @Override
    public List<InActor> findActors(String filter, Integer offset, Integer limit) {
        return actorDao.find(filter, offset, limit);
    }

    @Override
    public List<InActor> findActors(String filter, InActorType type, Integer offset, Integer limit) {
        return actorDao.find(filter, type, offset, limit);
    }

    @Override
    public Integer countActor() {
        return actorDao.count();
    }

    @Override
    public Integer countActor(InActorType type) {
        return actorDao.count(type);
    }

    @Override
    public Integer countActor(String filter) {
        return actorDao.count(filter);
    }

    @Override
    public Integer countActor(String filter, InActorType type) {
        return actorDao.count(filter, type);
    }
    
    @Override
    public boolean isActorEmailExists(String email) {
        return actorDao.isEmailExists(email);
    }
    
    @Override
    public boolean isActorIdentityNoExists(String identityNo) {
        return actorDao.isIdentityNoExists(identityNo);
    }


    //====================================================================================================
    // STAFF
    //====================================================================================================

    @Override
    public InStaff findStaffById(Long id) {
        return staffDao.findById(id);
    }

    @Override
    public InStaff findStaffByStaffNo(String StaffNo) {
        return staffDao.findByStaffNo(StaffNo);
    }

    @Override
    public InStaff findStaffByNricNo(String nricNo) {
        return staffDao.findByStaffNo(nricNo);
    }

    @Override
    public List<InStaff> findStaffs(Integer offset, Integer limit) {
        return staffDao.find(offset, limit);
    }

    @Override
    public List<InStaff> findStaffs(String filter, Integer offset, Integer limit) {
        return staffDao.find(filter, offset, limit);
    }

    @Override
    public Integer countStaff() {
        return staffDao.count();
    }

    @Override
    public Integer countStaff(String filter) {
        return staffDao.count(filter);
    }

    @Override
    public boolean isStaffEmailExists(String email) {
        return staffDao.isEmailExists(email);
    }

    @Override
    public boolean isStaffNoExists(String staffNo) {
        return staffDao.isExists(staffNo);
    }

    @Override
    public void saveStaff(InStaff staff) {
        staffDao.save(staff, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateStaff(InStaff staff) {
        staffDao.update(staff, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteStaff(InStaff staff) {
        staffDao.delete(staff, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void broadcastCreated(InStaff staff) {
        StaffCreatedEvent event = new StaffCreatedEvent(staff);
        applicationContext.publishEvent(event);
    }

    @Override
    public void broadcastUpdated(InStaff staff) {
        StaffUpdatedEvent event = new StaffUpdatedEvent(staff);
        applicationContext.publishEvent(event);
    }


    //====================================================================================================
    // APPLICANT
    //====================================================================================================

    @Override
    public InApplicant findApplicantById(Long id) {
        return applicantDao.findById(id);
    }

    @Override
    public InApplicant findApplicantByEmail(String email) {
        return applicantDao.findByEmail(email);
    }

    @Override
    public InApplicant findApplicantByApplicantNo(String ApplicantNo) {
        return applicantDao.findByApplicantNo(ApplicantNo);
    }

    @Override
    public List<InApplicant> findApplicants(Integer offset, Integer limit) {
        return applicantDao.find(offset, limit);
    }

    @Override
    public List<InApplicant> findApplicants(String filter, Integer offset, Integer limit) {
        return applicantDao.find(filter, offset, limit);
    }

    @Override
    public Integer countApplicant() {
        return applicantDao.count();
    }

    @Override
    public Integer countApplicant(String filter) {
        return applicantDao.count(filter);
    }

    @Override
    public void saveApplicant(InApplicant applicant) {
        applicantDao.save(applicant, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateApplicant(InApplicant applicant) {
    	SecurityContext sc = loginAsSystem();
        applicantDao.update(applicant, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        logoutAsSystem(sc);
    }
    
    @Override
    public void updatePrincipal(InPrincipal principal) {
    	SecurityContext sc = loginAsSystem();
        principalDao.update(principal, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        logoutAsSystem(sc);
    }
    
    @Override
	public void saveStaffIMSNonAcademicInActive(InStaff staff) {
		staffDao.save(staff, securityService.getCurrentUser());
		LOG.info("Save Staff IMS Service");
		
		LOG.debug("Staff Email In Service:{}",staff.getEmail());
		// User
		InUser user = new InUserImpl();
		user.setActor(staff);
		user.setEmail(staff.getEmail());
		user.setUsername(staff.getEmail());
		user.setPassword(staff.getStaffNo());
		user.setRealName(staff.getName());
		user.setName(staff.getEmail());
		user.setEnabled(false);
		user.setLocked(true);
		user.setPrincipalType(InPrincipalType.USER);
		saveUser(user);
				
		// Principal
		InPrincipal principal = findPrincipalByName(staff.getEmail());
		if(staff.getFacultyCode().getCode().equals("A10") && staff.getStaffCategory().equals("A")){
		LOG.info("If Faculty A10 MGSEB && Category A");
		
		// Principal Role
		InPrincipalRole role = new InPrincipalRoleImpl();
		role.setPrincipal(principal);
		role.setRole(InRoleType.ROLE_ADMINISTRATOR);
		addPrincipalRole(principal, role);

		try{
		// Group
		InGroup group = findGroupByName("GRP_PGW_ADM_A10");
		// GroupMember
		InGroupMember member = new InGroupMemberImpl();
		member.setGroup(group);
		member.setPrincipal(principal);
		addGroupMember(group, principal);
		} catch (RecursiveGroupException e) {
			
			e.printStackTrace();
		}
		}else if(staff.getFacultyCode().getCode().equals("A10")){
			LOG.info("If Faculty A10 MGSEB Only");
			
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
			addPrincipalRole(principal, role);

			try{
			// Group
			InGroup group = findGroupByName("GRP_KRN_ADM_A10");
			// GroupMember
			InGroupMember member = new InGroupMemberImpl();
			member.setGroup(group);
			member.setPrincipal(principal);
			addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {
				
				e.printStackTrace();
			}
		}else if(staff.getFacultyCode().getCode().equals("A09") && staff.getStaffCategory().equals("A")){
			LOG.info("If Faculty A09 CPS && Category A");
	
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
			addPrincipalRole(principal, role);

			try{
			// Group
			InGroup group = findGroupByName("GRP_PGW_ADM_A10");
			// GroupMember
			InGroupMember member = new InGroupMemberImpl();
			member.setGroup(group);
			member.setPrincipal(principal);
			addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {
				
				e.printStackTrace();
			}
		}else if(staff.getFacultyCode().getCode().equals("A09")){
			LOG.info("If Faculty A09 CPS Only");
			
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
			addPrincipalRole(principal, role);

			try{
			// Group
			InGroup group = findGroupByName("GRP_KRN_ADM_A10");
			// GroupMember
			InGroupMember member = new InGroupMemberImpl();
			member.setGroup(group);
			member.setPrincipal(principal);
			addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {
				
				e.printStackTrace();
			}
		}else if(staff.getStaffCategory().equals("A")){
			LOG.info("If All Faculty and Category A Only");
			
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_FCTY);
			addPrincipalRole(principal, role);

			try{
			// Group
			InGroup group = findGroupByName("GRP_PGW_FCTY_"+ staff.getFacultyCode().getCode());
			// GroupMember
			
			InGroupMember member = new InGroupMemberImpl();
			member.setGroup(group);
			member.setPrincipal(principal);
			addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {
				
				e.printStackTrace();
			}
		}else{
			LOG.info("If All Faculty Only");
			
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_FCTY);
			addPrincipalRole(principal, role);

			InGroup group = null;
			if ((group = findGroupByName("GRP_KRN_FCTY_" + staff.getFacultyCode().getCode())) != null) {
				LOG.info("If All Faculty Only");

				try {
					// Group
					group = findGroupByName("GRP_KRN_FCTY_" + staff.getFacultyCode().getCode());
					// GroupMember
					InGroupMember member = new InGroupMemberImpl();
					member.setGroup(group);
					member.setPrincipal(principal);
					addGroupMember(group, principal);
				} catch (RecursiveGroupException e) {

					e.printStackTrace();
				}
			} else {

				LOG.debug("No Group");

			}
			
		}      
        sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public void saveStaffIMSNonAcademicActive(InStaff staff) {
		staffDao.save(staff, securityService.getCurrentUser());
		LOG.info("Save Staff IMS Service");
		
		LOG.debug("Staff Email In Service:{}",staff.getEmail());
		// User
		InUser user = new InUserImpl();
		user.setActor(staff);
		user.setEmail(staff.getEmail());
		user.setUsername(staff.getEmail());
		user.setPassword(staff.getStaffNo());
		user.setRealName(staff.getName());
		user.setName(staff.getEmail());
		user.setEnabled(true);
		user.setLocked(true);
		user.setPrincipalType(InPrincipalType.USER);
		saveUser(user);
				
		// Principal
		InPrincipal principal = findPrincipalByName(staff.getEmail());
		if(staff.getFacultyCode().getCode().equals("A10") && staff.getStaffCategory().equals("A")){
		LOG.info("If Faculty A10 MGSEB && Category A");
		
		// Principal Role
		InPrincipalRole role = new InPrincipalRoleImpl();
		role.setPrincipal(principal);
		role.setRole(InRoleType.ROLE_ADMINISTRATOR);
		addPrincipalRole(principal, role);

		try{
		// Group
		InGroup group = findGroupByName("GRP_PGW_ADM_A10");
		// GroupMember
		InGroupMember member = new InGroupMemberImpl();
		member.setGroup(group);
		member.setPrincipal(principal);
		addGroupMember(group, principal);
		} catch (RecursiveGroupException e) {
			
			e.printStackTrace();
		}
		}else if(staff.getFacultyCode().getCode().equals("A10")){
			LOG.info("If Faculty A10 MGSEB Only");
			
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
			addPrincipalRole(principal, role);

			try{
			// Group
			InGroup group = findGroupByName("GRP_KRN_ADM_A10");
			// GroupMember
			InGroupMember member = new InGroupMemberImpl();
			member.setGroup(group);
			member.setPrincipal(principal);
			addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {
				
				e.printStackTrace();
			}
		}else if(staff.getFacultyCode().getCode().equals("A09") && staff.getStaffCategory().equals("A")){
			LOG.info("If Faculty A09 CPS && Category A");
	
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
			addPrincipalRole(principal, role);

			try{
			// Group
			InGroup group = findGroupByName("GRP_PGW_ADM_A10");
			// GroupMember
			InGroupMember member = new InGroupMemberImpl();
			member.setGroup(group);
			member.setPrincipal(principal);
			addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {
				
				e.printStackTrace();
			}
		}else if(staff.getFacultyCode().getCode().equals("A09")){
			LOG.info("If Faculty A09 CPS Only");
			
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
			addPrincipalRole(principal, role);

			try{
			// Group
			InGroup group = findGroupByName("GRP_KRN_ADM_A10");
			// GroupMember
			InGroupMember member = new InGroupMemberImpl();
			member.setGroup(group);
			member.setPrincipal(principal);
			addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {
				
				e.printStackTrace();
			}
		}else if(staff.getStaffCategory().equals("A")){
			LOG.info("If All Faculty and Category A Only");
			
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_FCTY);
			addPrincipalRole(principal, role);

			try {
				// Group
				InGroup group = null;
				if (isGroupExists("GRP_PGW_FCTY_"+ staff.getFacultyCode().getCode()))
				{

					group = findGroupByName("GRP_PGW_FCTY_" + staff.getFacultyCode().getCode());

					// GroupMember
					InGroupMember member = new InGroupMemberImpl();
					member.setGroup(group);
					member.setPrincipal(principal);
					addGroupMember(group, principal);
				} else {
					LOG.debug("Not Exist");
				}

			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}
			
		}else{
			LOG.info("If All Faculty Only");
			
			// Principal Role
			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_FCTY);
			addPrincipalRole(principal, role);
			
			try {
				// Group
				InGroup group = null;
				if (isGroupExists("GRP_KRN_FCTY_"+ staff.getFacultyCode().getCode()))
				{

					group = findGroupByName("GRP_KRN_FCTY_" + staff.getFacultyCode().getCode());

					// GroupMember
					InGroupMember member = new InGroupMemberImpl();
					member.setGroup(group);
					member.setPrincipal(principal);
					addGroupMember(group, principal);
				} else {
					LOG.debug("Not Exist");
				}

			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}      
        sessionFactory.getCurrentSession().flush();
    }    
    
    @Override
    public void changeEmail(InApplicant applicant, String newEmail) {
    	SecurityContext sc = loginAsSystem();
    	
    	applicant.setEmail(newEmail);
        applicantDao.update(applicant, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        
        InIntakeApplication intakeApplication = applicationService.findIntakeApplicationByApplicant(applicant);
        intakeApplication.setEmail(newEmail);
        intakeApplicationDao.update(intakeApplication, securityService.getCurrentUser());
        
        
        
        InCandidate candidate = admissionService.findCandidateByIntakeApplication(intakeApplication);
        candidate.setEmail(newEmail);
        candidateDao.update(candidate, securityService.getCurrentUser());
        LOG.debug("Candidate in change email :{}",candidate.getEmail());
        

        //must find actor
        InActor actor = this.findApplicantById(applicant.getId());
        actor.setEmail(newEmail);
        actorDao.update(actor, securityService.getCurrentUser());
        //must find user
        InUser user = this.findUserByActor(actor);
        
        user.setEnabled(false);
        user.setLocked(true);
        
        user.setEmail(newEmail);
        userDao.update(user, securityService.getCurrentUser());
        InPrincipal principal = this.findPrincipalById(user.getId());
        
        principal.setName(newEmail);
        this.updatePrincipal(principal);
        //generate token
        // generate token
        String token = registrationService.generateToken();
        InUserVerification verification = new InUserVerificationImpl();
        verification.setExpiryDate(calculateExpiryDate(ONE_WEEK));
        verification.setToken(token);
        verification.setUser(user);
        userVerificationDao.save(verification, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        
    	if (applicant == null) LOG.debug("ApplicantB is null");
    	if (applicant.getEmail() == null) LOG.debug("Email is null");

        String applicationUrl= systemService.findConfigurationByKey("application.url").getValue();
        InEmailQueue email= new InEmailQueueImpl();
        String subject = "Email verification";
        String body = "Please verify your email address upon 7 days from your registration day by clicking this url : "+applicationUrl+"/changeEmailVerification/"+ token;
        //verification.getToken();
        email.setTo(user.getEmail());
        email.setSubject(subject);
        email.setBody(body);
       //method send email 
        email.setCode("EQ/" + System.currentTimeMillis());
        email.setQueueStatus(InEmailQueueStatus.QUEUED);
        systemService.saveEmailQueue(email);
        
        // logout
        logoutAsSystem(sc);
    }
      
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

	@Override
    public void changeAddress(InIntakeApplication application, String newAddress) {
    	SecurityContext sc = loginAsSystem();  
    //	applicationService.findIntakeApplication();
    //	 LOG.debug("change address ler",application.getOfficialAddress1());
    	application.setMailingAddress1(newAddress);
    	intakeApplicationDao.update(application, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();      
        logoutAsSystem(sc);
    }
    
    //====================================================================================================
    // PRIVATE METHODS
    //====================================================================================================

    private SecurityContext loginAsSystem() {
        SecurityContext savedCtx = SecurityContextHolder.getContext();
        InAutoLoginToken authenticationToken = new InAutoLoginToken("system");
        Authentication authed = authenticationManager.authenticate(authenticationToken);
        SecurityContext system = new NonSerializableSecurityContext();
        system.setAuthentication(authed);
        SecurityContextHolder.setContext(system);
        return savedCtx;
    }

    private void logoutAsSystem(SecurityContext context) {
        SecurityContextHolder.setContext(context);

    }

	@Override
	public void saveUMKCEEnonAcademicStaff(InStaff staff) {
        staffDao.save(staff, securityService.getCurrentUser());
        LOG.debug("START SAVE UMKCEE STAFF");
    	InUser user = new InUserImpl();
		user.setActor(staff);
		user.setEmail(staff.getEmail());
		user.setUsername(staff.getEmail());
		user.setPassword(staff.getStaffNo());
		user.setRealName(staff.getName());
		user.setName(staff.getEmail());
		user.setEnabled(true);
		user.setLocked(true);
		user.setPrincipalType(InPrincipalType.USER);
		identityService.saveUser(user);

		InPrincipal principal = identityService.findPrincipalByName(staff.getEmail());
		if (staff.getFacultyCode().getCode().equals("UMKCEE") && staff.getStaffCategory().equals("A")) {

			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
			identityService.addPrincipalRole(principal, role);

			try {
				LOG.debug("group");
				// Group
				InGroup group = identityService.findGroupByName("GRP_PGW_ADM_" + staff.getFacultyCode().getCode());
				LOG.debug("GROUP:{}", group.getName());
				// GroupMember
				InGroupMember member = new InGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				identityService.addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}else if (staff.getFacultyCode().getCode().equals("UMKCEE") && staff.getStaffCategory().equals("B")) {

			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
			identityService.addPrincipalRole(principal, role);

			try {
				LOG.debug("group");
				// Group
				InGroup group = identityService.findGroupByName("GRP_KRN_ADM_" + staff.getFacultyCode().getCode());
				LOG.debug("GROUP:{}", group.getName());
				// GroupMember
				InGroupMember member = new InGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				identityService.addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}else if (staff.getStaffCategory().equals("A")) {

			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_FCTY);
			identityService.addPrincipalRole(principal, role);

			try {
				LOG.debug("group");
				// Group
				InGroup group = identityService.findGroupByName("GRP_PGW_FCTY_" + staff.getFacultyCode().getCode());
				LOG.debug("GROUP:{}", group.getName());
				// GroupMember
				InGroupMember member = new InGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				identityService.addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}else if (staff.getStaffCategory().equals("B")) {

			InPrincipalRole role = new InPrincipalRoleImpl();
			role.setPrincipal(principal);
			role.setRole(InRoleType.ROLE_FCTY);
			identityService.addPrincipalRole(principal, role);

			try {
				LOG.debug("group");
				// Group
				InGroup group = identityService.findGroupByName("GRP_KRN_FCTY_" + staff.getFacultyCode().getCode());
				LOG.debug("GROUP:{}", group.getName());
				// GroupMember
				InGroupMember member = new InGroupMemberImpl();
				member.setGroup(group);
				member.setPrincipal(principal);
				identityService.addGroupMember(group, principal);
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}else{
			LOG.debug("UNKNOWN");
		}
		  LOG.debug("FINISH SAVE UMKCEE STAFF");
        sessionFactory.getCurrentSession().flush();
		
	}
	
	@Override
	public void updateUMKCEEnonAcademicStaff(InStaff staff) {
        staffDao.update(staff, securityService.getCurrentUser());

        LOG.debug("START UPDATE UMKCEE STAFF");
        InFacultyCode facultyCodeUpdate = commonService.findFacultyCodeByCode(staff.getFacultyCode().getCode());
        LOG.debug("FacultyCodeUpdate:{}",facultyCodeUpdate.getCode());
        

		
    	InUser userUpdate = identityService.findUserByEmail(staff.getEmail());
    	userUpdate.setActor(staff);
    	userUpdate.setEmail(staff.getEmail());
    	userUpdate.setUsername(staff.getEmail());
    	userUpdate.setPassword(staff.getStaffNo());
    	userUpdate.setRealName(staff.getName());
		userUpdate.setName(staff.getEmail());
		userUpdate.setEnabled(true);
		userUpdate.setLocked(true);
		userUpdate.setPrincipalType(InPrincipalType.USER);
		identityService.updateUser(userUpdate);

		InPrincipal principalUpdate = identityService.findPrincipalByName(staff.getEmail());
		if (staff.getFacultyCode().getCode().equals("UMKCEE") && staff.getStaffCategory().equals("A")) {
			//Find Role Lama
			InPrincipalRole roleLama = identityService.findByPrincipal(principalUpdate);
			LOG.debug("Role_Lama:{}",roleLama.getRole().name());
			
			//Delete Role Lama
			identityService.deletePrincipalRole(principalUpdate, roleLama);
			
			//Add New Role
			InPrincipalRole roleUpdate = new InPrincipalRoleImpl();
			roleUpdate.setPrincipal(principalUpdate);
			roleUpdate.setRole(InRoleType.ROLE_ADMINISTRATOR);
			identityService.addPrincipalRole(principalUpdate, roleUpdate);

			try {
				LOG.debug("groupUpdate");

				//GroupLama
					//Find Group Lama
				InGroup groupLama = identityService.findGroupByUser(userUpdate);
				LOG.debug("Group_Lama:{}",groupLama.getName());
					//Find Membership Group Lama
				InGroupMember memberUpdate = identityService.findMemberByPrincipal(principalUpdate);
				LOG.debug("Member_Lama:{}",memberUpdate.getGroup().getName());
					//Delete Membership
				identityService.deleteGroupMember(groupLama, principalUpdate);

				// Find Group Baharu
				InGroup groupUpdate = identityService.findGroupByName("GRP_PGW_ADM_" + staff.getFacultyCode().getCode());
				LOG.debug("group_baharu:{}", groupUpdate.getName());
				// Add As Membership
				InGroupMember memberBaharu = new InGroupMemberImpl();
				memberBaharu.setGroup(groupUpdate);
				memberBaharu.setPrincipal(principalUpdate);
				identityService.addGroupMember(groupUpdate, principalUpdate);
				
				LOG.debug("Membership_baharu:{}",memberBaharu.getGroup().getName());
				
				LOG.debug("Finish Group Update");

			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}else if (staff.getFacultyCode().getCode().equals("UMKCEE") && staff.getStaffCategory().equals("B")) {

			//Find Role Lama
			InPrincipalRole roleLama = identityService.findByPrincipal(principalUpdate);
			LOG.debug("Role_Lama:{}",roleLama.getRole().name());
			
			//Delete Role Lama
			identityService.deletePrincipalRole(principalUpdate, roleLama);

			InPrincipalRole roleUpdate = new InPrincipalRoleImpl();
			roleUpdate.setPrincipal(principalUpdate);
			roleUpdate.setRole(InRoleType.ROLE_ADMINISTRATOR);
			identityService.addPrincipalRole(principalUpdate, roleUpdate);

			try {
				LOG.debug("groupUpdate");

				//GroupLama
					//Find Group Lama
				InGroup groupLama = identityService.findGroupByUser(userUpdate);
				LOG.debug("Group_Lama:{}",groupLama.getName());
					//Find Membership Group Lama
				InGroupMember memberUpdate = identityService.findMemberByPrincipal(principalUpdate);
				LOG.debug("Member_Lama:{}",memberUpdate.getGroup().getName());
					//Delete Membership
				identityService.deleteGroupMember(groupLama, principalUpdate);

				// Find Group Baharu
				InGroup groupUpdate = identityService.findGroupByName("GRP_KRN_ADM_" + staff.getFacultyCode().getCode());
				LOG.debug("group_baharu:{}", groupUpdate.getName());
				// Add As Membership
				InGroupMember memberBaharu = new InGroupMemberImpl();
				memberBaharu.setGroup(groupUpdate);
				memberBaharu.setPrincipal(principalUpdate);
				identityService.addGroupMember(groupUpdate, principalUpdate);
				
				LOG.debug("Membership_baharu:{}",memberBaharu.getGroup().getName());
				
				LOG.debug("Finish Group Update");

				
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}else if (staff.getStaffCategory().equals("A")) {

			//Find Role Lama
			InPrincipalRole roleLama = identityService.findByPrincipal(principalUpdate);
			LOG.debug("Role_Lama:{}",roleLama.getRole().name());
			
			//Delete Role Lama
			identityService.deletePrincipalRole(principalUpdate, roleLama);

			InPrincipalRole roleUpdate = new InPrincipalRoleImpl();
			roleUpdate.setPrincipal(principalUpdate);
			roleUpdate.setRole(InRoleType.ROLE_FCTY);
			identityService.addPrincipalRole(principalUpdate, roleUpdate);

			try {
				LOG.debug("groupUpdate");

				//GroupLama
					//Find Group Lama
				InGroup groupLama = identityService.findGroupByUser(userUpdate);
				LOG.debug("Group_Lama:{}",groupLama.getName());
					//Find Membership Group Lama
				InGroupMember memberUpdate = identityService.findMemberByPrincipal(principalUpdate);
				LOG.debug("Member_Lama:{}",memberUpdate.getGroup().getName());
					//Delete Membership
				identityService.deleteGroupMember(groupLama, principalUpdate);

				// Find Group Baharu
				InGroup groupUpdate = identityService.findGroupByName("GRP_PGW_FCTY_" + staff.getFacultyCode().getCode());
				LOG.debug("group_baharu:{}", groupUpdate.getName());
				// Add As Membership
				InGroupMember memberBaharu = new InGroupMemberImpl();
				memberBaharu.setGroup(groupUpdate);
				memberBaharu.setPrincipal(principalUpdate);
				identityService.addGroupMember(groupUpdate, principalUpdate);
				
				LOG.debug("Membership_baharu:{}",memberBaharu.getGroup().getName());
				
				LOG.debug("Finish Group Update");
				
			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}else if (staff.getStaffCategory().equals("B")) {

			//Find Role Lama
			InPrincipalRole roleLama = identityService.findByPrincipal(principalUpdate);
			LOG.debug("Role_Lama:{}",roleLama.getRole().name());
			
			//Delete Role Lama
			identityService.deletePrincipalRole(principalUpdate, roleLama);

			InPrincipalRole roleUpdate = new InPrincipalRoleImpl();
			roleUpdate.setPrincipal(principalUpdate);
			roleUpdate.setRole(InRoleType.ROLE_FCTY);
			identityService.addPrincipalRole(principalUpdate, roleUpdate);

			try {
				
				LOG.debug("groupUpdate");

				//GroupLama
					//Find Group Lama
				InGroup groupLama = identityService.findGroupByUser(userUpdate);
				LOG.debug("Group_Lama:{}",groupLama.getName());
					//Find Membership Group Lama
				InGroupMember memberUpdate = identityService.findMemberByPrincipal(principalUpdate);
				LOG.debug("Member_Lama:{}",memberUpdate.getGroup().getName());
					//Delete Membership
				identityService.deleteGroupMember(groupLama, principalUpdate);

				// Find Group Baharu
				InGroup groupUpdate = identityService.findGroupByName("GRP_KRN_FCTY_" + staff.getFacultyCode().getCode());
				LOG.debug("group_baharu:{}", groupUpdate.getName());
				// Add As Membership
				InGroupMember memberBaharu = new InGroupMemberImpl();
				memberBaharu.setGroup(groupUpdate);
				memberBaharu.setPrincipal(principalUpdate);
				identityService.addGroupMember(groupUpdate, principalUpdate);
				
				LOG.debug("Membership_baharu:{}",memberBaharu.getGroup().getName());
				
				LOG.debug("Finish Group Update");

			} catch (RecursiveGroupException e) {

				e.printStackTrace();
			}

		}else{
			LOG.debug("UNKNOWN");
		}
		  LOG.debug("FINISH UPDATE UMKCEE STAFF");

        
        
        sessionFactory.getCurrentSession().flush();
		
	}
}