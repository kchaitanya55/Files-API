package com.files.api.services;

import com.files.api.model.FileDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FilesService {
    List<FileDetails> getFileDetails(String name, String fileType, Long size, String location);
}
