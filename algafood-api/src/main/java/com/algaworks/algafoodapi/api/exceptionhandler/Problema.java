package com.algaworks.algafoodapi.api.exceptionhandler;

/**
 * Problema
 */
public record Problema(Integer status, String type, String title, String detail) {

  public static final class Builder {
    private Integer status;
    private String type;
    private String title;
    private String detail;

    public Builder() {
    }

    public static Builder newInstance() {
      return new Builder();
    }

    public Builder status(Integer status) {
      this.status = status;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public Problema build() {
      return new Problema(status, type, title, detail);
    }
  }
}
