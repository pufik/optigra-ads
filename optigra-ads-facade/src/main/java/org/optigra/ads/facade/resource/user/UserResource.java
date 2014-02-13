package org.optigra.ads.facade.resource.user;

import org.optigra.ads.facade.resource.Resource;
import org.optigra.ads.facade.resource.ResourceUri;

/**
 * 
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
public class UserResource extends Resource {

    protected Long id;
    
    protected String login;
    
    @Override
    public String getUri() {
        return ResourceUri.USER + ResourceUri.SLASH + id;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UserResource other = (UserResource) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserResource [id=" + id + ", login=" + login + "]";
    }
    
}
