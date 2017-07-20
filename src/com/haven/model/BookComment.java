package com.haven.model;

/**
 * 图书评论实体类，对应数据库中的评论表
 */
public class BookComment {

	private int commentId; // 评论编号
	private int bookId; // 图书编号
	private int orderId; // 评论所属订单
	private String userName; // 评论用户
	private int commentScore; // 评论分数
	private String commentContent; // 评论内容
	
	public BookComment() {
	}

	public BookComment(int bookId, int orderId, String userName, int commentScore, String commentContent) {
		super();
		this.bookId = bookId;
		this.orderId = orderId;
		this.userName = userName;
		this.commentScore = commentScore;
		this.commentContent = commentContent;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getCommentScore() {
		return commentScore;
	}

	public void setCommentScore(int commentScore) {
		this.commentScore = commentScore;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
}
