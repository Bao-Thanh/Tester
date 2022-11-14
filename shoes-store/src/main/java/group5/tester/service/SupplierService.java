package group5.tester.service;

import java.util.List;

import group5.tester.model.Supplier;

public interface SupplierService {
	List<Supplier> getAllSuppliers();

	Supplier getSupplierById(long id);
	
	Supplier createSupplier(Supplier supplier);
	
	Supplier updateSupplier(Supplier supplier, long id);
	
	void deleteSupplier(long id);
}
