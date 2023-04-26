package com.example.ms_account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ms_account.model.Moderator;

public interface ModeratorRepository extends JpaRepository<Moderator, Long>  {
    List<Moderator> findBymoderatorUserId(long moderatorUserId);
    List<Moderator> findBymoderatorComId(long moderatorComId);
}
