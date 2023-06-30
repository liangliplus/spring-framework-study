package indi.kenneth.mvc.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * @Author liangll
 * @Date 2023/3/8
 */
public class HelloControllerTest {

    private MockMvc mockMvc;


    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();
    }


    @Test
    public void testHello() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        System.out.println(mvcResult);
    }

    @Test
    public void testPage() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/sayHello"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        System.out.println(result);
    }

}
