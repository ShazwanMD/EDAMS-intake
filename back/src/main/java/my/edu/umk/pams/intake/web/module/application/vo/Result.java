package my.edu.umk.pams.intake.web.module.application.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
public class Result extends MetaObject {

    private ResultType resultType;
    private String name;
    private String field;
    private String malayResult;
    private String englishResult;
    private Integer graduationYear;
    private BigDecimal resultNumeric;
    private String resultAlphanumeric;

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public BigDecimal getResultNumeric() {
        return resultNumeric;
    }

    public void setResultNumeric(BigDecimal resultNumeric) {
        this.resultNumeric = resultNumeric;
    }

    public String getResultAlphanumeric() {
        return resultAlphanumeric;
    }

    public void setResultAlphanumeric(String resultAlphanumeric) {
        this.resultAlphanumeric = resultAlphanumeric;
    }
    

    public String getMalayResult() {
		return malayResult;
	}

	public void setMalayResult(String malayResult) {
		this.malayResult = malayResult;
	}

	public String getEnglishResult() {
		return englishResult;
	}

	public void setEnglishResult(String englishResult) {
		this.englishResult = englishResult;
	}

	@JsonCreator
    public static Result create(String jsonString) {
        Result o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, Result.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}