package com.juhick.onlineTest;

public class check {
    private String qName;
    private int qId;

    check(int qId, String qName){
        this.qName = qName;
        this.qId = qId;
    }

    public String getqName() {
        return qName;
    }

    public void setqName(String qName) {
        this.qName = qName;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }
}
