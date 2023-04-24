package com.example.ms_community.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_community.model.Community;
import com.example.ms_community.repository.CommunityRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/communities")
public class CommunityController {
    @Autowired
    private CommunityRepository communityRepository;
    
    @Operation(summary = "Adds a community do the database")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "201", description = "Community created", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Community.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid data", 
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
            content = @Content)
        })
    @PostMapping("/add")
    public Community addNewCommunity(@RequestBody Community newCommunity) {
        Community community = new Community();
        community.setCommunityName(newCommunity.getCommunityName());
        community.setCommunityDescription(newCommunity.getCommunityDescription());
        community.setCommunityCreatedOnDate(newCommunity.getCommunityCreatedOnDate());
        community.setCommunityCreatorId(newCommunity.getCommunityCreatorId());
        community.setCommunityLogo(newCommunity.getCommunityLogo());
        community.setCommunityHeaderImage(newCommunity.getCommunityHeaderImage());

        communityRepository.save(community);
        return community;
    }

    @Operation(summary = "Gets all communities")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found communities", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Community.class)) }),
        @ApiResponse(responseCode = "404", description = "The communities were not found", 
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
            content = @Content)
        })
    @GetMapping("/view/all")
    public @ResponseBody Iterable<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    @Operation(summary = "Get a community by its id")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found the community", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Community.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content), 
        @ApiResponse(responseCode = "404", description = "The community was not found", 
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
            content = @Content)
        })
    @GetMapping("/view/{id}")
    public Optional<Community> getCommunityById(@PathVariable Integer id) {
        return communityRepository.findById(id);
    }

    @Operation(summary = "Edits a community by its id")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Community updated", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Community.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content), 
        @ApiResponse(responseCode = "404", description = "The community was not found", 
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
            content = @Content)
        })
    @PutMapping("/edit/{id}")
    public String updateCommunity(@RequestBody Community updateCommunity, @PathVariable Integer id) {
        return communityRepository.findById(id)
            .map(community -> {
                community.setCommunityName(updateCommunity.getCommunityName());
                community.setCommunityDescription(updateCommunity.getCommunityDescription());
                community.setCommunityCreatedOnDate(updateCommunity.getCommunityCreatedOnDate());
                community.setCommunityCreatorId(updateCommunity.getCommunityCreatorId());
                community.setCommunityLogo(updateCommunity.getCommunityLogo());
                community.setCommunityHeaderImage(updateCommunity.getCommunityHeaderImage());
                return "Community details have been changed.";
            }).orElseGet(() -> {
                return "This community was not found in the database";
            });
    }

    @Operation(summary = "Deletes a community by its id")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Community deleted", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Community.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content), 
        @ApiResponse(responseCode = "404", description = "The community was not found", 
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal server error", 
            content = @Content)
        })
    @DeleteMapping("/delete/{id}")
    public String deleteCommunity(@PathVariable("id") Integer id) {
        communityRepository.deleteById(id);
        return "The community id: " + id + " has been deleted.";
    }
}
