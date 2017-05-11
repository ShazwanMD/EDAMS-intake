package my.edu.umk.pams.intake.web.module.common.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;
import my.edu.umk.pams.intake.web.module.policy.vo.ProgramLevel;

import java.io.IOException;

/**
 * @author PAMS
 */
public class ProgramCode extends MetaObject {
    private String code;
    private String descriptionEn;
    private String descriptionMs;
    private FacultyCode facultyCode;
    private GraduateCentre graduateCentre;
    private ProgramLevel programLevel;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getDescriptionMs() {
        return descriptionMs;
    }

    public void setDescriptionMs(String descriptionMs) {
        this.descriptionMs = descriptionMs;
    }
    
    public FacultyCode getFacultyCode() {
        return facultyCode;
    }

    public void setFacultyCode(FacultyCode facultyCode) {
        this.facultyCode = facultyCode;
    }
    
    public GraduateCentre getGraduateCentre() {
        return graduateCentre;
    }

    public void setGraduateCentre(GraduateCentre graduateCentre) {
        this.graduateCentre = graduateCentre;
    }
    
    public ProgramLevel getProgramLevel() {
        return programLevel;
    }

    public void setProgramLevel(ProgramLevel programLevel) {
        this.programLevel = programLevel;
    }

    @JsonCreator
    public static ProgramCode create(String jsonString) {
        ProgramCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ProgramCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
