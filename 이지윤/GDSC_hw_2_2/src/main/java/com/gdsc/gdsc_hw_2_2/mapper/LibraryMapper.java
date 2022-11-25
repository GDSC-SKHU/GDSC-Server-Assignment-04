package com.gdsc.gdsc_hw_2_2.mapper;

import com.gdsc.gdsc_hw_2_2.dto.Hope;
import com.gdsc.gdsc_hw_2_2.dto.Library;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface LibraryMapper {

    @Select("SELECT * FROM book;")
    List<Library> findAll();

    @Select("SELECT * FROM book " + "WHERE id = #{id}")
    Library findById(int id);

    @Insert("INSERT into hopebook(title, author, publisher) " + "VALUES (#{title}, #{author}, #{publisher})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Hope hope);

    @Select("SELECT * FROM hopebook;")
    List<Hope> findByAll();
}
