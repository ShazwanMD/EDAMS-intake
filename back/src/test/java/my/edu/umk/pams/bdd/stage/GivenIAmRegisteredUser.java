package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.intake.identity.model.InUser;
import my.edu.umk.pams.intake.policy.model.InIntakeSession;
import my.edu.umk.pams.intake.policy.service.PolicyService;
import my.edu.umk.pams.intake.security.integration.InUserDetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

@JGivenStage
public class GivenIAmRegisteredUser extends Stage<GivenIAmRegisteredUser> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmRegisteredUser.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PolicyService policyServce;

    @ProvidedScenarioState 
    private InUser user;
    

    
    public GivenIAmRegisteredUser I_am_a_registered_user() {
    	loginAsRegisteredUser();
    	return self();
     
    }

    private void loginAsRegisteredUser() {
       
    	String username = "root";
    	String password = "abc123";
    	
   
    	
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            
        Authentication authed = authenticationManager.authenticate(token);        
        Assert.notNull(authed, "authed is null");        
        user = ((InUserDetails) authed.getPrincipal()).getUser();       
        String found = user.getUsername();
        String message = "Expecting username " + username + " but found " + found;       
        Assert.isTrue(username.equals(found), message);       
        SecurityContextHolder.getContext().setAuthentication(authed);
    }

}
