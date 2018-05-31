package org.omgcms.web.rest.controller;

import org.omgcms.core.model.ResourceAction;
import org.omgcms.core.service.ResourceActionService;
import org.omgcms.web.constant.ResourceActionConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("resource/{type}")
    public Object getResourceActionsByType(@PathVariable(name = "type") Integer type) {

        List<ResourceAction> resourceActionList = resourceActionService.findAllByType(type, ResourceActionConstant.FIELD_RESOURCE_NAME, true);

        return resourceActionList;
    }

}
