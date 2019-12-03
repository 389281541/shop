package com.rainbow.common.dto;

import lombok.Data;

/**
 * @Author: lujinwei
 * @Date: 2019/8/19 下午5:11
 */
@Data
public class TwoTuple<A, B> {

    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        this.first = a;
        this.second = b;
    }

}
