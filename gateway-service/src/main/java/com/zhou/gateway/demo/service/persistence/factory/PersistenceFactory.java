package com.zhou.gateway.demo.service.persistence.factory;

import com.zhou.gateway.demo.pojo.GatewayRouteDefinition;
import com.zhou.gateway.demo.service.persistence.service.PersistenceService;
import com.zhou.gateway.demo.service.persistence.service.impl.H2PersistenceImpl;
import com.zhou.gateway.demo.service.persistence.service.impl.SpringGatewayPersistenceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program gatewaydemo
 * @description: 持久化工厂
 * @author: zhou
 * @create: 2019/12/21 16:03
 */
@Slf4j
@Component
public class PersistenceFactory {

    private static Map<String, PersistenceService> factoryMap = new LinkedHashMap<>();

    @Autowired
    H2PersistenceImpl h2Persistence;
    @Autowired
    SpringGatewayPersistenceImpl springGatewayPersistence;

    @PostConstruct
    private void init() {
        if (factoryMap.isEmpty()) {
            factoryMap.put(PersistenceType.H2.name(), h2Persistence);
            factoryMap.put(PersistenceType.GATWAY.name(), springGatewayPersistence);
            factoryMap = Collections.unmodifiableMap(factoryMap);
        }
    }

    enum PersistenceType {

        H2("h2"),
        GATWAY("gateway");

        private final String type;

        private PersistenceType(String type) {
            this.type = type;
        }
    }


    public String queryAll() {
        return springGatewayPersistence.queryAll();
    }


    @Transactional(rollbackFor = Exception.class)
    public void add(GatewayRouteDefinition gwdefinition) {
        factoryMap.values().stream().forEach(t -> t.addRoute(gwdefinition));
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(GatewayRouteDefinition gwdefinition) throws Exception {
        for (PersistenceService t : factoryMap.values()) {
            t.updateRoute(gwdefinition);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String routeId) throws Exception {
        for (PersistenceService t : factoryMap.values()) {
            t.deleteRoute(routeId);
        }
    }

}
