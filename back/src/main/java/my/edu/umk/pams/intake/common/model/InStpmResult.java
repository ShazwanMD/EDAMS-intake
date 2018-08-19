package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.core.InMetaObject;

public interface InStpmResult extends InMetaObject {
	
	String getGraduationYear();
	
	void setGraduationYear(String graduationYear);
		
	String getAggregate();
	
	void setAggregate(String aggregate);
	
    InResultType getResultType();
    
    void setResultType(InResultType resultType);

    InStpmSubjectCode getStpmSubjectCode();
    
    void setStpmSubjectCode(InStpmSubjectCode stpmSubjectCode);
    
    InGradeCode getGradeCode();
    
    void setGradeCode(InGradeCode gradeCode);
    
    InIntakeApplication getApplication();
    
    void setApplication(InIntakeApplication application);
}
