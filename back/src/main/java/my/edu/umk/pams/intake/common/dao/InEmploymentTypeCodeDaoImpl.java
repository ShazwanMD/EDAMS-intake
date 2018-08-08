package my.edu.umk.pams.intake.common.dao;

import my.edu.umk.pams.intake.common.model.InEmploymentTypeCode;
import my.edu.umk.pams.intake.common.model.InEmploymentTypeCodeImpl;
import my.edu.umk.pams.intake.core.GenericDaoSupport;
import my.edu.umk.pams.intake.core.InMetaState;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("InEmploymentTypeCodeDao")
public class InEmploymentTypeCodeDaoImpl extends GenericDaoSupport<Long, InEmploymentTypeCode> implements InEmploymentTypeCodeDao {

    private static final Logger LOG = LoggerFactory.getLogger(InEmploymentTypeCodeDaoImpl.class);

    public InEmploymentTypeCodeDaoImpl() {
        super(InEmploymentTypeCodeImpl.class);
    }

    @Override
    public InEmploymentTypeCode findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from InEmploymentTypeCode s where s.code = :code and  " +
                " s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        return (InEmploymentTypeCode) query.uniqueResult();
    }

    @Override
    public List<InEmploymentTypeCode> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from InEmploymentTypeCode s where " +
        		 "(upper(s.code) like upper(:filter) " +
                 "or upper(s.descriptionEn) like upper(:filter) " +
                 "or upper(s.descriptionMs) like upper(:filter)) " +
                 "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<InEmploymentTypeCode>) query.list();    
    }
    
    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from InEmploymentTypeCode s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from InEmploymentTypeCode s where " +
                "s.code = :code " +
                "and s.metadata.state = :state ");
        query.setString("code", code);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        return 0 < ((Long) query.uniqueResult()).intValue();
    }
}
