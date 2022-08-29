package com.comment;

public class CommentVO {
	private int commentID;
	private int num;
	private String userID;
	private String commentDate;
	private String commentText;
	private int commentAvilable;
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public int getCommentAvilable() {
		return commentAvilable;
	}
	public void setCommentAvilable(int commentAvilable) {
		this.commentAvilable = commentAvilable;
	}
}