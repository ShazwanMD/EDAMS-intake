package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.core.InMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface InResult extends InMetaObject {

    InResultType getResultType();

    void setResultType(InResultType resultType);
    
    String getName();

    void setName(String name);
    
    String getField();

    void setField(String field);

    Integer getGraduationYear();

    void setGraduationYear(Integer graduationYear);
    
    String getResultAlphanumeric();

    void setResultAlphanumeric(String resultAlphanumeric);

    BigDecimal getResultNumeric();

    void setResultNumeric(BigDecimal resultNumeric);

    InIntakeApplication getApplication();

    void setApplication(InIntakeApplication application);

}
