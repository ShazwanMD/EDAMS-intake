package my.edu.umk.pams.intake.policy.model;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.core.model.InDocument;

import java.util.Date;
import java.util.List;

public interface InIntake extends InDocument {

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);

    Integer getProjection(); // unjuran

    void setProjection(Integer projection);

    InIntakeSession getSession();

    void setSession(InIntakeSession session);

    InProgramLevel getProgramLevel();

    void setProgramLevel(InProgramLevel programLevel);

    InGraduateCenter getGraduateCenter();

    void setGraduateCenter(InGraduateCenter graduateCenter);

    List<InProgramOffering> getProgramOfferings();

    void setProgramOfferings(List<InProgramOffering> offerings);

    List<InStudyModeOffering> getModeOfferings();

    void setModeOfferings(List<InStudyModeOffering> offerings);

    List<InIntakeApplication> getApplications();

    void setApplications(List<InIntakeApplication> applications);
}
