package my.edu.umk.pams.intake.web.module.application.vo;

public enum GuardianType {

	MOTHER, 
	FATHER, 
	GUARDIAN;
	  
	public static GuardianType get(int index) {
        return values()[index];
    }
	
}
