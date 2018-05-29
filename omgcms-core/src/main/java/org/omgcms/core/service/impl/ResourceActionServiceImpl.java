package org.omgcms.core.service.impl;

import org.omgcms.core.model.ResourceAction;
import org.omgcms.core.repository.ResourceActionRepository;
import org.omgcms.core.service.ResourceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:26 2018/5/30
 * @Modified By
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ResourceActionServiceImpl implements ResourceActionService {

    @Autowired
    private ResourceActionRepository resourceActionRepository;

    public List<ResourceAction> findByResourceName(String resourceName){
        return resourceActionRepository.findByResourceName(resourceName);
    }

}
