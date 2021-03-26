package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("Select * from Credentials where userId=#{userId}")
    List<Credentials> getAllCredentials(int userId);

    @Select("Select * from Credentials where credentialId=#{credentialId}")
    Credentials getCredentials(int credentialId);

    @Insert("Insert into Credentials(url,userName,key,password) values(#{url},#{userName},#{key},#{password}) where userId=#{userId}")
    @Options(useGeneratedKeys = true,keyProperty ="credentialId")
    int insertCredentials(Credentials credentials);

    @Delete("Delete * from Credentials where where credentialId=#{credentialId}")
    void deleteCredentials(int credentialId);

    @Delete("Delete * from Credentials where where userId=#{userId}")
    void deleteAllCredentials(int userId);

}
