package com.pharmacy.service;

import java.util.List;

import com.pharmacy.entity.Medicine;

public interface MedicineService {

	public Medicine saveMedicine(Medicine medicine);

	public List<Medicine> findAllMedicines();

	public void deleteMedicine(Long id);
	
	public Medicine findByMedicineId(Long id);
}
