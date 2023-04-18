package org.epha.jwt.security.jwt;

import org.epha.jwt.security.model.DefaultUserDetails;

import java.util.Map;

/**
 * @author pangjiping
 */
public interface DefaultUserDetailsJwtClaimsConverter {

    DefaultUserDetails convert(final Map<String, Object> claims);

    Map<String, Object> convert(final DefaultUserDetails userDetails);

}
