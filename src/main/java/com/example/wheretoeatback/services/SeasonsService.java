package com.example.wheretoeatback.services;

import com.example.wheretoeatback.entities.Season;
import com.example.wheretoeatback.repositories.SeasonsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonsService {

    private SeasonsRepository seasonsRepository;

    public SeasonsService(SeasonsRepository seasonsRepository) {
        this.seasonsRepository = seasonsRepository;
    }

    public List<Season> getSeasons() {
        return this.seasonsRepository.findAll();
    }

    public Season getCurrentSeason() {
        return this.seasonsRepository.findByStartDateAfterAndEndDateBefore();
    }
}
