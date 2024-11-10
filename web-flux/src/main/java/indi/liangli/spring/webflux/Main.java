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
 * webflux 可以使用servlet 作为服务器，也可以使用netty作为服务，
 * 使用netty作为服务器使用 ReactorHttpHandlerAdapter，同时引入 reactor-netty jar包
 *
 * spring webflux 主要控制器类 DispatcherHandler
 * 主要HandlerMapping实现RequestMappingHandlerMapping用于 @RequestMapping注释方法、
 * RouterFunctionMapping功能端点路由以及SimpleUrlHandlerMappingURI 路径模式和实例的显式注册WebHandler
 *
 * 我们可以通过@Bean 方法配置一个DispatcherHandler，以及一些 RouterFunctionMapping。
 * 最后在通过WebHttpHandler.builder 来构建一个Httphandler
 * 最后在启动起来
 *
 * @Configuration
 * @EnableWebFlux
 * @author liangli
 * 2021/11/11 19:37
 */
public class Main {

    public static void main(String[] args) throws IOException {

        //1.创建一个处理请求的handler
        PersonHandler personHandler = new PersonHandler();
        //2.构建一个路由对象并接受handler
        PersonRouter routerFunction = new PersonRouter(personHandler);
        //3.绑定路由与handler方法映射
        RouterFunction<ServerResponse> route = routerFunction.personRouter();
        //4.获取一个httpHandler （是一个抽象）
        HttpHandler httpHandler = RouterFunctions.toHttpHandler(route);
        //5.创建handler-adapter
        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(httpHandler);
        //6.启动服务
        HttpServer.create().host("localhost").port(8081).handle(adapter).bind().block();
        System.out.println("服务已启动，监听端口：8081 。。。。");
        System.in.read();

    }
}
