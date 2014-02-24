package org.optigra.ads.dao.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.dao.pagination.PagedSearch;
import org.optigra.ads.model.Queries;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Dao clas for user entity.
 * @date Jan 23, 2014
 * @author ivanursul
 *
 */
@Repository("userDao")
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

    @Override
    public void createUser(final User user) {
        Assert.notNull(user);
        create(user);
    }

    @Override
    public PagedResult<User> getUsers(final int offset, final int limit) {
        
        Map<String, Object> parameters = Collections.emptyMap();
        Queries query = Queries.FIND_USERS;
        PagedSearch search = new PagedSearch(offset, limit, query , parameters);
        
        return search(search);
    }

}

