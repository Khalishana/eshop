package id.ac.ui.cs.advprog.eshop.controller;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.service.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @MockBean
    private ProductServiceImpl service;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPage() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("CreateProduct"));
    }

    Product createProduct(String productId, String productName, int productQuantity) {
        Product product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductQuantity(productQuantity);
        return product;
    }

    @Test
    void testCreateProductPost() throws Exception {
        Product productToCreate = new Product();
        productToCreate.setProductId("6f1238f8-d13a-4e5b-936f-e55156158104");
        productToCreate.setProductName("Sampo Cap Bambang");
        productToCreate.setProductQuantity(100);

        when(productService.create(any(Product.class))).thenReturn(productToCreate);

        mockMvc.perform(post("/product/create")
                        .param("productId", productToCreate.getProductId())
                        .param("productName", productToCreate.getProductName())
                        .param("productQuantity", String.valueOf(productToCreate.getProductQuantity())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService).create(any(Product.class));
    }

    @Test
    void testProductListPage() throws Exception {
        List<Product> products = new ArrayList<>();

        Product product1 = createProduct(
                "6f1238f8-d13a-4e5b-936f-e55156158104",
                "Sampo Cap Bambang",
                100);

        Product product2 = createProduct(
                "857b3c84-8eab-4296-8ca9-6773ffd86517",
                "Sampo Cap Usep",
                50);

        products.add(product1);
        products.add(product2);

        when(productService.findAll()).thenReturn(products);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk());
    }

    @Test
    void testEditProductPage() throws Exception {
        Product product = createProduct(
                "857b3c84-8eab-4296-8ca9-6773ffd86517",
                "Sampo Cap Usep",
                50
        );

        when(productService.findById(product.getProductId())).thenReturn(product);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
        mockMvc.perform(get("/product/edit/" + product.getProductId().toString()))
                .andExpect(status().isOk());
    }

    @Test
    void testEditProductPost() throws Exception {
        Product productToUpdate = new Product();
        productToUpdate.setProductId("6f1238f8-d13a-4e5b-936f-e55156158104");
        productToUpdate.setProductName("Sampo Cap Bambang");
        productToUpdate.setProductQuantity(100);

        when(productService.update(any(Product.class))).thenReturn(productToUpdate);

        mockMvc.perform(post("/product/edit")
                        .param("productId", productToUpdate.getProductId())
                        .param("productName", productToUpdate.getProductName())
                        .param("productQuantity", String.valueOf(productToUpdate.getProductQuantity())))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService).update(any(Product.class));
    }

    @Test
    void testDeleteProduct() throws Exception {
        String productIdToDelete = "857b3c84-8eab-4296-8ca9-6773ffd86517";

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        mockMvc.perform(get("/product/delete/" + productIdToDelete))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService).delete(productIdToDelete);
    }
}   