package com.landao.hearu.service.impl;

import com.landao.hearu.entity.RequestLog;
import com.landao.hearu.mapper.RequestLogMapper;
import com.landao.hearu.service.IRequestLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 *
 * @author 常珂洁
 * @since 2022-01-24
 */
@Service
public class IRequestLogServiceImpl extends ServiceImpl<RequestLogMapper, RequestLog> implements IRequestLogService {

}
