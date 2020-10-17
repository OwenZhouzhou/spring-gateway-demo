package com.zhou.gateway.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program gatewaydemo
 * @description: 启东时自动同步持久化层的数据到springgateway中
 * @author: zhou
 * @create: 2019/12/22 14:05
 */
@Component
@Slf4j
public final class SyncRouteUtil {

    @PostConstruct
    public void syncData2Gateway() {
        //TODO 同步持久化层的数据到springgateway中
        log.info("-----begin sync route data");
    }
}
