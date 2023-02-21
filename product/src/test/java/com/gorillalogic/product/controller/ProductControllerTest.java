package com.gorillalogic.product.controller;

import com.gorillalogic.product.dto.ProductDto;
import com.gorillalogic.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @InjectMocks
    ProductController productController;

    @Test
    public void whenJsonConverterIsFound_thenReturnResponse() throws Exception {
        String url = "/api/v1/product/1";
        ProductDto product = ProductDto.builder().productId(1).name("Test").description("test").build();

        Mockito.when(productService.getProductById(anyLong())).thenReturn(product);
        MvcResult result = this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"productId\":1,\"name\":\"Test\",\"description\":\"test\"}"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
    }
}
