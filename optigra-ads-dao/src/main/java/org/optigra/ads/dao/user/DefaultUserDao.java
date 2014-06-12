package org.optigra.ads.dao.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.model.pagination.PagedResult;
import org.optigra.ads.model.pagination.PagedSearch;
import org.optigra.ads.model.query.Queries;
import org.optigra.ads.model.query.Query;
import org.optigra.ads.model.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * Dao clas for user entity.
 *
 * @date Jan 23, 2014
 * @author ivanursul
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

        return executeSingleResultQuery(Queries.FIND_USER_BY_LOGIN_AND_PASS.getQuery(), parameters);
    }

    @Override
    public void createUser(final User user) {
        Assert.notNull(user);
        create(user);
    }

    @Override
    public PagedResult<User> getUsers(final int offset, final int limit) {
        Query<User> query = new Query<User>(getEntityClass(), Queries.FIND_USERS.getQuery(), Collections.<String, Object> emptyMap());
        PagedSearch<User> search = new PagedSearch<>(offset, limit, query);

        return search(search);
    }

    @Override
    public User getUserByLogin(final String login) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("login", login);

        return executeSingleResultQuery(Queries.FIND_USER_BY_LOGIN.getQuery(), parameters);
    }
}
