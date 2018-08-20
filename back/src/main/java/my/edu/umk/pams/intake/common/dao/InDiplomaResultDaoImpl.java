package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.common.model.InDiplomaResult;
import my.edu.umk.pams.intake.common.model.InDiplomaResultImpl;
import my.edu.umk.pams.intake.common.model.InSpmResult;
import my.edu.umk.pams.intake.core.GenericDaoSupport;
import my.edu.umk.pams.intake.core.InMetaState;

@Repository("InDiplomaResultDao")
public class InDiplomaResultDaoImpl extends GenericDaoSupport<Long, InDiplomaResult> implements InDiplomaResultDao {

	public InDiplomaResultDaoImpl() {
		super(InDiplomaResultImpl.class);
	}
	
	@Override
	public List<InDiplomaResult> findDiplomaResults(InIntakeApplication application) {
		Session currentSession = sessionFactory.getCurrentSession();
		Query query = currentSession.createQuery("select p from InDiplomaResult p where " + "p.application = :application "
				+ "and p.metadata.state = :state");
		query.setInteger("state", InMetaState.ACTIVE.ordinal());
		query.setEntity("application", application);
		return (List<InDiplomaResult>) query.list();
	}

}
