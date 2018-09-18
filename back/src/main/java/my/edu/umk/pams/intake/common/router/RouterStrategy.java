package my.edu.umk.pams.intake.common.router;

import java.util.List;



public interface RouterStrategy {

    public String findDrafterCandidate();

    public List<String> findDrafterCandidates();

    public String findRequesterCandidate();

    public List<String> findRequesterCandidates();

    public String findRegistererCandidate();

    public List<String> findRegistererCandidates();

    public String findVerifierCandidate();

    public List<String> findVerifierCandidates();

    public String findUpperVerifierCandidate();

    public List<String> findUpperVerifierCandidates();

    public String findUpperCheckerCandidate();

    public List<String> findUpperCheckerCandidates();

    public String findCheckerCandidate();

    public List<String> findCheckerCandidates();

    public String findQuerierCandidate();

    public List<String> findQuerierCandidates();

    public String findUpperQuerierCandidate();

    public List<String> findUpperQuerierCandidates();

    public String findApproverCandidate();

    public List<String> findApproverCandidates();

    public String findPreparerCandidate();

    public List<String> findPreparerCandidates();

    public String findEvaluatorCandidate();

    public List<String> findEvaluatorCandidates();

    public String findSelectorCandidate();

    public List<String> findSelectorCandidates();

    public String findPublisherCandidate();

    public List<String> findPublisherCandidates();

	public String findPreapproverCandidate();

	public String findUpperCandidate();

	public String findOfferCandidate();

	public String findRegisterCandidate();

	public List<String> findPreapproverCandidates();

	public List<String> findUpperCandidates();

	public List<String> findOfferCandidates();

	public List<String> findRegisterCandidates();

	List<String> findCreatorCandidates();


}



