package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.core.InMetaObject;

public interface InSpmResult extends InMetaObject {
	
	String getGraduationYear();
	
	void setGraduationYear(String graduationYear);
	
	String getAggregate();
	
	void setAggregate(String aggregate);
	

    String getCode();

    void setCode(String code);
	
	InSubjectCode getSubjectCode();
	
	void setSubjectCode(InSubjectCode subjectCode);
	
	InGradeCode getGradeCode();
	
	void setGradeCode(InGradeCode gradeCode);


}
