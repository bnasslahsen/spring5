package fr.training.samples.spring.shop.application.item;

import java.util.ArrayList;
import java.util.List;

import fr.training.samples.spring.shop.domain.item.ItemEntity;
import fr.training.samples.spring.shop.domain.item.ItemRepository;
import fr.training.samples.spring.shop.domain.item.ItemVO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
class ItemManagementImplTest {

	@Autowired
	private ItemManagement itemManagement;

	@MockBean
	private ItemRepository itemRepository;

	@Test
	void getAllItems() {
		// Create mock object
		ItemEntity itemEntity = new ItemEntity(new ItemVO("mock item description", 100));
		List<ItemEntity> itemEntitiesMock = new ArrayList<>();
		itemEntitiesMock.add(itemEntity);
        // Pass the mock object to mockito
		Mockito.when(itemRepository.getAllItems()).thenReturn(itemEntitiesMock);
        // Test the call to getAllItems
		List<ItemEntity> itemEntityList = itemManagement.getAllItems();
		// Check the result
		assertTrue(itemEntityList.size() == 1);
	}

	@Test
	void addItem() {
		ItemVO itemVO = new ItemVO("DESC99", 99);
		ItemEntity itemEntity = new ItemEntity(itemVO);
		when(itemRepository.addItem(itemEntity)).thenReturn(itemEntity);

		ItemEntity itemResultEntity = itemManagement.addItem(itemEntity);
		assertNotNull(itemResultEntity);
		assertEquals("DESC99", itemResultEntity.getItemVO().getDescription());
	}
}