package org.optigra.ads.service.application;

import javax.annotation.Resource;

import org.optigra.ads.dao.application.ApplicationDao;
import org.optigra.ads.model.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("anonymousApplicationService")
public class AnonymousApplicationService extends DefaultApplicationService {

    private static final Logger logger = LoggerFactory.getLogger(AnonymousApplicationService.class);

    @Resource(name = "anonymousApplicationDao")
    private ApplicationDao applicationDao;

    @Override
    public Application getApplication(final String applicationId) {
        logger.info("Retrieve Apploication for Anonymous user by application ID: [{}]", applicationId);

        return applicationDao.getApplicationById(applicationId);
    }
}
