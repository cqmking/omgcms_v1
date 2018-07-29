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

    /**
     * 通过类型查询所以资源Action
     *
     * @param type            类型
     * @param orderByProperty 排序字段名称
     * @param isAsc           是否顺序排列
     * @return 资源 Action 列表
     */
    List<ResourceAction> findAllByType(String type, String orderByProperty, boolean isAsc);

    /**
     * 根据resourceName和type查询资源action
     * @param resourceName 资源名称
     * @param type  资源类型
     * @return 资源 Action 列表
     */
    List<ResourceAction> findByResourceNameAndType(String resourceName, String type);

    /**
     * 获取所有资源Action
     *
     * @return 资源 Action 列表
     */
    List<ResourceAction> findAll();

    /**
     * 查询不重复资源名称和类型的 资源Action
     *
     * @return 资源 Action 列表
     */
    List<ResourceAction> findDistinctByResourceNameAndType();
}
