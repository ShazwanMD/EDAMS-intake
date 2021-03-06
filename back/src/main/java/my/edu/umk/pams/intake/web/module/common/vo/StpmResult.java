package my.edu.umk.pams.intake.web.module.common.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.intake.web.module.application.vo.IntakeApplication;
import my.edu.umk.pams.intake.web.module.application.vo.ResultType;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

public class StpmResult extends MetaObject {
	
	private String aggregate;
	private String graduationYear;
	private GradeCode gradeCode;
	private StpmSubjectCode stpmSubjectCode;
	private IntakeApplication application;
	private ResultType resultType;

    
    public ResultType getResultType() {
		return resultType;
	}

	public void setResultType(ResultType resultType) {
		this.resultType = resultType;
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
	
	public StpmSubjectCode getStpmSubjectCode() {
		return stpmSubjectCode;
	}

	public void setStpmSubjectCode(StpmSubjectCode stpmSubjectCode) {
		this.stpmSubjectCode = stpmSubjectCode;
	}

	public IntakeApplication getApplication() {
		return application;
	}

	public void setApplication(IntakeApplication application) {
		this.application = application;
	}

	@JsonCreator
	public static StpmResult create(String jsonString) {
		StpmResult o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, StpmResult.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}


}
