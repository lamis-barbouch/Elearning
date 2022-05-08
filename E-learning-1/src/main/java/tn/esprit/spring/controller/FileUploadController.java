package tn.esprit.spring.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tn.esprit.spring.dao.DatabaseFileRepository;
import tn.esprit.spring.entity.DatabaseFile;
import tn.esprit.spring.payload.Response;
import tn.esprit.spring.service.DatabaseFileService;

@RestController
@RequestMapping("/file")

public class FileUploadController {

    @Autowired
    private DatabaseFileService fileStorageService;
    
    @Autowired DatabaseFileRepository  filerepo;
    
    
    @GetMapping("/files")
    public List< DatabaseFile> getAllFiles() {
        return filerepo.findAll();
    }

    @PostMapping("/upload")
    public Response uploadFile(@RequestParam("files") MultipartFile file) {
    	DatabaseFile fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri, file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles")
    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }
}