package com.memorynotfound.ldap.dao;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.LikeFilter;

import com.memorynotfound.ldap.entities.Person;
public class PersonDao {
	private LdapTemplate ldapTemplate;
	private static class PersonAttributMapper implements AttributesMapper
	{
		public Person mapFromAttributes(Attributes attrs)
				throws javax.naming.NamingException {
			Person p = new Person();
			p.setFirstName(attrs.get("cn").get().toString());
		    p.setLastName(attrs.get("sn").get().toString());
			p.setUid(attrs.get("uid").get().toString());
			p.setEmail(attrs.get("mail").get().toString());
			p.setTelnumber(attrs.get("telephonenumber").get().toString());
			return p;
		}
	}
	public Person findByPrimaryKey(String uid) {
		Name dn = buildDn(uid);
		return (Person) ldapTemplate.lookup(dn, new
		PersonAttributMapper());
	}
	private Name buildDn(String uid) {
		DistinguishedName dn = new DistinguishedName();
		dn.add("uid", uid);
		return dn;
	}
	public void setLdapTemplate(LdapTemplate ldapTemplate) {
		this.ldapTemplate = ldapTemplate;
	}
//	public List getPersonNamesByLastName(String lastName) {
//		AndFilter filter = new AndFilter();
//		filter.and(new EqualsFilter("objectclass", "person"));
//		filter.and(new LikeFilter("sn", lastName));
//		return ldapTemplate.search("", filter.encode(),
//		new PersonAttributMapper());
//	}
	
	/**
     * Retrieves all the persons in the ldap server
     * @return list of person names
     */
    public List<String> getAllPersonNames() {
        return this.ldapTemplate.search(
                query().where("objectclass").is("person"),
                new AttributesMapper<String>() {
                    public String mapFromAttributes(Attributes attrs)
                            throws NamingException {
                        return (String) attrs.get("telephonenumber").get();
                    }
                });
    }
    
    /**
     * Retrieves all the persons in the ldap server
     * @return list of person names
     */
    public List<Person> getAllInfosPerson() {
        return this.ldapTemplate.search(
                query().where("objectclass").is("person"),
                new PersonAttributMapper());
    }
}
