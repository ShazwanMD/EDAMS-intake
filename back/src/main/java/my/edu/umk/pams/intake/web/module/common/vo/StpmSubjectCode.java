package my.edu.umk.pams.intake.web.module.common.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

public class StpmSubjectCode extends MetaObject {
	
	private String code;
	private String description;


	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	


	@JsonCreator
	public static StpmSubjectCode create(String jsonString) {
		StpmSubjectCode o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, StpmSubjectCode.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}


}
