package my.edu.umk.pams.intake.web.module.application.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.intake.web.module.common.vo.EmploymentSectorCode;
import my.edu.umk.pams.intake.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.util.Date;

/**
 * @author PAMS
 */
public class Employment extends MetaObject {
	private String employer;
	private String designation;
	private boolean current;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date startDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date endDate;
	private EmploymentType employmentType;
	private FieldCode fieldCode;
	private LevelCode levelCode;
	private EmploymentSectorCode employmentSectorCode;
	private EmploymentWorkingDuration workingDuration;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

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

	public FieldCode getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(FieldCode fieldCode) {
		this.fieldCode = fieldCode;
	}

	public LevelCode getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(LevelCode levelCode) {
		this.levelCode = levelCode;
	}

	public EmploymentSectorCode getEmploymentSectorCode() {
		return employmentSectorCode;
	}

	public void setEmploymentSectorCode(EmploymentSectorCode employmentSectorCode) {
		this.employmentSectorCode = employmentSectorCode;
	}

	public EmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
	}

	public EmploymentWorkingDuration getWorkingDuration() {
		return workingDuration;
	}

	public void setWorkingDuration(EmploymentWorkingDuration workingDuration) {
		this.workingDuration = workingDuration;
	}

	@JsonCreator
	public static Employment create(String jsonString) {
		Employment o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, Employment.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
}
