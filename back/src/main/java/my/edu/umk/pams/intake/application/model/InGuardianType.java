package my.edu.umk.pams.intake.application.model;

public enum InGuardianType {
	MOTHER, 
	FATHER, 
	GUARDIAN,
	NA;
	
	public static InGuardianType get(int index) {
        return values()[index];
    }
}
