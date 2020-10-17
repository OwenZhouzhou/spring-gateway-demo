package com.zhou.gateway.demo.service.persistence.service;

import com.zhou.gateway.demo.pojo.GatewayRouteDefinition;

/**
 * @program gatewaydemo
 * @description:
 * @author: zhou
 * @create: 2019/12/21 16:39
 */
public interface PersistenceService {


    default String queryAll() {
        return "";
    }

    void addRoute(GatewayRouteDefinition gwdefinition);


    void updateRoute(GatewayRouteDefinition gwdefinition) throws Exception;


    void deleteRoute(String routeId) throws Exception;

}