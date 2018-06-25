package my.edu.umk.pams.intake.application.model;

import my.edu.umk.pams.intake.common.model.InStateCode;
import my.edu.umk.pams.intake.common.model.InStateCodeImpl;
import my.edu.umk.pams.intake.core.InMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "InGuardian")
@Table(name = "IN_GRDN")
public class InGuardianImpl implements InGuardian {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_IN_GRDN")
    @SequenceGenerator(name = "SQ_IN_GRDN", sequenceName = "SQ_IN_GRDN", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "IDENTITY_NO", nullable = false)
    private String identityNo;

    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;
    
    @Column(name = "GRDN_ADDRS1", nullable = false)
    private String guardianAddress1;
    
    @Column(name = "GRDN_ADDRS2", nullable = false)
    private String guardianAddress2;
    
    @Column(name = "GRDN_ADDRS3", nullable = false)
    private String guardianAddress3;

    @Column(name = "GRDN_POSTCODE", nullable = false)
    private String guardianPostcode;
    
    @NotNull
    @Column(name = "GUARDIAN_TYPE")
    private InGuardianType type;
    
    @ManyToOne(targetEntity = InStateCodeImpl.class)
	@JoinColumn(name = "GRDN_STATE_CODE_ID")
	private InStateCode guardianState;
    
    @ManyToOne(targetEntity = InIntakeApplicationImpl.class)
    @JoinColumn(name = "APPLICATION_ID")
    private InIntakeApplication application;

    @Embedded
    private InMetadata metadata;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public InGuardianType getType() {
        return type;
    }

    @Override
    public void setType(InGuardianType type) {
        this.type = type;
    }

    @Override
    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public String getIdentityNo() {
        return identityNo;
    }

    @Override
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
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
        return InGuardian.class;
    }

	@Override
	public String getGuardianAddress1() {
		return guardianAddress1;
	}

	@Override
	public void setGuardianAddress1(String guardianAddress1) {
		 this.guardianAddress1 = guardianAddress1;
		
	}

	@Override
	public String getGuardianAddress2() {
		return guardianAddress2;
	}

	@Override
	public void setGuardianAddress2(String guardianAddress2) {
		 this.guardianAddress2 = guardianAddress2;
		
	}

	@Override
	public String getGuardianAddress3() {
		return guardianAddress3;
	}

	@Override
	public void setGuardianAddress3(String guardianAddress3) {
		this.guardianAddress3 = guardianAddress3;
		
	}

	@Override
	public String getGuardianPostcode() {
		return guardianPostcode;
	}

	@Override
	public void setGuardianPostcode(String guardianPostcode) {
		this.guardianPostcode = guardianPostcode;
		
	}

	@Override
	public InStateCode getGuardianState() {
		return guardianState;
	}

	@Override
	public void setGuardianState(InStateCode guardianState) {
		this.guardianState = guardianState;

	}

	
   
	
}
