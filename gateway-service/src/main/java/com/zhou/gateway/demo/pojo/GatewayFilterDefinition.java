package com.zhou.gateway.demo.pojo;

import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 过滤器定义模型
 */
@Data
public class GatewayFilterDefinition implements Serializable {


    private static Map<String, String> filterMap = new HashMap<>();


    static {
        filterMap.put("SetPath", "");
        filterMap.put("RequestHeaderToRequestUri", "");
        filterMap.put("RemoveRequestHeader", "");
        filterMap.put("Hystrix", "");
        filterMap.put("ModifyRequestBody", "");
        filterMap.put("AddRequestParameter", "");
        filterMap.put("RequestRateLimiter", "");
        filterMap.put("PreserveHostHeader", "");
        filterMap.put("RewritePath", "");
        filterMap.put("SetStatus", "");
        filterMap.put("SetRequestHeader", "");
        filterMap.put("PrefixPath", "");
        filterMap.put("SaveSession", "");
        filterMap.put("StripPrefix", "");
        filterMap.put("ModifyResponseBody", "");
        filterMap.put("RequestSize", "");
        filterMap.put("RedirectTo", "");
        filterMap.put("SetResponseHeader", "");
        filterMap.put("SecureHeaders", "");
        filterMap.put("AddResponseHeader", "");
        filterMap.put("FallbackHeaders", "");
        filterMap.put("Retry", "");
        filterMap.put("AddRequestHeader", "");
        filterMap.put("RemoveResponseHeader", "");
        filterMap.put("RewriteResponseHeader", "");

        filterMap = Collections.unmodifiableMap(filterMap);
    }


    /**
     * Filter Name must be in these
     * SetPath
     * RequestHeaderToRequestUri
     * RemoveRequestHeader
     * Hystrix
     * ModifyRequestBody
     * AddRequestParameter
     * RequestRateLimiter
     * PreserveHostHeader
     * RewritePath
     * SetStatus
     * SetRequestHeader
     * PrefixPath
     * SaveSession
     * StripPrefix
     * ModifyResponseBody
     * RequestSize
     * RedirectTo
     * SetResponseHeader
     * SecureHeaders
     * AddResponseHeader
     * FallbackHeaders
     * Retry
     * AddRequestHeader
     * RemoveResponseHeader
     * RewriteResponseHeader
     */

    private String name;


    public boolean isSupportFilter() {
        return !StringUtils.isEmpty(name) && filterMap.containsKey(this.name);
    }

    /**
     * 对应的路由规则
     */
    private Map<String, String> args = new LinkedHashMap<>();

    public static void main(String[] args) {
        String a=new String("sss");
        a = a.intern();
        String aa=new String("sss");
        aa = aa.intern();
        System.out.println(a == aa);
    }
}