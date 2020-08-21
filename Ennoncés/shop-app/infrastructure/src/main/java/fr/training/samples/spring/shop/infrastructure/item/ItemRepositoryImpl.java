package fr.training.samples.spring.shop.infrastructure.item;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import fr.training.samples.spring.shop.domain.common.exception.NotFoundException;
import fr.training.samples.spring.shop.domain.item.ItemEntity;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

	private final EntityManager entityManager;
	private final ItemDataJpaRepository itemDataJpaRepository;

	public ItemRepositoryImpl(ItemDataJpaRepository itemDataJpaRepository, EntityManager entityManager) {
		super();
		this.itemDataJpaRepository = itemDataJpaRepository;
		this.entityManager = entityManager;
	}

	@Override
	public ItemEntity addItem(final ItemEntity item) {
		entityManager.persist(item);
		return item;
	}


	@Override
	public ItemEntity findOne(final String itemId) {
		return itemDataJpaRepository.findById(itemId)
				.orElseThrow(() -> new NotFoundException("Item with id:" + itemId + ", not found"));
	}

	@Override
	public List<ItemEntity> getAllItems() {
		return itemDataJpaRepository.findAll();
	}

	@Override
	public Set<ItemEntity> getAllItems(final Set<String> itemsId) {
		return itemDataJpaRepository.findByIdIn(itemsId);
	}
}
