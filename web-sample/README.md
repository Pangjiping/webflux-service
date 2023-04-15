# WebFlux编程示例代码

- **基于注解**，类似于SpringMVC。[示例](src/main/java/org/epha/web/controller/AnnotatedController.java)
- **基于函数式编程**
    - `HandlerFunction`处理业务代码，类似于controller。[示例](src/main/java/org/epha/web/handler/UserHandler.java)
    - `RouterFunction`绑定路由、自定义`HttpServer`并启动。[示例](src/main/java/org/epha/web/Server.java)
    - 利用`WebClient`进行网络调用。[示例](src/main/java/org/epha/web/Client.java)
- **设置默认时区**。[示例](src/main/java/org/epha/web/configuration/LocaleConfiguration.java)
- **设置默认语言环境**。[示例](src/main/java/org/epha/web/configuration/TimeZoneConfiguration.java)
- **静态资源访问**。[示例](src/main/resources/application.properties)
    - 默认放在`resource/static`目录下的都可以直接通过路径访问。http://localhost:8081/images/reactive.png
    - 可以自定义uri pattern，比如增加一个前缀asserts。http://localhost:8081/assets/images/reactive.png
    - 配置http静态资源缓存
- **配置CORS**。[示例](src/main/java/org/epha/web/configuration/CorsConfiguration.java)
- **mapstruct使用**。[示例](src/main/java/org/epha/web/mapper/PlayerMapper.java)
- **下载文件**。[示例](src/main/java/org/epha/web/controller/DownloadController.java)
- **上传文件**。[示例](src/main/java/org/epha/web/controller/UploadController.java)