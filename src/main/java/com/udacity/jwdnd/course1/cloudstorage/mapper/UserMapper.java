package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("Select * from Users where username=#{username}")
    Users getUser(String username);

    @Insert("Insert into Users(username, salt, password, firstname, lastname) Values(#{username},#{salt},#{password},#{firstname},#{lastname})")
    @Options(useGeneratedKeys = true,keyProperty = "userid")
    int insertUsers(Users users);

    @Delete("Delete * from Users where username=#{username}")
    void deleteUser(String username);
}