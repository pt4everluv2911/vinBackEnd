package com.dictionary.vinsbackend.service;

import com.dictionary.vinsbackend.model.TranslationObject;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface TranslationObjectService {

    void save(MultipartFile file);

    Page<TranslationObject> getPage(Integer numPage, Integer size);


}
