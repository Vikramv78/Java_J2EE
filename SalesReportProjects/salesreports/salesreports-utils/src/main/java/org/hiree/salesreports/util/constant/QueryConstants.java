package org.hiree.salesreports.util.constant;

public class QueryConstants {
	public static class SOLD_USING {

		public static final String SELECT_SOLD_USING_USER_ID = "SELECT * from SOLD_USING WHERE USERS_ID = ?";
		public static final String CHECK_SOLD_USING_USER_ID_SOLD_NAME_EXIST = "SELECT COUNT(*) from SOLD_USING WHERE USERS_ID = ? and SOLD_NAME=?";
     	public static final String GET_SOLD_USING_ID_SEQ = "select SOLD_USING_ID_SEQ.nextval from dual";
		public static final String ADD_SOLD_USING = "insert into SOLD_USING (USERS_ID,SOLD_USING_ID,SOLD_NAME,CRET_DTM,CRET_LGN_UID) VALUES(?, SOLD_USING_ID_SEQ.nextval, ?, sysdate,?)";
		
		public static final String SELECT_SOLD_USING_ALL_USERD =  "SELECT distinct(sold_name) sold_name from SOLD_USING";
		
		public static final String SELECT_SOLD_USING_BY_NAME =  "SELECT SOLD_USING_ID from SOLD_USING  WHERE SOLD_USING_NAME in (:SOLD_USING_NAME)";
		
		
						
	}
	public static class USERS {

		public static final String SELECT_USERS_BY_USER_NAME_AND_PASSWORD = "select * from users where USER_LOGIN_NAME = ? and USER_LOGIN_Password = ?";
		
						
	}
	public static class SHOP_FROM {

		public static final String SELECT_SHOP_FROM_USER_ID = "SELECT * from SHOP_FROM WHERE USERS_ID = ?";
		
		public static final String SELECT_SHOP_FROM_ALL_USER = "SELECT distinct(SHOP_FROM_NAME) from SHOP_FROM";
		//public static final String CHECK_SHOP_FROM_USER_ID_SHOP_FROM_NAME_EXIST = "SELECT COUNT(*) from SOLD_USING WHERE USERS_ID = ? and SOLD_NAME=?";
		public static final String SELECT_SHOP_FROM_BY_NAME = "SELECT SHOP_FROM_ID from SHOP_FROM  WHERE SHOP_FROM_NAME in (:SHOPFROM_NAME)";
     	
		public static final String ADD_SHOP_FROM = "insert into SHOP_FROM(USERS_ID,SHOP_FROM_ID,SHOP_FROM_NAME,SHOP_FROM_TYPE,CRET_DTM,CRET_LGN_UID) VALUES(?, SHOP_FROM_ID_SEQ.nextval, ?, ?,sysdate,?)";
						
	}
	public static class ITEM_CATEGORIES {

		public static final String SELECT_ITEM_CATEGORIES_USER_ID = "SELECT * from ITEM_CATEGORIES  WHERE USERS_ID = ?";
		
		public static final String SELECT_ITEM_CATEGORIES_BU_CATEGORIES_NAME = "SELECT ITEM_CATEGORIES_ID from ITEM_CATEGORIES  WHERE ITEM_CATEGORIES_NAME in (:CATEGORIES_NAME)";
		public static final String SELECT_ITEM_CATEGORIES_ALL_USERD =  "SELECT distinct(ITEM_CATEGORIES_NAME) ITEM_CATEGORIES_NAME from ITEM_CATEGORIES";
		    	
		public static final String ADD_ITEM_CATEGORIES= "insert into ITEM_CATEGORIES(USERS_ID,ITEM_CATEGORIES_ID,ITEM_CATEGORIES_NAME,ITEM_CATEGORIES_SUB_TYPE,CRET_DTM,CRET_LGN_UID) VALUES(?, ITEM_CATEGORIES_ID_SEQ.nextval, ?, ?,sysdate,?)";
						
	}
	public static class PAYMENT_METHOD {	
		public static final String SELECT_PAYMENT_METHOD_USER_ID = "SELECT * from PAYMENT_METHOD WHERE USERS_ID = ?";
		public static final String SELECT_PAYMENT_METHOD_ALL_USER = "SELECT distinct(PAYMENT_METHOD_NAME)  from PAYMENT_METHOD";
		public static final String SELECT_PAYMENT_BY_PAYMENT_NAME = "SELECT PAYMENT_METHOD_ID from PAYMENT_METHOD  WHERE PAYMENT_METHOD_NAME in (:PAYMENT_NAME)";
		public static final String ADD_PAYMENT_METHOD = "insert into PAYMENT_METHOD(USERS_ID,PAYMENT_METHOD_ID,PAYMENT_METHOD_NAME,PAYMENT_METHOD_TYPE,CRET_DTM,CRET_LGN_UID) VALUES(?, PAYMENT_METHOD_ID_SEQ.nextval, ?, ?,sysdate,?)";
	}
	public static class ITEM_TRANSACTION {
		
