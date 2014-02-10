package org.optigra.ads.dao.user;

import java.util.HashMap;
import java.util.Map;

import org.optigra.ads.common.Queries;
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

    @Override
    public User getUserById(final Long userId) {
        return findById(userId);
    }

    @Override
    public User getUserByLoginAndPassword(final String login, final String password) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("login", login);
        parameters.put("password", password);
        
        return executeSingleResultNamedQuery(Queries.FIND_USER_BY_LOGIN_AND_PASS.getQueryName(), parameters );
    }

}

