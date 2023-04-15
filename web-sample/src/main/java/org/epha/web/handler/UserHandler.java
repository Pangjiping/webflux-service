package org.epha.web.handler;

import org.epha.web.entity.UserEntity;
import org.epha.web.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * 函数式编程方式实现UserHandler
 * 然后需要将其绑定到RouteFunction
 *
 * @author pangjiping
 */
public class UserHandler {

    private final UserService userService;

    public UserHandler(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据id查询用户
     */
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        // 获取路径变量上的id值
        int id = Integer.parseInt(request.pathVariable("id"));

        Mono<UserEntity> userEntityMono = this.userService.getUserById(id);

        // 把Mono进行转换返回
        // 使用flatMap
        return userEntityMono.flatMap(userEntity -> {
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(fromObject(userEntity))
                    .switchIfEmpty(ServerResponse.notFound().build());
        });
    }

    /**
     * 查询所有
     */
    public Mono<ServerResponse> getAllUsers(ServerRequest request) {
        // 调用service得到结果
        Flux<UserEntity> allUser = this.userService.getAllUser();

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(allUser, UserEntity.class);

    }

    /**
     * 添加User
     */
    public Mono<ServerResponse> addUser(ServerRequest request) {
        // 得到User对象
        Mono<UserEntity> mono = request.bodyToMono(UserEntity.class);
        return ServerResponse.ok().build(this.userService.saveUserInfo(mono));
    }

}
