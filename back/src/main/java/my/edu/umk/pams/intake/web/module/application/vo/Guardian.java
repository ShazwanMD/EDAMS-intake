package my.edu.umk.pams.intake.web.module.application.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.intake.web.module.common.vo.StateCode;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
public class Guardian extends MetaObject {

	private String name;
	private BigDecimal salary;
	private String identityNo;
	private String guardianAddress1;
	private String guardianAddress2;
	private String guardianAddress3;
	private String guardianPostcode;
	private GuardianType guardianType;
	private StateCode guardianState;
	private String guardianNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getGuardianAddress1() {
		return guardianAddress1;
	}

	public void setGuardianAddress1(String guardianAddress1) {
		this.guardianAddress1 = guardianAddress1;
	}

	public String getGuardianAddress2() {
		return guardianAddress2;
	}

	public void setGuardianAddress2(String guardianAddress2) {
		this.guardianAddress2 = guardianAddress2;
	}

	public String getGuardianAddress3() {
		return guardianAddress3;
	}

	public void setGuardianAddress3(String guardianAddress3) {
		this.guardianAddress3 = guardianAddress3;
	}

	public String getGuardianPostcode() {
		return guardianPostcode;
	}
	
	public void setGuardianPostcode(String guardianPostcode) {
		this.guardianPostcode = guardianPostcode;
	}

	public StateCode getGuardianState() {
		return guardianState;
	}

	public void setGuardianState(StateCode guardianState) {
		this.guardianState = guardianState;
	}

	public String getGuardianNo() {
		return guardianNo;
	}

	public void setGuardianNo(String guardianNo) {
		this.guardianNo = guardianNo;
	}
	
	public GuardianType getGuardianType() {
		return guardianType;
	}

	public void setGuardianType(GuardianType guardianType) {
		this.guardianType = guardianType;
	}
	
	@JsonCreator
	public static Guardian create(String jsonString) {
		Guardian o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, Guardian.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
}
