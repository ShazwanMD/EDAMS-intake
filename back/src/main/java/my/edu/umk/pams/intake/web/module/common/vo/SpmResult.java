package my.edu.umk.pams.intake.web.module.common.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

public class SpmResult extends MetaObject {

	private String code;
	private String aggregate;
	private String graduationYear;
	private GradeCode gradeCode;
	private SubjectCode subjectCode;

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public String getAggregate() {
        return aggregate;
    }

    public void setAggregate(String aggregate) {
        this.aggregate = aggregate;
    }
   
    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

	public GradeCode getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(GradeCode gradeCode) {
		this.gradeCode = gradeCode;
	}

	public SubjectCode getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(SubjectCode subjectCode) {
		this.subjectCode = subjectCode;
	}

	@JsonCreator
	public static SpmResult create(String jsonString) {
		SpmResult o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, SpmResult.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}

}
