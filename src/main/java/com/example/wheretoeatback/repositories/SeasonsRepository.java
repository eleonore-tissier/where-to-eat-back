package com.example.wheretoeatback.repositories;

import com.example.wheretoeatback.entities.Season;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Qualifier("seasons")
@Repository
public interface SeasonsRepository extends CrudRepository<Season, Long> {

    @Nonnull
    List<Season> findAll();

    @Query(value = "select * from saisons where date_debut < CURRENT_DATE and date_fin > CURRENT_DATE", nativeQuery = true)
    Season findByStartDateAfterAndEndDateBefore();
}
