package com.haven.dao;

import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.haven.model.Book;

/**
 *  与Book.xml文件对应的接口，方法对应XML文件中的增删改查操作
 */
@Repository("bookDao")
@Scope("prototype")
public interface BookDAO {

	/**
	 * 插入一本书
	 */
	public int insertOne(Book book);
	
	/**
	 * 批量插入图书
	 */
	public int insertBatch(List<Book> bookList);
	
	/**
	 * 删除一本书
	 */
	public int deleteOne(int bookId);
	
	/**
	 * 批量删除图书
	 */
	public int deleteBatch(int[] ids);
	
	/**
	 * 修改图书信息
	 */
	public int update(Book book);
	
	/**
	 * 下单成功，修改图书的销量以及库存量
	 * 传入的销售量和库存量应该是相等的，即订单中图书的购买量
	 */
	public int updateFromOrder(Book book);
	
	/**
	 * 获取（店铺内）销量最好的4本书
	 */
	public List<Book> getBookWithHotSale(String storeName);
	
	/**
	 * 获取（店铺内）最新上传的4本书
	 */
	public List<Book> getBookWithNew(String storeName);
	
	/**
	 * 根据（店铺内）图书名称或者类别查询图书，分页显示
	 */
	public List<Book> getBookByPage(Map<String, Object> parameter);
	
	/**
	 * 根据图书编号获取图书详细信息
	 */
	public Book getBookInfo(int bookId);
	
	/**
	 * 插入图书前判断图书是否已经存在
	 * 根据图书名、作者以及店铺名同时确定
	 */
	public Book isExisted(Book book);
	
}
