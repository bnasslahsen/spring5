package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.customer.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;
import fr.training.samples.spring.shop.domain.customer.CustomerVO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerManagementImplTest {

	@Autowired
	private CustomerManagement customerManagement;

	@MockBean
	private CustomerRepository customerRepository;

	@Test
	void testCreate() {
		CustomerEntity customerEntity = initCustomerEntity();
		when(customerRepository.create(customerEntity)).thenReturn(customerEntity);
		CustomerEntity customerResultEntity = customerManagement.create(customerEntity);
		assertEquals("NAME1", customerResultEntity.getCustomerVO().getName());
	}


	@Test
	void testFindOne() {
		CustomerEntity customerEntity = initCustomerEntity();
		customerEntity.setId("testId");
		when(customerRepository.findOne("testId")).thenReturn(customerEntity);
		CustomerEntity customerResultEntity = customerManagement.findOne("testId");
		assertEquals("NAME1", customerResultEntity.getCustomerVO().getName());
	}

	@Test
	void testUpdate() {
		CustomerEntity customerEntity = initCustomerEntity();
		customerEntity.setId("testId");
		when(customerRepository.update(customerEntity)).thenReturn(customerEntity);
		CustomerEntity customerResultEntity = customerManagement.update(customerEntity);
		assertEquals("NAME1", customerResultEntity.getCustomerVO().getName());
	}

	private CustomerEntity initCustomerEntity() {
		CustomerVO customerVO = new CustomerVO("NAME1", "PASS1");
		CustomerEntity customerEntity = new CustomerEntity(customerVO);
		return customerEntity;
	}
}