package org.optigra.ads.dao.user;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.model.User;

/**
 * Dao clas for user entity.
 * @date Jan 23, 2014
 * @author ivanursul
 *
 */
public class DefaultUserDao extends AbstractDao<User, Long> implements UserDao {

    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }
}

