package com.files.api.services.impl;

import com.files.api.model.FileDetails;
import com.files.api.repositories.FilesRepository;
import com.files.api.services.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Service
public class FilesServiceImpl implements FilesService {
    @Autowired
    private FilesRepository filesRepository;

    @Override
    public List<FileDetails> getFileDetails(String name, String fileType, Long size, String location) {
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("fileType",contains().ignoreCase())
                .withMatcher("location",contains().ignoreCase());
        FileDetails fileDetails = FileDetails
                .builder()
                .name(name)
                .fileType(fileType)
                .size(size)
                .location(location)
                .build();
        return filesRepository.findAll(Example.of(fileDetails,matcher));
    }
}
