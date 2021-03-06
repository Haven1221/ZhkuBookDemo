package com.haven.daotest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.haven.bean.PageBean;
import com.haven.dao.BookCommentDAO;
import com.haven.dao.BookDAO;
import com.haven.model.Book;
import com.haven.model.BookComment;
import com.haven.util.MyBatisUtil;

public class BookTest {

	private SqlSession sqlSession = null;
	private BookDAO bi = null;
	
	@Test
	public void testBook_insertOne() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			Book b = new Book(1, "当当书城", "978-456456-123", "道德情操论", "亚当斯密", "中央编译出版社", 39.99, 0, 100, 
					"哲学", 437, "温总理五次推荐阅读", "/daodeqingcaolun.jpg");
			int count = bi.insertOne(b);
			System.out.println("插入行数："+ count);
			System.out.println("图书编号："+ b.getBookId());
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testBook_insertBatch() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			List<Book> bookList = new ArrayList<Book>();
			bookList.add(new Book(3, "京东购书", "978-456456-123", "道德情操论", "亚当斯密", "中央编译出版社", 39.99, 0, 100, 
					"哲学", 437, "温总理五次推荐阅读", "/daodeqingcaolun.jpg"));
			bookList.add(new Book(3, "京东购书", "978-456456-123", "道德情操论", "亚当斯密", "中央编译出版社", 39.99, 0, 100, 
					"哲学", 437, "温总理五次推荐阅读", "/daodeqingcaolun.jpg"));
			bookList.add(new Book(3, "京东购书", "978-456456-123", "道德情操论", "亚当斯密", "中央编译出版社", 39.99, 0, 100, 
					"哲学", 437, "温总理五次推荐阅读", "/daodeqingcaolun.jpg"));
			int count = bi.insertBatch(bookList);
			System.out.println("插入的行数："+ count);
			for (Book b : bookList) {
				System.out.println("插入的图书编号："+ b.getBookId());
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testBook_deleteOne() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			int count = bi.deleteOne(4);
			System.out.println("删除的行数："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testBook_deleteBatch() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			int[] ids = {2, 5, 8};
			int count = bi.deleteBatch(ids);
			System.out.println("删除的行数："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testBook_update() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			Book b = new Book();
			b.setBookId(9);
			b.setBookName("道德情操论");
			b.setBookPrice(49.99);
			b.setBookDescript("温家宝总理五次推荐阅读...");
			int count = bi.update(b);
			System.out.println("修改的行数为："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testBook_udpateFromOrder() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			Book b = new Book();
			b.setBookId(1);
			b.setBookSaleNum(2);
			b.setBookSumNum(2);
			int count = bi.updateFromOrder(b);
			System.out.println("修改的行数为："+ count);
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	@Test
	public void testBook_getBookWithHotSale() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
//			List<Book> bookList = bi.getBookWithHotSale(null);
			List<Book> bookList = bi.getBookWithHotSale("京东购书");
			if(bookList != null && !bookList.isEmpty()) {
				for (Book b : bookList) {
					System.out.println("图书编号："+ b.getBookId());
					System.out.println("图书名称："+ b.getBookName());
					System.out.println("店铺编号："+ b.getStoreId());
					System.out.println("图书店铺："+ b.getStoreName());
					System.out.println("图书单价："+ b.getBookPrice());
					System.out.println("==========================");
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
	
	@Test
	public void testBook_getBookWithNew() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
//			List<Book> bookList = bi.getBookWithNew(null);
			List<Book> bookList = bi.getBookWithNew("京东购书");
			if(bookList != null && !bookList.isEmpty()) {
				for (Book b : bookList) {
					System.out.println("图书编号："+ b.getBookId());
					System.out.println("图书名称："+ b.getBookName());
					System.out.println("店铺编号："+ b.getStoreId());
					System.out.println("图书店铺："+ b.getStoreName());
					System.out.println("图书单价："+ b.getBookPrice());
					System.out.println("==========================");
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
	
	@Test
	public void testBook_getBookByPage() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			Map<String, Object> parameter = new HashMap<String, Object>();
			PageBean pb = new PageBean();
			pb.setCurrentPage(1);
			Book book = new Book();
//			book.setStoreName("京东购书");
			book.setBookName("情操");
//			book.setBookClass("哲");
			parameter.put("page", pb);
			parameter.put("book", book);
			List<Book> bookList = bi.getBookByPage(parameter);
			if(bookList != null && !bookList.isEmpty()) {
				for (Book b : bookList) {
					System.out.println("图书编号："+ b.getBookId());
					System.out.println("图书名称："+ b.getBookName());
					System.out.println("店铺编号："+ b.getStoreId());
					System.out.println("图书店铺："+ b.getStoreName());
					System.out.println("图书单价："+ b.getBookPrice());
					System.out.println("==========================");
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
	
	@Test
	public void testBook_getBookInfo() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			BookCommentDAO ci = sqlSession.getMapper(BookCommentDAO.class);
			Book book = bi.getBookInfo(1);
			if(book != null) {
				System.out.println("图书编号："+ book.getBookId());
				System.out.println("图书名称："+ book.getBookName());
				System.out.println("图书页数："+ book.getBookPage());
				BookComment comment = new BookComment();
				comment.setBookId(book.getBookId());
				List<BookComment> commentList = ci.getComment(comment);
				if(commentList != null && commentList.size() > 0) {
					System.out.println("图书评论如下：");
					for (BookComment c : commentList) {
						System.out.println("评论用户："+ c.getUserName());
						System.out.println("评论分数："+ c.getCommentScore());
						System.out.println("评论内容："+ c.getCommentContent());
						System.out.println("------------------------");
					}
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
	
	@Test
	public void testBook_isExisted() {
		try {
			sqlSession = MyBatisUtil.openSession();
			bi = sqlSession.getMapper(BookDAO.class);
			Book b = new Book(1, "当当书城", "978-456456-123", "情操论", "亚当斯密", "中央编译出版社", 39.99, 0, 100, 
					"哲学", 437, "温总理五次推荐阅读", "/daodeqingcaolun.jpg");
			if(bi.isExisted(b) != null) {
				System.out.println("该图书已存在！");
			} else {
				int count = bi.insertOne(b);
				System.out.println("插入行数："+ count);
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
