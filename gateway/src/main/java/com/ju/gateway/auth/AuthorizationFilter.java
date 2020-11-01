package com.ju.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import com.ju.gateway.service.AuthService;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created on 2020/8/24 9:31 上午
 *
 * @author barry
 * Description: 鉴权
 */
@Component
public class AuthorizationFilter extends AbstractGatewayFilterFactory {

    @Autowired
    private AuthService authService;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            String uri = request.getURI().getPath();
            String method = request.getMethodValue();

            // 1.从AuthenticationFilter中获取userName
            String key = "X-User-Name";
            if (!request.getHeaders().containsKey(key)) {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }

            String userName = Objects.requireNonNull(request.getHeaders().get(key)).get(0);

            // 2.验证权限
//            if (!session.checkPermissions(userName, uri, method)) {
//                log.info("用户：{}, 没有权限", userName);
//                response.setStatusCode(HttpStatus.FORBIDDEN);
//                return response.setComplete();
//            }

            // 使用数据库的数据鉴权
            List<String> auths = authService.getAuths(Integer.parseInt(userName));


            if (!auths.contains(uri)) {
                response.setStatusCode(HttpStatus.FORBIDDEN);
                return response.setComplete();
            }

            return chain.filter(exchange);
        };
    }
}
