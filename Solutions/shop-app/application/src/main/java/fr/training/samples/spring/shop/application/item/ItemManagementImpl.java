package fr.training.samples.spring.shop.application.item;

import java.util.List;

import fr.training.samples.spring.shop.domain.item.ItemEntity;
import fr.training.samples.spring.shop.domain.item.ItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemManagementImpl implements ItemManagement {

	private ItemRepository itemRepository;

	public ItemManagementImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	public ItemEntity addItem(ItemEntity itemEntity) {
		return itemRepository.addItem(itemEntity);
	}

	@Override
	public List<ItemEntity> getAllItems() {
		return itemRepository.getAllItems();
	}
}
