package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.common.model.InStpmResult;
import my.edu.umk.pams.intake.core.GenericDao;

public interface InStpmResultDao extends GenericDao<Long, InStpmResult> {
	
	InStpmResult findByCode(String code);

	List<InStpmResult> find(String filter, Integer offset, Integer limit);
	
	List<InStpmResult> findStpmResults(InIntakeApplication application);


}
