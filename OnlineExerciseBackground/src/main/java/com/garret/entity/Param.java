package com.garret.entity;

public class Param {
    private Integer id;
    private Integer qid;
    private String param;
    private Integer weight;

    private Boolean answer1;
    private Boolean answer2;
    private Boolean answer3;
    private Boolean answer4;
    private Boolean answer5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getAnswer1() {
        return answer1;
    }

    public void setAnswer1(Boolean answer1) {
        this.answer1 = answer1;
    }

    public Boolean getAnswer2() {
        return answer2;
    }

    public void setAnswer2(Boolean answer2) {
        this.answer2 = answer2;
    }

    public Boolean getAnswer3() {
        return answer3;
    }

    public void setAnswer3(Boolean answer3) {
        this.answer3 = answer3;
    }

    public Boolean getAnswer4() {
        return answer4;
    }

    public void setAnswer4(Boolean answer4) {
        this.answer4 = answer4;
    }

    public Boolean getAnswer5() {
        return answer5;
    }

    public void setAnswer5(Boolean answer5) {
        this.answer5 = answer5;
    }

    @Override
    public String toString() {
        return "Param{" +
                "id=" + id +
                ", qid=" + qid +
                ", param='" + param + '\'' +
                ", weight=" + weight +
                ", answer1=" + answer1 +
                ", answer2=" + answer2 +
                ", answer3=" + answer3 +
                ", answer4=" + answer4 +
                ", answer5=" + answer5 +
                '}';
    }
}