	   public static final String SELECT_ITEM_TRANSACTION_BY_USERID = "select it.USERS_ID,us.FIRST_NAME,us.LAST_NAME,us.EMAIL_ID,ITEM_TRANSACTION_ID,sf.SHOP_FROM_ID,pm.PAYMENT_METHOD_ID,ITEM_NAME,QUANTITY,BUY_PRICE_TAX,SHIPPING_FEE,LISTING_FEE,FINAL_VALUE_FEE,PAYPAL_FEE,OTHER_FEES,SOLD_USING_ITEM_ID,"
				+ "COMMENTS,SHIPPING_TRACK_ID,SHIPPING_COMPANY,SOLD_USING_ID,SOLD,SOLD_PRICE_TAX,BUY_DATE,SOLD_DATE,PROFIT,it.ITEM_CATEGORIES_ID  ITEM_CATEGORIES_ID,ic.item_categories_name item_categories_name ,sf.shop_from_name shop_from_name ,"
				+ "pm.PAYMENT_METHOD_name PAYMENT_METHOD_name, (select sold_name from sold_using where SOLD_USING_ID =  it.SOLD_USING_ID) sold_name,CUST_ADDRESS,CANCLE_ITEM from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm,users us where it.USERS_ID = ? and "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id and us.USERS_ID=it.USERS_ID order by ITEM_TRANSACTION_ID ";
		
	   public static final String SELECT_ALL_USERS_ITEM_TRANSACTION_BY_USERID = "select it.USERS_ID,us.FIRST_NAME,us.LAST_NAME,us.EMAIL_ID,ITEM_TRANSACTION_ID,sf.SHOP_FROM_ID,pm.PAYMENT_METHOD_ID,ITEM_NAME,QUANTITY,BUY_PRICE_TAX,SHIPPING_FEE,LISTING_FEE,FINAL_VALUE_FEE,PAYPAL_FEE,OTHER_FEES,SOLD_USING_ITEM_ID,"
				+ "COMMENTS,SHIPPING_TRACK_ID,SHIPPING_COMPANY,SOLD_USING_ID,SOLD,SOLD_PRICE_TAX,BUY_DATE,SOLD_DATE,PROFIT,it.ITEM_CATEGORIES_ID  ITEM_CATEGORIES_ID,ic.item_categories_name item_categories_name ,sf.shop_from_name shop_from_name ,"
				+ "pm.PAYMENT_METHOD_name PAYMENT_METHOD_name, (select sold_name from sold_using where SOLD_USING_ID =  it.SOLD_USING_ID) sold_name,CUST_ADDRESS,CANCLE_ITEM from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm ,users us where "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id and us.USERS_ID=it.USERS_ID order by ITEM_TRANSACTION_ID ";
		
		
		public static final String SELECT_SOLD_ONLY_ITEM_TRANSACTION_BY_USERID = "select it.USERS_ID,us.FIRST_NAME,us.LAST_NAME,us.EMAIL_ID,ITEM_TRANSACTION_ID,sf.SHOP_FROM_ID,pm.PAYMENT_METHOD_ID,ITEM_NAME,QUANTITY,BUY_PRICE_TAX,SHIPPING_FEE,LISTING_FEE,FINAL_VALUE_FEE,PAYPAL_FEE,OTHER_FEES,SOLD_USING_ITEM_ID,"
				+ "COMMENTS,SHIPPING_TRACK_ID,SHIPPING_COMPANY,SOLD_USING_ID,SOLD,SOLD_PRICE_TAX,BUY_DATE,SOLD_DATE,PROFIT,it.ITEM_CATEGORIES_ID  ITEM_CATEGORIES_ID,ic.item_categories_name item_categories_name ,sf.shop_from_name shop_from_name ,"
				+ "pm.PAYMENT_METHOD_name PAYMENT_METHOD_name, (select sold_name from sold_using where SOLD_USING_ID =  it.SOLD_USING_ID) sold_name,CUST_ADDRESS,CANCLE_ITEM from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm ,users us where it.USERS_ID = ? and "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id and us.USERS_ID=it.USERS_ID and SOLD='Y' and (it.cancle_item is null or it.cancle_item='N')  order by ITEM_TRANSACTION_ID ";
		
