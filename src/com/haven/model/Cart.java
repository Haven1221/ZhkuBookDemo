package com.haven.model;

/**
 * 购物车实体类，对应数据库中的购物车表
 */
public class Cart {

	private int cartId;// 购物车编号
	private int bookId;// 图书编号
	private String userName;// 购物车所属用户
	private String storeName;// 图书所属店铺
	private String bookName;// 购物车图书名称
	private double bookPrice;// 购物车图书单价
	private int bookCount;// 购物车每本书的数量
	private String bookImage;// 图书封面
	
	public Cart() {
	}

	public Cart(int bookId, String userName, String storeName, String bookName, double bookPrice, int bookCount,
			String bookImage) {
//		super();
		this.bookId = bookId;
		this.userName = userName;
		this.storeName = storeName;
		this.bookName = bookName;
		this.bookPrice = bookPrice;
		this.bookCount = bookCount;
		this.bookImage = bookImage;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getBookCount() {
		return bookCount;
	}

	public void setBookCount(int bookCount) {
		this.bookCount = bookCount;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}
	
}
