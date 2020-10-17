package com.zhou.gateway.demo.service.persistence.service;


import com.alibaba.fastjson.JSON;
import com.zhou.gateway.demo.pojo.GatewayRouteDefinition;
import com.zhou.gateway.demo.service.persistence.factory.PersistenceFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class DynamicRouteServiceImpl {


    @Autowired
    private PersistenceFactory persistenceFactory;

    /**
     * 增加路由
     *
     * @param gwdefinition
     * @return
     */
    public String add(GatewayRouteDefinition gwdefinition) {
        log.info("add begin ,params is {}", JSON.toJSON(gwdefinition));

        try {
            persistenceFactory.add(gwdefinition);
            return "add:success";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "add:fail,msg is " + e.getMessage();
        }
    }

    /**
     * 更新路由
     *
     * @param gwdefinition
     * @return
     */
    public String update(GatewayRouteDefinition gwdefinition) {
        try {
            persistenceFactory.update(gwdefinition);
            return "update:success";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "update:fail,msg is " + e.getMessage();
        }
    }

    /**
     * 删除路由
     *
     * @param routeId
     * @return
     */
    public String delete(String routeId) {
        log.info("delete begin ,params is {}", JSON.toJSON(routeId));
        try {
            persistenceFactory.delete(routeId);
            return "delete:success";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "delete:fail,msg is " + e.getMessage();
        }
    }

    /**
     * 查询所有路由
     *
     * @return
     */
    public String queryAll() {
        return persistenceFactory.queryAll();
    }
}