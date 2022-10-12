package com.dictionary.vinsbackend.repository;

import com.dictionary.vinsbackend.model.TranslationObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationObjectRepository extends JpaRepository<TranslationObject, String> {
}
