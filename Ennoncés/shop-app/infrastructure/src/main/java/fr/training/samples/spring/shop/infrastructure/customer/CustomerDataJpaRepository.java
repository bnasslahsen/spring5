package fr.training.samples.spring.shop.infrastructure.customer;

import fr.training.samples.spring.shop.domain.customer.CustomerEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDataJpaRepository extends JpaRepository<CustomerEntity, String> {

}
