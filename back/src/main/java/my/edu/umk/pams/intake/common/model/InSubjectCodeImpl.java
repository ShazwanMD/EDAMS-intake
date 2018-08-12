package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.core.InMetadata;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "InSubjectCode")
@Table(name = "IN_SBJT_CODE")
public class InSubjectCodeImpl implements InSubjectCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_SBJT_CODE")
    @SequenceGenerator(name = "SQ_IN_SBJT_CODE", sequenceName = "SQ_IN_SBJT_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code; //BM,BI,MATH

    @NotNull
    @Column(name = "DESCRIPTION_MS")
    private String descriptionMs;

    @NotNull
    @Column(name = "DESCRIPTION_EN")
    private String descriptionEn;

    @OneToMany(targetEntity = InSpmSubjectCodeImpl.class, mappedBy = "subjectCode")
    private List<InSpmSubjectCode> spmSubjectCodes;
    
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
    public InMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(InMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return InSubjectCode.class;
    }

	@Override
	public List<InSpmSubjectCode> getSpmSubjectCodes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSpmSubjectCodes(List<InSpmSubjectCode> spmSubjectCodes) {
		// TODO Auto-generated method stub
		
	}
}
