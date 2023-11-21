package com.example.gateway;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GatewayRouter extends RouteBuilder {

    CamelContext context;

    @Autowired
    public GatewayRouter(CamelContext context) {
        this.context = context;
    }

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .enableCORS(true);


        rest().get("/authenticate")
                .route()
                .serviceCall("auth-service/authenticate").log("authenticate called");

//        rest().get("/bye")
//                .route()
//                .serviceCall("bye-service/greet");

    }
}
