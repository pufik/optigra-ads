package org.optigra.ads.common;

/**
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
public class Queries {

    public static final String USER_FIND_BY_LOGIN_AND_PASSWORD_QUERY_NAME = "User.findByLoginAndPassword";
    public static final String USER_FIND_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT u FROM User u WHERE login = :login AND password = :password";
    
    public static final Queries FIND_USER_BY_LOGIN_AND_PASS = new Queries(USER_FIND_BY_LOGIN_AND_PASSWORD_QUERY_NAME, USER_FIND_BY_LOGIN_AND_PASSWORD_QUERY);

    private final String queryName;
    private final String query;

    private Queries(final String queryName, final String query) {
        this.queryName = queryName;
        this.query = query;
    }

    public String getQueryName() {
        return queryName;
    }

    public String getQuery() {
        return query;
    }
    
}
