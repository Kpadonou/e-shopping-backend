package com.rem.eshop.repositories;

import java.util.List;

import com.rem.eshop.models.Indent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IndentRepository extends JpaRepository<Indent, Long> {
    List<Indent> findByUserId(Long id);
}
