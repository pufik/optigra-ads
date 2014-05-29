package org.optigra.ads.service.application;

import java.util.UUID;

import javax.annotation.Resource;

import org.optigra.ads.dao.application.ApplicationDao;
import org.optigra.ads.model.application.Application;
import org.optigra.ads.model.application.ApplicationStatus;
import org.optigra.ads.model.pagination.PagedResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Default implementation for application service.
 *
 * @date Feb 12, 2014
 * @author ivanursul
 */
@Service("applicationService")
public class DefaultApplicationService implements ApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultApplicationService.class);

    @Resource(name = "applicationDao")
    private ApplicationDao applicationDao;

    @Override
    public void createApplication(final Application application) {
        String applicationId = getApplicationId();
        application.setApplicationId(applicationId);

        logger.info("Create Application: {}", application);

        applicationDao.create(application);
    }

    private String getApplicationId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public PagedResult<Application> getApplications(final int offset, final int limit) {
        return applicationDao.getApplications(offset, limit);
    }

    @Override
    public ApplicationStatus getApplicationStatus(final String applicationId) {
        logger.info("Retrieve Application's status by application ID: [{}]", applicationId);

        Application application = getApplication(applicationId);

        return application.getStatus();
    }

    @Override
    public Application getApplication(final String applicationId) {
        logger.info("Retrieve Application by application ID: [{}]", applicationId);

        return applicationDao.getApplicationById(applicationId);
    }

    @Override
    public void deleteApplication(final String applicationId) {
        Application application = getApplication(applicationId);
        applicationDao.remove(application);
    }

    @Override
    public void updateApplication(final Application application) {
        applicationDao.update(application);
    }

}
