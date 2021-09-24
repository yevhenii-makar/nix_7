package com.yevheniimakar.entity;

public class Solution {

    private int problemId;
    private int coast;

    public Solution(int problemId, int coast) {
        this.problemId = problemId;
        this.coast = coast;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }


}
