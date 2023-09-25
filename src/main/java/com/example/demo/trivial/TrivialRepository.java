package com.example.demo.trivial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrivialRepository extends JpaRepository<TrivialCardEntity, Long> {

    List<TrivialCardEntity> findByCategory(String category);
    List<TrivialCardEntity> findByDifficulty(String difficulty);
    List<TrivialCardEntity> findByType(String type);
    List<TrivialCardEntity> findByCategoryAndDifficulty(String category, String difficulty);
    List<TrivialCardEntity> findByCategoryAndType(String category, String type);

    
}
