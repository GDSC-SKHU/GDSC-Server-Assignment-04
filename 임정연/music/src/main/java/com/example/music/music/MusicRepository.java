package com.example.music.music;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import  java.util.*;


@Repository
@Getter
@Setter
public class MusicRepository {
    public static Map<String, Music> musicMap = new HashMap<>();

    public static Music save(Music music){
        music.setTitle("하이");
        musicMap.put(music.getTitle(),music); //음악 넣어주기
        return music;
    }

    public static List<Music> findAll(){
        Collection<Music> values = musicMap.values();
        return new ArrayList<>(values);
    }

    public List<Music> findBytitle(String title){
        return List.of(musicMap.get(title));
    }

    public void delete(String title){
        musicMap.remove(title);
    }
}

