package org.epha.web.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pangjiping
 */
@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    private static final String UPLOAD_DIRECTORY = "/upload";

    @PostMapping(value = "/file")
    public Mono<Map> upload(@RequestPart("file") FilePart filePart,
                            FormData formData) {
        log.debug("formData --> {}", formData);

        File dir = new File(UPLOAD_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, filePart.filename());

        return filePart.transferTo(file)
                .then(Mono.fromCallable(() -> {
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("name", file.getName());
                    map.put("lastModified", file.lastModified());
                    map.put("size", file.length());
                    return map;
                }));
    }


    @Data
    public static class FormData {
        private String description;
    }

}
