package my.edu.umk.pams.intake.identity.dao;

import my.edu.umk.pams.intake.core.GenericDao;
import my.edu.umk.pams.intake.identity.model.InPrincipal;
import my.edu.umk.pams.intake.identity.model.InPrincipalRole;
import my.edu.umk.pams.intake.identity.model.InPrincipalType;
import my.edu.umk.pams.intake.identity.model.InUser;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface InPrincipalDao extends GenericDao<Long, InPrincipal> {

    // ====================================================================================================
    // HELPERS
    // ====================================================================================================

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    InPrincipal findByName(String name);

    List<InPrincipal> findAllPrincipals();

    List<InPrincipal> find(String filter);

    List<InPrincipal> find(String filter, InPrincipalType type);

    List<InPrincipal> find(String filter, Integer offset, Integer limit);

    void addRole(InPrincipal principal, InPrincipalRole principalRole, InUser user);

    void deleteRole(InPrincipal principal, InPrincipalRole principalRole, InUser user);

    Integer count(String filter);

	InPrincipalRole findByPrincipal(InPrincipal principal);


    // ====================================================================================================
    // CRUD
    // ====================================================================================================

}
