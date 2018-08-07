package my.edu.umk.pams.intake.web.module.application.vo;


public enum EmploymentWorkingDuration {

SATU_TAHUN,
DUA_TAHUN,
TIGA_TAHUN,
EMPAT_TAHUN,
LIMA_TAHUN_KE_ATAS;

public static EmploymentWorkingDuration get(int index) {
    return values()[index];

}
}
