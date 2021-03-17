package com.infosys.project.order.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.infosys.project.order.entity.ProductsOrdered;
import com.infosys.project.order.entity.CompositeClass;

public interface ProductsOrderedRepo extends JpaRepository<ProductsOrdered, CompositeClass>{
List<ProductsOrdered> findByorderid(int orderid);
List<ProductsOrdered> findBysellerid(int sellerid);
}
