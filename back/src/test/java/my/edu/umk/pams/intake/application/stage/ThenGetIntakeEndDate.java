package my.edu.umk.pams.intake.application.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.intake.policy.model.InIntake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author PAMS
 */
@JGivenStage
public class ThenGetIntakeEndDate extends Stage<ThenGetIntakeEndDate> {

    private static final Logger LOG = LoggerFactory.getLogger(ThenGetIntakeEndDate.class);

    @ExpectedScenarioState
    private InIntake intake;

    public ThenGetIntakeEndDate i_get_intake_end_date() {
        Assert.notNull(intake.getEndDate(), "End date cannot be null");

        return self();
    }
}
