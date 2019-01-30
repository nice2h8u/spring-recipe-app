package com.nicetoh8u.springrecipeapp.Service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    void save(Long id , MultipartFile file);
}
