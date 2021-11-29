package indi.liangli.spring.webflux;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author liangli
 * 2021/11/11 20:03
 */
public class PersonHandler {


    //正常格式是 PersonService 或者 PersonRepository， 这里的handler 类似controller

    private  final Map<Integer,Person> DB = new HashMap<>();

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        String id = request.pathVariable("id");
        return null;
    }

    public Mono<ServerResponse> listPerson(ServerRequest request) {
        Flux<Person> flux = Flux.fromStream(DB.values().stream());
        return flux.collectList().flatMap(list -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(list));
    }


    public Mono<ServerResponse> createPerson(ServerRequest request) {
        Mono<Person> personMono = request.bodyToMono(Person.class);
        return personMono.flatMap(person -> {
            DB.put(person.getId(),person);
            return Mono.empty();
        }).flatMap(r -> ServerResponse.ok().bodyValue("success"));

    }





}
