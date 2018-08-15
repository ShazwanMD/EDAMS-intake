package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.common.model.InSpmResult;
import my.edu.umk.pams.intake.core.GenericDao;

public interface InSpmResultDao extends GenericDao<Long, InSpmResult> {

	InSpmResult findByCode(String code);

	List<InSpmResult> find(String filter, Integer offset, Integer limit);
	
	List<InSpmResult> findSpmResults(InIntakeApplication application);

	Integer count(String filter);
}
