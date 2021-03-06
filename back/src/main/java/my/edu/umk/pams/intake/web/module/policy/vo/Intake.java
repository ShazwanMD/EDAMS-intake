package my.edu.umk.pams.intake.web.module.policy.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.intake.web.module.common.vo.GraduateCenter;
import my.edu.umk.pams.intake.web.module.core.vo.Document;

import java.io.IOException;
import java.util.Date;

/**
 * @author PAMS
 */
public class Intake extends Document {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endDate;
    private Integer projection;
    private IntakeSession intakeSession;
    private ProgramLevel programLevel;
    private GraduateCenter graduateCenter;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getProjection() {
        return projection;
    }

    public void setProjection(Integer projection) {
        this.projection = projection;
    }

    public IntakeSession getIntakeSession() {
        return intakeSession;
    }

    public void setIntakeSession(IntakeSession intakeSession) {
        this.intakeSession = intakeSession;
    }

    public ProgramLevel getProgramLevel() {
        return programLevel;
    }

    public void setProgramLevel(ProgramLevel programLevel) {
        this.programLevel = programLevel;
    }

    public GraduateCenter getGraduateCenter() {
        return graduateCenter;
    }

    public void setGraduateCenter(GraduateCenter graduateCenter) {
        this.graduateCenter = graduateCenter;
    }

    @JsonCreator
    public static Intake create(String jsonString) {
        Intake o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, Intake.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}

