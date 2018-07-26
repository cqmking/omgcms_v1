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

        List<ResourceAction> systemResourceActionList = resourceActionService
                .findAllByType(ResourceActionConstant.TYPE_SYSTEM_RESOURCE, ResourceActionConstant.FIELD_RESOURCE_NAME, true);

        List<ResourceAction> modelResourceActionList = resourceActionService
                .findAllByType(ResourceActionConstant.TYPE_MODEL, ResourceActionConstant.FIELD_RESOURCE_NAME, true);

        Map<String, Object> dataMap = new HashMap<String, Object>();

        List<Map<String, Object>> systemMapList = loadLoopDataMap(systemResourceActionList);
        List<Map<String, Object>> modelMapList = loadLoopDataMap(modelResourceActionList);

        dataMap.put("system", systemMapList);
        dataMap.put("model", modelMapList);

        return dataMap;

    }

    /**
     * 循环获取资源树
     *
     * @param resourceActionList 资源列表
     * @return mapList
     */
    private List<Map<String, Object>> loadLoopDataMap(List<ResourceAction> resourceActionList) {

        if (CollectionUtils.isEmpty(resourceActionList)) {
            return null;
        }

        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>(resourceActionList.size());

        for (ResourceAction resourceAction : resourceActionList) {

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("name",resourceAction.getResourceName());
            map.put("actionId",resourceAction.getActionId());
            map.put("value",resourceAction.getBitwiseValue());
            map.put("id",resourceAction.getResourceActionId());
            map.put("type",resourceAction.getType());

            mapList.add(map);
        }

        return mapList;
    }

}
