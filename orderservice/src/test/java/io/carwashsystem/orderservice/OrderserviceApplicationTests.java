package io.carwashsystem.orderservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import io.carwashsystem.orderservice.model.OrderDetails;
import io.carwashsystem.orderservice.repo.OrderRepository;
import io.carwashsystem.orderservice.service.OrderService;

@SpringBootTest
class OrderserviceApplicationTests {

	@Mock
	OrderRepository repository;
	
	@InjectMocks
	OrderService orderService;
	
	
	public List<OrderDetails> myorders;
	
	@Test
	@Order(1)
	public void test_getOrders() {
		List<OrderDetails> myorders = new ArrayList<OrderDetails>();
		myorders.add(new OrderDetails(25,"Swing","Sg125","TejaKrishna",1,12,76));
		myorders.add(new OrderDetails(22,"Sw","Sg","Teja",2,26,73));
		
		when(repository.findAll()).thenReturn(myorders);
		assertEquals(2,orderService.getUsers().size());
	}
	
	@Test
	@Order(2)
	public void test_addorder() {
		OrderDetails order = new OrderDetails(21,"S","S123","Teju",3,27,74);
		
		when(repository.save(order)).thenReturn(order);
		assertEquals(order,orderService.addorder(order));
	}
	
	@Test
	@Order(3)
	public void test_deleteOrder() {
		OrderDetails order = new OrderDetails(21,"S","S123","Teju",3,27,74);
		
		orderService.deleteUser(order);
		verify(repository,times(1)).delete(order);
	}



}
