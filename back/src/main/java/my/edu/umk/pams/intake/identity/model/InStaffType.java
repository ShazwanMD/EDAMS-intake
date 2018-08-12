package my.edu.umk.pams.intake.identity.model;

public enum InStaffType {

	ACADEMIC,
	NON_ACADEMIC;
	
	public static InStaffType get(int index){
        return values()[index];
    }
}