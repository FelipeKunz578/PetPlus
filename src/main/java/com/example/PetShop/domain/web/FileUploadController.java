package com.example.PetShop.domain.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class FileUploadController {

    @Value("${upload.path}")
    private String uploadPath;

    @PostMapping(value = "/upload", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body(java.util.Map.of("error", "Por favor, selecione um arquivo para fazer upload."));
        }

        try {
            // Assegura que o diretório de destino exista e usa resolve para montar o caminho
            Path dir = Paths.get(uploadPath != null ? uploadPath : "");
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }

            Path path = dir.resolve(file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            Files.write(path, bytes);

            return ResponseEntity.ok(java.util.Map.of("fileName", file.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(java.util.Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/uploadArquivo")
    public String upload(){
        return "uploadArquivo";
    }
}
