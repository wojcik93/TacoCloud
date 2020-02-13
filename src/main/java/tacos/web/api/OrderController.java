package tacos.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tacos.Order;
import tacos.data.OrderRepository;

@RestController
@RequestMapping(path="/orders", produces="application/json")
@CrossOrigin(origins="*")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	//pełne zastąpienie instniejącego wpisu pod danym id, niewypełnione elementy zostaną zastąpione nullem
	@PutMapping("/{orderId}")
	public Order putOrder(@RequestBody Order order) {
		return orderRepo.save(order);
	}
	
	@PatchMapping(path="/{orderId}", consumes="application/json")
	public Order patchOrder(@PathVariable("orderId") Long orderId, @RequestBody Order patch) {
		Order order = orderRepo.findById(orderId).get();
		if(patch.getName() != null) {
			order.setName(patch.getName());
		}
		if(patch.getStreet() != null) {
			order.setStreet(patch.getStreet());
		}
		if(patch.getCity() != null) {
			order.setCity(patch.getCity());
		}
		if(patch.getState() != null) {
			order.setState(patch.getState());
		}
		if(patch.getZip() != null) {
			order.setZip(patch.getZip());
		}
		if(patch.getCcNumber() != null) {
			order.setCcNumber(patch.getCcNumber());
		}
		if(patch.getCcExpiration() != null) {
			order.setCcExpiration(patch.getCcExpiration());
		}
		if(patch.getCcCVV() != null) {
			order.setCcCVV(patch.getCcCVV());
		}
		
		return orderRepo.save(order);
	}
	
	@DeleteMapping("/{orderId}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("orderId") Long orderId) {
		try {
			orderRepo.deleteById(orderId);
		} catch(EmptyResultDataAccessException e) {
			
		}
	}

}
