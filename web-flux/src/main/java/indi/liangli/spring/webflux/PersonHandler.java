package indi.liangli.spring.webflux;

import com.sun.xml.internal.bind.v2.model.core.ID;
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
    {
        DB.put(1,new Person(1,"张三"));
    }

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        String id = request.pathVariable("id");
        Person person = DB.get(id);
        return Mono.justOrEmpty(person).flatMap(r -> ServerResponse.ok().bodyValue(r));
    }

    public Mono<ServerResponse> listPerson(ServerRequest request) {
        Flux<Person> flux = Flux.fromStream(DB.values().stream());
        return flux.collectList().flatMap(list -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(list));
    }


    public Mono<ServerResponse> createPerson(ServerRequest request) {
        //例如
        // bodyToMono 把请求体中转为单个对象
        // bodyToFlux 把请求中数据转为数组
        Mono<Person> personMono = request.bodyToMono(Person.class);
        return personMono.flatMap(person -> {
            DB.put(person.getId(), person);
            return Mono.just("success");
        }).flatMap(r -> ServerResponse.ok().bodyValue(r));


    }





}
