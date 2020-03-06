package com.rainbow.common.enums;

/**
 * header key enum
 *
 * @author sunyue
 * @date 2019/7/2 下午2:29
 */
public enum HeaderEnum {


    UNKNOWN(0, "unknown_header_name_$#@"),
    /**
     * 版本号
     */
    VERSION(1, "version");

    private Integer value;
    private String headerName;

    public Integer getValue() {
        return value;
    }

    public String getHeaderName() {
        return headerName;
    }

    HeaderEnum(Integer value, String headerName) {
        this.headerName = headerName;
        this.value = value;
    }

    public static HeaderEnum get(String headerName) {
        for (HeaderEnum headerEnum : HeaderEnum.values()) {
            if (headerEnum.headerName.equals(headerName)) {
                return headerEnum;
            }
        }
        return UNKNOWN;
    }

}
