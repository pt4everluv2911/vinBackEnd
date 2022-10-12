package com.dictionary.vinsbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "translation_object")
public class TranslationObject {

    @Id
    private String id;
    @Column(name="text", length = 10485760)
    private String text;
    @Column(name="url")
    private String url;
    @Column(name="translation_id")
    private String translationId;
    @Column(name="translation_text", length = 10485760)
    private String translationText;

    public TranslationObject(){}

    public TranslationObject(String id, String text, String url, String translationId, String translationText) {
        this.id = id;
        this.text = text;
        this.url = url;
        this.translationId = translationId;
        this.translationText = translationText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTranslationId() {
        return translationId;
    }

    public void setTranslationId(String translationId) {
        this.translationId = translationId;
    }

    public String getTranslationText() {
        return translationText;
    }

    public void setTranslationText(String translationText) {
        this.translationText = translationText;
    }
}
