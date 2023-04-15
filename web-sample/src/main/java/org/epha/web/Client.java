package org.epha.web;

import org.epha.web.entity.UserEntity;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author pangjiping
 */
public class Client {
    public static void main(String[] args) {
        // 调用服务器地址
        WebClient webClient = WebClient.create("http://localhost:8081");

        // 根据id查询
        String id = "1";
        UserEntity userRes = webClient.get().uri("/function/users/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(UserEntity.class)
                .block();

        System.out.println(userRes.getName());
    }
}
