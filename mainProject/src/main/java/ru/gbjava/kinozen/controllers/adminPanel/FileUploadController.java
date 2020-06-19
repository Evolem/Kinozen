package ru.gbjava.kinozen.controllers.adminPanel;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.gbjava.kinozen.dto.ContentDto;
import ru.gbjava.kinozen.exceptions.StorageFileNotFoundException;
import ru.gbjava.kinozen.services.storage.StorageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/storage")
@RequiredArgsConstructor
public class FileUploadController {
//
//    //todo
//
//    private StorageService storageService;
//
//    @Value("${files.storage.video_download}")
//    private Path rootLocation;
//
//
//    /**
//     * Метод ищет список загруженных файлов из StorageService и загружает его в шаблон Thymeleaf.
//     * Он вычисляет ссылку на фактический ресурс с помощью MvcUriComponentsBuilder.
//     *
//     * @param model
//     * @return
//     * @throws IOException
//     */
//
//    @GetMapping("/")
//    public String listUploadedFiles(Model model) throws IOException {
//
//        model.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "serveFile", path.getFileName().toString()).build().toUri().toString())
//                .collect(Collectors.toList()));
//
//        return "uploadForm";
//    }
//
//    /**
//     * Загружает ресурс (если он существует) и отправляет его в браузер для загрузки с
//     * помощью заголовка ответа Content-Disposition.
//     *
//     * @param filename
//     * @return
//     */
//
//    @GetMapping("/files/{filename:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
//
//        Resource file = storageService.loadAsResource(filename);
//        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").build();
//    }
//
//    /**
//     * Обрабатывает входящий фаил, состоящий из нескольких частей, и передает его StorageService для сохранения.
//     *
//     * @param file
//     * @param redirectAttributes
//     * @return
//     */
//
//    @PostMapping("/upload")
//    public void handleFileUpload(@RequestParam("file") MultipartFile file,
//                                 @ModelAttribute ContentDto contentDto,
//                                 RedirectAttributes redirectAttributes,
//                                 HttpServletRequest request,
//                                 HttpServletResponse response) throws IOException {
//
//
//        String filename = storageService.store(file);
//        contentDto.setImageName(filename);
//
//        redirectAttributes.addFlashAttribute("message",
//                "You successfully uploaded " + file.getOriginalFilename() + "!");
//
//        response.sendRedirect(request.getHeader("referer"));
//    }
//
//    @ExceptionHandler(StorageFileNotFoundException.class)
//    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
//        return ResponseEntity.notFound().build();
//    }

}
