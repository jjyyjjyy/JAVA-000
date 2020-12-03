package io.github.jjyy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.jjyy.domain.Order;

import java.util.List;

/**
 * @author jy
 */
public interface OrderMapper extends BaseMapper<Order> {

    int insertBatchSomeColumn(List<Order> entityList);

}
