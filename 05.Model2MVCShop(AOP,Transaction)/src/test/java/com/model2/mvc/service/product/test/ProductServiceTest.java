package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
		"classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml" })
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws Exception{
	
		//==> SqlSessionFactoryBean.getSqlSession()을 이용 SqlSession instance GET
				
				
				//==> Test 용 User instance 생성  
				Product product = new Product();
				product.setProdNo(10010);
				product.setProdName("Hong");
				product.setProdDetail("seong");
				product.setManuDate("2020-11-11");
				product.setPrice(30000);
				product.setFileName("min");
				
				productService.addProduct(product);
				
				product = productService.getProduct(10010);
				
				Assert.assertEquals(10010, product.getProdNo());
				Assert.assertEquals("Hong", product.getProdName());
				Assert.assertEquals("seong", product.getProdDetail());
				Assert.assertEquals("2020-11-11", product.getManuDate());
				Assert.assertEquals(30000, product.getPrice());
				Assert.assertEquals("min", product.getFileName());
				
	}
	
	@Test
	public void testGetProduct() throws Exception{
		
		Product product = new Product();
		
		product = productService.getProduct(10010);
		
		Assert.assertEquals(10010, product.getProdNo());
		Assert.assertEquals("Hong", product.getProdName());
		Assert.assertEquals("seong", product.getProdDetail());
		Assert.assertEquals("2020-11-11", product.getManuDate());
		Assert.assertEquals(30000, product.getPrice());
		Assert.assertEquals("min", product.getFileName());
		
	}
	
	@Test
	public void testUpdateProduct() throws Exception{
		
		Product product = new Product();
		
		product = productService.getProduct(10010);
		Assert.assertNotNull(product);
		
		Assert.assertEquals(10010, product.getProdNo());
		Assert.assertEquals("Hong", product.getProdName());
		Assert.assertEquals("2020-11-11", product.getManuDate());
		Assert.assertEquals("min", product.getFileName());
		
		product.setProdDetail("따라잡는중");
		product.setPrice(50000);
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10010);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("따라잡는중", product.getProdDetail());
		Assert.assertEquals(50000, product.getPrice());
		
	}
	
	@Test
	public void testGetProductList() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	@Test
	public void testRemoveProduct() throws Exception{
		
		Product product = new Product();
		
		product = productService.getProduct(10010);
		
		productService.removeProduct("Hong");
		
		Assert.assertNotNull(product);
					
		//END::SqlSession  close
		System.out.println("::END:: 닫기..");
				
				
	}//end of main
}//end of class