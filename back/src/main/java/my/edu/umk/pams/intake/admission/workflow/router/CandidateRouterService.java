package my.edu.umk.pams.intake.admission.workflow.router;

import my.edu.umk.pams.intake.admission.model.InCandidate;
import my.edu.umk.pams.intake.admission.service.AdmissionService;
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


 
@Component("candidateRouterService")
public class CandidateRouterService extends RouterServiceSupport {

    private static final Logger log = LoggerFactory.getLogger(CandidateRouterService.class);

    private static Map<String, RouterStrategy> strategies = new HashMap<String, RouterStrategy>();

    static {
//        strategies.put("MGSEB", new MGSEBCandidateRouterStrategy());
        strategies.put("UMKCEE", new CPSCandidateRouterStrategy());
    }

    @Autowired
    private AdmissionService admissionService;
    
    public List<String> findCreatorCandidates(Long candidateId) {
        Validate.notNull(candidateId, "Id must not be null");
        
        InCandidate candidate = admissionService.findCandidateById(candidateId);

		InGraduateCenter center = candidate.getIntake().getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findCreatorCandidates();
        
        return candidates;
    }
    
    public List<String> findVerifierCandidates(Long candidateId) {
        Validate.notNull(candidateId, "Id must not be null");

        InCandidate candidate = admissionService.findCandidateById(candidateId);

        InGraduateCenter center = candidate.getIntake().getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findVerifierCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }

    public List<String> findPublisherCandidates(Long candidateId) {
        Validate.notNull(candidateId, "Id must not be null");

        InCandidate candidate = admissionService.findCandidateById(candidateId);

        InGraduateCenter center = candidate.getIntake().getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findPublisherCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }

    public List<String> findEvaluatorCandidates(Long candidateId) {
        Validate.notNull(candidateId, "Id must not be null");

        InCandidate candidate = admissionService.findCandidateById(candidateId);

        InGraduateCenter center = candidate.getIntake().getGraduateCenter();
        RouterStrategy strategy = strategies.get(center.getCode());
        List<String> candidates = strategy.findEvaluatorCandidates();
        // todo(ashraf): permission publishing

        return candidates;
    }
    
   

}



