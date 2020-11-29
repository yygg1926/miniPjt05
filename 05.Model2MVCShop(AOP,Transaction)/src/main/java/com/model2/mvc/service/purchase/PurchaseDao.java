package com.model2.mvc.service.purchase;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;

public interface PurchaseDao {
	
	public int addPurchase(Purchase purchase)throws Exception;
	
	public Purchase getPurchase(int tranNo)throws Exception;
	
	public Purchase getPurchase2(int prodNo)throws Exception;
	
	public List<Purchase> getPurchaseList(Search search, String buyerId)throws Exception;
	
	public List<Purchase> getSaleList(Search search) throws Exception;
	
	public int updatePurchase(Purchase purchase)throws Exception;
	
	public int updateTranCode(Purchase purchase)throws Exception;
	
	public int getTotalCount(Search search)throws Exception;
	
	public int removePurchase(int tranNo)throws Exception;
}
