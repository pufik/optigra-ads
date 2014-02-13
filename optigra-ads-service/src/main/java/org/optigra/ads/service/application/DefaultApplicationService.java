package org.optigra.ads.service.application;

import javax.annotation.Resource;

import org.optigra.ads.dao.application.ApplicationDao;
import org.optigra.ads.dao.pagination.PagedResult;
import org.optigra.ads.model.application.Application;
import org.springframework.stereotype.Service;

/** Default implementation for application service. 
 * @date Feb 12, 2014
 * @author ivanursul
 *
 */
@Service("applicationService")
public class DefaultApplicationService implements ApplicationService {

    @Resource(name = "applicationDao")
    private ApplicationDao applicationDao;
    
    @Override
    public void createApplication(final Application application) {
        applicationDao.createApplication(application);
    }

    @Override
    public PagedResult<Application> getApplications(final int start, final int offset) {
        return applicationDao.getApplications(start, offset);
    }

    @Override
    public String getApplicationStatus(final String applicationId) {
        
        Application application = applicationDao.getApplicationById(applicationId);
        String status = application.getStatus().name();
        
        return status;
    }

}