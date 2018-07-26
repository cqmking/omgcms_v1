package org.omgcms.core.service;

import org.omgcms.core.model.ListType;

import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:43 2018/7/27
 * @Modified By
 */
public interface ListTypeService {

    ListType save(ListType listType);

    List<ListType> findAllByType(String type);

}
