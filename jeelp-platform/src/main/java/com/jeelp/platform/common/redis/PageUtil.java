package com.jeelp.platform.common.redis;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

/**
 * 分页工具
 */
public class PageUtil {

    /**
     * 分页
     */
    public static List toPage(int page, int size, List list) {
        int fromIndex = page * size;
        int toIndex = page * size + size;
        if (fromIndex > list.size()) {
            return new ArrayList();
        } else if (toIndex >= list.size()) {
            return list.subList(fromIndex, list.size());
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }

    /**
     * 数据处理，预防redis反序列化报错
     */
    public static Map<String, Object> toPage(Page page) {
        return toPage(page.getContent(),page.getTotalElements());
    }

    /**
     * 自定义分页
     */
    public static Map<String, Object> toPage(Object object, Object total) {
        Map<String, Object> map = new LinkedHashMap<>(2);
        map.put("code", "0");
        map.put("data", object);
        map.put("total", total);
        return map;
    }

}
