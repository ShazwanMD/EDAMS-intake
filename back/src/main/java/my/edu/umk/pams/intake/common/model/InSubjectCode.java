package my.edu.umk.pams.intake.common.model;

import java.util.List;

import my.edu.umk.pams.intake.core.InMetaObject;

public interface InSubjectCode extends InMetaObject {

    String getCode();

    void setCode(String code);

    String getDescriptionMs();

    void setDescriptionMs(String descriptionMs);

    String getDescriptionEn();

    void setDescriptionEn(String descriptionEn);
    
    List<InSpmSubjectCode> getSpmSubjectCodes();

	void setSpmSubjectCodes(List<InSpmSubjectCode> spmSubjectCodes);
    
}
