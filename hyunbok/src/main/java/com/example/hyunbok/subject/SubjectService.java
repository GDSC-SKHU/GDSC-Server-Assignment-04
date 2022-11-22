package com.example.hyunbok.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public void save(Subject subject){
        subjectRepository.save(subject);
    }

    public List<Subject> findAll() { return subjectRepository.findAll(); }

    public List<Subject> findBySubName(String subName) { return subjectRepository.findBySubName(subName); }

    public void deleteBySubject(String subName) { subjectRepository.deleteBySubject(subName);}
}
