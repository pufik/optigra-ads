package org.optigra.ads.dao.application;

import org.optigra.ads.dao.AbstractDao;
import org.optigra.ads.model.application.Application;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/** Default implementation of Application Dao layer.
 * @date Feb 12, 2014
 * @author ivanursul
 *
 */
@Repository("applicationDao")
public class DefaultApplicationDao extends AbstractDao<Application, Long> implements ApplicationDao {

    @Override
    protected Class<Application> getEntityClass() {
        return Application.class;
    }

    @Override
    public void createApplication(final Application application) {
        Assert.isNull(application.getId());
        persist(application);
    }

}
