package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.core.InMetadata;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "InFieldCode")
@Table(name = "IN_FILD_CODE")
public class InFieldCodeImpl implements InFieldCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_FILD_CODE")
    @SequenceGenerator(name = "SQ_IN_FILD_CODE", sequenceName = "SQ_IN_FILD_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION_MS", nullable = false)
    private String descriptionMs;
    
    @NotNull
    @Column(name = "DESCRIPTION_EN", nullable = false)
    private String descriptionEn;

    @ManyToOne(targetEntity = InFacultyCodeImpl.class)
    @JoinColumn(name = "FACULTY_CODE_ID", nullable = false)
    private InFacultyCode facultyCode;

    @Embedded
    private InMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public String getDescriptionMs() {
		return descriptionMs;
	}

    @Override
	public void setDescriptionMs(String descriptionMs) {
		this.descriptionMs = descriptionMs;
	}

    @Override
	public String getDescriptionEn() {
		return descriptionEn;
	}

    @Override
	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

    @Override
	public InFacultyCode getFacultyCode() {
		return facultyCode;
	}

    @Override
	public void setFacultyCode(InFacultyCode facultyCode) {
		this.facultyCode = facultyCode;
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
        return InFieldCode.class;
    }
}
