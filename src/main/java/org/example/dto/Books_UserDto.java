package org.example.dto;

import java.time.LocalDateTime;

public class Books_UserDto {
    private int userId;
    private String name;
    private String title;
    private LocalDateTime issueDate;
    private LocalDateTime returnedDate;
    private Boolean isReturn = false;

    public Books_UserDto() {
    }

    public Books_UserDto(int userId, String name, String title, LocalDateTime issueDate, LocalDateTime returnedDate, Boolean isReturn) {
        this.userId = userId;
        this.name = name;
        this.title = title;
        this.issueDate = issueDate;
        this.returnedDate = returnedDate;
        this.isReturn = false;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;
    }

    public Boolean getReturn() {
        return isReturn;
    }

    public void setReturn(Boolean aReturn) {
        isReturn = aReturn;
    }

    @Override
    public String toString() {
        return "Books_UserDto{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", issueDate=" + issueDate +
                ", returnedDate=" + returnedDate +
                ", isReturn=" + isReturn +
                '}';
    }
}
