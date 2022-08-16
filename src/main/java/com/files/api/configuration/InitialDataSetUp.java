package com.files.api.configuration;

import com.files.api.model.FileDetails;
import com.files.api.repositories.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.util.*;

@Component
public class InitialDataSetUp {
    @Autowired
    private FilesRepository filesRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws IOException {
        //Scanning only D: as it takes lots of time
        Arrays.stream(File.listRoots()).filter(d->d.getAbsolutePath().startsWith("D")).map(File::toPath).forEach(path ->
        {
            try {
                Files.walkFileTree(path,
                        new HashSet<FileVisitOption>(Collections.singletonList(FileVisitOption.FOLLOW_LINKS)),
                        Integer.MAX_VALUE, new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
                                    throws IOException {
                                String mimetype = Files.probeContentType(file);
                                if (mimetype != null && mimetype.split("/")[0].equals("image")) {
                                    BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                                    FileDetails fileDetails = new FileDetails();
                                    fileDetails.setName(file.getFileName().toString());
                                    fileDetails.setFileType(mimetype);
                                    fileDetails.setLocation(file.toFile().getAbsolutePath());
                                    fileDetails.setSize(attr.size());
                                    fileDetails.setCreationTime(new Timestamp(attr.creationTime().toMillis()));
                                    fileDetails.setLastModifiedTime(new Timestamp(attr.lastModifiedTime().toMillis()));
                                    fileDetails.setLastModifiedTime(new Timestamp(attr.lastAccessTime().toMillis()));
                                    fileDetails.setReadable(file.toFile().canRead());
                                    fileDetails.setWriteable(file.toFile().canWrite());
                                    filesRepository.save(fileDetails);

                                }
                                return FileVisitResult.CONTINUE;
                            }

                            @Override
                            public FileVisitResult visitFileFailed(Path file, IOException e)
                                    throws IOException {
                                System.err.printf("Visiting failed for %s\n", file);
                                return FileVisitResult.SKIP_SUBTREE;
                            }

                            @Override
                            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                                    throws IOException {
                                return FileVisitResult.CONTINUE;
                            }
                        });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("Application ready to use");

    }
}
