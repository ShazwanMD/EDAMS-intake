package my.edu.umk.pams.intake.web.module.identity.vo;

public enum StaffType {
	
	ACADEMIC,
	NON_ACADEMIC;
	
	public static StaffType get(int index){
		return values()[index];
	}

}
