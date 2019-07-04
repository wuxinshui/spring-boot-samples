package com.wxs.async.service;

import com.wxs.async.entity.AuditLog;

/**
 * @Author: yoyo
 * @Description:
 * @Date: Created in 2019/4/28 17:01
 */
public interface AuditLogService {
    int saveLog(AuditLog auditLog);
}
