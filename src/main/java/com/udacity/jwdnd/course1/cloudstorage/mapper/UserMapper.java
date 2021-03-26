package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("Select * from Users where username=#{userName}")
    Users getUser(String userName);

    @Insert("Insert into Users(userName, salt, password, firstName, lastName) Values(#{userName},#{salt},#{password},#{firstName},#{lastName})")
    @Options(useGeneratedKeys = true,keyProperty = "userName")
    int insertUsers(Users users);

    @Delete("Delete * from Users where userName=#{userName}")
    void deleteUser(String userName);
}
