package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.core.InMetaObject;

/**
 * IC, Birth Certificate, SPM, Other Qualification
*/
public interface InAttachment extends InMetaObject {

    String getName();

    void setName(String name);

    byte[] getBytes();

    void setBytes(byte[] bytes);

	String getMimeType();

	void setMimeType(String mimeType);

	Long getSize();

	void setSize(Long size);

    InIntakeApplication getApplication();

    void setApplication(InIntakeApplication application);
    
    InAttachmentType getAttachmentType();

    void setAttachmentType(InAttachmentType attachmentType);
}
