package com.comment.model;

public class CommentVO {
	private int commentID;
	private int num;
	private String userID;
	private String userName;
	private String commentDate;
	private String commentText;
	private int commentAvilable;
	private String imageUID;
	
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public String getImageUID() {
		return imageUID;
	}
	public void setImageUID(String imageUID) {
		this.imageUID = imageUID;
	}
}