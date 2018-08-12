package my.edu.umk.pams.intake.identity.model;

public enum InStaffCategoryType {
	
	PEGAWAI_KEATAS,
	PENOLONG_PEGAWAI_KEBAWAH;

	public static InStaffCategoryType get(int index){
        return values()[index];
    }

}
