package org.omgcms.core.service;

import org.omgcms.core.model.ResourceAction;

import java.util.List;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:26 2018/5/30
 * @Modified By
 */
public interface ResourceActionService {

    /**
     * 根据资源名称获取资源信息列表
     *
     * @param resourceName 资源名称
     * @return 资源信息列表
     */
    List<ResourceAction> findByResourceName(String resourceName);

    /**
     * 保存或更新
     *
     * @param resourceAction 更新、保存对象
     * @return 更新保存后的对象
     */
    ResourceAction save(ResourceAction resourceAction);
}
