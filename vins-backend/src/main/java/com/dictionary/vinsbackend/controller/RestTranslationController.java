package com.dictionary.vinsbackend.controller;
import com.dictionary.vinsbackend.model.TranslationObject;
import com.dictionary.vinsbackend.service.TranslationObjectService;
import com.dictionary.vinsbackend.service.impl.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class RestTranslationController {

    @Autowired
    private TranslationObjectService translationObjectService;

    @PostMapping("upload")
    public ResponseEntity<String>  saveData(@RequestParam("file") MultipartFile file){
        String message = "";
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                translationObjectService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("getPage")
    public Page<TranslationObject> getPage(@RequestParam Integer numPage, @RequestParam Integer size){
        return translationObjectService.getPage(numPage, size);
    }

}
