package com.garret.entity;

public class Exam {
    private Integer id;
    private Integer uid;
    private String startTime;

    private Integer score;
    private Boolean finished;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", uid=" + uid +
                ", startTime='" + startTime + '\'' +
                ", score=" + score +
                ", finished=" + finished +
                '}';
    }
}
