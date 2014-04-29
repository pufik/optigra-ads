package org.optigra.ads.model.query;


/**
 * @date Feb 7, 2014
 * @author ivanursul
 *
 */
public class Queries {
   
    // Constants 
    public static final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY_NAME = "User.findByLoginAndPassword";
    public static final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT u FROM User u WHERE login = :login AND password = :password";
    
    public static final String FIND_APPLICATIONS_QUERY_NAME = "Application.findApplications";
    public static final String FIND_APPLICATIONS_QUERY = "SELECT a FROM Application a";
    
    public static final String FIND_APPLICATION_BY_ID_QUERY_NAME = "Application.findApplicationById";
    public static final String FIND_APPLICATION_BY_ID_QUERY = "SELECT a FROM Application a WHERE a.applicationId = :appId";

    public static final String FIND_ADVERTISINGS_QUERY_NAME = "Advertising.findAdvertisings";
    public static final String FIND_ADVERTISINGS_QUERY = "SELECT a FROM Advertising a";

    public static final String FIND_USERS_QUERY_NAME = "User.findUsers";
    public static final String FIND_USERS_QUERY = "SELECT u FROM User u";
    
    public static final String FIND_DEVICE_BY_UID_QUERY_NAME = "Device.findDeviceByUid";
    public static final String FIND_DEVICE_BY_UID_QUERY = "SELECT d FROM Device d WHERE d.deviceUid = :deviceUid";

    // Queries
    public static final Queries FIND_USER_BY_LOGIN_AND_PASS = new Queries(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY_NAME, FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY);
    public static final Queries FIND_APPLICATIONS = new Queries(FIND_APPLICATIONS_QUERY_NAME, FIND_APPLICATIONS_QUERY);
    public static final Queries FIND_APPLICATION_BY_ID = new Queries(FIND_APPLICATION_BY_ID_QUERY_NAME, FIND_APPLICATION_BY_ID_QUERY);
    public static final Queries FIND_ADVERTISINGS = new Queries(FIND_ADVERTISINGS_QUERY_NAME, FIND_ADVERTISINGS_QUERY);
    public static final Queries FIND_USERS = new Queries(FIND_USERS_QUERY_NAME, FIND_USERS_QUERY);
	public static final Queries FIND_DEVICE_BY_UID = new Queries(FIND_DEVICE_BY_UID_QUERY_NAME, FIND_DEVICE_BY_UID_QUERY);

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