package org.epha.web;

import org.epha.web.handler.UserHandler;
import org.epha.web.service.UserService;
import org.epha.web.service.impl.UserServiceImpl;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.netty.http.server.HttpServer;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RouterFunctions.toHttpHandler;

/**
 * @author pangjiping
 */
public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.createReactorServer();
        System.out.println("enter to exit");
        System.in.read();
    }

    /**
     * 创建Router路由
     */
    public RouterFunction<ServerResponse> routingFunction() {
        UserService userService = new UserServiceImpl();
        UserHandler userHandler = new UserHandler(userService);

        //设置路由
        return RouterFunctions.route()
                .GET("/function/user/{id}", RequestPredicates.accept(APPLICATION_JSON), userHandler::getUserById)
                .GET("/function/users", RequestPredicates.accept(APPLICATION_JSON), userHandler::getAllUsers)
                .POST("/function/user", RequestPredicates.accept(APPLICATION_JSON), userHandler::addUser).build();
    }

    /**
     * 创建服务器完成适配
     */
    public void createReactorServer() {
        // 路由和handler适配
        RouterFunction<ServerResponse> route = routingFunction();
        HttpHandler httpHandler = toHttpHandler(route);

        ReactorHttpHandlerAdapter handlerAdapter = new ReactorHttpHandlerAdapter(httpHandler);

        // 创建服务器
        HttpServer httpServer = HttpServer.create();
        httpServer.handle(handlerAdapter).port(8081).bindNow();
    }

}
