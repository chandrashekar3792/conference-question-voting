package com.creaza.conferencevoting.model.dto;

public class ChoiceDto {

    private Long choiceId;

    private Long questionId;

    private Integer voteCount;

    public ChoiceDto() {

    }

    public ChoiceDto(Long choiceId, Long questionId, Integer voteCount) {
        this.choiceId = choiceId;
        this.questionId = questionId;
        this.voteCount = voteCount;
    }

    public Long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public String toString() {
        return "ChoiceDto{" +
                "choiceId=" + choiceId +
                ", questionId=" + questionId +
                ", voteCount=" + voteCount +
                '}';
    }
}
