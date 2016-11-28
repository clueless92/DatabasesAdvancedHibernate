package bg.tilchev.serviceImpls;

import bg.tilchev.models.Resource;
import bg.tilchev.repos.ResourceRepo;
import bg.tilchev.services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Todor Ilchev on 2016-11-08.
 */

@Service
public class ResourceServiceImpl implements ResourceService {

    private ResourceRepo resourceRepo;

    @Autowired
    public ResourceServiceImpl(ResourceRepo resourceRepo) {
        this.resourceRepo = resourceRepo;
    }

    @Override
    public void persist(Resource resource) {
        this.resourceRepo.saveAndFlush(resource);
    }

    @Override
    public long count() {
        return this.resourceRepo.count();
    }
}
