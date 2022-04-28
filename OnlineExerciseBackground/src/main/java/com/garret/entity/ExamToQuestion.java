package com.garret.entity;

public class ExamToQuestion {
    private Integer id;
    private Integer eid;
    private Integer qid;
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "ExamToQuestion{" +
                "id=" + id +
                ", eid=" + eid +
                ", qid=" + qid +
                ", score=" + score +
                '}';
    }
}
