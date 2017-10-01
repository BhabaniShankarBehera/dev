/**
 * 
 */
package com.air.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author BhabaniShankar
 *
 */
public class AnonAuthentication  extends AbstractAuthenticationToken  {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 4951545595978903619L;

	public AnonAuthentication() {
	        super( null );
	    }

	    @Override
	    public Object getCredentials() {
	        return null;
	    }

	    @Override
	    public Object getPrincipal() {
	        return null;
	    }

	    @Override
	    public boolean isAuthenticated() {
	        return true;
	    }

	    @Override
	    public int hashCode() {
	        Integer hash = 7;
	        return hash.hashCode();
	    }

	    @Override
	    public boolean equals( Object obj ) {
	        if ( this == obj ) {
	            return true;
	        }
	        else if ( obj == null ) {
	            return false;
	        }
	        else if ( getClass() != obj.getClass() ) {
	            return false;
	        }
	        return true;
	    }
}
