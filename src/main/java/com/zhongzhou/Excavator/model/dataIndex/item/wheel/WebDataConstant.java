package com.zhongzhou.Excavator.model.dataIndex.item.wheel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lixiaohao on 2016/11/22
 *
 * @Description
 * @Create 2016-11-22 10:17
 * @Company
 */
public enum WebDataConstant {
    MADE_IN_CHIAN("MADE_IN_CHIAN"),ALIBABA("ALIBABA");
    private String value;
    private static final Map<String, WebDataConstant> ENUM_MAP = new HashMap<String, WebDataConstant>();
    static{
        for(WebDataConstant item : values()){
            ENUM_MAP.put(item.toString(), item);
        }
    }

    WebDataConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static WebDataConstant getEnume(String name){
        return ENUM_MAP.get(name);
    }
}
