package com.udacity.jwdnd.course1.cloudstorage.mapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialsMapper {

    @Select("Select * from Credentials where userid=#{userid}")
    List<Credentials> getAllCredentials(int userid);

    @Select("Select * from Credentials where credentialid=#{credentialid}")
    Credentials getCredentials(int credentialid);

    @Insert("Insert into Credentials(url,username,key,password,userid) values(#{url},#{username},#{key},#{password},#{userid}) where userid=#{userid}")
    @Options(useGeneratedKeys = true,keyProperty ="credentialid")
    int insertCredentials(Credentials credentials);

    @Delete("Delete * from Credentials where where credentialid=#{credentialid}")
    void deleteCredentials(int credentialid);

    @Delete("Delete * from Credentials where where userid=#{userid}")
    void deleteAllCredentials(int userid);

}
