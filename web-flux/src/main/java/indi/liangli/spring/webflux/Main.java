package indi.liangli.spring.webflux;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

/**
 * @author liangli
 * 2021/11/11 19:37
 */
@Configuration
@EnableWebFlux
public class Main {

    public static void main(String[] args) throws IOException {

        PersonHandler personHandler = new PersonHandler();
        PersonRouter routerFunction = new PersonRouter(personHandler);
        RouterFunction<ServerResponse> route = routerFunction.personRouter();
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);

        HttpServer.create().host("localhost").port(8081).handle(adapter).bind().block();
        System.in.read();

    }
}
