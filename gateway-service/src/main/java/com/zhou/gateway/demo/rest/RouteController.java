package com.zhou.gateway.demo.rest;

import com.zhou.gateway.demo.pojo.GatewayRouteDefinition;
import com.zhou.gateway.demo.service.persistence.service.DynamicRouteServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/route")
@Slf4j
public class RouteController {

    @Autowired
    private DynamicRouteServiceImpl dynamicRouteService;

    /**
     * 增加路由
     *
     * @param gwdefinition
     * @return
     */
    @PostMapping("/add")
    public String add(@RequestBody GatewayRouteDefinition gwdefinition) {
        try {
            return this.dynamicRouteService.add(gwdefinition);
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return "succss";
    }

    @GetMapping("/delete/{routeId}")
    public String delete(@PathVariable String routeId) {
        return this.dynamicRouteService.delete(routeId);
    }

    @PostMapping("/update")
    public String update(@RequestBody GatewayRouteDefinition gwdefinition) {
        return this.dynamicRouteService.update(gwdefinition);
    }

    @GetMapping("/queryAll")
    public String queryAll() {
        return this.dynamicRouteService.queryAll();
    }
}