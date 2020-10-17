package com.zhou.gateway.demo.service.persistence.service.impl;

import com.zhou.gateway.demo.pojo.GatewayRouteDefinition;
import com.zhou.gateway.demo.service.persistence.service.PersistenceService;
import com.zhou.gateway.demo.util.GatewayRouteUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program gatewaydemo
 * @description:
 * @author: zhou
 * @create: 2019/12/21 16:04
 */
@Slf4j
@Service("SpringGatewayPersistenceImpl")
public class SpringGatewayPersistenceImpl implements PersistenceService {


    @Autowired
    private GatewayRouteUtil gatewayRouteUtil;

    @Override
    public String queryAll() {
        return gatewayRouteUtil.queryAllMermoy();
    }

    @Override
    public void addRoute(GatewayRouteDefinition gwdefinition) {
        log.info("springgateway Persistence addRoute");
        gatewayRouteUtil.add(gwdefinition);
    }

    @Override
    public void updateRoute(GatewayRouteDefinition gwdefinition) throws Exception {
        log.info("springgateway Persistence updateRoute");
        gatewayRouteUtil.update(gwdefinition);
    }

    @Override
    public void deleteRoute(String routeId) throws Exception {
        log.info("springgateway Persistence deleteRoute");
        gatewayRouteUtil.delete(routeId);
    }

}
