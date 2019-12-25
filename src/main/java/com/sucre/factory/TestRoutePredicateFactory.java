package com.sucre.factory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
@Slf4j
public class TestRoutePredicateFactory extends AbstractRoutePredicateFactory<TestRoutePredicateFactory.Config> {

    public static final String NAME_KEY = "name";
    public static final String REGEXP_KEY = "value";

    public TestRoutePredicateFactory(){
        super(Config.class);
    }


    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return exchange -> {
            List<String> strings = exchange.getRequest().getQueryParams().get(config.name);
            return Objects.nonNull(strings) && strings.contains(config.value);
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
        private String value;

        public String getName() {
            return name;
        }

        public TestRoutePredicateFactory.Config setName(String name) {
            this.name = name;
            return this;
        }

        public String getValue() {
            return value;
        }

        public TestRoutePredicateFactory.Config setValue(String value) {
            this.value = value;
            return this;
        }
    }
}
