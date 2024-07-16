package com.pharmacy.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pharmacy.entity.Medicine;
import com.pharmacy.entity.Order;
import com.pharmacy.entity.User;
import com.pharmacy.service.MedicineService;
import com.pharmacy.service.OrderService;
import com.pharmacy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private MedicineService medicineService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@GetMapping("/medicines")
	public String viewMedicines(Model model) {
		model.addAttribute("medicines", medicineService.findAllMedicines());
		model.addAttribute("medicineIds", new ArrayList<Long>());
		return "usersMedicine";
	}
	
	@GetMapping("/dashboard")
    public String userDashboard() {
        return "userDashboard";
    }

	@PostMapping("/order")
    public String placeOrder(@RequestParam(required = false) List<Long> medicineIds, Principal principal) {
        if (medicineIds == null) {
            medicineIds = new ArrayList<>();
        }

        User user = userService.findByUserName(principal.getName());
        Set<Medicine> medicines = medicineIds.stream()
            .map(medicineService::findByMedicineId)
            .collect(Collectors.toSet());
        Order order = new Order();
        order.setUser(user);
        order.setMedicines(medicines);
        orderService.saveOrder(order);
        return "redirect:/user/orders";
    }

	@GetMapping("/orders")
	public String viewOrders(Model model, Principal principal) {
		User user = userService.findByUserName(principal.getName());
		List<Order> orders = orderService.findOrdersByUser(user);
		model.addAttribute("orders", orders);
		return "userOrders";
	}
}
