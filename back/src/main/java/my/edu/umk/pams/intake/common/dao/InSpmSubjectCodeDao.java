package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import my.edu.umk.pams.intake.common.model.InSpmSubjectCode;
import my.edu.umk.pams.intake.core.GenericDao;

public interface InSpmSubjectCodeDao extends GenericDao<Long, InSpmSubjectCode> {

	InSpmSubjectCode findByCode(String code);
	
    List<InSpmSubjectCode> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);

    boolean isExists(String code);
}
