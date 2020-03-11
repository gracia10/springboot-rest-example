package com.rest.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.web.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
}
