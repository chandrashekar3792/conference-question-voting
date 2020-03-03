package com.creaza.conferencevoting.model.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class QuestionUpdationDto {

    @NotNull
    private Long id;

    private String title;

    private String category;

    private List<ChoiceCreationDto> choices;

    public QuestionUpdationDto() {

    }

    public QuestionUpdationDto(@NotNull Long id, String title, String category, List<ChoiceCreationDto> choices) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.choices = choices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<ChoiceCreationDto> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceCreationDto> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "QuestionUpdationDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", choices=" + choices +
                '}';
    }
}
