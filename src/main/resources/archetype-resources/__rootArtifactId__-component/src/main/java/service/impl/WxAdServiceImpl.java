#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package}.domain.entity.WxAd;
import ${package}.domain.mapper.WxAdMapper;
import ${package}.service.WxAdService;

/**
 * @author: zhouchao06
 * @create: 2019-10-11 15:14
 **/
@Service
public class WxAdServiceImpl extends ServiceImpl<WxAdMapper, WxAd> implements WxAdService {

    @Resource
    private WxAdMapper wxAdMapper;

    @Override
    public List<WxAd> getMap() {
        return wxAdMapper.getAll();
    }
}
