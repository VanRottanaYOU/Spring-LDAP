package com.memorynotfound.ldap;

import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.LdapContextSource;

public class LdapContextSourceFactory {
	public static ContextSource getLdapContextSource() throws Exception {
		LdapContextSource ldapContextSource = new LdapContextSource();
//		ldapContextSource.setUrl("ldap://ldap.forumsys.com:389");
//		ldapContextSource.setBase("ou=mathematicians,dc=example,dc=com");
		//ldapContextSource.setPassword("password");
		
		ldapContextSource.setUrl("ldap://ldap.forumsys.com:389");
		ldapContextSource.setBase("uid=boyle,dc=example,dc=com");
		
//		ldapContextSource.setUrl("ldap://www.zflexldap.com:389");
//		ldapContextSource.setBase("cn=ro_admin,ou=sysadmins,dc=zflexsoftware,dc=com");
//		ldapContextSource.setPassword("zflexpass");
		
		ldapContextSource.afterPropertiesSet();
		return ldapContextSource;
	}
}
