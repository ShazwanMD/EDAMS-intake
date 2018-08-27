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
		return Arrays.asList("GRP_ADM","GRP_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE","GRP_PGW_ADM_UMKCEE"); // cps-kerani , root
	}
	
    @Override
    public List<String> findPublisherCandidates() {
        return Arrays.asList("GRP_ADM","GRP_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE","GRP_PGW_ADM_UMKCEE");   //cps-pegawai , root
    }

	@Override
	public List<String> findEvaluatorCandidates() {
		return Arrays.asList("GRP_ADM","GRP_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE","GRP_PGW_ADM_UMKCEE"); // cps-pegawai , io-pegawai, root
	}

	@Override
	public List<String> findSelectorCandidates() {
		return Arrays.asList("GRP_ADM","GRP_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE","GRP_PGW_ADM_UMKCEE"); // fakulti-kerani-A01 , root
	}

	@Override
	public List<String> findPreapproverCandidates() {
		return Arrays.asList("GRP_ADM","GRP_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE","GRP_PGW_ADM_UMKCEE"); // fakulti-pegawai-A01 , root
	}

	@Override
	public List<String> findUpperCandidates() {
		return Arrays.asList("GRP_ADM","GRP_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE","GRP_PGW_ADM_UMKCEE"); // fakulti-pegawai-A01 , root
	}

	@Override
	public List<String> findOfferCandidates() {
		return Arrays.asList("GRP_ADM","GRP_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE","GRP_PGW_ADM_UMKCEE"); // fakulti-pegawai-A01 , root
	}

	@Override
	public List<String> findRegisterCandidates() {
		return Arrays.asList("GRP_ADM","GRP_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE","GRP_PGW_ADM_UMKCEE"); // fakulti-pegawai-A01 , root
	}
	
    @Override
    public List<String> findCreatorCandidates(){
        return Arrays.asList("GRP_PGW_ADM_UMKCEE","GRP_KRN_ADM_UMKCEE", 
				"GRP_PGW_FCTY_A01", "GRP_KRN_FCTY_A01",
				"GRP_KRN_FCTY_A02", "GRP_PGW_FCTY_A02", 
				"GRP_KRN_FCTY_A04", "GRP_PGW_FCTY_A04",
				"GRP_KRN_FCTY_A05", "GRP_PGW_FCTY_A05",
				"GRP_KRN_FCTY_A06", "GRP_PGW_FCTY_A06",
				"GRP_KRN_FCTY_A07", "GRP_PGW_FCTY_A07",
				"GRP_KRN_FCTY_A08", "GRP_PGW_FCTY_A08",
				"GRP_KRN_FCTY_A11", "GRP_PGW_FCTY_A11",
				"GRP_KRN_FCTY_A12", "GRP_PGW_FCTY_A12",
				"GRP_KRN_FCTY_A13", "GRP_PGW_FCTY_A13",
				"GRP_KRN_FCTY_A14", "GRP_PGW_FCTY_A14", "GRP_ADM");
    }

}
