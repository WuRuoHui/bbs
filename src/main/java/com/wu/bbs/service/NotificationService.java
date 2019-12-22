package com.wu.bbs.service;

import com.github.pagehelper.PageInfo;
import com.wu.bbs.dto.NotificationDTO;

public interface NotificationService {
    PageInfo<NotificationDTO> list(Integer id, Integer currentPage, Integer size);
}
