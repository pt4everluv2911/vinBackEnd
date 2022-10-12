package com.dictionary.vinsbackend.repository;

import com.dictionary.vinsbackend.model.TranslationObject;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationObjectPagingRepository extends PagingAndSortingRepository<TranslationObject, String> {
}
