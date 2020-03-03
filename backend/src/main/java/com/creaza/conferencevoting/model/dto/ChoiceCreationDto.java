package com.creaza.conferencevoting.model.dto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class ChoiceCreationDto {

    @NotBlank
    private String statement;

    public ChoiceCreationDto() {

    }

    public ChoiceCreationDto(@NotBlank String statement) {
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
        ChoiceCreationDto choiceCreationDto = (ChoiceCreationDto) o;
        return statement.equals(choiceCreationDto.statement);
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
