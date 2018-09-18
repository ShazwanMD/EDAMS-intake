package my.edu.umk.pams.intake.web.module.application.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public class DiplomaResult extends MetaObject {

	private String graduationYear;
	private String cgpa;
	private String institution;
	private IntakeApplication application;
	private String program;
	private ResultType resultType;

	public String getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(String graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getCgpa() {
		return cgpa;
	}

	public void setCgpa(String cgpa) {
		this.cgpa = cgpa;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
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
	
	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	@JsonCreator
	public static DiplomaResult create(String jsonString) {
		DiplomaResult o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, DiplomaResult.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
}