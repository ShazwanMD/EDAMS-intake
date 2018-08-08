package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.intake.common.model.InGuardianTypeCode;
import my.edu.umk.pams.intake.common.model.InGuardianTypeCodeImpl;
import my.edu.umk.pams.intake.core.GenericDaoSupport;
import my.edu.umk.pams.intake.core.InMetaState;

@Repository("InGuardianTypeCodeDao")
public class InGuardianTypeCodeDaoImpl extends GenericDaoSupport<Long, InGuardianTypeCode>
		implements InGuardianTypeCodeDao {

	private static final Logger LOG = LoggerFactory.getLogger(InGuardianTypeCodeDaoImpl.class);

	public InGuardianTypeCodeDaoImpl() {
		super(InGuardianTypeCodeImpl.class);
	}

	@Override
	public InGuardianTypeCode findByCode(String code) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select s from InGuardianTypeCode s where s.code = :code and  " + " s.metadata.state = :state");
		query.setString("code", code);
		query.setCacheable(true);
		query.setInteger("state", InMetaState.ACTIVE.ordinal());
		return (InGuardianTypeCode) query.uniqueResult();
	}

	@Override
	public List<InGuardianTypeCode> find(String filter, Integer offset, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select s from InGuardianTypeCode s where "
				+ "(upper(s.code) like upper(:filter) " + "or upper(s.descriptionEn) like upper(:filter) "
				+ "or upper(s.descriptionMs) like upper(:filter)) " + "and s.metadata.state = :state ");
		query.setString("filter", WILDCARD + filter + WILDCARD);
		query.setInteger("state", InMetaState.ACTIVE.ordinal());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		query.setCacheable(true);
		return (List<InGuardianTypeCode>) query.list();
	}

	@Override
	public Integer count(String filter) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select count(s) from InGuardianTypeCode s where " + "(upper(s.code) like upper(:filter) "
						+ "or upper(s.description) like upper(:filter)) " + "and s.metadata.state = :state ");
		query.setString("filter", WILDCARD + filter + WILDCARD);
		query.setInteger("state", InMetaState.ACTIVE.ordinal());
		return ((Long) query.uniqueResult()).intValue();
	}

	@Override
	public boolean isExists(String code) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from InGuardianTypeCode s where " + "s.code = :code "
				+ "and s.metadata.state = :state ");
		query.setString("code", code);
		query.setInteger("state", InMetaState.ACTIVE.ordinal());
		return 0 < ((Long) query.uniqueResult()).intValue();
	}
}
