package org.optigra.ads.facade.resource.user;

import org.optigra.ads.facade.resource.Resource;
import org.optigra.ads.facade.resource.ResourceUri;
import org.optigra.ads.model.user.UserRole;

/**
 * @date Feb 7, 2014
 * @author ivanursul
 */
public class UserResource extends Resource {

    private Long id;

    private String login;

    private String email;

    private String fullName;

    private UserRole role;
    
    private String imageUrl;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(final UserRole role) {
        this.role = role;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((imageUrl == null) ? 0 : imageUrl.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
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
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (imageUrl == null) {
            if (other.imageUrl != null)
                return false;
        } else if (!imageUrl.equals(other.imageUrl))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (role != other.role)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "UserResource [id=" + id + ", login=" + login + ", email=" + email + ", fullName=" + fullName + ", role=" + role + ", imageUrl=" + imageUrl
                + "]";
    }

}
