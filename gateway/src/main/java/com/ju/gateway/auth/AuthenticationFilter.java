package com.ju.gateway.auth;


import org.apache.logging.log4j.util.Strings;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;




/**
 * Created on 2020/8/15 9:35 下午
 *
 * @author barry
 * Description: 身份认证
 */

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory {
//
//    @Autowired
//    private Session session;

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            // 1. 获取token
            String token = request.getHeaders().getFirst("token");
            String userId = request.getHeaders().getFirst("userId");


             // 简单认证，token不为空即可，复杂认证需要解析token中的用户名
            if (Strings.isEmpty(token)) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }


//            // 2. 验证用户是否已登陆
//            LoginUser loginUser = this.session.getSession(token);
//            if (loginUser == null) {
//                response.setStatusCode(HttpStatus.UNAUTHORIZED);
//                return response.setComplete();
//            }
//
//            // 3. 将用户名传递给后端服务, 下一个filter提取其中的数据
            ServerWebExchange build;
            try {
                ServerHttpRequest host = exchange.getRequest().mutate()
                        .header("X-User-Name",token)
                        // 中文字符需要编码
                        .header("X-Real-Name", URLEncoder.encode("陈", "utf-8"))
                        .build();
                build = exchange.mutate().request(host).build();
            } catch (UnsupportedEncodingException e) {
                build = exchange;
            }
            return chain.filter(build);
        };
    }
}