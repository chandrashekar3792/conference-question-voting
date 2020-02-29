package com.creaza.conferencevoting.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ChoiceDto {

    @NotBlank
    private String statement;

    public ChoiceDto() {

    }

    public ChoiceDto(@NotBlank String statement) {
        this.statement = statement;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoiceDto choiceDto = (ChoiceDto) o;
        return statement.equals(choiceDto.statement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statement);
    }

    @Override
    public String toString() {
        return "ChoiceDto{" +
                "statement='" + statement + '\'' +
                '}';
    }
}
