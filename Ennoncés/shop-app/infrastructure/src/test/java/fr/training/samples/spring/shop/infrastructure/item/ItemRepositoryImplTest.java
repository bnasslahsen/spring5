package fr.training.samples.spring.shop.infrastructure.item;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.training.samples.spring.shop.domain.item.ItemEntity;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.item.ItemVO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class ItemRepositoryImplTest {

	@Autowired
	private ItemRepository itemRepository;

	@Test
	void addItem() {
		final ItemEntity itemEntity = new ItemEntity(new ItemVO("DESC99", 99));
		itemRepository.addItem(itemEntity);
		assertNotNull(itemEntity.getId());
	}

	@Test
	void findOne() {
		ItemEntity itemEntity = itemRepository.findOne("123e4567-e89b-42d3-a456-556642440001");
		assertEquals("DESC1", itemEntity.getItemVO().getDescription());
		assertEquals(10, itemEntity.getItemVO().getPrice());
	}

	@Test
	void getAllItems() {
		List<ItemEntity> itemEntities = itemRepository.getAllItems();
		assertTrue(itemEntities.size() == 5);
	}

	@Test
	void testGetAllItems() {
		final Set<String> items = Stream.of("123e4567-e89b-42d3-a456-556642440001",
				"123e4567-e89b-42d3-a456-556642440002", "123e4567-e89b-42d3-a456-556642440003")
				.collect(Collectors.toSet());
		final Set<ItemEntity> itemEntities = itemRepository.getAllItems(items);
		assertNotNull(itemEntities);
		assertTrue(itemEntities.size() == 3);
	}
}