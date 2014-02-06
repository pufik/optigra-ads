package org.optigra.ads.dao.user;

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

}

