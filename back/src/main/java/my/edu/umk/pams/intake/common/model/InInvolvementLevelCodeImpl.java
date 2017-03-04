package my.edu.umk.pams.intake.common.model;

import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "InInvolvementLevelCode")
@Table(name = "IN_INVT_LEVL_CODE")
public class InInvolvementLevelCodeImpl implements InInvolvementLevelCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_INVT_LEVL_CODE")
    @SequenceGenerator(name = "SQ_IN_INVT_LEVL_CODE", sequenceName = "SQ_IN_INVT_LEVL_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = InInvolvementTypeCodeImpl.class)
    @JoinColumn(name = "TYPE_CODE_ID")
    private InInvolvementTypeCode typeCode;

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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public InInvolvementTypeCode getTypeCode() {
        return typeCode;
    }

    @Override
    public void setTypeCode(InInvolvementTypeCode typeCode) {
        this.typeCode = typeCode;
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
        return InInvolvementLevelCode.class;
    }
}
