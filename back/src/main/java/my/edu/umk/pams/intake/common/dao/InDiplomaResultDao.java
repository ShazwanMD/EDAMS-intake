package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.common.model.InDiplomaResult;
import my.edu.umk.pams.intake.core.GenericDao;

public interface InDiplomaResultDao extends GenericDao<Long, InDiplomaResult> {
	
	List<InDiplomaResult> findDiplomaResults(InIntakeApplication application);

}
