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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
	void getAllItemsWithCache(){
		ItemEntity itemEntity = new ItemEntity(new ItemVO("mock item description", 100));
		List<ItemEntity> itemEntitiesMock = new ArrayList<>();
		Mockito.when(itemRepository.getAllItems()).thenReturn(itemEntitiesMock);

		itemManagement.getAllItems();
		verify(itemRepository).getAllItems();

		// Test with cache
		itemManagement.getAllItems();
		verify(itemRepository).getAllItems();
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

	@Test
	void addItemWithCache() {
		// Load entries in the cache
		ItemEntity itemEntity = new ItemEntity(new ItemVO("mock item description", 100));
		List<ItemEntity> itemEntitiesMock = new ArrayList<>();
		Mockito.when(itemRepository.getAllItems()).thenReturn(itemEntitiesMock);
		itemManagement.getAllItems();

		ItemVO itemVO = new ItemVO("DESC99", 99);
		ItemEntity itemEntityToAdd = new ItemEntity(itemVO);
		when(itemRepository.addItem(itemEntity)).thenReturn(itemEntityToAdd);
		// Evict entries from the cache
		itemManagement.addItem(itemEntityToAdd);

		// Check evict - cache reloded
		itemManagement.getAllItems();
		verify(itemRepository,times(2)).getAllItems();
	}
}