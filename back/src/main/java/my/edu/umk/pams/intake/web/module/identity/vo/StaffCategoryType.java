package my.edu.umk.pams.intake.web.module.identity.vo;

public enum StaffCategoryType {
	
	PEGAWAI_KEATAS,
	PENOLONG_PEGAWAI_KEBAWAH;
	
	public static StaffCategoryType get(int index){
		return values()[index];
	}

}
