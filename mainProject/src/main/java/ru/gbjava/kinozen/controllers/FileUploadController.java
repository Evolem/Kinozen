package ru.gbjava.kinozen.controllers;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gbjava.kinozen.exceptions.StorageFileNotFoundException;
import ru.gbjava.kinozen.services.storage.StorageService;

@Controller
@RequestMapping("/storage")
@RequiredArgsConstructor
public class FileUploadController {

    //todo необходимо настроить безопасность для этого контроллера!

    private final StorageService storageService;


    /**
     * Метод ищет список загруженных файлов из StorageService и загружает его в шаблон Thymeleaf.
     * Он вычисляет ссылку на фактический ресурс с помощью MvcUriComponentsBuilder.
     *
     * @param model
     * @return
     * @throws IOException
     */

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    /**
     * Загружает ресурс (если он существует) и отправляет его в браузер для загрузки с
     * помощью заголовка ответа Content-Disposition.
     *
     * @param filename
     * @return
     */

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    /**
     * Обрабатывает входящий фаил, состоящий из нескольких частей, и передает его StorageService для сохранения.
     *
     * @param file
     * @param redirectAttributes
     * @return
     */

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "uploadForm";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
