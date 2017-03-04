package my.edu.umk.pams.intake.common.model;


import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "InMaritalCode")
@Table(name = "IN_MRTL_CODE")
public class InMaritalCodeImpl implements InMaritalCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_MRTL_CODE")
    @SequenceGenerator(name = "SQ_IN_MRTL_CODE", sequenceName = "SQ_IN_MRTL_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Embedded
    private InMetadata metadata;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

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

    public InMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(InMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return InMaritalCode.class;
    }
}
