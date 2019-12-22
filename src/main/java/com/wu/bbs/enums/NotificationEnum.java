/**
 * @program: bbs
 * @description:
 * @author: Wu
 * @create: 2019-12-21 09:10
 **/
package com.wu.bbs.enums;

public enum  NotificationEnum {
    REPLY_QUESTION(1,"回复了问题"),
    REPLY_COMMENT(2,"回复了评论"),
    ;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private int type;
    private String name;

    NotificationEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
}
