package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.core.InMetaObject;

public interface InStateCode extends InMetaObject {

    String getCode();

    void setCode(String code);

    String getDescriptionMs();

    void setDescriptionMs(String descriptionMs);
    
    String getDescriptionEn();

    void setDescriptionEn(String descriptionEn);

    InCountryCode getCountryCode();

    void setCountryCode(InCountryCode countryCode);
}
