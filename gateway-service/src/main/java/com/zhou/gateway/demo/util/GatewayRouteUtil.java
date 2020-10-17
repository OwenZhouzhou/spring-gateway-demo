package com.zhou.gateway.demo.util;

import com.alibaba.fastjson.JSON;
import com.zhou.gateway.demo.pojo.GatewayFilterDefinition;
import com.zhou.gateway.demo.pojo.GatewayPredicateDefinition;
import com.zhou.gateway.demo.pojo.GatewayRouteDefinition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.*;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program gatewaydemo
 * @description:
 * @author: zhou
 * @create: 2019/12/21 14:56
 */
@Component
@Slf4j
public class GatewayRouteUtil extends InMemoryRouteDefinitionRepository implements ApplicationEventPublisherAware {

    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter;

    private ApplicationEventPublisher publisher;

    @Autowired
    private
    RouteDefinitionLocator routeDefinitionLocator;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public String queryAllMermoy() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();

        super.getRouteDefinitions().subscribe(
                t -> routeDefinitions.add(t),
                t -> log.error(t.getMessage(), t),
                () -> log.info("Completed!"));
        routeDefinitionLocator.getRouteDefinitions().subscribe(
                t -> routeDefinitions.add(t),
                t -> log.error(t.getMessage(), t),
                () -> log.info("Completed!"));

        return JSON.toJSONString(routeDefinitions);
    }


    /**
     * 返回springCloud gateway 的路由定义
     *
     * @param gwdefinition
     * @return RouteDefinition
     */
    private RouteDefinition resloveRouteDefinition(GatewayRouteDefinition gwdefinition) {

        RouteDefinition definition = new RouteDefinition();

        List<PredicateDefinition> Predicates;

        List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gwdefinition.getPredicates();

        if (!CollectionUtils.isEmpty(gatewayPredicateDefinitionList)) {

            if (gatewayPredicateDefinitionList.stream().filter(t -> t.isSupportPredicate()).collect(Collectors.toList()).isEmpty()) {
                throw new IllegalArgumentException("not support this predicate ,please check");
            }
            Predicates = gatewayPredicateDefinitionList.stream().map(gatewayPredicateDefinition -> {
                PredicateDefinition predicateDefinition = new PredicateDefinition();
                BeanUtils.copyProperties(gatewayPredicateDefinition, predicateDefinition);
                return predicateDefinition;
            }).collect(Collectors.toList());
            definition.setPredicates(Predicates);
        }

        List<GatewayFilterDefinition> gatewayFilterDefinitions = gwdefinition.getFilters();

        List<FilterDefinition> filters;
        if (!CollectionUtils.isEmpty(gatewayFilterDefinitions)) {

            if (gatewayFilterDefinitions.stream().filter(t -> t.isSupportFilter()).collect(Collectors.toList()).isEmpty()) {
                throw new IllegalArgumentException("not support this filter ,please check");
            }
            filters = gatewayFilterDefinitions.stream().map(gatewayFilterDefinition -> {
                FilterDefinition filterDefinition = new FilterDefinition();
                BeanUtils.copyProperties(gatewayFilterDefinition, filterDefinition);
                return filterDefinition;
            }).collect(Collectors.toList());
            definition.setFilters(filters);
        }

        definition.setId(gwdefinition.getId());
        URI uri = UriComponentsBuilder.fromHttpUrl(gwdefinition.getUri()).build().toUri();
        definition.setUri(uri);
        return definition;
    }


    public String add(GatewayRouteDefinition gwdefinition) {
        this.addRoute(gwdefinition);
        return "success";
    }

    private void addRoute(GatewayRouteDefinition gwdefinition) {
        RouteDefinition definition = this.resloveRouteDefinition(gwdefinition);
        routeDefinitionWriter.save(Mono.just(definition)).subscribe();
        this.publisher.publishEvent(new RefreshRoutesEvent(this));
    }

    public String update(GatewayRouteDefinition gwdefinition) throws Exception {
        try {
            this.routeDefinitionWriter.delete(Mono.just(gwdefinition.getId())).subscribe();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception("update fail,not find route  routeId: " + gwdefinition.getId());
        }
        try {
            this.addRoute(gwdefinition);
            return "success";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception("update route fail" + e.getMessage());
        }
    }


    public String delete(String routeId) throws Exception {

        try {
            this.routeDefinitionWriter.delete(Mono.just(routeId)).subscribe();
            this.publisher.publishEvent(new RefreshRoutesEvent(this));
            return "delete success";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new Exception("delete fail" + e.getMessage());
        }
    }


}
