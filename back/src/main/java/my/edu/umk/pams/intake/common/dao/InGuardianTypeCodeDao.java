package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import my.edu.umk.pams.intake.common.model.InGuardianTypeCode;
import my.edu.umk.pams.intake.core.GenericDao;

public interface InGuardianTypeCodeDao extends GenericDao<Long, InGuardianTypeCode> {
	// ====================================================================================================
	// FINDER
	// ====================================================================================================
	InGuardianTypeCode findByCode(String code);

	List<InGuardianTypeCode> find(String filter, Integer offset, Integer limit);

	// ====================================================================================================
	// HELPER
	// ====================================================================================================
	Integer count(String filter);

	boolean isExists(String code);
}
