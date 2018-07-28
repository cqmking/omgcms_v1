package org.omgcms.web.rest.controller;

import org.omgcms.core.model.ResourceAction;
import org.omgcms.core.service.ResourceActionService;
import org.omgcms.kernel.util.UUIDUtil;
import org.omgcms.web.constant.ResourceActionConstant;
import org.omgcms.web.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/resource/{type}")
    public Object getResourceActionsByType(@PathVariable(name = "type") String type) {

        List<ResourceAction> resourceActionList = resourceActionService.findAllByType(type, ResourceActionConstant.FIELD_RESOURCE_NAME, true);

        return resourceActionList;
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
