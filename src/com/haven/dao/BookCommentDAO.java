package com.haven.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.haven.model.BookComment;

/**
 *  与BookComment.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
@Repository("commentDao")
@Scope("prototype")
public interface BookCommentDAO {

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
	public int deleteBatch(int[] ids);
	
	/**
	 * 根据用户名或者图书编号获取图书评论
	 */
	public List<BookComment> getComment(BookComment comment);
	
}
