package com.ajou.prcoding.myweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ajou.prcoding.myweb.dto.MusicList;
import com.ajou.prcoding.myweb.repository.FavoriteRepository;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import com.ajou.prcoding.myweb.entity.FavoriteMusic;
import com.ajou.prcoding.myweb.dto.FavoriteMusicRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {
    private final FavoriteRepository albumsRepo;
    RestTemplate restTemplate = new RestTemplate();

    public MusicList searchMusic(String name) {
        try {
            String url = String.format("https://itunes.apple.com/search?term=%s&entity=album", name);
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            MusicList list = mapper.readValue(response, MusicList.class);
            System.out.println(list.getResultCount());
            return list;
        } catch (IOException e) {
            System.out.println(e.toString());
            return null;
        }
    }

    public List<FavoriteMusic> getLikes() {
        try {
            return albumsRepo.findAll();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

    @Transactional
    public int saveFavorite(FavoriteMusicRequestDto favorite) {
        FavoriteMusic music = albumsRepo.save(favorite.toEntity());
        if (music != null) {
            return 1;
        } else {
            return 0;
        }
    }

    public String deleteFavorite(String id) {
        try {
            albumsRepo.deleteById(id);
            return id + "had been deleted successfully";
        } catch (Exception e) {
            System.out.println(e.toString());
            return "Cannot found the music with id: " + id;
        }
    }
}
