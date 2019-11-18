package com.juhick.onlineTest;

public class input {
    private int qId;
    private String lContent;
    private String rContent;

    input(int qId, String lContent, String rContent){
        this.qId = qId;
        this.lContent = lContent;
        this.rContent = rContent;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getlContent() {
        return lContent;
    }

    public void setlContent(String lContent) {
        this.lContent = lContent;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }
}
