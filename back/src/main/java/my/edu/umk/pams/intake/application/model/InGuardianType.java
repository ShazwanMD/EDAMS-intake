package my.edu.umk.pams.intake.application.model;

public enum InGuardianType {
	MOTHER, 
	FATHER, 
	GUARDIAN,
	NOT_APPLICABLE;
	
	public static InGuardianType get(int index) {
        return values()[index];
    }
}
