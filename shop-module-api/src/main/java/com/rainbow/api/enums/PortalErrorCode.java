package com.rainbow.api.enums;

import com.rainbow.common.exception.errorcode.ErrorSideEnum;
import com.rainbow.common.exception.errorcode.SubModuleEnum;
import com.rainbow.common.exception.errorcode.UniverseErrorCode;

/**
 * 门户入口错误码
 *
 * @author lujinwei
 * @since 2020/3/16
 */
public enum PortalErrorCode implements UniverseErrorCode {

    UNKNOW(10000001, "未知"),
    SPU_PULL_OFF(10000002, "spu下架了"),
    PROMOTION_LIMIT_EXCEED(10000003, "超出限购数了"),
    NOT_ENOUGH_STOCK(10000004, "库存不足"),
    EMPTY_ORDER_SKU(10000005, "订单sku为空"),
    COUPON_NOT_EXIST(10000006, "优惠券不存在"),
    USER_NOT_EXIST(10000007, "用户不存在"),
    INTEGRATION_RULE_NOT_EXIST(10000008, "积分规则未配置"),
    INTEGRATION_CAN_NOT_USE(10000009, "积分不可使用"),
    CART_CAN_NOT_EMPTY(10000010, "购物车信息不能为空"),
    ADDRESS_NOT_EXIST(10000011, "地址不存在"),
    INTEGRATION_NOT_ENOUGH(10000012, "积分不足"),
    INTEGRATION_UPDATE_FAILED(10000013, "积分修改失败"),
    SHOP_NOT_EXIST(10000014, "店铺不存在"),
    ORDER_NOT_EXIST(10000015, "订单不存在"),
    COUPON_RECEIVE_EXCEED(10000016, "优惠券领取超出限制"),
    WAIT_TO_PAY_ORDER_ERROR(10000017, "优惠券领取超出限制"),
    ALIPAY_ORDER_FAILED(10000018, "支付宝预下单失败"),
    ALIPAY_EXCEPTION_UNKNOWN(10000019, "支付宝下单位置错误"),
    ALIPAY_UNSUPPORT_EXCEPTION(10000020, "不支持的交易状态"),
    ALIPAY_VERIFY_ERROR(10000021, "非法请求,验证不通过"),
    FLASH_CONFIRM_ORDER_EXPIRED(10000022, "秒杀订单失效"),
    ;

    private ErrorSideEnum errorSideEnum;
    private SubModuleEnum subModuleEnum;
    private int code;
    private String message;

    PortalErrorCode(ErrorSideEnum errorSideEnum, SubModuleEnum subModuleEnum, int code, String msg) {
        this.errorSideEnum = errorSideEnum;
        this.subModuleEnum = subModuleEnum;
        this.code = code;
        this.message = msg;
    }

    PortalErrorCode(int code, String message) {
        this(ErrorSideEnum.SERVER, SubModuleEnum.PORTAL, code, message);
    }

    @Override
    public ErrorSideEnum getErrorSideEnum() {
        return errorSideEnum;
    }

    @Override
    public SubModuleEnum getSubSystem() {
        return subModuleEnum;
    }

    @Override
    public SubModuleEnum getSubModule() {
        return subModuleEnum;
    }

    @Override
    public int getDetailCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public UniverseErrorCode fromCode(int code) {
        for (PortalErrorCode errorCode : values()) {
            if (errorCode.getDetailCode() == code) {
                return errorCode;
            }
        }
        return UNKNOW;
    }
}
