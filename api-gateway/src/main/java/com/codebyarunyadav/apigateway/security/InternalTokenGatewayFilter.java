package com.codebyarunyadav.apigateway.security; 
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InternalTokenGatewayFilter implements GlobalFilter, Ordered {

    @Autowired
	private final GatewayJwtUtil gatewayJwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();

        if (isPublicPath(path)) {
            return chain.filter(exchange);
        }

        String authHeader = exchange.getRequest()
                .getHeaders()
                .getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        try {
            String userToken = authHeader.substring(7);

            Claims userClaims = gatewayJwtUtil.validateUserToken(userToken);

            String internalToken = gatewayJwtUtil.generateInternalAccessToken(userClaims);

            ServerWebExchange newExchange = exchange.mutate()
                    .request(request -> request.headers(headers -> {
                        headers.remove(HttpHeaders.AUTHORIZATION);
                        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + internalToken);
                    }))
                    .build();

            return chain.filter(newExchange);

        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
    }

    private boolean isPublicPath(String path) {
        return path.contains("/auth/")
                || path.contains("/v3/api-docs")
                || path.contains("/swagger-ui")
                || path.contains("/webjars")
                || path.contains("/actuator/health");
    }

    @Override
    public int getOrder() {
        return -1;
    }
}