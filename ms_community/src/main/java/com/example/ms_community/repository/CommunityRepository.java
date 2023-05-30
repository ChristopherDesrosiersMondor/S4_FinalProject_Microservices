package com.example.ms_community.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ms_community.model.Community;;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findBycommunityName(String name);
}