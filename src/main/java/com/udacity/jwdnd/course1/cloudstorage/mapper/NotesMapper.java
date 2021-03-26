package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("Select * from Notes where userId=#{userId}")
    List<Notes> getAllNotes(int userId);

    @Select("Select * from Notes where noteId=#{noteId}")
    Notes getOneNote(int noteId);

    @Insert("Insert into Notes() values() where userId=#{userId}")
    @Options(useGeneratedKeys = true,keyProperty = "noteId")
        int insertNotes(Notes notes);

    @Delete("Delete * from Notes where noteId=#{noteId}")
    void deleteNote(int noteId);

    @Delete("Delete * from Notes where userId=#{userId}")
    void deleteAllNotes(int userId);


}
