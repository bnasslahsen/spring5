package fr.training.samples.spring.shop.application.order;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.training.samples.spring.shop.domain.customer.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.customer.CustomerVO;
import fr.training.samples.spring.shop.domain.item.ItemEntity;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.item.ItemVO;
import fr.training.samples.spring.shop.domain.order.OrderEntity;
import fr.training.samples.spring.shop.domain.order.OrderRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderManagementImplTest {

	@Autowired
	private OrderManagement orderManagement;

	@MockBean
	private OrderRepository orderRepository;

	@MockBean
	private CustomerRepository customerRepository;

	@MockBean
	private ItemRepository itemRepository;

	@Test
	void addOrder() {
		final OrderEntity orderEntity = createOrder();
		when(orderRepository.addOrder(orderEntity)).thenReturn(orderEntity);
		final OrderEntity orderResult = orderManagement.addOrder(orderEntity);
		assertNotNull(orderResult);
	}

	@Test
	void getOrdersForCustomer() {
		final OrderEntity orderEntity1 = createOrder();
		final OrderEntity orderEntity2 = createOrder();
		final Set<OrderEntity> orders = Stream.of(orderEntity1, orderEntity2).collect(Collectors.toSet());
		when(orderRepository.getOrdersForCustomer("customerId")).thenReturn(orders);
		final Set<OrderEntity> ordersResult = orderManagement.getOrdersForCustomer("customerId");
		assertEquals(2, ordersResult.size());
	}

	@Test
	void findOne() {
		final OrderEntity orderEntity = createOrder();
		orderEntity.setId("testId");
		when(orderRepository.findOne("testId")).thenReturn(orderEntity);
		OrderEntity orderEntityResult = orderManagement.findOne("testId");
		assertEquals("nass", orderEntityResult.getCustomer().getCustomerVO().getName());
	}

	@Test
	void addOrders() {
     // TODO
	}

	private OrderEntity createOrder() {
		final CustomerEntity customer = new CustomerEntity(new CustomerVO("nass", "123456"));
		final ItemEntity itemEntity = new ItemEntity(new ItemVO("DESC99", 99));
		final OrderEntity orderEntity = new OrderEntity();
		orderEntity.setCustomer(customer);
		orderEntity.setItems(Stream.of(itemEntity).collect(Collectors.toSet()));
		return orderEntity;
	}
}