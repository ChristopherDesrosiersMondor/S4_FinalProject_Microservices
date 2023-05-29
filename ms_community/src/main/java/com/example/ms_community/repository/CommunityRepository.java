package com.example.ms_community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ms_community.model.Community;;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findByCommunityName(String name);
}