/**
 * @program: bbs
 * @description:
 * @author: Wu
 * @create: 2019-12-20 22:07
 **/
package com.wu.bbs.cache;

import com.wu.bbs.dto.TagCacheDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagCache {
    public List<TagCacheDTO> get() {
        ArrayList<TagCacheDTO> tagCacheDTOS = new ArrayList<>();
        TagCacheDTO program = new TagCacheDTO();
        program.setCategoryName("开发语言");
        program.setTags(Arrays.asList("js","java","php","css","html","node","python"));
        tagCacheDTOS.add(program);

        TagCacheDTO framework = new TagCacheDTO();
        framework.setCategoryName("平台框架");
        framework.setTags(Arrays.asList("Spring","Spring Boot","Mybatis","Hibernate","Struts"));
        tagCacheDTOS.add(framework);

        TagCacheDTO server = new TagCacheDTO();
        server.setCategoryName("服务器");
        server.setTags(Arrays.asList("Apache","Nginx","tomcat"));
        tagCacheDTOS.add(server);

        return tagCacheDTOS;
    }
}
