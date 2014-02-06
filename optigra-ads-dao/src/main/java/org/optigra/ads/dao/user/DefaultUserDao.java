package org.optigra.ads.dao.user;

import java.util.List;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Repository;

/**
 * Dao clas for user entity.
 * @date Jan 23, 2014
 * @author ivanursul
 *
 */
@Repository
public class DefaultUserDao extends AbstractDao<User, Long> implements UserDao {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    /* 
     * Get List of users
     * @date Feb 6, 2014
     * @author ivanursul
     *
     * @see org.optigra.ads.dao.user.UserDao#getUsers(int, int)
     */
    @Override
    public List<User> getUsers(final int start, final int length) {
        return find(start, length);
    }
}

