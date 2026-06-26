package com.ajou.prcoding.myweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.ajou.prcoding.myweb.entity.FavoriteMusic;

public interface FavoriteRepository extends JpaRepository<FavoriteMusic, String> {
    List<FavoriteMusic> findAll();
}
