package com.sucre.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
@Slf4j
public class TestRoutePredicateFactory extends AbstractRoutePredicateFactory<TestRoutePredicateFactory.Config> {

    public static final String NAME_KEY = "name";
    public static final String REGEXP_KEY = "regexp";

    public TestRoutePredicateFactory(){
        super(Config.class);
    }


    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            List<String> strings = exchange.getRequest().getQueryParams().get(config.name);
            return true;
        };
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(NAME_KEY, REGEXP_KEY);
    }

    @Validated
    public static class Config {

        @NotEmpty
        private String name;
        @NotEmpty
        private String regexp;

        public String getName() {
            return name;
        }

        public TestRoutePredicateFactory.Config setName(String name) {
            this.name = name;
            return this;
        }

        public String getRegexp() {
            return regexp;
        }

        public TestRoutePredicateFactory.Config setRegexp(String regexp) {
            this.regexp = regexp;
            return this;
        }
    }
}
