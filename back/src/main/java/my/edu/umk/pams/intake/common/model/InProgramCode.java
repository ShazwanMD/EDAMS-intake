package my.edu.umk.pams.intake.common.model;

import java.util.List;

import my.edu.umk.pams.intake.core.InMetaObject;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;

public interface  InProgramCode extends InMetaObject {

    String getCode();

    void setCode(String code);

	InProgramLevel getProgramLevel();

	void setProgramLevel(InProgramLevel programLevel);

	String getDescriptionMs();

	void setDescriptionMs(String descriptionMs);

	String getDescriptionEn();

	void setDescriptionEn(String descriptionEn);
	
	InFacultyCode getFacultyCode();
	
	void setFacultyCode(InFacultyCode facultyCode);

	InGraduateCenter getGraduateCenter();

	void setGraduateCenter(InGraduateCenter graduateCenter);

	List<InProgramFieldCode> getProgramFieldCodes();

	void setProgramFieldCodes(List<InProgramFieldCode> programFieldCodes);
}
