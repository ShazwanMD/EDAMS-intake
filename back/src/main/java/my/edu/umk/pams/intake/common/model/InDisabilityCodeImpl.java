package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "InDisabilityCode")
@Table(name = "IN_DBLY_CODE")
public class InDisabilityCodeImpl implements InDisabilityCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_DBLY_CODE")
    @SequenceGenerator(name = "SQ_IN_DBLY_CODE", sequenceName = "SQ_IN_DBLY_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION_MS")
    private String descriptionMs;

    @NotNull
    @Column(name = "DESCRIPTION_EN")
    private String descriptionEn;

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
        return InDisabilityCode.class;
    }
}
