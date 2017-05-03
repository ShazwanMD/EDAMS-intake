package my.edu.umk.pams.intake.web.module.admission.controller;

import my.edu.umk.pams.intake.admission.model.InCandidate;
import my.edu.umk.pams.intake.web.module.admission.vo.Candidate;
import my.edu.umk.pams.intake.web.module.common.controller.CommonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

/**
 * @author PAMS
 */
@Component("admissionTransformer")
public class AdmissionTransformer {

    @Autowired
    private CommonTransformer commonTransformer;

    public Candidate toCandidateVo(InCandidate e) {
        Candidate vo = new Candidate();
        vo.setId(e.getId());
        vo.setName(e.getName());
        vo.setEmail(e.getEmail());
        vo.setIdentityNo(e.getIdentityNo());
        vo.setMatricNo(e.getMatricNo());;
        vo.setStudyMode(commonTransformer.toStudyModeVo(e.getStudyMode()));
        return vo;
    }

    public List<Candidate> toCandidateVos(List<InCandidate> candidates) {
        return candidates.stream()
                .map((task) -> toCandidateVo(task))
                .collect(toCollection(() -> new ArrayList<Candidate>()));
    }
}
