package com.algaworks.algafoodapi.api.exceptionhandler;

import java.util.List;

/**
 * Problema
 */
public record Problem(Integer status, String type, String title, String detail, List<Field> fields) {

  public static class Field {
    private String name;
    private String userMessage;

    private Field(String name, String userMessage) {
      this.name = name;
      this.userMessage = userMessage;
    }

    public static final class Builder {
      private String name;
      private String userMessage;

      public Builder() {
      }

      public static Builder newInstance() {
        return new Builder();
      }

      public Builder name(String name) {
        this.name = name;
        return this;
      }

      public Builder userMessage(String userMessage) {
        this.userMessage = userMessage;
        return this;
      }

      public Field build() {
        return new Field(name, userMessage);
      }
    }

    public String getName() {
      return name;
    }

    public String getUserMessage() {
      return userMessage;
    }
  }

  public static final class Builder {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private List<Field> fields;

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

    public Builder fields(List<Field> fields) {
      this.fields = fields;
      return this;
    }

    public Problem build() {
      return new Problem(status, type, title, detail, fields);
    }
  }
}
