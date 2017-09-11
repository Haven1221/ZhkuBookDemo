package com.haven.daotest;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.haven.dao.BookCommentDAO;
import com.haven.model.BookComment;
import com.haven.util.MyBatisUtil;

public class BookCommentTest {

	private SqlSession sqlSession = null;
	private BookCommentDAO ci = null;
	
	@Test
	public void testComment_insert() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(BookCommentDAO.class);
			BookComment comment = new BookComment(1, 3, "Jayin", 4, "看着还行，看完在追评！");
			int count = ci.insert(comment);
			System.out.println("插入的行数："+ count);
			System.out.println("插入的评论编号："+ comment.getCommentId());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testComment_deleteOne() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(BookCommentDAO.class);
			int count = ci.deleteOne(1);
			System.out.println("删除行数："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testComment_deleteBatch() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(BookCommentDAO.class);
			int[] ids = {2, 3, 4};
			int count = ci.deleteBatch(ids);
			System.out.println("删除行数："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testComment_getComment() {
		try {
			sqlSession = MyBatisUtil.openSession();
			ci = sqlSession.getMapper(BookCommentDAO.class);
			BookComment comment = new BookComment();
			comment.setUserName("Haven");
//			comment.setBookId(1);
			List<BookComment> commentList = ci.getComment(comment);
			if(commentList != null && !commentList.isEmpty()) {
				for (BookComment c : commentList) {
					System.out.println("评论编号："+ c.getCommentId());
					System.out.println("订单号："+ c.getOrderId());
					System.out.println("图书编号："+ c.getBookId());
					System.out.println("评论分数："+ c.getCommentScore());
					System.out.println("评论内容："+ c.getCommentContent());
					System.out.println("============================");
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
}
