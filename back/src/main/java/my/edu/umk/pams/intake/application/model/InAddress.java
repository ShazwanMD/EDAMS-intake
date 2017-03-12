package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.common.model.InCountryCode;
import my.edu.umk.pams.intake.common.model.InStateCode;
import my.edu.umk.pams.intake.core.InMetaObject;

public interface InAddress extends InMetaObject {

    InAddressType getType();

    void setType(InAddressType type);

    String getAddress1();

    void setAddress1(String address1);

    String getAddress2();

    void setAddress2(String address2);

    String getAddress3();

    void setAddress3(String address3);

    String getPostCode();

    void setPostCode(String postCode);

    InCountryCode getCountryCode();

    void setCountryCode(InCountryCode countryCode);

    InStateCode getStateCode();

    void setStateCode(InStateCode stateCode);

    InIntakeApplication getApplication();

    void setApplication(InIntakeApplication application);

	String getDun();

	void setDun(String dun);

	String getParlimen();

	void setParlimen(String parlimen);

}
