package my.edu.umk.pams.intake.common.dao;

import java.util.List;

import my.edu.umk.pams.intake.common.model.InStpmSubjectCode;
import my.edu.umk.pams.intake.core.GenericDao;

public interface InStpmSubjectCodeDao extends GenericDao<Long, InStpmSubjectCode> {
	
	InStpmSubjectCode findByCode(String code);
	
    List<InStpmSubjectCode> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);

    boolean isExists(String code);

}
