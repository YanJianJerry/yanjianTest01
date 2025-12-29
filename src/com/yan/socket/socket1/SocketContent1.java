package com.yan.socket.socket1;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SocketContent1 implements Serializable {
    private String content1;
    private String content2;
    private int content3;
    private Map<String, String> content4;
    private List<String> content5;
    private String[] content6;

    public SocketContent1() {
    }

    public SocketContent1(String content1, String content2, int content3, Map<String, String> content4, List<String> content5, String[] content6) {
        this.content1 = content1;
        this.content2 = content2;
        this.content3 = content3;
        this.content4 = content4;
        this.content5 = content5;
        this.content6 = content6;
    }

    public String getContent1() {
        return content1;
    }

    public void setContent1(String content1) {
        this.content1 = content1;
    }

    public String getContent2() {
        return content2;
    }

    public void setContent2(String content2) {
        this.content2 = content2;
    }

    public int getContent3() {
        return content3;
    }

    public void setContent3(int content3) {
        this.content3 = content3;
    }

    public Map<String, String> getContent4() {
        return content4;
    }

    public void setContent4(Map<String, String> content4) {
        this.content4 = content4;
    }

    public List<String> getContent5() {
        return content5;
    }

    public void setContent5(List<String> content5) {
        this.content5 = content5;
    }

    public String[] getContent6() {
        return content6;
    }

    public void setContent6(String[] content6) {
        this.content6 = content6;
    }

    @Override
    public String toString() {
        return "SocketContent1{" +
                "content1='" + content1 + '\'' +
                ", content2='" + content2 + '\'' +
                ", content3=" + content3 +
                ", content4=" + content4 +
                ", content5=" + content5 +
                ", content6=" + Arrays.toString(content6) +
                '}';
    }
}
