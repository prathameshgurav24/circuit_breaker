package com.catelogService;
import com.catelogService.entity.Order;
import com.catelogService.repository.OrderRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class CatelogServiceApplication {

	@Autowired
	private OrderRepository orderRepository;

	@PostConstruct
	public void initOrdersTable() {
		orderRepository.saveAll(Stream.of(
						new Order("mobile", "electronics", "white", 20000),
						new Order("T-Shirt", "clothes", "black", 999),
						new Order("Jeans", "clothes", "blue", 1999),
						new Order("Laptop", "electronics", "gray", 50000),
						new Order("digital watch", "electronics", "black", 2500),
						new Order("Fan", "electronics", "black", 50000)
				).
				collect(Collectors.toList()));
	}

	@GetMapping
	public List<Order> getOrders(){
		return orderRepository.findAll();
	}
	@GetMapping("/{category}")
	public List<Order> getOrdersByCategory(@PathVariable String category){
		return orderRepository.findByCategory(category);
	}

	public static void main(String[] args) {
		SpringApplication.run(CatelogServiceApplication.class, args);
	}

}