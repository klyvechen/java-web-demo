package rm.project.component;

public enum REnum {
    SUCCESS(1, "成功"),
    FAIL(0, "失敗");

    REnum(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    final String msg;
    final int code;
}
