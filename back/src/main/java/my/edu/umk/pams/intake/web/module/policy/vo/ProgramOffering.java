package my.edu.umk.pams.intake.web.module.policy.vo;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.intake.web.module.common.vo.ProgramCode;
import my.edu.umk.pams.intake.web.module.common.vo.ProgramFieldCode;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

import java.io.IOException;

/**
 * @author PAMS
 */
public class ProgramOffering extends MetaObject {

    private Boolean interview;
    private Integer projection;
    private ProgramCode programCode;
    private String generalCriteria;
    private String specificCriteria;

    public Boolean getInterview() {
        return interview;
    }

    public void setInterview(Boolean interview) {
        this.interview = interview;
    }

    public Integer getProjection() {
        return projection;
    }

    public void setProjection(Integer projection) {
        this.projection = projection;
    }

	public void setGeneralCriteria (String generalCriteria){
    	this.generalCriteria = generalCriteria;
    }
    
    public String getGeneralCriteria(){
    	return generalCriteria;
    }
    
    public void setSpecificCriteria (String specificCriteria){
    	this.specificCriteria = specificCriteria;
    }
    
    public String getSpecificCriteria(){
    	return specificCriteria;
    }
    
    public ProgramCode getProgramCode() {
		return programCode;
	}

	public void setProgramCode(ProgramCode programCode) {
		this.programCode = programCode;
	}

	@JsonCreator
    public static ProgramOffering create(String jsonString) {
        ProgramOffering o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ProgramOffering.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
