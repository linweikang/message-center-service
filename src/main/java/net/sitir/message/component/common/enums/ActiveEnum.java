package net.sitir.message.component.common.enums;

/**
 * 数据停用启用枚举类
 * @author linweikang
 * @since 2021/8/3
 */
public enum ActiveEnum {

    ENABLE(1, "启用"),
    DISABLE(0, "停用");

    private Integer value;

    private String desc;

    ActiveEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByValue(Integer value) {
        for (ActiveEnum activeEnum : ActiveEnum.values()) {
            if (activeEnum.getValue().equals(value)) {
                return activeEnum.getDesc();
            }
        }
        return "";
    }

    public static ActiveEnum getByValue(Integer value) {
        for (ActiveEnum activeEnum : ActiveEnum.values()) {
            if (activeEnum.getValue().equals(value)) {
                return activeEnum;
            }
        }
        return null;
    }
}
