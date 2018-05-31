package org.omgcms.core.service.impl;

import org.omgcms.core.model.ResourceAction;
import org.omgcms.core.repository.ResourceActionRepository;
import org.omgcms.core.service.ResourceActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public List<ResourceAction> findByResourceName(String resourceName) {
        return resourceActionRepository.findByResourceName(resourceName);
    }

    public ResourceAction save(ResourceAction resourceAction) {
        return resourceActionRepository.save(resourceAction);
    }

    public List<ResourceAction> findAllByType(int type, String orderByProperty, boolean isAsc) {

        Sort.Direction direction = Sort.Direction.ASC;

        if (!isAsc) {
            direction = Sort.Direction.DESC;
        }

        Sort.Order idOrder = new Sort.Order(direction, orderByProperty);
        Sort sort = new Sort(idOrder);

        PageRequest pageable = new PageRequest(0, Integer.MAX_VALUE, sort);

        Page<ResourceAction> page = resourceActionRepository.findByType(type, pageable);

        List<ResourceAction> content = page.getContent();

        return content;

    }

}
