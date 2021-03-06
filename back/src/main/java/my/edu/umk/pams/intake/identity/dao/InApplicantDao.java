package my.edu.umk.pams.intake.identity.dao;


import my.edu.umk.pams.intake.core.GenericDao;
import my.edu.umk.pams.intake.identity.model.InApplicant;
import my.edu.umk.pams.intake.identity.model.InUser;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface InApplicantDao extends GenericDao<Long, InApplicant> {

    InApplicant findByApplicantNo(String applicantNo);
    
    InApplicant findByEmail(String email);

    List<InApplicant> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
