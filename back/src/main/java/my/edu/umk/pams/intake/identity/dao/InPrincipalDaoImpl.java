package my.edu.umk.pams.intake.identity.dao;

import my.edu.umk.pams.intake.core.GenericDaoSupport;
import my.edu.umk.pams.intake.core.InMetadata;
import my.edu.umk.pams.intake.identity.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static my.edu.umk.pams.intake.core.InMetaState.ACTIVE;


/**
 * @author canang technologies
 * @since 1/31/14
 */
@Repository("principalDao")
public class InPrincipalDaoImpl extends GenericDaoSupport<Long, InPrincipal> implements InPrincipalDao {

    private static final Logger LOG = LoggerFactory.getLogger(InPrincipalDaoImpl.class);

    public InPrincipalDaoImpl() {
        super(InPrincipalImpl.class);
    }

    @Override
    public List<InPrincipal> findAllPrincipals() {
        Session session = sessionFactory.getCurrentSession();
        List<InPrincipal> results = new ArrayList<InPrincipal>();
        Query query = session.createQuery("select p from InUser p order by p.name");
        results.addAll((List<InPrincipal>) query.list());

        Query queryGroup = session.createQuery("select p from InGroup p order by p.name ");
        results.addAll((List<InPrincipal>) queryGroup.list());

        return results;
    }

    @Override
    public List<InPrincipal> find(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InPrincipal p where p.name like :filter order by p.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return query.list();
    }

    @Override
    public List<InPrincipal> find(String filter, InPrincipalType type) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InPrincipal p where " +
                "p.name like :filter " +
                "and p.principalType = :principalType " +
                "order by p.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("principalType", type.ordinal());
        return query.list();
    }

    @Override
    public List<InPrincipal> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InPrincipal p where " +
                "p.id in (" +
                "select u.id from InUser u where " +
                "(upper(u.name) like upper(:filter)" +
                "or upper(u.firstName) like upper(:filter)" +
                "or upper(u.lastName) like upper(:filter))" +
                ") " +
                "or p.id in (select g.id from InGroup g  where upper(g.name) like upper(:filter)) " +
                "order by p.name");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }

    @Override
    public void addRole(InPrincipal principal, InPrincipalRole role, InUser user) {
        Session session = sessionFactory.getCurrentSession();
        role.setPrincipal(principal);

        // prepare metadata
        InMetadata metadata = new InMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        role.setMetadata(metadata);
        session.save(role);
    }

    @Override
    public void deleteRole(InPrincipal principal, InPrincipalRole role, InUser user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(role);
    }
    
    @Override
    public InPrincipalRole findByPrincipal(InPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InPrincipalRole p where " +
                "p.principal = :principal ");
        query.setEntity("principal", principal);
        return (InPrincipalRole) query.uniqueResult();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(u) from InPrincipal u where " +
                "u.name like :filter ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public InPrincipal findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InPrincipal p where " +
                "upper(p.name) = upper(:name) ");
        query.setString("name", name);
        return (InPrincipal) query.uniqueResult();
    }
}
