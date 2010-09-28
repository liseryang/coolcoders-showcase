package net.coolcoders.showcase

/**
 * Peter Schneider-Manzell
 */
public enum SortOrder {
    ASC("asc"),DESC("desc")

  SortOrder(hqlString) {
    this.hqlString = hqlString
  }

  private String hqlString;

  public String getHQLString() {
    return hqlString
  }
}