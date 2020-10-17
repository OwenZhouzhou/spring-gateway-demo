package com.zhou.gateway.demo.pojo;

import lombok.Data;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.hibernate.criterion.BetweenExpression;
import org.springframework.cloud.gateway.handler.predicate.ReadBodyPredicateFactory;
import org.springframework.util.StringUtils;
import sun.jvm.hotspot.memory.HeapBlock;

import javax.management.Query;
import java.io.Serializable;
import java.util.*;

/**
 * 路由断言定义模型
 */
@Data
public class GatewayPredicateDefinition implements Serializable {

    private static Map<String, String> predicateMap = new HashMap<>();

    static {
        predicateMap.put("After", "");
        predicateMap.put("Before", "");
        predicateMap.put("Between", "");
        predicateMap.put("Cookie", "");
        predicateMap.put("Header", "");
        predicateMap.put("Host", "");
        predicateMap.put("Method", "");
        predicateMap.put("Path", "");
        predicateMap.put("Query", "");
        predicateMap.put("ReadBodyPredicateFactory", "");
        predicateMap.put("RemoteAddr", "");
        predicateMap.put("Weight", "");
        predicateMap.put("CloudFoundryRouteService", "");
        predicateMap = Collections.unmodifiableMap(predicateMap);
    }

    public boolean isSupportPredicate() {
        return !StringUtils.isEmpty(name) && predicateMap.containsKey(this.name);
    }
    /**
     * name must be in these
     * 1.After
     * 2.Before
     * 3.Between
     * 4.Cookie
     * 5.Header
     * 6.Host
     * 7.Method
     * 8.Path
     * 9.Query
     * 10.ReadBodyPredicateFactory
     * 11.RemoteAddr
     * 12.Weight
     * 13.CloudFoundryRouteService
     */
    /**
     * 断言对应的Name
     */
    private String name;

    /**
     * 配置的断言规则
     */
    private Map<String, String> args = new LinkedHashMap<>();
}