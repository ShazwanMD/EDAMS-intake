package my.edu.umk.pams.intake.policy.workflow.router;

import my.edu.umk.pams.intake.application.model.InIntakeApplication;
import my.edu.umk.pams.intake.application.service.ApplicationService;
import my.edu.umk.pams.intake.common.model.InGraduateCenter;
import my.edu.umk.pams.intake.common.router.RouterServiceSupport;
import my.edu.umk.pams.intake.common.router.RouterStrategy;
import my.edu.umk.pams.intake.policy.model.InIntake;
import my.edu.umk.pams.intake.policy.service.PolicyService;

import org.apache.commons.lang.Validate;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


 
@Component("intakeRouterService")
public class IntakeRouterService extends RouterServiceSupport {

    private static final Logger log = LoggerFactory.getLogger(IntakeRouterService.class);

    private static Map<String, RouterStrategy> strategies = new HashMap<String, RouterStrategy>();

    static {
        strategies.put("MGSEB", new MGSEBIntakeRouterStrategy());
        strategies.put("CPS", new CPSIntakeRouterStrategy());
        strategies.put("UMKCEE", new UMKCEEIntakeRouterStrategy());
    }

    @Autowired
    private PolicyService policyService;

    public List<String> findVerifierCandidates(Long intakeId) {
        Validate.notNull(intakeId, "Id must not be null");

        InIntake intake = policyService.findIntakeById(intakeId);

        InGraduateCenter center = intake.getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findVerifierCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }

    public List<String> findPublisherCandidates(Long intakeId) {
        Validate.notNull(intakeId, "Id must not be null");

        InIntake intake = policyService.findIntakeById(intakeId);
        InGraduateCenter center = intake.getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findPublisherCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }

    public List<String> findEvaluatorCandidates(Long intakeId) {
        Validate.notNull(intakeId, "Id must not be null");

        InIntake intake = policyService.findIntakeById(intakeId);
        InGraduateCenter center = intake.getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findEvaluatorCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }
    
    public List<String> findSelectorCandidates(Long intakeId) {
        Validate.notNull(intakeId, "Id must not be null");

        InIntake intake = policyService.findIntakeById(intakeId);
        InGraduateCenter center = intake.getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findSelectorCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }
    
    public List<String> findPreapproverCandidates(Long intakeId) {
        Validate.notNull(intakeId, "Id must not be null");

        InIntake intake = policyService.findIntakeById(intakeId);
        InGraduateCenter center = intake.getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findPreapproverCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }
    
    public List<String> findUpperCandidates(Long intakeId) {
        Validate.notNull(intakeId, "Id must not be null");

        InIntake intake = policyService.findIntakeById(intakeId);
        InGraduateCenter center = intake.getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findUpperCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }
    
    public List<String> findOfferCandidates(Long intakeId) {
        Validate.notNull(intakeId, "Id must not be null");

        InIntake intake = policyService.findIntakeById(intakeId);
        InGraduateCenter center = intake.getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findOfferCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }
    
//    public List<String> findRegisterCandidates(Long intakeId) {
//        Validate.notNull(intakeId, "Id must not be null");
//
//        InIntake intake = policyService.findIntakeById(intakeId);
//        InGraduateCenter center = intake.getGraduateCenter();
//        RouterStrategy strategy = strategies.get(center.getCode());
//        List<String> candidates = strategy.findRegisterCandidates();
//        // todo(ashraf): permission publishing
//
//        return candidates;
//    }
}



