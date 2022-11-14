package group5.tester.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group5.tester.model.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
