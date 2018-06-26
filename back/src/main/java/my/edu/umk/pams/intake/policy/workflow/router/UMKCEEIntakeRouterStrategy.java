package my.edu.umk.pams.intake.policy.workflow.router;

import java.util.Arrays;
import java.util.List;

import my.edu.umk.pams.intake.common.router.RouterStrategySupport;

public class UMKCEEIntakeRouterStrategy extends RouterStrategySupport {

	@Override
	public String findPreapproverCandidate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findUpperCandidate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findOfferCandidate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findRegisterCandidate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> findVerifierCandidates() {
		return Arrays.asList("GRP_ADM"); // cps-kerani , root
	}
	
    @Override
    public List<String> findPublisherCandidates() {
        return Arrays.asList("GRP_ADM");   //cps-pegawai , root
    }

	@Override
	public List<String> findEvaluatorCandidates() {
		return Arrays.asList("GRP_ADM"); // cps-pegawai , io-pegawai, root
	}

	@Override
	public List<String> findSelectorCandidates() {
		return Arrays.asList("GRP_ADM"); // fakulti-kerani-A01 , root
	}

	@Override
	public List<String> findPreapproverCandidates() {
		return Arrays.asList("GRP_ADM"); // fakulti-pegawai-A01 , root
	}

	@Override
	public List<String> findUpperCandidates() {
		return Arrays.asList("GRP_ADM"); // fakulti-pegawai-A01 , root
	}

	@Override
	public List<String> findOfferCandidates() {
		return Arrays.asList("GRP_ADM"); // fakulti-pegawai-A01 , root
	}

	@Override
	public List<String> findRegisterCandidates() {
		return Arrays.asList("GRP_ADM"); // fakulti-pegawai-A01 , root
	}

}
