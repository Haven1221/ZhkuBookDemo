package com.haven.interfaces;

import java.util.List;

import com.haven.model.BookComment;

/**
 *  与BookComment.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
public interface BookCommentInterface {

	/**
	 * 插入一条评论
	 */
	public int insert(BookComment comment);
	
	/**
	 * 根据编号删除一条评论
	 */
	public int deleteOne(int commentId);
	
	/**
	 * 用于用户批量删除评论
	 */
	public int deleteBatch(List<Integer> ids);
	
	/**
	 * 用户获取自己的评论
	 */
	public List<BookComment> getComment(String userName);
	
}
