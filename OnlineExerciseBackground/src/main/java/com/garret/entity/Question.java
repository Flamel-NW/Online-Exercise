package com.garret.entity;

public class Question {
    private Integer id;
    private String stem;
    private Integer weight;

    private Integer chosenTimes;
    private Integer totalScore;
    private Integer cheatTimes;

    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getChosenTimes() {
        return chosenTimes;
    }

    public void setChosenTimes(Integer chosenTimes) {
        this.chosenTimes = chosenTimes;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getCheatTimes() {
        return cheatTimes;
    }

    public void setCheatTimes(Integer cheatTimes) {
        this.cheatTimes = cheatTimes;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", stem='" + stem + '\'' +
                ", weight=" + weight +
                ", chosenTimes=" + chosenTimes +
                ", totalScore=" + totalScore +
                ", cheatTimes=" + cheatTimes +
                ", option1='" + option1 + '\'' +
                ", option2='" + option2 + '\'' +
                ", option3='" + option3 + '\'' +
                ", option4='" + option4 + '\'' +
                ", option5='" + option5 + '\'' +
                '}';
    }
}
