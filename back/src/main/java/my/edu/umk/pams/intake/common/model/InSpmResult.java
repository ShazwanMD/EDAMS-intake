package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InResult;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.core.InMetaObject;

public interface InSpmResult extends InMetaObject {
	
	String getGraduationYear();
	
	void setGraduationYear(String graduationYear);
	
	String getAggregate();
	
	void setAggregate(String aggregate);
		
	InSpmSubjectCode getSpmSubjectCode();
	
	void setSpmSubjectCode(InSpmSubjectCode spmSubjectCode);
	
	InGradeCode getGradeCode();
	
	void setGradeCode(InGradeCode gradeCode);
		
    InIntakeApplication getApplication();

    void setApplication(InIntakeApplication application);
    
    InResultType getResultType();
    
    void setResultType(InResultType resultType);


}
