package com.rainbow.common.dto;

import lombok.EqualsAndHashCode;

/**
 * @Author: lujinwei
 * @Date: 2019/8/19 下午5:10
 */

@EqualsAndHashCode(callSuper = true)
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

    public C three;

    /**
     * @param a
     * @param b
     */
    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.three = c;
    }

    public C getThree() {
        return three;
    }

    public void setThree(C three) {
        this.three = three;
    }

    @Override
    public String toString() {
        return super.toString() + " , three: " + this.three.toString();
    }
}
