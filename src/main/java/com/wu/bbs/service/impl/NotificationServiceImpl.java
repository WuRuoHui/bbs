/**
 * @program: bbs
 * @description: 回复通知service
 * @author: Wu
 * @create: 2019-12-22 09:10
 **/
package com.wu.bbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wu.bbs.dto.NotificationDTO;
import com.wu.bbs.entity.Notification;
import com.wu.bbs.entity.NotificationExample;
import com.wu.bbs.entity.User;
import com.wu.bbs.mapper.NotificationMapper;
import com.wu.bbs.mapper.UserMapper;
import com.wu.bbs.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<NotificationDTO> list(Integer id, Integer currentPage, Integer size) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(Long.valueOf(id));
        Integer totalCount = Math.toIntExact(notificationMapper.countByExample(notificationExample));    //获得总记录数
        int pages = (totalCount + size - 1) / size;                   //获得总页数
//        int offset = (currentPage - 1) * size;
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > pages) {
            currentPage = pages;
        }
        PageHelper.startPage(currentPage, size);
        List<Notification> notificationList = notificationMapper.selectByExample(notificationExample);
        if (notificationList == null || notificationList.size() <= 0) {
            return new PageInfo<>();
        }
        System.out.println(notificationList);
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (Notification notification : notificationList) {
            User user = userMapper.selectByPrimaryKey(notification.getNotifier().intValue());
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setNotifier(user);
            notificationDTOList.add(notificationDTO);
        }
        PageInfo<NotificationDTO> notificationDTOPageInfo = new PageInfo<NotificationDTO>(notificationDTOList);
        notificationDTOPageInfo.setPageSize(size);
        notificationDTOPageInfo.setPageNum(currentPage);
        notificationDTOPageInfo.setSize(totalCount);
        if (pages < 1) pages = 1;
        notificationDTOPageInfo.setPages(pages);
        return notificationDTOPageInfo;
    }
}
