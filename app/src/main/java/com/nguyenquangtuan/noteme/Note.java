package com.nguyenquangtuan.noteme;

public class Note {
    private long id;
    private String title;
    private String content;
    private String createdDate;
    private String createdTime;
    private String modifiedDate;
    private String modifiedTime;
    private String subject;

    Note(String title, String content, String createdDate, String createdTime,String subject) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.subject = subject;
    }

    Note(long id, String title, String content, String modifiedDate, String modifiedTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.modifiedDate = modifiedDate;
        this.modifiedTime = modifiedTime;
    }

    public Note(long id, String title, String content, String createdDate, String createdTime, String modifiedDate, String modifiedTime, String subject) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.createdTime = createdTime;
        this.modifiedDate = modifiedDate;
        this.modifiedTime = modifiedTime;
        this.subject = subject;
    }

    Note() {
        // empty constructor
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedDate='" + modifiedDate + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
