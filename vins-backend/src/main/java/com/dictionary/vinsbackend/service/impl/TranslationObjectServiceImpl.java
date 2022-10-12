package com.dictionary.vinsbackend.service.impl;

import com.dictionary.vinsbackend.model.TranslationObject;
import com.dictionary.vinsbackend.repository.TranslationObjectPagingRepository;
import com.dictionary.vinsbackend.repository.TranslationObjectRepository;
import com.dictionary.vinsbackend.service.TranslationObjectService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TranslationObjectServiceImpl implements TranslationObjectService {


    @Autowired
    private TranslationObjectRepository translationObjectRepository;

    @Autowired
    private TranslationObjectPagingRepository translationObjectPagingRepository;


    @Override
    public void save(MultipartFile file) {
        try {
            List<TranslationObject> objects = CSVHelper.csvToTutorials(file.getInputStream());

            List<List<TranslationObject>> partitions = Lists.partition(objects, 500);
            for(List<TranslationObject> list : partitions){
                translationObjectRepository.saveAll(list);
            }


        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public Page<TranslationObject> getPage(Integer numPage, Integer size) {
        Pageable pageable = PageRequest.of(numPage, size);
        return translationObjectPagingRepository.findAll(pageable);
    }
}
