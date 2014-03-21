package org.optigra.ads.content.jcr;

import javax.annotation.Resource;
import javax.jcr.Session;

import org.springframework.stereotype.Component;

@Component("jcrSessionFactory")
public class JcrSessionFactoryImpl implements JcrSessionFactory {
    
    @Resource(name = "jcrSession")
    private Session session;

    @Override
    public Session getCurrentSession() {
        return session;
    }

}
