package org.optigra.ads.content.jcr;

import javax.annotation.Resource;
import javax.jcr.Session;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("jcrSessionFactory")
@Scope(value = "session", proxyMode = ScopedProxyMode.NO)
public class JcrSessionFactoryImpl implements JcrSessionFactory {
    
    @Resource(name = "jcrSession")
    private Session session;

    @Override
    public Session getCurrentSession() {
        return session;
    }

}
