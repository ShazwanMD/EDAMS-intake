package my.edu.umk.pams.intake.security.integration;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author canang technologies
 * @since 1/13/14
 */
public class InAutoLoginToken extends AbstractAuthenticationToken {

    private final Object principal;
    private Object credentials;

    public InAutoLoginToken(Object principal) {
        super(null);
        this.principal = principal;
        setAuthenticated(true);
    }

    public InAutoLoginToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}