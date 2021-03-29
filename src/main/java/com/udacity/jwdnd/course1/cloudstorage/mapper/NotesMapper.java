package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NotesMapper {

    @Select("Select * from Notes where userid=#{userid}")
    List<Notes> getAllNotes(int userid);

    @Select("Select * from Notes where noteid=#{noteid}")
    Notes getOneNote(int noteid);

    @Insert("Insert into Notes(notetitle,notedescription) values(#{notetitle},#{notedescription}) where userid=#{userid}")
    @Options(useGeneratedKeys = true,keyProperty = "noteid")
    int insertNotes(Notes notes);

    @Delete("Delete * from Notes where noteid=#{noteid}")
    void deleteNote(int noteid);

    @Delete("Delete * from Notes where userid=#{userid}")
    void deleteAllNotes(int userid);


}
