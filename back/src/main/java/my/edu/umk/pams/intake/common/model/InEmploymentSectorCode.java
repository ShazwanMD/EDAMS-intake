package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.core.InMetaObject;


public interface InEmploymentSectorCode extends InMetaObject {

    String getCode();

    void setCode(String code);

    String getDescriptionEn();

    void setDescriptionEn(String descriptionEn);
    
    String getDescriptionMs();

    void setDescriptionMs(String descriptionMs);
}
