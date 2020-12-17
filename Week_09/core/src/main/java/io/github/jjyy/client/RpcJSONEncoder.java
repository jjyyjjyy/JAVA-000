package io.github.jjyy.client;

import com.alibaba.fastjson.JSON;

/**
 * @author jy
 */
public class RpcJSONEncoder implements RpcEncoder {

    @Override
    public Object encode(Object body) {
        return JSON.toJSONString(body);
    }

}
