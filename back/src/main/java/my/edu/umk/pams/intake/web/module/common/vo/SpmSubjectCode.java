package my.edu.umk.pams.intake.web.module.common.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

public class SpmSubjectCode extends MetaObject {

	private String code;
	private String descriptionMs;
	private String descriptionEn;
	private SubjectCode subjectCode;

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
	
	public SubjectCode getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(SubjectCode subjectCode) {
		this.subjectCode = subjectCode;
	}

	@JsonCreator
	public static SpmSubjectCode create(String jsonString) {
		SpmSubjectCode o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, SpmSubjectCode.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
}
