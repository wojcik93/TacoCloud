package tacos.web;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.Client;
import tacos.data.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepo;

	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}

	@GetMapping("/current")
	public String orderForm(@AuthenticationPrincipal Client user, @ModelAttribute Order order) {
		if (order.getName() == null) {
			order.setName(user.getFullname());
		}
		if (order.getStreet() == null) {
			order.setStreet(user.getStreet());
		}
		if (order.getCity() == null) {
			order.setCity(user.getCity());
		}
		if (order.getState() == null) {
			order.setState(user.getState());
		}
		if (order.getZip() == null) {
			order.setZip(user.getZip());
		}
		return "orderForm";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors,
			SessionStatus sesstionStatus /* Principal principal lub Authentication authentication */,
			@AuthenticationPrincipal Client user) {
		if (errors.hasErrors()) {
			return "orderForm";
		}

		/*
		 * User user = userRepo.findByUsername(principal.getName());
		 * order.setUser(user); lub User user = (User) authentication.getPrincipal();
		 * order.setUser(user);
		 */

		order.setUser(user);

		orderRepo.save(order);
		sesstionStatus.setComplete();

		return "redirect:/";
	}

}
