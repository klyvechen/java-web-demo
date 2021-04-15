package rm.project.enums;

import lombok.Getter;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * 註冊類型
 *
 * @author Klyve.Chen
 * @since 2020-10-17
 */
public enum RegisterTypeEnum {

    EMAIL(0),

    PHONE(1),

    OTHER(-1);

    private static final Map<Integer, RegisterTypeEnum> lookup = new HashMap<>();

    static {
        for(RegisterTypeEnum i : EnumSet.allOf(RegisterTypeEnum.class)) {
            lookup.put(i.getType(), i);
        }
    }

    RegisterTypeEnum(Integer type) {
        this.type = type != null ? type : 0;
    }

    @Getter
    final int type;

    public static RegisterTypeEnum get(Integer i) {
        if (i == null)
            return EMAIL;
        RegisterTypeEnum regTypeEnum = lookup.get(i);
        return regTypeEnum != null ? regTypeEnum : OTHER;
    }
}
