package my.edu.umk.pams.intake.policy.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.common.model.InProgramCode;
import my.edu.umk.pams.intake.common.model.InProgramCodeImpl;
import my.edu.umk.pams.intake.common.service.CommonService;
import my.edu.umk.pams.intake.policy.Data;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.model.InProgramOffering;
import my.edu.umk.pams.intake.policy.model.InProgramOfferingImpl;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenIAddPrograms extends Stage<WhenIAddPrograms> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddIntake.class);

    @Autowired
    private CommonService commonService;

    @ProvidedScenarioState
    private List<Data> codesData;

    @ProvidedScenarioState
    private InIntake intake;

    @ExpectedScenarioState
    private InIntakeSession intakeSession;

    @Autowired
    private PolicyService policyService;

    @ProvidedScenarioState
    private List<InProgramOffering> programOfferings;

    public WhenIAddPrograms i_add_program_codes(List<Data> codesData) {
        this.codesData = codesData;


        for (Data data : codesData) {
            InProgramCode code = new InProgramCodeImpl();
            code.setCode(data.name());
            code.setDescriptionEn(data.descriptionEn);
            code.setDescriptionMs(data.descriptionMs);
           // code.setFacultyCode(commonService.findFacultyCodeByCode("A09"));
            code.setProgramLevel(policyService.findProgramLevelByCode("MASTER"));
            code.setGraduateCenter(commonService.findGraduateCenterByCode("CPS"));
            commonService.saveProgramCode(code);
            
            
        }

        return self();
    }

    public WhenIAddPrograms i_offer_programs_to_intake(String intakeReferenceNo) {
        intake = policyService.findIntakeByReferenceNo(intakeReferenceNo);
        Assert.notNull(intake, "intake is null");

        programOfferings = new ArrayList<>();
        for (Data code : codesData) {
            //InProgramCode programCode = commonService.findProgramFieldCodeByCode(code.name());
            //Assert.notNull(programCode, code + " ProgramCode cannot be null");

            InProgramOffering offering = new InProgramOfferingImpl();
//            offering.setProgramFieldCode(null);
            policyService.addProgramOffering(intake, offering);
            programOfferings.add(offering);
        }

        return self();
    }
}