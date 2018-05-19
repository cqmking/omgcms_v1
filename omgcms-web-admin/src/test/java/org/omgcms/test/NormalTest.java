package org.omgcms.test;

import org.junit.Test;

/**
 * @Author Madfrog Yang
 * @Description
 * @Date created in 0:55 2018/5/20
 * @Modified By
 */
public class NormalTest {

    @Test
    public void test(){
        System.out.println(System.getProperty("java.version"));   //java版本号
        System.out.println(System.getProperty("java.vendor"));    //Java提供商名称
        System.out.println(System.getProperty("java.vendor.url"));   //Java提供商网站
        System.out.println(System.getProperty("java.home"));   //Java，哦，应该是jre目录
        System.out.println(System.getProperty("java.vm.specification.version"));   //Java虚拟机规范版本号
        System.out.println(System.getProperty("java.vm.specification.vendor"));   //Java虚拟机规范提供商
        System.out.println(System.getProperty("java.vm.specification.name"));   //Java虚拟机规范名称
        System.out.println(System.getProperty("java.vm.version"));   //Java虚拟机版本号
        System.out.println(System.getProperty("java.vm.vendor"));   //Java虚拟机提供商
        System.out.println(System.getProperty("java.vm.name"));   //Java虚拟机名称
        System.out.println(System.getProperty("java.specification.version"));   //Java规范版本号
        System.out.println(System.getProperty("java.specification.vendor"));   //Java规范提供商
        System.out.println(System.getProperty("java.specification.name"));   //Java规范名称
        System.out.println(System.getProperty("java.class.version"));   //Java类版本号
        System.out.println(System.getProperty("java.class.path"));   //Java类路径
        System.out.println(System.getProperty("java.library.path"));   //Java lib路径
        System.out.println(System.getProperty("java.io.tmpdir"));   //Java输入输出临时路径
        System.out.println(System.getProperty("java.compiler"));   //Java编译器
        System.out.println(System.getProperty("java.ext.dirs"));   //Java执行路径
        System.out.println(System.getProperty("os.name"));   //操作系统名称
        System.out.println(System.getProperty("os.arch"));   //
        System.out.println(System.getProperty("os.version"));   //版本号
        System.out.println(System.getProperty("file.separator"));   //文件分隔符
        System.out.println(System.getProperty("path.separator"));   //路径分隔符
        System.out.println(System.getProperty("line.separator"));   //直线分隔符
        System.out.println(System.getProperty("user.name"));   //用户名
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("user.dir"));
    }

}
