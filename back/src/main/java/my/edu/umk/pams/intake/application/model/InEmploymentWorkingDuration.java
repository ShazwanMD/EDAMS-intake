package my.edu.umk.pams.intake.application.model;

public enum InEmploymentWorkingDuration {
	
	SATU_TAHUN,
    DUA_TAHUN,
    TIGA_TAHUN,
    EMPAT_TAHUN,
    LIMA_TAHUN_KE_ATAS;
	
    public static InEmploymentWorkingDuration get(int index) {
        return values()[index];
    }
}