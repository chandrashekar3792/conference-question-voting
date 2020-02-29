package com.creaza.conferencevoting.exception;

public class IncorrectChoiceException extends RuntimeException {
    private Long questionId;
    private Long choiceId;

    public IncorrectChoiceException(Long questionId, Long choiceId) {
        super(String.format("%s is not a choice for %s", choiceId.toString(), questionId.toString()));
        this.questionId = questionId;
        this.choiceId = choiceId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }
}
