package com.example.gateway;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpMethods;
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

        from("rest://get:authenticate")
//                .route()
                .routeId("get_authenticate")
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .removeHeader("CamelHttp*")
                .removeHeader(Exchange.HTTP_PATH)
                .removeHeader(Exchange.HTTP_URI)
                .log("authenticate called")
                .serviceCall("authentication-server/authenticate");

//        rest().get("/authenticate")
//                .route()
//                .serviceCall("authentication-server/authenticate").log("authenticate called");

//        rest().get("/bye")
//                .route()
//                .serviceCall("bye-service/greet");

    }
}
