package com.rengu.demo.springcloud.springclouduserconsumer.Utils;

import com.rengu.demo.springcloud.springclouduserconsumer.entity.ResultEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResultUtils {

    public static <T> ResultEntity<Object> build(T t) {
        ResultEntity resultEntity = new ResultEntity<>();
        resultEntity.setData(t);
        return resultEntity;
    }
}
