package com.example.ms_community.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ms_community.model.Community;
import com.example.ms_community.repository.CommunityRepository;

@RestController
@RequestMapping("/communities")
public class CommunityController {
    @Autowired
    private CommunityRepository communityRepository;
    
    
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

    @GetMapping("/view/all")
    public @ResponseBody Iterable<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

    @GetMapping("/view/{id}")
    public Optional<Community> getCommunityById(@PathVariable Integer id) {
        return communityRepository.findById(id);
    }

    @GetMapping("/edit/{id}")
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

    @GetMapping("/delete/{id}")
    public String deleteCommunity(@PathVariable("id") Integer id) {
        communityRepository.deleteById(id);
        return "The community id: " + id + " has been deleted.";
    }
}
