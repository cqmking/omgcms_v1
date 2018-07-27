package org.omgcms.web.rest.controller;

import org.omgcms.core.model.ResourceAction;
import org.omgcms.core.service.ResourceActionService;
import org.omgcms.web.constant.ResourceActionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        List<ResourceAction> allResActionList = resourceActionService.findAll();

        Map<String, List<ResourceAction>> stringListMap = loadLoopDataMap(allResActionList);

        return stringListMap;
    }

    /**
     *
     * 封装资源菜单数据
     *
     * @param resourceActionList 资源信息列表
     * @return 资源信息Map
     */
    private Map<String, List<ResourceAction>> loadLoopDataMap(List<ResourceAction> resourceActionList) {

        if (CollectionUtils.isEmpty(resourceActionList)) {
            return null;
        }

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>(resourceActionList.size());

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
