package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InLanguage;
import my.edu.umk.pams.intake.common.model.InSpmResult;
import my.edu.umk.pams.intake.common.model.InSpmResultImpl;
import my.edu.umk.pams.intake.core.GenericDaoSupport;
import my.edu.umk.pams.intake.core.InMetaState;

@Repository("InSpmResultDao")
public class InSpmResultDaoImpl extends GenericDaoSupport<Long, InSpmResult> implements InSpmResultDao {

	public InSpmResultDaoImpl() {
		super(InSpmResultImpl.class);
	}

	@Override
    public InSpmResult findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from InSpmResult s where s.code = :code and  " +
                " s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        return (InSpmResult) query.uniqueResult();
    }

    @Override
    public List<InSpmResult> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from InSpmResult s where " +
                "(upper(s.code) like upper(:filter) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<InSpmResult>) query.list();

    }
    
	@Override
	public List<InSpmResult> findSpmResults(InIntakeApplication application) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("select p from InSpmResult p where " + "p.application = :application "
				+ "and p.metadata.state = :state");
		query.setInteger("state", InMetaState.ACTIVE.ordinal());
		query.setEntity("application", application);
		return (List<InSpmResult>) query.list();
	}

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from InSpmResult s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

}
