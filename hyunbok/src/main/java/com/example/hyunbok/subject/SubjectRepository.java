package com.example.hyunbok.subject;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class SubjectRepository {
    public List<Subject> subjectList = new ArrayList<>();

    public void save(Subject subject){
        subjectList.add(subject);
    }

    public List<Subject> findAll(){
        return subjectList;
    }


    public List<Subject> findBySubName(String subName){
        for(int i =0 ; i<subjectList.size();i++){
            if(subjectList.get(i).getName().equals(subName))
                return List.of(subjectList.get(i));
        }
        return List.of();
    }

    public void deleteBySubject(String subName){
        for(int i =0 ; i<subjectList.size();i++){
            if(subjectList.get(i).getName().equals(subName))
                subjectList.remove(i);
        }
    }

}
