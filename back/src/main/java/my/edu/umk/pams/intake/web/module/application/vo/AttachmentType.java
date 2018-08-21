package my.edu.umk.pams.intake.web.module.application.vo;

/**
 * @author PAMS
 */
public enum AttachmentType {
    SPM,
    STAM,
    STPM,
    MUET,
    TOEFL,
    DIPLOMA,
    DIPLOMA_EQUIVALENT,
    BACHELOR,
    BACHELOR_EQUIVALENT,
    PROCESSING_FEE,
    SPONSOR,
    BANK_STATEMENT,
    REFEREE_FORM,
    RESEARCH_PROPOSAL,
    LANGUAGE_RESULT,
    IELTS,
	IC_COPY,
	PASSPORT_COPY,
    MASTER,
    MASTER_EQUIVALENT,
    PHD,
    PHD_EQUIVALENT,
	PASSPORT_IMAGE,
	EMPLOYMENT_VERIFICATION,
	APEL_CERTIFICATE;

    public static AttachmentType get(int index) {
        return values()[index];
    }
}
