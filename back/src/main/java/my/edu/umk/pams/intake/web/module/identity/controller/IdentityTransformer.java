package my.edu.umk.pams.intake.web.module.identity.controller;

import my.edu.umk.pams.intake.identity.model.InActor;
import my.edu.umk.pams.intake.identity.model.InStaff;
import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.web.module.common.controller.CommonTransformer;
import my.edu.umk.pams.intake.web.module.identity.vo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author PAMS
 */
@Component("identityTransformer")
public class IdentityTransformer {
	
	@Autowired
	private CommonTransformer commonTransformer;

    public Staff toStaffVo(InStaff staff) {
        Staff m = new Staff();
        m.setId(staff.getId());
        m.setIdentityNo(staff.getIdentityNo());
        m.setName(staff.getName());
        m.setEmail(staff.getEmail());
        m.setMobile(staff.getMobile());
        m.setFacultyCode(commonTransformer.toFacultyCodeVo(staff.getFacultyCode()));
        //m.setStaffCategory(staff.getStaffCategory());
        m.setStaffType(StaffType.get(staff.getStaffType().ordinal()));
        return m;
    }

    public List<Staff> toStaffVos(List<InStaff> staffs) {
        List<Staff> vos = staffs.stream()
                .map((staff) -> toStaffVo(staff))
                .collect(toList());
        return vos;
    }

    public Applicant toApplicantVo(InApplicant Applicant) {
        Applicant m = new Applicant();
        m.setId(Applicant.getId());
        m.setIdentityNo(Applicant.getIdentityNo());
        m.setName(Applicant.getName());
        m.setEmail(Applicant.getEmail());
        m.setMobile(Applicant.getMobile());
        m.setPhone(Applicant.getPhone());
        m.setFax(Applicant.getFax());
    
        return m;
    }

    public List<Applicant> toApplicantVos(List<InApplicant> applicants) {
        List<Applicant> vos = applicants.stream()
                .map((applicant) -> toApplicantVo(applicant))
                .collect(toList());
        return vos;
    }

    public List<Actor> toActorVos(List<InActor> actors) {
        List<Actor> vos = actors.stream()
                .map((actor) -> toActorVo(actor))
                .collect(toList());
        return vos;
    }

    public Actor toActorVo(InActor actor) {
         if(actor instanceof InStaff)
        return toStaffVo((InStaff) actor);
        else if(actor instanceof InApplicant)
        return toApplicantVo((InApplicant) actor);
        else return null;
    }
    
    public User toUserVo(InUser user) {
        User m = new User();
        m.setId(user.getId());
        m.setName(user.getName());
        m.setPassword(user.getPassword());
        m.setRealName(user.getRealName());
        m.setEmail(user.getEmail());
    
        return m;
    }

    public List<User> toUserVos(List<InUser> users) {
        List<User> vos = users.stream()
                .map((user) -> toUserVo(user))
                .collect(toList());
        return vos;
    }    
    
    
}
