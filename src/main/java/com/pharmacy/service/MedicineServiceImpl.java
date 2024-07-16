package com.pharmacy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pharmacy.entity.Medicine;
import com.pharmacy.repository.MedicineRepository;

@Service
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;

	public Medicine saveMedicine(Medicine medicine) {
		return medicineRepository.save(medicine);
	}

	public List<Medicine> findAllMedicines() {
		return medicineRepository.findAll();
	}

	public void deleteMedicine(Long id) {
		medicineRepository.deleteById(id);
	}

	@Override
	public Medicine findByMedicineId(Long id) {
		Optional<Medicine> findById = medicineRepository.findById(id);
		if(findById.isPresent()) {
			Medicine medicine = findById.get();
			return medicine;
		}
		return null;
	}

}
