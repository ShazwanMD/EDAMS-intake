package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.core.InMetaObject;

public interface InSpmSubjectCode extends InMetaObject {

    String getCode();

    void setCode(String code);
    
	InSubjectCode getSubjectCode();
	
	void setSubjectCode(InSubjectCode subjectCode);
}
