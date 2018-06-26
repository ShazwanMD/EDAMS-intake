package my.edu.umk.pams.intake.application.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;

@JGivenStage
public class WhenIListIntakeProgramsOffered extends Stage<WhenIListIntakeProgramsOffered> {

	 private static final Logger LOG = LoggerFactory.getLogger(WhenIListIntakeProgramsOffered.class);
	 
	@Autowired
    private CommonService commonService;
	
	@Autowired
    private PolicyService policyService;

    @ProvidedScenarioState
    private InIntake intake;

    @ProvidedScenarioState
    private List<InProgramOffering> programOfferings;

    public WhenIListIntakeProgramsOffered i_list_current_intake_offered_programs(String code) {
        preloadProgramOffering(code);

        Assert.notNull(intake, "intake cannot be null");
        programOfferings = policyService.findProgramOfferings(intake);
        Assert.notEmpty(programOfferings, "programOfferings cannot be empty");

        for (InProgramOffering program : programOfferings) {
//            LOG.debug(program.getProgramFieldCode().getCode());
        }

        return self();
    }

    private void preloadProgramOffering(String code) {
        Assert.notNull(intake, "intake cannot be null");
        //InProgramCode programCode = commonService.findProgramFieldCodeByCode(code);
        //Assert.notNull(programCode, programCode.getCode() + " ProgramCode cannot be null");

        InProgramOffering offering = new InProgramOfferingImpl();
        policyService.addProgramOffering(intake, offering);
    }
}

