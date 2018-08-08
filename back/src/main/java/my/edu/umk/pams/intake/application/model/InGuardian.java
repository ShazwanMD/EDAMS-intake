package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.common.model.InEmploymentSectorCode;
import my.edu.umk.pams.intake.common.model.InGuardianTypeCode;
import my.edu.umk.pams.intake.common.model.InStateCode;
import my.edu.umk.pams.intake.core.InMetaObject;

import java.math.BigDecimal;

public interface InGuardian extends InMetaObject {

	String getIdentityNo();

	void setIdentityNo(String identityNo);

	String getName();

	void setName(String name);

	BigDecimal getSalary();

	void setSalary(BigDecimal salary);

	// InGuardianType getType();
	//
	// void setType(InGuardianType type);

	InGuardianTypeCode getGuardianTypeCode();

	void setGuardianTypeCode(InGuardianTypeCode guardianTypeCode);

	String getGuardianAddress1();

	void setGuardianAddress1(String guardianAddress1);

	String getGuardianAddress2();

	void setGuardianAddress2(String guardianAddress2);

	String getGuardianAddress3();

	void setGuardianAddress3(String guardianAddress3);

	String getGuardianPostcode();

	void setGuardianPostcode(String guardianPostcode);

	InStateCode getGuardianState();

	void setGuardianState(InStateCode guardianState);

	String getGuardianNo();

	void setGuardianNo(String guardianNo);

	InIntakeApplication getApplication();

	void setApplication(InIntakeApplication application);

}
