package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.common.model.InSpmResult;
import my.edu.umk.pams.intake.common.model.InStpmResult;
import my.edu.umk.pams.intake.common.model.InStpmResultImpl;
import my.edu.umk.pams.intake.core.GenericDaoSupport;
import my.edu.umk.pams.intake.core.InMetaState;

@Repository("InStpmResultDao")
public class InStpmResultDaoImpl extends GenericDaoSupport<Long, InStpmResult> implements InStpmResultDao {

	public InStpmResultDaoImpl() {
		super(InStpmResultImpl.class);
	}

	@Override
	public InStpmResult findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from InStpmResult s where s.code = :code and  " +
                " s.metadata.state = :state");
        query.setString("code", code);
        query.setCacheable(true);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        return (InStpmResult) query.uniqueResult();
	}

	@Override
	public List<InStpmResult> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from InStpmResult s where " +
                "(upper(s.code) like upper(:filter) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", InMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<InStpmResult>) query.list();
	}

	@Override
	public List<InStpmResult> findStpmResults(InIntakeApplication application) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("select p from InStpmResult p where " + "p.application = :application "
				+ "and p.metadata.state = :state");
		query.setInteger("state", InMetaState.ACTIVE.ordinal());
		query.setEntity("application", application);
		return (List<InStpmResult>) query.list();
	}

}
