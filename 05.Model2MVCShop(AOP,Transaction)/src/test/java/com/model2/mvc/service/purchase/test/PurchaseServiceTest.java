package com.model2.mvc.service.purchase.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	//@Test
	public void testAddPurchase() throws Exception{
		
		User user = new User();
		user.setUserId("testUserId");
		user.setUserName("testUserName");
		user.setPassword("testPasswd");
		user.setSsn("1111112222222");
		user.setPhone("111-2222-3333");
		user.setAddr("경기도");
		user.setEmail("test@test.com");
		
		Product product = new Product();
		product.setProdNo(10010);
		product.setProdName("Hong");
		product.setProdDetail("seong");
		product.setManuDate("2020-11-11");
		product.setPrice(30000);
		product.setFileName("min");
		
		Purchase purchase = new Purchase();
		purchase.setBuyer(user);
		purchase.setDlvyAddr("seoul");
		purchase.setDlvyRequest("hurry");
		purchase.setPaymentOption("1");
		purchase.setPurchaseProd(product);
		purchase.setReceiverName("Hong");
		purchase.setReceiverPhone("010-4421");
		purchase.setTranNo(10010);
		purchase.setTranCode("0");
		
		purchaseService.addPurchase(purchase);
		purchase = purchaseService.getPurchase2(purchase.getPurchaseProd().getProdNo());
		
		Assert.assertEquals("testUserId", purchase.getBuyer().getUserId());
		Assert.assertEquals("seoul", purchase.getDlvyAddr());
		Assert.assertEquals("hurry", purchase.getDlvyRequest());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals(10010, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("Hong", purchase.getReceiverName());
		Assert.assertEquals("010-4421", purchase.getReceiverPhone());
		Assert.assertEquals(10010, purchase.getTranNo());
		Assert.assertEquals("0", purchase.getTranCode().trim());
		
	}
	
	//@Test
	public void testGetPurchase() throws Exception{
		
		User user = new User();
		user.setUserId("testUserId");
		user.setUserName("testUserName");
		user.setPassword("testPasswd");
		user.setSsn("1111112222222");
		user.setPhone("111-2222-3333");
		user.setAddr("경기도");
		user.setEmail("test@test.com");
		
		Product product = new Product();
		product.setProdNo(10010);
		product.setProdName("Hong");
		product.setProdDetail("seong");
		product.setManuDate("2020-11-11");
		product.setPrice(30000);
		product.setFileName("min");
		
		Purchase purchase = new Purchase();
		purchase = purchaseService.getPurchase(10010);
		
		Assert.assertEquals("testUserId", purchase.getBuyer().getUserId());
		Assert.assertEquals("seoul", purchase.getDlvyAddr());
		Assert.assertEquals("hurry", purchase.getDlvyRequest());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals(10010, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("Hong", purchase.getReceiverName());
		Assert.assertEquals("010-4421", purchase.getReceiverPhone());
		Assert.assertEquals(10010, purchase.getTranNo());
		Assert.assertEquals("0", purchase.getTranCode().trim());
	
	}
	
	
	//@Test
	public void testGetPurchaseList() throws Exception{
		
		User user = new User();
		user.setUserId("testUserId");
		user.setUserName("testUserName");
		user.setPassword("testPasswd");
		user.setSsn("1111112222222");
		user.setPhone("111-2222-3333");
		user.setAddr("경기도");
		user.setEmail("test@test.com");
		
		Map<String, Object> map = new HashMap<String, Object>();
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		
		
		map = purchaseService.getPurchaseList(search, user.getUserId());
		
		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(1, list.size());
		
		Integer totalCount=(Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		
	}
	
	//@Test
	public void testGetSaleList() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
map = purchaseService.getSaleList(search);
		
		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());
		Integer totalCount=(Integer)map.get("totalCount");
		System.out.println(totalCount);
	}

	
	//@Test
	public void testUpdatePurchase() throws Exception{
		
		Purchase purchase = purchaseService.getPurchase(10010);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("testUserId", purchase.getBuyer().getUserId());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals(10010, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("Hong", purchase.getReceiverName());
		Assert.assertEquals("010-4421", purchase.getReceiverPhone());
		Assert.assertEquals(10010, purchase.getTranNo());
		Assert.assertEquals("0", purchase.getTranCode().trim());
		
		purchase.setDlvyAddr("orangeCounty");
		purchase.setDlvyRequest("hurryHurry");
		
		purchaseService.updatePurchase(purchase);
		
		purchase = purchaseService.getPurchase(10010);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("testUserId", purchase.getBuyer().getUserId());
		Assert.assertEquals("orangeCounty", purchase.getDlvyAddr());
		Assert.assertEquals("hurryHurry", purchase.getDlvyRequest());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals(10010, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("Hong", purchase.getReceiverName());
		Assert.assertEquals("010-4421", purchase.getReceiverPhone());
		Assert.assertEquals(10010, purchase.getTranNo());
		Assert.assertEquals("0", purchase.getTranCode().trim());
		
	}
	
	
	//@Test
	public void testUpdateTranCode() throws Exception{
		
		Purchase purchase = purchaseService.getPurchase(10010);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("testUserId", purchase.getBuyer().getUserId());
		Assert.assertEquals("orangeCounty", purchase.getDlvyAddr());
		Assert.assertEquals("hurryHurry", purchase.getDlvyRequest());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals(10010, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("Hong", purchase.getReceiverName());
		Assert.assertEquals("010-4421", purchase.getReceiverPhone());
		Assert.assertEquals(10010, purchase.getTranNo());
		Assert.assertEquals("0", purchase.getTranCode().trim());
		
		purchaseService.updateTranCode(purchase);
		
		purchase = purchaseService.getPurchase(10010);
		Assert.assertNotNull(purchase);
		
		Assert.assertEquals("testUserId", purchase.getBuyer().getUserId());
		Assert.assertEquals("orangeCounty", purchase.getDlvyAddr());
		Assert.assertEquals("hurryHurry", purchase.getDlvyRequest());
		Assert.assertEquals("1", purchase.getPaymentOption().trim());
		Assert.assertEquals(10010, purchase.getPurchaseProd().getProdNo());
		Assert.assertEquals("Hong", purchase.getReceiverName());
		Assert.assertEquals("010-4421", purchase.getReceiverPhone());
		Assert.assertEquals(10010, purchase.getTranNo());
		Assert.assertEquals("1", purchase.getTranCode().trim());
	}
	
	
	@Test
	public void testRemovePurchase() throws Exception{
		Purchase purchase = new Purchase();
		
		purchase = purchaseService.getPurchase(10010);
		
		purchaseService.removePurchase(10010);
		
		Assert.assertNotNull(purchase);
		
		System.out.println("끝");
	}
}
