package my.edu.umk.pams.intake.common.model;
import my.edu.umk.pams.intake.core.InMetadata;
import my.edu.umk.pams.intake.policy.model.InProgramLevel;
import my.edu.umk.pams.intake.policy.model.InProgramLevelImpl;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "InProgramCode")
@Table(name = "IN_PRGM_CODE")
public class InProgramCodeImpl implements InProgramCode {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_IN_PRGM_CODE")
    @SequenceGenerator(name = "SQ_IN_PRGM_CODE", sequenceName = "SQ_IN_PRGM_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;
    
    @OneToOne(targetEntity = InProgramLevelImpl.class)
    @JoinColumn(name = "PROGRAM_LEVEL_ID", nullable = false)
    private InProgramLevel programLevel;
    
    @NotNull
    @Column(name = "DESCRIPTION_MS", nullable = false)
    private String descriptionMs;
    
    @NotNull
    @Column(name = "DESCRIPTION_EN", nullable = false)
    private String descriptionEn;
    
    @OneToOne(targetEntity = InGraduateCenterImpl.class)
    @JoinColumn(name = "GRADUATE_CENTER_ID", nullable = false)
    private InGraduateCenter graduateCenter;
    
	@ManyToOne(targetEntity = InFacultyCodeImpl.class)
    @JoinColumn(name = "FACULTY_CODE_ID")
    private InFacultyCode facultyCode;
    
    @OneToMany(targetEntity = InProgramFieldCodeImpl.class, mappedBy = "programCode")
    private List<InProgramFieldCode> programFieldCodes;

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
    public String getCode() {
        return code;
    }

    @Override
    public InProgramLevel getProgramLevel() {
		return programLevel;
	}

    @Override
	public void setProgramLevel(InProgramLevel programLevel) {
		this.programLevel = programLevel;
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
	public InGraduateCenter getGraduateCenter() {
		return graduateCenter;
	}

	@Override
	public void setGraduateCenter(InGraduateCenter graduateCenter) {
		this.graduateCenter = graduateCenter;
	}

	@Override
	public List<InProgramFieldCode> getProgramFieldCodes() {
		return programFieldCodes;
	}

	@Override
	public void setProgramFieldCodes(List<InProgramFieldCode> programFieldCodes) {
		this.programFieldCodes = programFieldCodes;
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
    public InFacultyCode getFacultyCode() {
		return facultyCode;
	}
    @Override
	public void setFacultyCode(InFacultyCode facultyCode) {
		this.facultyCode = facultyCode;
	}

	@Override
    public Class<?> getInterfaceClass() {
        return InProgramCode.class;
    }

}
