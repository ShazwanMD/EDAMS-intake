package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.common.model.InSpmResult;
import my.edu.umk.pams.intake.common.model.InSpmResultImpl;
import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */

@Entity(name = "InResult")
@Table(name = "IN_RSLT")
@Inheritance(strategy = InheritanceType.JOINED)
public class InResultImpl implements InResult {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_RSLT")
    @SequenceGenerator(name = "SQ_IN_RSLT", sequenceName = "SQ_IN_RSLT", allocationSize = 1)
    private Long id;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "RESULT_TYPE")
    private InResultType resultType;
    
    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;
    
    
    @Column(name = "FIELD")
    private String field;
    
    @Column(name = "GRADUATION_YEAR")
    private Integer graduationYear;
    
    @Column(name = "RESULT_ALPAANUMERIC")
    private String resultAlphanumeric;
    
    @Column(name = "RESULT_NUMERIC")
    private BigDecimal resultNumeric;
    
    @Column(name = "MALAY_RESULT")
    private String malayResult;
    
    @Column(name = "ENGLISH_RESULT")
    private String englishResult;

    @ManyToOne(targetEntity = InIntakeApplicationImpl.class)
    @JoinColumn(name = "APPLICATION_ID")
    private InIntakeApplication application;
    
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
    public InResultType getResultType() {
        return resultType;
    }

    @Override
    public void setResultType(InResultType resultType) {
        this.resultType = resultType;
    }
    
    @Override
    public String getName() {
		return name;
	}

    @Override
	public void setName(String name) {
		this.name = name;
	}

    @Override
	public String getField() {
		return field;
	}

    @Override
	public void setField(String field) {
		this.field = field;
	}

    @Override
	public Integer getGraduationYear() {
		return graduationYear;
	}

    @Override
	public void setGraduationYear(Integer graduationYear) {
		this.graduationYear = graduationYear;
	}

    @Override
	public String getResultAlphanumeric() {
		return resultAlphanumeric;
	}

    @Override
	public void setResultAlphanumeric(String resultAlphanumeric) {
		this.resultAlphanumeric = resultAlphanumeric;
	}
    
    @Override
	public BigDecimal getResultNumeric() {
		return resultNumeric;
	}

    @Override
	public void setResultNumeric(BigDecimal resultNumeric) {
		this.resultNumeric = resultNumeric;
	}

    @Override
	public String getMalayResult() {
		return malayResult;
	}

	@Override
	public void setMalayResult(String malayResult) {
		this.malayResult = malayResult;
	}

	@Override
	public String getEnglishResult() {
		return englishResult;
	}

	@Override
	public void setEnglishResult(String englishResult) {
		this.englishResult = englishResult;
	}

	@Override
    public InIntakeApplication getApplication() {
        return application;
    }

    @Override
    public void setApplication(InIntakeApplication application) {
        this.application = application;
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
        return InResult.class;
    }


}