		public static final String SELECT_SEARCH_ITEM_TRANSACTION_BY_USERID = "select it.USERS_ID,us.FIRST_NAME,us.LAST_NAME,us.EMAIL_ID,ITEM_TRANSACTION_ID,sf.SHOP_FROM_ID,pm.PAYMENT_METHOD_ID,ITEM_NAME,QUANTITY,BUY_PRICE_TAX,SHIPPING_FEE,LISTING_FEE,FINAL_VALUE_FEE,PAYPAL_FEE,OTHER_FEES,SOLD_USING_ITEM_ID,"
				+ "COMMENTS,SHIPPING_TRACK_ID,SHIPPING_COMPANY,SOLD_USING_ID,SOLD,SOLD_PRICE_TAX,BUY_DATE,SOLD_DATE,PROFIT,it.ITEM_CATEGORIES_ID  ITEM_CATEGORIES_ID,ic.item_categories_name item_categories_name ,sf.shop_from_name shop_from_name ,"
				+ "pm.PAYMENT_METHOD_name PAYMENT_METHOD_name, (select sold_name from sold_using where SOLD_USING_ID =  it.SOLD_USING_ID) sold_name,CUST_ADDRESS,CANCLE_ITEM from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm ,users us where it.USERS_ID = ? and "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id and us.USERS_ID=it.USERS_ID";
		
		
		public static final String SELECT_ADMIN_SEARCH_ITEM_TRANSACTION = "select it.USERS_ID,us.FIRST_NAME,us.LAST_NAME,us.EMAIL_ID,ITEM_TRANSACTION_ID,sf.SHOP_FROM_ID,pm.PAYMENT_METHOD_ID,ITEM_NAME,QUANTITY,BUY_PRICE_TAX,SHIPPING_FEE,LISTING_FEE,FINAL_VALUE_FEE,PAYPAL_FEE,OTHER_FEES,SOLD_USING_ITEM_ID,"
				+ "COMMENTS,SHIPPING_TRACK_ID,SHIPPING_COMPANY,SOLD_USING_ID,SOLD,SOLD_PRICE_TAX,BUY_DATE,SOLD_DATE,PROFIT,it.ITEM_CATEGORIES_ID  ITEM_CATEGORIES_ID,ic.item_categories_name item_categories_name ,sf.shop_from_name shop_from_name ,"
				+ "pm.PAYMENT_METHOD_name PAYMENT_METHOD_name, (select sold_name from sold_using where SOLD_USING_ID =  it.SOLD_USING_ID) sold_name,CUST_ADDRESS,CANCLE_ITEM from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm ,users us where "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id and us.USERS_ID=it.USERS_ID";
		
		public static final String SELECT_ADMIN_SEARCH_ITEM_TRANSACTION_SUM_TOTAL = " select extract(year from sold_date) as year,extract(month from sold_date) as numberMon, CASE WHEN extract(month from sold_date) =1 THEN 'JAN' "
				+ "WHEN extract(month from sold_date) =2 THEN 'FEB' "
				+ "WHEN extract(month from sold_date) =3 THEN 'MAR' "
				+ "WHEN extract(month from sold_date) =4 THEN 'APR' "
				+ "WHEN extract(month from sold_date) =5 THEN 'MAY' "
				+ "WHEN extract(month from sold_date) =6 THEN 'JUNE' "
				+ "WHEN extract(month from sold_date) =7 THEN 'JULY' "
				+ "WHEN extract(month from sold_date) =8 THEN 'AUGUST' "
				+ "WHEN extract(month from sold_date) =9 THEN 'SEPTEMEBR' "
				+ "WHEN extract(month from sold_date) =10 THEN 'OCTOBER' "
				+ "WHEN extract(month from sold_date) =11 THEN 'NOVEMBER' "
				+ "WHEN extract(month from sold_date) =12 THEN 'DECEMBER' "
				+ "END as month,sum(sold_price_tax) sumSoldPrice,sum(profit)sumProfit,sum(buy_price_tax) sumBuyPrice,sum(shipping_fee) sumShippingFee,(sum(sold_price_tax) - sum(shipping_fee)) soldWithOutShipping from "
				+ "ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm where  ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id " ;
				
		
		public static final String SELECT_ADMIN_SEARCH_ITEM_TRANSACTION_SUM_TOTAL_GROUP = "  group by extract(year from sold_date), extract(month from sold_date) order by 1, 2 ";
		
