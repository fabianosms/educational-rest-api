package com.machado.fabiano.forum.controller.dto;

public class FormErrorDto {

    private String field;
    private String error;

    public FormErrorDto(String campo, String erro) {
        this.field = campo;
        this.error = erro;
    }

    public String getField() {
        return field;
    }

    public String getError() {
        return error;
    }
}
