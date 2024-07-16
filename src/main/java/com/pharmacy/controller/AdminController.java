package com.pharmacy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pharmacy.entity.Medicine;
import com.pharmacy.service.MedicineService;
import com.pharmacy.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private MedicineService medicineService;

	@Autowired
	private UserService userService;

	@GetMapping("/dashboard")
	public String adminDashboard() {
		return "adminDashboard";
	}
	
	@GetMapping("/loadMedicineForm")
	public String loadMedicineForm(Model model) {
		model.addAttribute("medicine", new Medicine());
		return "loadFrom";
	}
	
	@PostMapping("/medicines/add")
	public String addMedicine(@ModelAttribute Medicine medicine) {
		medicineService.saveMedicine(medicine);
		return "redirect:/admin/medicines";
	}

	@GetMapping("/medicines")
	public String viewMedicines(Model model) {
		model.addAttribute("medicines", medicineService.findAllMedicines());
		return "medicine";
	}

	@GetMapping("/users")
	public String viewUsers(Model model) {
		model.addAttribute("users", userService.findAllUsers());
		return "adminUsers";
	}

	@GetMapping("/medicines/delete/{id}")
	public String deleteMedicine(@PathVariable Long id) {
		medicineService.deleteMedicine(id);
		return "redirect:/admin/medicines";
	}
}
