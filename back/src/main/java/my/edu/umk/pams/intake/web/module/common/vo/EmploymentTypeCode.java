package my.edu.umk.pams.intake.web.module.common.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

import java.io.IOException;

/**
 * @author PAMS
 */
public class EmploymentTypeCode extends MetaObject {
    private String code;
    private String descriptionMs;
    private String descriptionEn;
  

    public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}


	public String getDescriptionMs() {
		return descriptionMs;
	}



	public void setDescriptionMs(String descriptionMs) {
		this.descriptionMs = descriptionMs;
	}



	public String getDescriptionEn() {
		return descriptionEn;
	}



	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}



	@JsonCreator
    public static EmploymentTypeCode create(String jsonString) {
		EmploymentTypeCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, EmploymentTypeCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}