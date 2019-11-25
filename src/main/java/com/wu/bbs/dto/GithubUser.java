/**
 * @program: bbs
 * @description: GitHub用户
 * @author: Wu
 * @create: 2019-11-25 00:20
 **/
package com.wu.bbs.dto;

public class GithubUser {
    private String name;
    private Long id;
    private String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
