package indi.liangli.spring.webflux;


import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

/**
 *
 * @author liangli
 * 2021/11/11 20:01
 */

public class PersonRouter {



    private final PersonHandler handler;

    public PersonRouter(PersonHandler handler) {this.handler = handler;}


    public RouterFunction<ServerResponse> personRouter() {
        RouterFunction<ServerResponse> build = RouterFunctions.route()
                .GET("/person/{id}", handler::getPerson)
                .GET("/person", handler::listPerson)
                .POST("/person", accept(MediaType.APPLICATION_JSON),handler::createPerson).build();
        return build;
    }

}
