package com.avito;

import org.hibernate.internal.util.config.ConfigurationException;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

@Controller
@RequestMapping("/image")
public class ImageController {

    final Logger logger = Logger.getLogger(ImageController.class.getName());
    private final String[] arrayParametrs = new String[1];

    @GetMapping
    @ResponseBody
    protected JSONObject getImage() {
        JSONObject object = new JSONObject();
        String fileway = arrayParametrs[0];
        if (fileway == null) {
            fileway = "/pictures/emptyPhoto.JPG";
        } else {
            fileway = "/pictures/" + fileway.substring(fileway.lastIndexOf("\\") + 1);
        }
        object.put("image", fileway);
        return object;

    }

    @PostMapping
    protected String writeImage(@RequestParam("uploadFile") MultipartFile mFile) throws IOException {
        Path currentRelativePath = Paths.get("");
        String rootPath = currentRelativePath.toAbsolutePath().toString();
        String fileName = mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf("\\") + 1);
        if (!mFile.isEmpty()) {
            File file = new File(arrayParametrs[0] = rootPath + "\\src\\main\\resources\\META-INF\\resources\\pictures\\" + fileName);
            mFile.transferTo(file);
        }
        return "createCar";
    }
}