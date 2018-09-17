package com.cisakowski.playgrounds.repository;

import com.cisakowski.playgrounds.model.Playground;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaygroundRepository extends JpaRepository<Playground, Long> {
    Optional<Playground> getById(Long Id);
}
