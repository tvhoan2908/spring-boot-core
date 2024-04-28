package vn.spring.rnd.User.Constant;

public enum EUserStatus {
  ACTIVE(1, "Hoạt động"),
  DISABLED(2, "Khoá"),
  BANNED(3, "Vô hiệu hoá");

  public static final int ACTIVE_VALUE = 1;
  public static final int DISABLED_VALUE = 2;
  public static final int BANNED_VALUE = 3;

  public final int value;
  public final String label;

  private EUserStatus(int value, String label) {
    this.label = label;
    this.value = value;
  }
}
