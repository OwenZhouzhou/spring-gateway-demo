package com.zhou.gateway.demo.service.persistence.service.impl;

import com.zhou.gateway.demo.pojo.GatewayRouteDefinition;
import com.zhou.gateway.demo.service.persistence.service.PersistenceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program gatewaydemo
 * @description:
 * @author: zhou
 * @create: 2019/12/21 16:04
 */

@Slf4j
@Service("H2PersistenceImpl")
public class H2PersistenceImpl implements PersistenceService {

    @Override
    public void addRoute(GatewayRouteDefinition gwdefinition) {
        log.info("H2 Persistence addRoute");

    }

    @Override
    public void updateRoute(GatewayRouteDefinition gwdefinition) {
        log.info("H2 Persistence updateRoute");
    }

    @Override
    public void deleteRoute(String routeId) {
        log.info("H2 Persistence routeId");

    }

}
