package my.edu.umk.pams.intake.application.model;


public enum InGuardianType {
	MOTHER, 
	FATHER, 
	GUARDIAN;
	
	public static InGuardianType get(int index) {
        return values()[index];
    }
}
