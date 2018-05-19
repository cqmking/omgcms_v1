package org.omgcms.web.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:38 2018/5/20
 * @Modified By
 */
@RestController
public class CommonAction {

    private static Logger logger = LoggerFactory.getLogger(CommonAction.class);

    @GetMapping("/server-info")
    public Object getServerInfo() {

        Properties props = System.getProperties(); //获得系统属性集
        String osName = props.getProperty("os.name"); //操作系统名称
        String osArch = props.getProperty("os.arch"); //操作系统构架
        String osVersion = props.getProperty("os.version"); //操作系统版本

        String jvmName = props.getProperty("java.vm.name"); // java虚拟机
        String jreVersion = props.getProperty("java.specification.version"); // java虚拟机版本
        String jreName = props.getProperty("java.specification.name");  // java运行环境

        String userHome = props.getProperty("user.home");  // 用户的主目录
        String userDir = props.getProperty("user.dir");  // 用户的当前工作目录
        String tempDir = props.getProperty("java.io.tmpdir");  // 用户的临时文件目录

        double totalMemory = (Runtime.getRuntime().totalMemory()) / (1024.0 * 1024);    //已用内存
        double maxMemory = (Runtime.getRuntime().maxMemory()) / (1024.0 * 1024);    // 最大内存
        // double freeMemory = (Runtime.getRuntime().freeMemory()) / (1024.0 *
        // 1024);
        double freeMemory = maxMemory - totalMemory;    // 空闲内存

        Map<String, Object> sysInfoMap = new HashMap<String, Object>();

        sysInfoMap.put("osName", osName);
        sysInfoMap.put("osArch", osArch);
        sysInfoMap.put("osVersion", osVersion);
        sysInfoMap.put("jvmName", jvmName);
        sysInfoMap.put("jreName", jreName);
        sysInfoMap.put("jreVersion", jreVersion);
        sysInfoMap.put("userHome", userHome);
        sysInfoMap.put("userDir", userDir);
        sysInfoMap.put("tempDir", tempDir);
        sysInfoMap.put("totalMemory", totalMemory);
        sysInfoMap.put("maxMemory", maxMemory);
        sysInfoMap.put("freeMemory", freeMemory);

        return sysInfoMap;
    }



}
