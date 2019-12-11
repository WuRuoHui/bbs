/**
 * @program: bbs
 * @description: 用户mapper
 * @author: Wu
 * @create: 2019-12-08 09:58
 **/
package com.wu.bbs.mapper;

import com.wu.bbs.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    //根据github获取到的信息插入到user表中
    @Insert("insert into USER (account_id, name, token, gmt_create,avatar_url) VALUES(#{account_id} ,#{name} ,#{token} ,#{gmtCreate} ,#{avatarUrl} ) ")
    void insert(User user);
    //根据token获取用户
    @Select("select * from  user where token = #{token} ")
    User getUserByToken(String token);
    //根据account_id获取用户
    @Select("select * from  user where id = #{id} ")
    User findUserById(Integer id);

    @Select("select * from  user where account_id = #{accountId} ")
    User findUserByAccountId(Integer accountId);

    @Update("update user set token = #{token} where id = #{id}")
    void updateUserTokenById(Integer id, String token);


}