		public static final String SELECT_SEARCH_TOTAL_PROFIT = "select  sum(it.profit) from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm where it.USERS_ID = ? and "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id ";
		
		public static final String SELECT_ADMIN_SEARCH_TOTAL_PROFIT = "select  sum(it.profit) from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm where "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id ";
		
		
		public static final String SELECT_SEARCH_TOTAL_SALES = "select  sum(it.SOLD_PRICE_TAX) from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm where it.USERS_ID = ? and "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id ";
		
		
		
		public static final String SELECT_ADMIN_SEARCH_TOTAL_SALES = "select  sum(it.SOLD_PRICE_TAX) from ITEM_TRANSACTION it ,item_categories ic ,shop_from sf ,payment_method pm where "
				+ " ic.item_categories_id = it.item_categories_id and sf.shop_from_id=it.shop_from_id and pm.payment_method_id =it.payment_method_id ";
		
				
		public static final String ADD_ITEM_TRANSACTION = "insert into ITEM_TRANSACTION (USERS_ID,ITEM_TRANSACTION_ID,SHOP_FROM_ID,PAYMENT_METHOD_ID,ITEM_NAME,QUANTITY,BUY_PRICE_TAX,SHIPPING_FEE,Listing_Fee,FINAL_VALUE_FEE,PAYPAL_FEE,OTHER_FEEs,SOLD_USING_ITEM_ID,"
				+ "SOLD_USING_ID,BUY_DATE,SOLD_DATE,SOLD,BILL_PICS,SOLD_PRICE_TAX,COMMENTS,SHIPPING_TRACK_ID,SHIPPING_COMPANY,profit,ITEM_CATEGORIES_ID,CUST_ADDRESS,CANCLE_ITEM,CRET_DTM,CRET_LGN_UID) VALUES(?, ITEM_TRANSACTION_ID_SEQ.nextval, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate,?)";
		
		public static final String UPDATE_ITEM_TRANSACTION = "update ITEM_TRANSACTION  set USERS_ID=?,SHOP_FROM_ID=?,PAYMENT_METHOD_ID=?,ITEM_NAME=?,QUANTITY=?,BUY_PRICE_TAX=?,SHIPPING_FEE=?,Listing_Fee=?,FINAL_VALUE_FEE=?,PAYPAL_FEE=?,OTHER_FEEs=?,SOLD_USING_ITEM_ID=?,"
				+ " SOLD_USING_ID=?,BUY_DATE=?,SOLD_DATE=?,SOLD=?,BILL_PICS=?,SOLD_PRICE_TAX=?,COMMENTS=?,SHIPPING_TRACK_ID=?,SHIPPING_COMPANY=?,profit=?,ITEM_CATEGORIES_ID=?,CUST_ADDRESS=?,CANCLE_ITEM=?,LST_UPDT_DTM=sysdate,LST_UPDT_LGN_UID =? where  ITEM_TRANSACTION_ID = ?";
		
		public static final String DELETE_ITEM_TRANSACTION = "DELETE FROM ITEM_TRANSACTION WHERE ITEM_TRANSACTION_ID =?";
		
		public static final String GET_ITEM_TRANSACTION_BILL_PICS_BLOB= "Select  BILL_PICS  From ITEM_TRANSACTION WHERE ITEM_TRANSACTION_ID = ?";
		
		public static final String GET_TOTAL_PROFIT= "select sum(profit) from ITEM_TRANSACTION where  SOLD='Y' and (cancle_item is null or cancle_item='N') and USERS_ID = ?";
		
		public static final String GET_TOTAL_SALES= "select sum(SOLD_PRICE_TAX) from ITEM_TRANSACTION where  SOLD='Y' and USERS_ID = ?";
		
						
	}
}
