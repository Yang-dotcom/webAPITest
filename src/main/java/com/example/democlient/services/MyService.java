package com.example.democlient.services;

import com.example.democlient.models.MultipleProducts;
import com.example.democlient.models.Product;
import com.example.democlient.client.MyFeignClient;
import com.example.democlient.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  Service class that handles the business logic of a springboot application
 * @author dingyang.chen
 * @version 1.0
 * @since 2024-05-08
 */
@Slf4j
@Service
@ComponentScan(basePackageClasses= ProductRepository.class)
public class MyService {
    /**
     *  Dependency Injection of Interface {@code ProductRepository} that interacts
     *  directly with the Database/API through {@code MyFeignClient}
     *  <p>
     *      Inversion of Control is implemented through the Dependency Injection:
     *      the field {@code private final ProductRepository} is not managed (e.g. its creation)
     *      by program directly, but instead an external entity controls it (Springboot framework).
     *
     *      this allows decoupling, allowing more modularity, testability and maintainable code.
     *
     *  </p>
     */
    private final ProductRepository productRepository;

    @Autowired
    public MyService(MyFeignClient myFeignClient, ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public MultipleProducts getAllProducts() {
       return productRepository.getAllProducts();
    }

    /**
     *
     * @param id of a single product we're looking for
     * @return the single product
     */

    public Product getProductById(int id) {
        return productRepository.getProductById(id);
    }


    /**Filters the response form a GET request to get only
     * those that contain the inputted keyword.
     * NB: the commented lines are not useful since the processing is handled
     * by the API
     *
     * <p>
     *     The commented code is left in case it may be needed for the client
     *     to process the incoming data (e.g. use of filters)
     * </p>
     *
     * @param keyword {@code String } to be searched for
     * @return the filtered response {@code MultipleProducts}
     */
    public MultipleProducts searchProducts(String keyword){
//        List<Product> filteredProducts = productRepository.getAllProducts().getProducts()
//                                    .stream().filter(product -> product.getCategory()
//                                    .toLowerCase().contains("phone".toLowerCase()))
//                                    .collect(Collectors.toList());
//       List<Product> filteredProducts = productRepository.searchProduct(keyword).getProducts();
       // filteredProducts.forEach(x -> log.info(x.getTitle()));
//        log.info("Hello");
//        for (Product pr: filteredProducts){
//            System.out.println(pr.getTitle() + "                empty");
//        }

//        return MultipleProducts.builder()
//                                    .products(filteredProducts)
//                                    .total(filteredProducts.size())
//                                    .skip(0).limit(0).build();

        return productRepository.searchProduct(keyword);
    }

    public String[] getCategories(){
        return productRepository.getCategories();
    }

}