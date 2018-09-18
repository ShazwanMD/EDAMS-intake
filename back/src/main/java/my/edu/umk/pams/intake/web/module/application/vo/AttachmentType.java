package my.edu.umk.pams.intake.web.module.application.vo;

/**
 * @author PAMS
 */
public enum AttachmentType {
    SPM, //0
    STAM, //1
    STPM,//2
    MUET,//3
    TOEFL,//4
    DIPLOMA,//5
    DIPLOMA_EQUIVALENT,//6
    BACHELOR,//7
    BACHELOR_EQUIVALENT,//8
    PROCESSING_FEE,//9
    SPONSOR,//10
    BANK_STATEMENT,//11
    REFEREE_FORM,//12
    RESEARCH_PROPOSAL,//13
    LANGUAGE_RESULT,//14
    IELTS,//15
	IC_COPY, //16
	PASSPORT_COPY, //17
    MASTER, //18
    MASTER_EQUIVALENT, //19
    PHD, //20
    PHD_EQUIVALENT, //21
    PASSPORT_IMAGE,  //22
    EMPLOYMENT_VERIFICATION, //23
    APEL_CERTIFICATE; //24

    public static AttachmentType get(int index) {
        return values()[index];
    }
}
