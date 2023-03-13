package com.gorillalogic.product.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gorillalogic.product.dto.ProductDto;
import com.gorillalogic.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles(value = "test")
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
        testGetProductUsingURL(url);
    }

    @Test
    public void getProduct_thenReturnResponse() throws Exception {
        String url = "/api/v1/product/get/1";
        testGetProductUsingURL(url);
    }

    @Test
    public void get1Product_thenReturnResponse() throws Exception {
        String url = "/api/v1/product/get1/1";
        testGetProductUsingURL(url);
    }

    private void testGetProductUsingURL(String url) throws Exception {
        ProductDto product = ProductDto.builder().productId(1).name("Test").description("test").build();

        Mockito.when(productService.getProductById(anyLong())).thenReturn(product);
        MvcResult result = this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"productId\":1,\"name\":\"Test\",\"description\":\"test\"}"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        ProductDto product = ProductDto.builder().productId(1).name("Test").description("test").build();
        String url = "/api/v1/product/all";
        Mockito.when(productService.getAll()).thenReturn(Arrays.asList(product));
        MvcResult result = this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"productId\":1,\"name\":\"Test\",\"description\":\"test\"}]"))
                .andReturn();
        String content = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        ProductDto[] receivedDtos = mapper.readValue(content, ProductDto[].class);
        assertEquals(product.getProductId(), receivedDtos[0].getProductId());

        List<ProductDto> receivedDtoList = mapper.readValue(content, new TypeReference<List<ProductDto>>(){});
        assertEquals(product.getProductId(), receivedDtoList.get(0).getProductId());
    }

    @Test
    public void deleteProducts() throws Exception {
        String url = "/api/v1/product/1";
        MvcResult result = this.mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals("", content);
    }

    @Test
    public void saveProduct() throws Exception {
        ProductDto product = ProductDto.builder().productId(1).name("Test").description("test").build();
        String url = "/api/v1/product/save";
        MvcResult result = this.mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        assertEquals("", content);

    }

}

