package my.edu.umk.pams.intake.common.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.intake.core.InMetadata;

@Entity(name = "InEmploymentTypeCode")
@Table(name = "IN_EMPT_TYPE_CODE")
public class InEmploymentTypeCodeImpl implements InEmploymentTypeCode {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_IN_EMPT_TYPE_CODE")
	@SequenceGenerator(name = "SQ_IN_EMPT_TYPE_CODE", sequenceName = "SQ_IN_EMPT_TYPE_CODE", allocationSize = 1)
	private Long id;

	@NotNull
	@Column(name = "CODE", unique = true, length = 2)
	private String code;

	@NotNull
	@Column(name = "DESCRIPTION")
	private String description;

	@Embedded
	private InMetadata metadata;

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public InMetadata getMetadata() {
		return metadata;
	}

	@Override
	public void setMetadata(InMetadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public Class<?> getInterfaceClass() {
		return InEmploymentSectorCode.class;
	}

	@Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

}
