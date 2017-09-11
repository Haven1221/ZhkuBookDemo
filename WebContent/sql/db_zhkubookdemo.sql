/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50020
Source Host           : localhost:3306
Source Database       : db_zhkubookdemo

Target Server Type    : MYSQL
Target Server Version : 50020
File Encoding         : 65001

Date: 2017-09-11 10:37:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `tb_address`
-- ----------------------------
DROP TABLE IF EXISTS `tb_address`;
CREATE TABLE `tb_address` (
  `addrId` int(11) NOT NULL auto_increment,
  `addrProvince` varchar(255) NOT NULL,
  `addrDetail` varchar(255) NOT NULL,
  `addrPhone` varchar(255) NOT NULL,
  `addrConsinee` varchar(255) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY  (`addrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_address
-- ----------------------------
INSERT INTO tb_address VALUES ('1', '广东省茂名市化州市', '化州市第一中学', '15800000000', '吴虹婷', '1');
INSERT INTO tb_address VALUES ('4', '广东省广州市', '海珠区仲恺路500号', '18814142805', '黄华冬', '1');

-- ----------------------------
-- Table structure for `tb_book`
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `bookId` int(11) NOT NULL auto_increment,
  `storeId` int(11) NOT NULL,
  `storeName` varchar(255) NOT NULL,
  `bookISBN` varchar(255) NOT NULL,
  `bookName` varchar(255) NOT NULL,
  `bookAuthor` varchar(255) NOT NULL,
  `bookPublisher` varchar(255) NOT NULL,
  `bookPrice` double NOT NULL,
  `bookSaleNum` int(11) NOT NULL,
  `bookSumNum` int(11) NOT NULL,
  `bookClass` varchar(255) NOT NULL,
  `bookPage` int(11) NOT NULL,
  `bookDescript` varchar(255) NOT NULL,
  `bookImage` varchar(255) NOT NULL,
  PRIMARY KEY  (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO tb_book VALUES ('1', '1', '当当书城', '978-456456-123', '道德情操论', '亚当斯密', '中央编译出版社', '49.99', '2', '98', '哲学', '437', '温家宝总理五次推荐阅读...', '/daodeqingcaolun.jpg');
INSERT INTO tb_book VALUES ('3', '2', '淘书网', '978-456456-123', '道德情操论', '亚当斯密', '中央编译出版社', '39.99', '3', '97', '哲学', '437', '温总理五次推荐阅读', '/daodeqingcaolun.jpg');
INSERT INTO tb_book VALUES ('6', '3', '京东购书', '978-456456-123', '道德情操论', '亚当斯密', '中央编译出版社', '39.99', '2', '98', '哲学', '437', '温总理五次推荐阅读', '/daodeqingcaolun.jpg');
INSERT INTO tb_book VALUES ('7', '3', '京东购书', '978-456456-123', '道德情操论', '亚当斯密', '中央编译出版社', '39.99', '0', '100', '哲学', '437', '温总理五次推荐阅读', '/daodeqingcaolun.jpg');
INSERT INTO tb_book VALUES ('9', '1', '当当书城', '978-456456-123', '道德情操论', '亚当斯密', '中央编译出版社', '49.99', '2', '98', '哲学', '437', '温家宝总理五次推荐阅读...', '/daodeqingcaolun.jpg');

-- ----------------------------
-- Table structure for `tb_bookcomment`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookcomment`;
CREATE TABLE `tb_bookcomment` (
  `commentId` int(11) NOT NULL auto_increment,
  `bookId` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `commentScore` int(11) NOT NULL,
  `commentContent` varchar(255) NOT NULL,
  PRIMARY KEY  (`commentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_bookcomment
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_cart`
-- ----------------------------
DROP TABLE IF EXISTS `tb_cart`;
CREATE TABLE `tb_cart` (
  `cartId` int(11) NOT NULL auto_increment,
  `bookId` int(11) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `storeName` varchar(255) NOT NULL,
  `bookName` varchar(255) NOT NULL,
  `bookPrice` double NOT NULL,
  `bookCount` int(11) NOT NULL,
  `bookImage` varchar(255) NOT NULL,
  PRIMARY KEY  (`cartId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_cart
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `orderId` int(11) NOT NULL auto_increment,
  `storeName` varchar(255) NOT NULL,
  `orderDate` varchar(255) NOT NULL,
  `orderPrice` double NOT NULL,
  `userName` varchar(255) NOT NULL,
  `orderState` varchar(255) NOT NULL,
  `addrId` int(11) NOT NULL,
  PRIMARY KEY  (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO tb_order VALUES ('1', '京东购书', '2017-07-19 21:37', '99.99', 'Haven', '配送中', '1');
INSERT INTO tb_order VALUES ('2', '淘书网', '2017-07-19 21:37', '99.99', 'Haven', '正在出库', '1');
INSERT INTO tb_order VALUES ('3', '当当书城', '2017-07-19 21:37', '99.99', 'Haven', '正在出库', '1');

-- ----------------------------
-- Table structure for `tb_orderproduct`
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderproduct`;
CREATE TABLE `tb_orderproduct` (
  `productId` int(11) NOT NULL auto_increment,
  `orderId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `productPrice` double NOT NULL,
  `productCount` int(11) NOT NULL,
  `productImage` varchar(255) NOT NULL,
  PRIMARY KEY  (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_orderproduct
-- ----------------------------
INSERT INTO tb_orderproduct VALUES ('1', '1', '6', '道德情操论', '39.99', '1', '/daodeqingcaolun.jpg');
INSERT INTO tb_orderproduct VALUES ('2', '2', '3', '道德情操论', '39.99', '1', '/daodeqingcaolun.jpg');
INSERT INTO tb_orderproduct VALUES ('3', '3', '1', '道德情操论', '49.99', '3', '/daodeqingcaolun.jpg');
INSERT INTO tb_orderproduct VALUES ('4', '3', '6', '道德情操论', '39.99', '1', '/daodeqingcaolun.jpg');

-- ----------------------------
-- Table structure for `tb_store`
-- ----------------------------
DROP TABLE IF EXISTS `tb_store`;
CREATE TABLE `tb_store` (
  `storeId` int(11) NOT NULL auto_increment,
  `storeName` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `storeSaleNum` int(11) NOT NULL,
  `storeSale` double NOT NULL,
  `storeScore` int(11) NOT NULL,
  `storeComment` int(11) NOT NULL,
  `storeEvaluate` double NOT NULL,
  `storeDescript` varchar(255) NOT NULL,
  `storeLicense` varchar(255) NOT NULL,
  `storeState` varchar(255) NOT NULL,
  `storeOpenDate` varchar(255) NOT NULL,
  PRIMARY KEY  (`storeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_store
-- ----------------------------
INSERT INTO tb_store VALUES ('1', '当当书城', 'Haven', '1', '49.99', '9', '2', '4.5', '好书店', '/dangdangshucheng.jpg', '休息中', '2017-07-20 16:09');
INSERT INTO tb_store VALUES ('2', '淘书网', 'Jayin', '0', '0', '0', '0', '0', '良心卖书，童叟无欺', '/taoshuwang.jpg', '营业中', '2017-07-20 16:09');

-- ----------------------------
-- Table structure for `tb_tempstore`
-- ----------------------------
DROP TABLE IF EXISTS `tb_tempstore`;
CREATE TABLE `tb_tempstore` (
  `tempStoreId` int(11) NOT NULL auto_increment,
  `userName` varchar(255) NOT NULL,
  `tempStoreName` varchar(255) NOT NULL,
  `tempStoreDescript` varchar(255) NOT NULL,
  `tempStoreLicense` varchar(255) NOT NULL,
  `tempStoreState` varchar(255) NOT NULL,
  PRIMARY KEY  (`tempStoreId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_tempstore
-- ----------------------------
INSERT INTO tb_tempstore VALUES ('1', 'Haven', '当当书城', '好书店', '/dangdangshucheng.jpg', '通过');
INSERT INTO tb_tempstore VALUES ('6', 'Haven', '当当书城', '好书店', '/dangdangshucheng.jpg', '失败');
INSERT INTO tb_tempstore VALUES ('7', 'Haven', '当当书城', '好书店', '/dangdangshucheng.jpg', '正在审核');
INSERT INTO tb_tempstore VALUES ('8', 'Jayin', '淘书网', '良心卖书，童叟无欺', '/taoshuwang.jpg', '通过');

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `userId` int(11) NOT NULL auto_increment,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userType` varchar(255) NOT NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO tb_user VALUES ('1', 'Jayin', 'asdfasdf', '1');
INSERT INTO tb_user VALUES ('5', 'Haven', 'asdfasdfasdf', '2');
INSERT INTO tb_user VALUES ('6', '张三', 'sdf', '1');
INSERT INTO tb_user VALUES ('7', '李四', 'jkl', '1');
INSERT INTO tb_user VALUES ('8', '王五', 'sfal', '1');
INSERT INTO tb_user VALUES ('10', '测试', 'test', '1');
INSERT INTO tb_user VALUES ('11', '测试', 'test', '1');
INSERT INTO tb_user VALUES ('13', '测试', 'test', '1');
INSERT INTO tb_user VALUES ('15', '测试', 'test', '1');
INSERT INTO tb_user VALUES ('16', '测试', 'test', '1');
INSERT INTO tb_user VALUES ('17', '测试', 'test', '1');
