package my.edu.umk.pams.intake.identity.dao;

import my.edu.umk.pams.intake.core.GenericDaoSupport;
import my.edu.umk.pams.intake.core.InMetaState;
import my.edu.umk.pams.intake.core.InMetadata;
import my.edu.umk.pams.intake.identity.model.*;
import org.apache.commons.lang3.Validate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.LongType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static my.edu.umk.pams.intake.core.InMetaState.ACTIVE;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Repository("groupDao")
public class InGroupDaoImpl extends GenericDaoSupport<Long, InGroup> implements InGroupDao {

    private static final Logger LOG = LoggerFactory.getLogger(InGroupDaoImpl.class);

    public InGroupDaoImpl() {
        super(InGroupImpl.class);
    }
    // =============================================================================
    // FINDER METHODS
    // =============================================================================

    @Override
    public List<InGroup> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from InGroup g order by g.name");
        return (List<InGroup>) query.list();
    }

    @Override
    public InGroup findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from InGroup g where g.name = :name");
        query.setString("name", name);
        return (InGroup) query.uniqueResult();
    }

    @Override
    public InGroup findGroupByUser(InUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InGroup p where " +
                "p in (select gm.group from InGroupMember gm where gm.principal = :user)" +
                "and p.metadata.state = :state " +
                "order by p.name asc");
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("user", user);
        return (InGroup) query.uniqueResult();
    }
    
    @Override
    public InGroupMember findByPrincipal(InPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InGroupMember p where " +
                "p.principal = :principal " +
                "and p.metadata.state = :state ");
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("principal", principal);
        return (InGroupMember) query.uniqueResult();
    }

    @Override
    public List<InGroup> findImmediate(InPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.group from InGroupMember gm inner join gm.principal where " +
                "gm.principal = :principal");
        query.setEntity("principal", principal);
        return (List<InGroup>) query.list();
    }


    @Override
    public List<InGroup> findImmediate(InPrincipal principal, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.group from InGroupMember gm inner join gm.principal where " +
                "gm.principal = :principal");
        query.setEntity("principal", principal);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<InGroup>) query.list();
    }

    @Override
    public Set<InGroup> findEffectiveAsNative(InPrincipal principal) {
        Set<InGroup> groups = new HashSet<InGroup>();
        Session session = sessionFactory.getCurrentSession();
        SQLQuery query = session.createSQLQuery("WITH RECURSIVE " +
                "Q AS " +
                "( SELECT  GROUP_ID, PRINCIPAL_ID " +
                "    FROM    IN_GROP_MMBR " +
                "    WHERE   PRINCIPAL_ID = " + principal.getId() +
                "    UNION ALL " +
                "    SELECT  GM.GROUP_ID, GM.PRINCIPAL_ID " +
                "    FROM    IN_GROP_MMBR GM " +
                "    JOIN    Q " +
                "    ON      GM.PRINCIPAL_ID = Q.GROUP_ID " +
                ") " +
                "SELECT  GROUP_ID FROM  Q");
        query.addScalar("GROUP_ID", LongType.INSTANCE);
        List<Long> results = (List<Long>) query.list();
        for (Long result : results) {
            groups.add(findById(result));
        }
        return groups;
    }

    @Override
    public List<InGroup> findAvailableGroups(InUser user) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InGroup p where " +
                "p not in (select gm.group from InGroupMember gm where gm.principal = :user) " +
                "and p <> :user " +
                "and p.metadata.state = :state " +
                "order by p.name asc");
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("user", user);
        return (List<InGroup>) query.list();
    }

    @Override
    public List<InPrincipal> findAvailableMembers(InGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from InPrincipal p where " +
                "p not in (select gm.principal from InGroupMember gm where gm.group = :group) " +
                "and p <> :group " +
                "and p.metadata.state = :state " +
                "order by p.name asc");
        query.setInteger("state", ACTIVE.ordinal());
        query.setEntity("group", group);
        return (List<InPrincipal>) query.list();
    }

    @Override
    public List<InPrincipal> findMembers(InGroup group, InPrincipalType principalType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.group from InGroupMember gm inner join gm.principal where " +
                "gm.group = :group " +
                "and gm.principal.principalType= :principalType " +
                "and gm.principal.metadata.state = :state ");
        query.setEntity("group", group);
        query.setInteger("principalType", principalType.ordinal());
        return (List<InPrincipal>) query.list();
    }

    @Override
    public List<InPrincipal> findMembers(InGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.principal from InGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state " +
                "order by gm.principal.name");
        query.setEntity("group", group);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<InPrincipal>) query.list();
    }

    @Override
    public List<InPrincipal> findMembers(InGroup group, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select gm.principal from InGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state " +
                "order by gm.principal.name");
        query.setEntity("group", group);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<InPrincipal>) query.list();
    }

    @Override
    public List<InGroup> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct g from InGroup g where " +
                "g.name like upper(:filter) ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<InGroup>) query.list();
    }

    @Override
    public List<InGroup> findMemberships(InPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select distinct gm.group from InGroupMember gm where " +
                "gm.principal = :principal ");
        query.setEntity("principal", principal);
        return (List<InGroup>) query.list();
    }

    @Override
    public Integer count() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from InGroup g");
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from InGroup g where " +
                "g.name like upper(:filter)");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countMember(InGroup group) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(gm.principal) from InGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal.metadata.state = :state");
        query.setEntity("group", group);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(g) from InGroup g where " +
                "g.name = :name");
        query.setString("name", name);
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }

    @Override
    public boolean hasMembership(InGroup group, InPrincipal principal) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(gm) from InGroupMember gm where " +
                "gm.group = :group " +
                "and gm.principal = :principal");
        query.setEntity("group", group);
        query.setEntity("principal", principal);
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }

// =============================================================================
    // CRUD METHODS
    // =============================================================================

    @Override
    public void addMember(InGroup group, InPrincipal member, InUser user) throws RecursiveGroupException {
        Validate.notNull(group, "Group should not be null");
        Validate.notNull(member, "Group member should not be null");

        if (member instanceof InGroup) {
            if (checkRecursive(group, (InGroup) member))
                throw new RecursiveGroupException("Recursive user group " + group.getName() + " > " + member.getName());
        }

        Session session = sessionFactory.getCurrentSession();

        InGroupMember groupMember = new InGroupMemberImpl();
        groupMember.setGroup(group);
        groupMember.setPrincipal(member);

        InMetadata metadata = new InMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(InMetaState.ACTIVE);
        groupMember.setMetadata(metadata);

        session.save(groupMember);
    }

    @Override
    public void addMembers(InGroup group, List<InPrincipal> members, InUser user) throws RecursiveGroupException {
        List<InPrincipal> currentMembers = findMembers(group);
        List<InPrincipal> newMembers = new ArrayList<InPrincipal>();

        for (InPrincipal currentMember : currentMembers) {
            if (!newMembers.contains(currentMember)) {
                deleteMember(group, currentMember);
            }
        }
        for (InPrincipal newMember : newMembers) {
            if (!currentMembers.contains(newMember)) {
                addMember(group, newMember, user);
            }
        }
    }

    @Override
    public void deleteMember(InGroup group, InPrincipal member) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select g from InGroupMember g where " +
                "g.group = :group " +
                "and g.principal = :principal");
        query.setEntity("group", group);
        query.setEntity("principal", member);
        InGroupMember groupMember = (InGroupMember) query.uniqueResult();
        session.delete(groupMember);
    }

    private boolean checkRecursive(InGroup parent, InGroup child) {
        Set<InGroup> hierarchicalGroup = findEffectiveAsNative(parent);
        return !hierarchicalGroup.add(child);
    }

}
