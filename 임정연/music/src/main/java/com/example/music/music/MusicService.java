package com.example.music.music;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    public void save(Music music) {
        musicRepository.save(music);
    }

    public void delete(String title){
        musicRepository.delete(title);
    }

    public List<Music> findAll(){
        return musicRepository.findAll();
    }

    public List<Music> findBytitle(String title){
        return musicRepository.findBytitle(title);
    }
}
