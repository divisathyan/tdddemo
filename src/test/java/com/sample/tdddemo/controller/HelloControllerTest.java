package com.sample.tdddemo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest   {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void accessAPI_returnsOK() throws  Exception {
        mockMvc.perform(
                get("/api/hello")
        ).andExpect(status().isOk());

    }

    @Test
    public void callHellowithName_returnsOK_withName() throws  Exception {
        /*MvcResult mvcResult = mockMvc.perform(
                get("/api/hello?name=Divya")
        ).andExpect(status().isOk()).andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("Hello Divya!");*/

        mockMvc.perform(
                get("/api/hello?name=Divya")
        ).andExpect(status().isOk())
        .andExpect(content().string("Hello Divya!"));
    }
}
