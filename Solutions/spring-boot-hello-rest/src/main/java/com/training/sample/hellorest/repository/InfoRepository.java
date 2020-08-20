package com.training.sample.hellorest.repository;

import com.training.sample.hellorest.domaine.Info;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<Info, Long> {
}
