package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.model.InResultType;
import my.edu.umk.pams.intake.core.InMetaObject;

public interface InDiplomaResult extends InMetaObject {
	
	String getGraduationYear();

	void setGraduationYear(String graduationYear);
	
	String getCgpa();

	void setCgpa(String cgpa);
	
	String getInstitution();
	
	void setInstitution(String institution);
	
	String getProgram();
	
	void setProgram(String program);
	
	InResultType getResultType();

	void setResultType(InResultType resultType);
	
	InIntakeApplication getApplication();

	void setApplication(InIntakeApplication application);
	

}
