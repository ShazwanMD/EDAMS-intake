package my.edu.umk.pams.intake.web.module.identity.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.intake.web.module.common.vo.FacultyCode;

/**
 * @author PAMS
 */
public class Staff extends Actor {
	
	private StaffCategoryType staffCategory;
	private StaffType staffType;
	private FacultyCode facultyCode;

	


	public StaffCategoryType getStaffCategory() {
		return staffCategory;
	}
	public void setStaffCategory(StaffCategoryType staffCategory) {
		this.staffCategory = staffCategory;
	}
	
	public StaffType getStaffType() {
		return staffType;
	}
	public void setStaffType(StaffType staffType) {
		this.staffType = staffType;
	}
	public FacultyCode getFacultyCode() {
		return facultyCode;
	}
	public void setFacultyCode(FacultyCode facultyCode) {
		this.facultyCode = facultyCode;
	}
	
	@JsonCreator
	public static Staff create(String jsonString) {
		Staff o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, Staff.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
	
	
	
	

	
}
