package datpt.spring.config;

import datpt.spring.security.filter.TokenBlacklistFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    @Autowired
    private TokenBlacklistFilter tokenBlacklistFilter;

//    @Bean
//    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route(r -> r.path("/api/**")
//                        .filters(f -> f.filter(tokenBlacklistFilter)).uri("lb://gateway-service")
//                )
//                .build();
//    }
}
