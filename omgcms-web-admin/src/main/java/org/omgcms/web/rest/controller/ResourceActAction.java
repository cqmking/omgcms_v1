package org.omgcms.web.rest.controller;

import org.omgcms.core.exception.CustomSystemException;
import org.omgcms.core.exception.ExceptionCode;
import org.omgcms.core.model.ResourceAction;
import org.omgcms.core.model.ResourcePermission;
import org.omgcms.core.model.Role;
import org.omgcms.core.service.ResourceActionService;
import org.omgcms.core.service.ResourcePermissionService;
import org.omgcms.core.service.RoleService;
import org.omgcms.kernel.util.UUIDUtil;
import org.omgcms.web.constant.ResourceActionConstant;
import org.omgcms.web.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 23:57 2018/5/31
 * @Modified By
 */
@RestController
public class ResourceActAction {

    @Autowired
    private ResourceActionService resourceActionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourcePermissionService resourcePermissionService;

    @GetMapping("/resource/{type}")
    public Object getResourceActionsByType(@PathVariable(name = "type") String type) {

        List<ResourceAction> resourceActionList = resourceActionService
                .findAllByType(type, ResourceActionConstant.FIELD_RESOURCE_NAME, true);

        return resourceActionList;
    }

    @GetMapping("/resource/get-resource-actions")
    public Object getResourceActionsByType(@RequestParam(value = "roleId") Long roleId,
                                           @RequestParam(value = "primaryKey", required = false, defaultValue = "0") Long primaryKey,
                                           @RequestParam(value = "type") String type,
                                           @RequestParam(value = "resourceName") String resourceName) {

        if (roleId == null || roleId <= 0) {
            throw new CustomSystemException(ExceptionCode.INVALID_PARAM_MESSAGE, "roleId");
        }

        List<ResourceAction> resourceActionList = resourceActionService
                .findByResourceNameAndType(resourceName, type);

        Role role = roleService.getRole(roleId);

        ResourcePermission resourcePermission = null;

        if (role != null) {

            int scope = 1;
            if ("system".equals(type)) {
                scope = 1;
            } else if ("model".equals(type)) {
                scope = 2;
            } else if ("instance".equals(type)) {
                scope = 3;
            }

            resourcePermission = resourcePermissionService
                    .getByPrimaryKeyAndResourceNameAndRoleAndScope(primaryKey, resourceName, role, scope);
        }

        List<Map<String, Object>> resourceActionMapList = getResourceActionMapList(resourceActionList, resourcePermission);

        return resourceActionMapList;
    }


    @GetMapping("/resource/tree")
    public Object getResourceActionsTreeMap() {

        List<ResourceAction> allResActionList = resourceActionService.findDistinctByResourceNameAndType();

        Map<String, List<ResourceAction>> stringListMap = loadLoopDataMap(allResActionList);
        Set<Map.Entry<String, List<ResourceAction>>> entries = stringListMap.entrySet();

        List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();

        for (Map.Entry<String, List<ResourceAction>> entry : entries) {
            String key = entry.getKey();

            final List<ResourceAction> resourceList = entry.getValue();
            Map<String, Object> tagMap = new HashMap<String, Object>();

            final List<Map<String, Object>> resourceMapList = getResourceMapList(resourceList);
            tagMap.put("id", UUIDUtil.getUuidLowerCaseWithoutDash());
            tagMap.put("key", key);
            tagMap.put("label", MessageUtil.getMessage("label.resource.type.".concat(key)));
            tagMap.put("children", resourceMapList);

            treeList.add(tagMap);
        }

        return treeList;
    }

    private List<Map<String, Object>> getResourceActionMapList(List<ResourceAction> listData,
                                                               ResourcePermission resourcePermission) {

        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        if (!CollectionUtils.isEmpty(listData)) {
            for (ResourceAction resourceAction : listData) {

                Map<String, Object> itemMap = new HashMap<String, Object>();
                Long bitwiseValue = resourceAction.getBitwiseValue();

                itemMap.put("label", MessageUtil.getMessage("label.resource.permission.".concat(resourceAction.getActionId())));
                itemMap.put("type", resourceAction.getType());
                itemMap.put("resourceActionId", resourceAction.getResourceActionId());
                itemMap.put("value", bitwiseValue);
                itemMap.put("resourceName", resourceAction.getResourceName());
                itemMap.put("actionId", resourceAction.getActionId());

                if (resourcePermission != null && (bitwiseValue & resourcePermission.getActionIds()) != 0) {
                    // resourcePermission 中包含该资源权限
                    itemMap.put("isChecked", true);
                } else {
                    itemMap.put("isChecked", false);
                }

                dataList.add(itemMap);
            }
        }

        return dataList;

    }

    private List<Map<String, Object>> getResourceMapList(List<ResourceAction> resourceList) {

        if (CollectionUtils.isEmpty(resourceList)) {
            return null;
        }

        List<Map<String, Object>> resourceMapList = new ArrayList<Map<String, Object>>();

        for (ResourceAction resourceAction : resourceList) {

            Map<String, Object> map = new HashMap<String, Object>();
            String resName = resourceAction.getResourceName();

            map.put("id", UUIDUtil.getUuidLowerCaseWithoutDash());
            map.put("resourceName", resName);
            map.put("type", resourceAction.getType());
            map.put("label", MessageUtil.getMessage("label.resource.name.".concat(resName)));

            resourceMapList.add(map);
        }
        return resourceMapList;
    }

    /**
     * 封装资源菜单数据
     *
     * @param resourceActionList 资源信息列表
     * @return 资源信息Map
     */
    private Map<String, List<ResourceAction>> loadLoopDataMap(List<ResourceAction> resourceActionList) {

        if (CollectionUtils.isEmpty(resourceActionList)) {
            return null;
        }

        Map<String, List<ResourceAction>> resourceMap = new HashMap<String, List<ResourceAction>>();

        for (ResourceAction resourceAction : resourceActionList) {

            String type = resourceAction.getType();
            List<ResourceAction> resourceActionsList = resourceMap.get(type);

            if (resourceActionsList == null) {
                resourceActionsList = new ArrayList<ResourceAction>();
                resourceMap.put(type, resourceActionsList);
            }

            resourceActionsList.add(resourceAction);

        }

        return resourceMap;
    }

}
