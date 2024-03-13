package com.example.demo.mapper.tap;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ForTransaction {
    @Delete("DELETE FROM TAP.posts WHERE id = #{postNo} AND user_mail = #{userMail}")
    void deletePost(@Param("postNo") String postNo, @Param("userMail") String userMail);

    @Delete("DELETE FROM TAP.likes WHERE post_id = #{postNo}")
    void deleteLikes(String postNo);

    @Delete("DELETE FROM TAP.comments WHERE post_id = #{postNo}")
    void deleteComments(String postNo);
}
