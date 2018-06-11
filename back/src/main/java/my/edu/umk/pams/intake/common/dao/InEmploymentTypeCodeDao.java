package my.edu.umk.pams.intake.common.dao;
import my.edu.umk.pams.intake.common.model.InEmploymentTypeCode;
import my.edu.umk.pams.intake.core.GenericDao;

import java.util.List;

public interface InEmploymentTypeCodeDao extends GenericDao<Long, InEmploymentTypeCode> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
	InEmploymentTypeCode findByCode(String code);

    List<InEmploymentTypeCode> find(String filter, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================
    Integer count(String filter);

    boolean isExists(String code);
}
