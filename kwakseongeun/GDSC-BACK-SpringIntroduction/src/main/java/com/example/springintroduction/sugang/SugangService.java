package com.example.springintroduction.sugang;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SugangService {

    private final SugangRepository sugangRepository;


    public void save(Sugang sugang){
        sugangRepository.save(sugang);
    }
    public void delete(String sugangname) {
        sugangRepository.delete(sugangname);
    }

    public List<Sugang> findAll(){
        return sugangRepository.findAll();
    }

    public List<Sugang> findBysugangname(String sugangname){
        return sugangRepository.findBysugangname(sugangname);
    }

}
