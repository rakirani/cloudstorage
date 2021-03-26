    package com.udacity.jwdnd.course1.cloudstorage.mapper;

    import com.udacity.jwdnd.course1.cloudstorage.model.Files;
    import org.apache.ibatis.annotations.*;

    import java.util.List;

    @Mapper
    public interface FilesMapper {
        @Select("Select * from Files userId=#{userId} ")
        List<Files> getAllFiles(int userId);

        @Select("Select * from Files fileId=#{FileId} ")
        Files getFile(int FileId);

        @Insert("Insert into Files(fileName,contentType,fileSize,fileData) values(#{fileName},#{contentType},#{fileSize}" +
                ",#{fileData})")
        @Options(useGeneratedKeys = true,keyProperty = "fileId")
        int insertFile(Files files);

        @Delete("Delete * from files where fileId=#{fileId}")
        void deleteFile(int fileId);

        @Delete("Delete * from files where userId=#{userId}")
        void deleteAllFiles(int userId);
    }
