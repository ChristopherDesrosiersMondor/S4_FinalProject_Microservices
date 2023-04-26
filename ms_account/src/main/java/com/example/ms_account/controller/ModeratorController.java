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

    @Operation(summary = "Get all moderators")
    @ApiResponses(value = { 
        @ApiResponse (responseCode = "200", description = "Found the moderators", 
        content = {@Content (mediaType = "application/json",
        schema = @Schema (implementation = Moderator.class))}),
        @ApiResponse(responseCode = "404", description = "Could not find the moderators", 
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", 
        content = @Content) })

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


    @Operation(summary = "Get moderator by Id")
    @ApiResponses(value = { 
        @ApiResponse (responseCode = "200", description = "Moderator found", 
        content = {@Content (mediaType = "application/json",
        schema = @Schema (implementation = Moderator.class))}),
        @ApiResponse(responseCode = "404", description = "Moderator not found", 
            content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid id", 
        content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", 
        content = @Content) })

    @GetMapping("view/{id}")
    public ResponseEntity<Moderator> getModeratorById(@PathVariable("id") long id) {
        Optional<Moderator> moderatorData = moderatorRepository.findById(id);

        if(moderatorData.isPresent()) {
            return new ResponseEntity<>(moderatorData.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Get list of moderators by UserId")
    @ApiResponses(value = { 
        @ApiResponse (responseCode = "200", description = "List of moderators found", 
        content = {@Content (mediaType = "application/json",
        schema = @Schema (implementation = Moderator.class))}),
        @ApiResponse(responseCode = "404", description = "Moderators not found", 
            content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid id", 
        content = @Content),
        @ApiResponse(responseCode = "204", description = "No moderator for this user ID", 
        content = @Content), 
        @ApiResponse(responseCode = "500", description = "Internal Server Error", 
        content = @Content) })

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

    @GetMapping("view/by_community/{id}")
    public ResponseEntity<List<Moderator>> getModeratorsByCommunityId(@PathVariable ("id") long id) {
        try {
            List<Moderator> moderatorData = new ArrayList<Moderator>();
            moderatorRepository.findBymoderatorComId(id).forEach(moderatorData::add);

            if (moderatorData.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(moderatorData, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "Add a moderator")
    @ApiResponses(value = { 
        @ApiResponse (responseCode = "201", description = "Moderator added", 
        content = {@Content (mediaType = "application/json",
        schema = @Schema (implementation = Moderator.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid data", 
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", 
        content = @Content) })

    @PostMapping("/add")
    public ResponseEntity<Moderator> createModerator(@RequestBody Moderator moderator) {
        try {
            Moderator moderatorToAdd = moderatorRepository.save(new Moderator(moderator.getModeratorUserId(), moderator.getModeratorComId()));
            return new ResponseEntity<>(moderatorToAdd, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary = "Edit moderator")
    @ApiResponses(value = { 
        @ApiResponse (responseCode = "200", description = "Moderator edited", 
        content = {@Content (mediaType = "application/json",
        schema = @Schema (implementation = Moderator.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid data", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Moderator not found", 
        content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", 
        content = @Content) })

    @PutMapping("/edit/{id}")
    public ResponseEntity<Moderator> updateModerator(@PathVariable("id") long id, @RequestBody Moderator moderator) {
        Optional<Moderator> moderatorData = moderatorRepository.findById(id);

        if(moderatorData.isPresent()) {
            Moderator modifiedModerator = moderatorData.get();
            modifiedModerator.setModeratorUserId(moderator.getModeratorUserId());
            modifiedModerator.setModeratorComId(moderator.getModeratorComId());

            return new ResponseEntity<>(moderatorRepository.save(modifiedModerator), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Delete a moderator")
    @ApiResponses(value = { 
        @ApiResponse (responseCode = "200", description = "Moderator deleted", 
        content = {@Content (mediaType = "application/json",
        schema = @Schema (implementation = Moderator.class))}),
        @ApiResponse(responseCode = "405", description = "Invalid data", 
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Moderator not found", 
        content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", 
        content = @Content) })

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteModerator(@PathVariable("id") long id) {
        Optional<Moderator> moderatorData = moderatorRepository.findById(id);
        if(moderatorData.isPresent()) {
            try {
                moderatorRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}
