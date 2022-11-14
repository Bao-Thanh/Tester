package group5.tester.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group5.tester.exception.ResourceNotFoundException;
import group5.tester.model.Supplier;
import group5.tester.repository.SupplierRepository;
import group5.tester.service.SupplierService;
@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	private SupplierRepository supplierRepo;

	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierRepo.findAll();
	}

	@Override
	public Supplier getSupplierById(long id) {
		return supplierRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Supplier", "Id", id));
	}
	@Override
	public Supplier createSupplier(Supplier supplier) {
		return supplierRepo.save(supplier);
	}

	@Override
	public Supplier updateSupplier(Supplier supplier, long id) {
		Supplier existingSupplier = supplierRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Supplier", "Id", id));
		if(!supplier.getName().equals("")){
			existingSupplier.setName(supplier.getName());
		}
		if(!supplier.getAddress().equals("")){
			existingSupplier.setAddress(supplier.getAddress());
		}
		if(!supplier.getPhone().equals("")){
			existingSupplier.setPhone(supplier.getPhone());
		}
		if(!supplier.getAddress().equals("")){
			existingSupplier.setAddress(supplier.getAddress());
		}
		supplierRepo.save(existingSupplier);
		return existingSupplier;
	}

	@Override
	public void deleteSupplier(long id) {
		supplierRepo.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Supplier", "Id", id));
		supplierRepo.deleteById(id);
	}
}
