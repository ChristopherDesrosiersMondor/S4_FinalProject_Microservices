package com.example.ms_account.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.ms_account.model.Moderator;
import com.example.ms_account.repository.ModeratorRepository;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/moderators")
public class ModeratorController {

    @Autowired
    ModeratorRepository moderatorRepository;

    @GetMapping("view/all")
    public ResponseEntity<List<Moderator>> getAllModerators() {
        try {
            List<Moderator> moderatorsList = new ArrayList<Moderator>();
            moderatorRepository.findAll().forEach(moderatorsList::add);

            if(moderatorsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(moderatorsList, HttpStatus.OK);
        }
        catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("view/by_user/{id}")
    public ResponseEntity<List<Moderator>> getModeratorsByUserId(@PathVariable ("id") long id) {
        try {
            List<Moderator> moderatorData = new ArrayList<Moderator>();
            moderatorRepository.findBymoderatorUserId(id).forEach(moderatorData::add);

            if (moderatorData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(moderatorData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
