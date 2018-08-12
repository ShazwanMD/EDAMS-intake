package my.edu.umk.pams.intake.identity.dao;


import my.edu.umk.pams.intake.core.GenericDao;
import my.edu.umk.pams.intake.identity.model.InGroup;
import my.edu.umk.pams.intake.identity.model.InGroupMember;
import my.edu.umk.pams.intake.identity.model.InPrincipal;
import my.edu.umk.pams.intake.identity.model.InPrincipalType;
import my.edu.umk.pams.intake.identity.model.InUser;

import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface InGroupDao extends GenericDao<Long, InGroup> {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================
    boolean hasMembership(InGroup group, InPrincipal principal);

    boolean isExists(String name);

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    InGroup findByName(String name);

    List<InGroup> findAll();

    List<InGroup> findImmediate(InPrincipal principal);

    List<InGroup> findImmediate(InPrincipal principal, Integer offset, Integer limit);

    Set<InGroup> findEffectiveAsNative(InPrincipal principal);

    List<InGroup> findAvailableGroups(InUser user);

    List<InPrincipal> findAvailableMembers(InGroup group);

    List<InPrincipal> findMembers(InGroup group, InPrincipalType principalType);

    List<InPrincipal> findMembers(InGroup group);

    List<InPrincipal> findMembers(InGroup group, Integer offset, Integer limit);

    List<InGroup> find(String filter, Integer offset, Integer limit);

    List<InGroup> findMemberships(InPrincipal principal);

    Integer count(String filter);

    Integer countMember(InGroup group);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addMember(InGroup group, InPrincipal member, InUser user) throws RecursiveGroupException;

    void addMembers(InGroup group, List<InPrincipal> members, InUser user) throws RecursiveGroupException;

    void deleteMember(InGroup group, InPrincipal member);

	InGroup findGroupByUser(InUser user);
	
	InGroupMember findByPrincipal(InPrincipal principal);

}
