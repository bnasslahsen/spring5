package fr.training.spring.shop.web.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import fr.training.spring.shop.web.dto.ItemDTO;
import fr.training.spring.shop.web.dto.OrderLightDTO;
import fr.training.spring.shop.web.exception.TechnicalException;
import fr.training.spring.shop.web.model.OrderModel;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class OrderController {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${spring.shop.showItems.url}")
	private String showItemsUrl;

	@Value("${spring.shop.addOrder.url}")
	private String addOrderUrl;

	@GetMapping("/addOrder")
	public ModelAndView showAddOrder(@ModelAttribute("orderModel") OrderModel orderModel) {
		ItemDTO[] response = restTemplate.getForObject(showItemsUrl, ItemDTO[].class);
		List<ItemDTO> items = Arrays.asList(response);

		orderModel.setItems(items);
		return new ModelAndView("addOrder", "orderModel", orderModel);
	}

	@PostMapping(value = "/addOrder")
	@Secured({ "ROLE_NORMAL_USER", "ROLE_ADMIN" })
	public ModelAndView addOrder(@Valid OrderModel orderModel, BindingResult bindingResult) {
		Set<String> itemIDs = orderModel.getItemIDs();

		if (itemIDs == null || itemIDs.isEmpty()) {
			bindingResult.rejectValue("itemIDs", null, "no items selected!");
		}
		if (StringUtils.isBlank(orderModel.getCustomerID())) {
			bindingResult.rejectValue("customerID", null, "no customerID has been set!");
		}

		if (bindingResult.hasErrors()) {
			return showAddOrder(orderModel);
		}

		OrderLightDTO order = new OrderLightDTO(orderModel.getCustomerID().toString());
		HashSet<String> strs = new HashSet<>(itemIDs.size());
		itemIDs.forEach(i -> strs.add(i.toString()));

		order.setItems(strs);

		try {
			restTemplate.postForObject(addOrderUrl, order, OrderLightDTO.class);
		} catch (TechnicalException e) {
			bindingResult.rejectValue("customerID", null, e.getMessage());
			return showAddOrder(orderModel);
		}

		return new ModelAndView(new RedirectView("addOrder"));
	}

}
