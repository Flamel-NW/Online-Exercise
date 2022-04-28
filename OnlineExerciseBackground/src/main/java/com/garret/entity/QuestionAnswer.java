package com.garret.entity;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswer {
    private Integer qid;
    private Integer pid;
    private String stem;
    private List<String> options;
    private List<Boolean> answers;

    public QuestionAnswer(Question question, Param param, String placeholder) {
        qid = question.getId();
        pid = question.getId();
        stem = question.getStem().replace(placeholder, param.getParam());

        options = new ArrayList<>();
        options.add(question.getOption1());
        options.add(question.getOption2());
        options.add(question.getOption3());
        options.add(question.getOption4());
        options.add(question.getOption5());

        answers = new ArrayList<>();
        answers.add(param.getAnswer1());
        answers.add(param.getAnswer2());
        answers.add(param.getAnswer3());
        answers.add(param.getAnswer4());
        answers.add(param.getAnswer5());
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Boolean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Boolean> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "qid=" + qid +
                ", pid=" + pid +
                ", stem='" + stem + '\'' +
                ", options=" + options +
                ", answers=" + answers +
                '}';
    }
}
