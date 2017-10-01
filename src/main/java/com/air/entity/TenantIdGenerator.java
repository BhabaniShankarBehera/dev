/**
 * 
 */
package com.air.entity;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.stereotype.Component;

/**
 * @author BhabaniShankar
 *
 */
@Component
public class TenantIdGenerator implements IdentifierGenerator {

	
	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1) throws HibernateException {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String output = sb.toString();
		
		
		return output;
	}

}
