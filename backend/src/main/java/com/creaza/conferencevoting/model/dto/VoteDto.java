package com.creaza.conferencevoting.model.dto;

public class VoteDto {

    private Long questionId;

    private Long choiceId;

    private Long voteCount;

    public VoteDto() {

    }

    public VoteDto(Long questionId, Long choiceId, Long voteCount) {
        this.questionId = questionId;
        this.choiceId = choiceId;
        this.voteCount = voteCount;
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

    public Long getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Long voteCount) {
        this.voteCount = voteCount;
    }
}
