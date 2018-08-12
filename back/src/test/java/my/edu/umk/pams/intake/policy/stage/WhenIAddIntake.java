package my.edu.umk.pams.intake.policy.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.common.model.InFacultyCode;
import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.identity.dao.RecursiveGroupException;
import my.edu.umk.pams.intake.identity.model.InActorType;
import my.edu.umk.pams.intake.identity.model.InGroup;
import my.edu.umk.pams.intake.identity.model.InGroupMember;
import my.edu.umk.pams.intake.identity.model.InGroupMemberImpl;
import my.edu.umk.pams.intake.identity.model.InPrincipal;
import my.edu.umk.pams.intake.identity.model.InPrincipalRole;
import my.edu.umk.pams.intake.identity.model.InPrincipalRoleImpl;
import my.edu.umk.pams.intake.identity.model.InPrincipalType;
import my.edu.umk.pams.intake.identity.model.InRoleType;
import my.edu.umk.pams.intake.identity.model.InStaff;
import my.edu.umk.pams.intake.identity.model.InStaffImpl;
import my.edu.umk.pams.intake.identity.model.InStaffType;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.model.InUserImpl;
import my.edu.umk.pams.intake.identity.service.IdentityService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeImpl;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenIAddIntake extends Stage<WhenIAddIntake> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIAddIntake.class);

	@Autowired
	private PolicyService policyService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private IdentityService identityService;

	@ExpectedScenarioState
	private InIntakeSession session;

	@ExpectedScenarioState
	private InGraduateCenter graduateCenter;

	@ProvidedScenarioState
	private InProgramLevel level;

	@ProvidedScenarioState
	private InIntake intake;

	@ProvidedScenarioState
	private String referenceNo;

	public WhenIAddIntake I_add_intake() {
		
		InPrincipal principal = identityService.findPrincipalByName("SAM@UMK.EDU.MY");
		LOG.debug("Principal:{}",principal.getName());
		
		InPrincipalRole role = identityService.findByPrincipal(principal);
		LOG.debug("Role:{}",role.getRole().name());


//		InFacultyCode facultyCode = commonService.findFacultyCodeByCode("UMKCEE");
//		LOG.debug("FacultyCode:{}", facultyCode.getCode());
//
//		InStaff staff = new InStaffImpl();
//		staff.setIdentityNo("x1");
//		staff.setStaffType(InStaffType.NON_ACADEMIC);
//		staff.setName("MOHAMAD SHAZWAN BIN MOHD DIN");
//		staff.setActorType(InActorType.STAFF);
//		staff.setPhone("0179282817");
//		staff.setFacultyCode(facultyCode);
//		staff.setStaffCategory("A");
//		staff.setEmail("x1@umk.edu.my");
//		identityService.saveStaff(staff);
//
//		InUser user = new InUserImpl();
//		user.setActor(staff);
//		user.setEmail(staff.getEmail());
//		user.setUsername(staff.getEmail());
//		user.setPassword(staff.getStaffNo());
//		user.setRealName(staff.getName());
//		user.setName(staff.getEmail());
//		user.setEnabled(true);
//		user.setLocked(true);
//		user.setPrincipalType(InPrincipalType.USER);
//		identityService.saveUser(user);
//
//		InPrincipal principal = identityService.findPrincipalByName(staff.getEmail());
//		if (staff.getFacultyCode().getCode().equals("UMKCEE") && staff.getStaffCategory().equals("A")) {
//
//			InPrincipalRole role = new InPrincipalRoleImpl();
//			role.setPrincipal(principal);
//			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
//			identityService.addPrincipalRole(principal, role);
//
//			try {
//				LOG.debug("group");
//				// Group
//				InGroup group = identityService.findGroupByName("GRP_PGW_ADM_" + facultyCode.getCode());
//				LOG.debug("GROUP:{}", group.getName());
//				// GroupMember
//				InGroupMember member = new InGroupMemberImpl();
//				member.setGroup(group);
//				member.setPrincipal(principal);
//				identityService.addGroupMember(group, principal);
//			} catch (RecursiveGroupException e) {
//
//				e.printStackTrace();
//			}
//			InGroupMember mem = identityService.findMemberByPrincipal(principal);
//			LOG.debug("MEMBER:{}",mem.getGroup().getName());
//
//		}else if (staff.getFacultyCode().getCode().equals("UMKCEE") && staff.getStaffCategory().equals("B")) {
//
//			InPrincipalRole role = new InPrincipalRoleImpl();
//			role.setPrincipal(principal);
//			role.setRole(InRoleType.ROLE_ADMINISTRATOR);
//			identityService.addPrincipalRole(principal, role);
//
//			try {
//				LOG.debug("group");
//				// Group
//				InGroup group = identityService.findGroupByName("GRP_KRN_ADM_" + facultyCode.getCode());
//				LOG.debug("GROUP:{}", group.getName());
//				// GroupMember
//				InGroupMember member = new InGroupMemberImpl();
//				member.setGroup(group);
//				member.setPrincipal(principal);
//				identityService.addGroupMember(group, principal);
//			} catch (RecursiveGroupException e) {
//
//				e.printStackTrace();
//			}
//
//		}else if (staff.getStaffCategory().equals("A")) {
//
//			InPrincipalRole role = new InPrincipalRoleImpl();
//			role.setPrincipal(principal);
//			role.setRole(InRoleType.ROLE_FCTY);
//			identityService.addPrincipalRole(principal, role);
//
//			try {
//				LOG.debug("group");
//				// Group
//				InGroup group = identityService.findGroupByName("GRP_PGW_FCTY_" + facultyCode.getCode());
//				LOG.debug("GROUP:{}", group.getName());
//				// GroupMember
//				InGroupMember member = new InGroupMemberImpl();
//				member.setGroup(group);
//				member.setPrincipal(principal);
//				identityService.addGroupMember(group, principal);
//			} catch (RecursiveGroupException e) {
//
//				e.printStackTrace();
//			}
//
//		}else if (staff.getStaffCategory().equals("B")) {
//
//			InPrincipalRole role = new InPrincipalRoleImpl();
//			role.setPrincipal(principal);
//			role.setRole(InRoleType.ROLE_FCTY);
//			identityService.addPrincipalRole(principal, role);
//
//			try {
//				LOG.debug("group");
//				// Group
//				InGroup group = identityService.findGroupByName("GRP_KRN_FCTY_" + facultyCode.getCode());
//				LOG.debug("GROUP:{}", group.getName());
//				// GroupMember
//				InGroupMember member = new InGroupMemberImpl();
//				member.setGroup(group);
//				member.setPrincipal(principal);
//				identityService.addGroupMember(group, principal);
//			} catch (RecursiveGroupException e) {
//
//				e.printStackTrace();
//			}
//
//		}else{
//			LOG.debug("UNKNOWN");
//		}
		
//        LOG.debug("START UPDATE UMKCEE STAFF");
//        InFacultyCode facultyCodeUpdate = commonService.findFacultyCodeByCode("A01");
//        LOG.debug("FacultyCodeUpdate:{}",facultyCodeUpdate.getCode());
//        
//        InStaff staffUpdate = identityService.findStaffByStaffNo("x1");
//        staffUpdate.setStaffType(InStaffType.NON_ACADEMIC);
//        staffUpdate.setName("MOHAMAD SHAZWAN BIN MOHD DIN");
//        staffUpdate.setActorType(InActorType.STAFF);
//        staffUpdate.setPhone("0179282817");
//        staffUpdate.setFacultyCode(facultyCodeUpdate);
//        staffUpdate.setStaffCategory("A");
//        staffUpdate.setEmail("x1@umk.edu.my");
//		identityService.updateStaff(staffUpdate);
//		LOG.debug("Staff-UPDATE-fac:{}",staffUpdate.getFacultyCode());
//		
//    	InUser userUpdate = identityService.findUserByEmail(staffUpdate.getEmail());
//    	userUpdate.setActor(staffUpdate);
//    	userUpdate.setEmail(staffUpdate.getEmail());
//    	userUpdate.setUsername(staffUpdate.getEmail());
//    	userUpdate.setPassword(staffUpdate.getStaffNo());
//    	userUpdate.setRealName(staffUpdate.getName());
//		userUpdate.setName(staffUpdate.getEmail());
//		userUpdate.setEnabled(true);
//		userUpdate.setLocked(true);
//		userUpdate.setPrincipalType(InPrincipalType.USER);
//		identityService.updateUser(userUpdate);
//
//		InPrincipal principalUpdate = identityService.findPrincipalByName(staffUpdate.getEmail());
//		if (staffUpdate.getFacultyCode().getCode().equals("UMKCEE") && staffUpdate.getStaffCategory().equals("A")) {
//
//			InPrincipalRole roleUpdate = new InPrincipalRoleImpl();
//			roleUpdate.setPrincipal(principalUpdate);
//			roleUpdate.setRole(InRoleType.ROLE_ADMINISTRATOR);
//			identityService.addPrincipalRole(principalUpdate, roleUpdate);
//
//			try {
//				LOG.debug("groupUpdate");
//				// Group
//				InGroup groupUpdate = identityService.findGroupByName("GRP_PGW_ADM_" + staffUpdate.getFacultyCode().getCode());
//				LOG.debug("GROUP_UPDATE:{}", groupUpdate.getName());
//				// GroupMember
//				InGroupMember memberUpdate = new InGroupMemberImpl();
//				memberUpdate.setGroup(groupUpdate);
//				memberUpdate.setPrincipal(principalUpdate);
//				identityService.addGroupMember(groupUpdate, principalUpdate);
//			} catch (RecursiveGroupException e) {
//
//				e.printStackTrace();
//			}
//
//		}else if (staffUpdate.getFacultyCode().getCode().equals("UMKCEE") && staffUpdate.getStaffCategory().equals("B")) {
//
//			InPrincipalRole roleUpdate = new InPrincipalRoleImpl();
//			roleUpdate.setPrincipal(principalUpdate);
//			roleUpdate.setRole(InRoleType.ROLE_ADMINISTRATOR);
//			identityService.addPrincipalRole(principalUpdate, roleUpdate);
//
//			try {
//				LOG.debug("groupUpdate");
//				// Group
//				InGroup groupUpdate = identityService.findGroupByName("GRP_KRN_ADM_" + staffUpdate.getFacultyCode().getCode());
//				LOG.debug("GROUP_UPDATE:{}", groupUpdate.getName());
//				// GroupMember
//				InGroupMember memberUpdate = new InGroupMemberImpl();
//				memberUpdate.setGroup(groupUpdate);
//				memberUpdate.setPrincipal(principalUpdate);
//				identityService.addGroupMember(groupUpdate, principalUpdate);
//			} catch (RecursiveGroupException e) {
//
//				e.printStackTrace();
//			}
//
//		}else if (staffUpdate.getStaffCategory().equals("A")) {
//
//			InPrincipalRole roleUpdate = new InPrincipalRoleImpl();
//			roleUpdate.setPrincipal(principalUpdate);
//			roleUpdate.setRole(InRoleType.ROLE_FCTY);
//			identityService.addPrincipalRole(principalUpdate, roleUpdate);
//
//			try {
//				LOG.debug("groupUpdate");
//
//				// GroupMember
//				InGroup groupLama = identityService.findGroupByUser(userUpdate);
//				LOG.debug("Group_Lama:{}",groupLama.getName());
//				
//				InGroupMember memberUpdate = identityService.findMemberByPrincipal(principalUpdate);
//				LOG.debug("Member_Lama:{}",memberUpdate.getGroup().getName());
//				
//				identityService.deleteGroupMember(groupLama, principalUpdate);
//
//				// Group
//				InGroup groupUpdate = identityService.findGroupByName("GRP_PGW_FCTY_" + staffUpdate.getFacultyCode().getCode());
//				LOG.debug("GROUP_UPDATE:{}", groupUpdate.getName());
//				
//				InGroupMember memberBaharu = new InGroupMemberImpl();
//				memberBaharu.setGroup(groupUpdate);
//				memberBaharu.setPrincipal(principalUpdate);
//				identityService.addGroupMember(groupUpdate, principalUpdate);
//				InGroupMember memberBaru = identityService.findMemberByPrincipal(principalUpdate);
//				LOG.debug("Member_baru:{}",memberBaru.getGroup().getName());
//			} catch (RecursiveGroupException e) {
//
//				e.printStackTrace();
//			}
//
//		}else if (staffUpdate.getStaffCategory().equals("B")) {
//
//			InPrincipalRole roleUpdate = new InPrincipalRoleImpl();
//			roleUpdate.setPrincipal(principalUpdate);
//			roleUpdate.setRole(InRoleType.ROLE_FCTY);
//			identityService.addPrincipalRole(principalUpdate, roleUpdate);
//
//			try {
//				LOG.debug("groupUpdate");
//				// Group
//				InGroup groupUpdate = identityService.findGroupByName("GRP_KRN_FCTY_" + staffUpdate.getFacultyCode().getCode());
//				LOG.debug("GROUP_UPDATE:{}", groupUpdate.getName());
//				// GroupMember
//				InGroupMember memberUpdate = new InGroupMemberImpl();
//				memberUpdate.setGroup(groupUpdate);
//				memberUpdate.setPrincipal(principalUpdate);
//				identityService.addGroupMember(groupUpdate, principalUpdate);
//			} catch (RecursiveGroupException e) {
//
//				e.printStackTrace();
//			}
//
//		}else{
//			LOG.debug("UNKNOWN");
//		}
//		  LOG.debug("FINISH UPDATE UMKCEE STAFF");


		return self();
	}
}