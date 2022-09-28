package io.carwashsystem.orderservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.carwashsystem.orderservice.controller.OrderController;
import io.carwashsystem.orderservice.model.OrderDetails;
import io.carwashsystem.orderservice.service.OrderService;

@SpringBootTest
public class OrderControllerTests {

	
	@Mock
	OrderService orderService;
	
	@InjectMocks
	OrderController orderController;
	
	List<OrderDetails> myorders;
	OrderDetails order;
	
	@Test
	public void test_addorder()
	{
		order = new OrderDetails(31,"Sap", "Sg", "TejaKrishna", 7, 29, 70);
		
       when(orderService.addorder(order)).thenReturn(order);
		ResponseEntity<String> res = orderController.addorder(order);
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		String result = "Order is Placed with Washer and will be Proceesed soon " + order;
		assertEquals(result,res.getBody());
	
	}
	
	@Test
	public void test_getorder() {
		myorders = new ArrayList<OrderDetails>();
		myorders.add(new OrderDetails(34,"SUV", "Swiz", "Ravi", 2, 23, 72));
		myorders.add(new OrderDetails(35,"SUV", "Swiz", "RaviRam", 3,23, 78));
		
		when(orderService.getUsers()).thenReturn(myorders);
		ResponseEntity<List<OrderDetails>> res = orderController.getorder();
		assertEquals(HttpStatus.FOUND,res.getStatusCode());
		assertEquals(2,res.getBody().size());
	}
	
}
