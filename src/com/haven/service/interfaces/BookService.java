package com.haven.service.interfaces;

import java.util.List;
import java.util.Map;

import com.haven.model.Book;
import com.haven.model.BookComment;

/**
 * 图书业务层的接口
 */
public interface BookService {

	/**
	 * 添加图书，包括单条添加和批量添加，插入前判断图书是否存在，若存在则值修改图书数量
	 * @param bookList	：图书列表
	 * @return			：成功则返回true，否则返回false。
	 */
	boolean addBook(List<Book> bookList);
	
	/**
	 * 根据图书编号删除图书，包括单条删除和批量删除
	 * @param ids	：图书编号
	 * @return		：成功则返回true，否则返回false。
	 */
	boolean deleteBook(String[] ids);
	
	/**
	 * 根据图书编号修改图书信息
	 * @param book	：图书对象
	 * @return		：成功则返回true，否则返回false。
	 */
	boolean updateBook(Book book);
	
	/**
	 * 用户进入首页获取店铺时，获取最近上新和销量最好的图书
	 * @param storeName	：店铺名
	 * @return			：成功则返回图书信息集合，否则返回null。
	 */
	Map<String, List<Book>> getInitBook(String storeName);
	
	/**
	 * 根据书名、类别或者店铺名查询图书，分页显示
	 * @param storeName		：图书所属店铺
	 * @param bookName		：图书名称
	 * @param bookClass		：图书类别
	 * @param currentPage	：显示的当前页数
	 * @return				：成功则返回图书信息，否则返回null。
	 */
	List<Book> searchBookByPage(String storeName, String bookName, String bookClass, String currentPage);
	
	/**
	 * 根据图书编号查看图书详细信息
	 * @param bookId	：图书编号
	 * @return			：成功则返回图书信息，否则返回null。
	 */
	Book getBookInfo(String bookId);
	
	/**
	 * 根据图书编号或者用户名获取评论信息
	 * @param userName	：用户名
	 * @param bookId	：图书编号
	 * @return			：成功则返回评论信息，否则返回null。
	 */
	List<BookComment> getComment(String userName, String bookId);
	
	/**
	 * 添加图书评论，买了此书方可评价
	 * @param comment	：评论对象
	 * @return			：成功则返回true，否则返回false。
	 */
	boolean addComment(BookComment comment);
	
	/**
	 * 根据评论编号删除评论信息
	 * @param ids	：评论编号
	 * @return		：成功则返回true，否则返回false。
	 */
	boolean deleteComment(String[] ids);
	
}
