package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.common.model.InEmploymentFieldCode;
import my.edu.umk.pams.intake.common.model.InEmploymentLevelCode;
import my.edu.umk.pams.intake.common.model.InEmploymentSectorCode;
import my.edu.umk.pams.intake.core.InMetaObject;

import java.util.Date;

public interface InEmployment extends InMetaObject {

    Boolean isCurrent();

    void setCurrent(Boolean current);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);
    
    String getEmployer();

    void setEmployer(String employer);

    InEmploymentLevelCode getLevelCode();

    void setLevelCode(InEmploymentLevelCode levelCode);

    InEmploymentFieldCode getFieldCode();

    void setFieldCode(InEmploymentFieldCode fieldCode);

    InEmploymentSectorCode getSectorCode();

    void setSectorCode(InEmploymentSectorCode sectorCode);

    InIntakeApplication getApplication();

    void setApplication(InIntakeApplication application);
    
	String getDesignation();

	void setDesignation(String designation);
	
    InEmploymentType getEmploymentType();

    void setEmploymentType(InEmploymentType employmentType);
    
    InEmploymentWorkingDuration getWorkingDuration();
    
    void setWorkingDuration(InEmploymentWorkingDuration workingDuration);
	

}
