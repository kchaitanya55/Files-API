package com.files.api.contoller;

import com.files.api.model.FileDetails;
import com.files.api.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FilesController {
    @Autowired
    private FilesService filesService;

    @GetMapping("/search")
    public List<FileDetails> getFileDetails(@RequestParam(value = "name", required = false) String name,@RequestParam(value = "fileType",required = false) String fileType,@RequestParam(value = "size",required = false) Long size,@RequestParam(value = "location",required = false) String location){
        return filesService.getFileDetails(name,fileType,size,location);
    }

}
