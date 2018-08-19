package my.edu.umk.pams.intake.web.module.common.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.intake.web.module.application.vo.IntakeApplication;
import my.edu.umk.pams.intake.web.module.application.vo.ResultType;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

public class SpmResult extends MetaObject {

	private String graduationYear;
	private String aggregate;
	private GradeCode gradeCode;
	private SpmSubjectCode spmSubjectCode;
	private ResultType resultType;
	private IntakeApplication application;
	
	

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public IntakeApplication getApplication() {
		return application;
	}

	public void setApplication(IntakeApplication application) {
		this.application = application;
	}

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

	public GradeCode getGradeCode() {
		return gradeCode;
	}

	public void setGradeCode(GradeCode gradeCode) {
		this.gradeCode = gradeCode;
	}
	
	public SpmSubjectCode getSpmSubjectCode() {
		return spmSubjectCode;
	}

	public void setSpmSubjectCode(SpmSubjectCode spmSubjectCode) {
		this.spmSubjectCode = spmSubjectCode;
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
