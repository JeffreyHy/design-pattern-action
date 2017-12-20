package com.huang.pattern.observer.listener;

/**
 * Created by JeffreyHy on 2017/12/20.
 */
public enum EventTypeEnum {
    /**
     * 0-草稿
     */
    DRAFT((byte) 0, "草稿"),
    /**
     * 1-待发布
     */
    BE_RELEASE((byte) 1, "待发布"),
    /**
     * 5-已发布
     */
    RELEASE((byte) 5, "已发布"),
    /**
     * 10-进行中
     */
    START((byte) 10, "进行中"),
    /**
     * 15-已结束
     */
    FINISH((byte) 15, "已结束"),
    /**
     * 16-结算中
     */
    SETTLEMENT_ING((byte) 16, "结算中"),
    /**
     * 20-已结算
     */
    SETTLEMENT((byte) 20, "已结算"),
    /**
     * 98-已取消
     */
    CANCEL((byte) 98, "已取消"),
    /**
     * 99-已终止
     */
    TERMINATE((byte) 99, "已终止"),
    /**
     * 100-已删除
     */
    DELETE((byte) 100, "已删除"),

    ;

    private byte code;
    private String mean;

    EventTypeEnum(byte code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    public static EventTypeEnum toEnum(byte code) {
        for (EventTypeEnum en : values()) {
            if (en.code == code) {
                return en;
            }
        }
        return null;
    }

    public byte getCode() {
        return code;
    }

    public String getMean() {
        return mean;
    }
}
