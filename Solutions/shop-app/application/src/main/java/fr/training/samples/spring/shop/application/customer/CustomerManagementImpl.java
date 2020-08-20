package fr.training.samples.spring.shop.application.customer;

import fr.training.samples.spring.shop.domain.customer.CustomerEntity;
import fr.training.samples.spring.shop.domain.customer.CustomerRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerManagementImpl implements CustomerManagement{

	private final CustomerRepository customerRepository;

	public CustomerManagementImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public CustomerEntity create(CustomerEntity customer) {
		return customerRepository.create(customer);
	}

	@Override
	public CustomerEntity findOne(String customerID) {
		return customerRepository.findOne(customerID);
	}

	@Override
	public CustomerEntity update(CustomerEntity customerEntity) {
		return customerRepository.update(customerEntity);
	}
}
