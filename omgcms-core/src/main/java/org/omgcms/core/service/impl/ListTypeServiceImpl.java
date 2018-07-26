package org.omgcms.core.service.impl;

import org.omgcms.core.model.ListType;
import org.omgcms.core.repository.ListTypeRepository;
import org.omgcms.core.service.ListTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:43 2018/7/27
 * @Modified By
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class ListTypeServiceImpl implements ListTypeService {

    @Autowired
    private ListTypeRepository listTypeRepository;

    public ListType save(ListType listType) {
        return listTypeRepository.save(listType);
    }

    public List<ListType> findAllByType(String type) {
        return listTypeRepository.findAllByType(type);
    }

}
