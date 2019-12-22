/**
 * @program: bbs
 * @description: 回复通知传输DTO
 * @author: Wu
 * @create: 2019-12-22 08:53
 **/
package com.wu.bbs.dto;

import com.wu.bbs.entity.User;
import lombok.Data;

@Data
public class NotificationDTO {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private User notifier;
    private String outerTitle;
    private String type;
}