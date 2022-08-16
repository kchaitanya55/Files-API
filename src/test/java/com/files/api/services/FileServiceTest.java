package com.files.api.services;

import com.files.api.model.FileDetails;
import com.files.api.repositories.FilesRepository;
import com.files.api.services.impl.FilesServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {
    @InjectMocks
    private FilesServiceImpl filesService;

    @Mock
    private FilesRepository filesRepository;

    @Test
    void getFileDetailsTest(){
        List<FileDetails> fileDetailsList=new ArrayList<>();
        FileDetails file=new FileDetails();
        fileDetailsList.add(file);
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("name", contains().ignoreCase())
                .withMatcher("fileType",contains().ignoreCase())
                .withMatcher("location",contains().ignoreCase());
        FileDetails fileDetails = FileDetails
                .builder()
                .name(file.getName())
                .fileType(file.getFileType())
                .size(file.getSize())
                .location(file.getLocation())
                .build();
        when(filesRepository.findAll(Example.of(fileDetails,matcher))).thenReturn(fileDetailsList);
        List<FileDetails> list=filesService.getFileDetails(file.getName(),file.getFileType(),file.getSize(),file.getLocation());
        Assertions.assertEquals(1,list.size());

    }
}
