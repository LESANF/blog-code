/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.jojoldu.blogcode.springboot.tips;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jojoldu.blogcode.springboot.tips.setter.RequestDtoSetterController;
import com.jojoldu.blogcode.springboot.tips.setter.RequestSetterDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RequestDtoSetterController.class)
public class RequestDtoSetterControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void RequestBody에서는_setter가_없어도된다() throws Exception {
        String content = objectMapper.writeValueAsString(new RequestSetterDto("jojoldu", 1000L));
        mvc
                .perform(post("/request/setter")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void Get에서는_setter가_없어도된다() throws Exception {
        String content = objectMapper.writeValueAsString(new RequestSetterDto("jojoldu", 1000L,  LocalDate.of(2019,2,22), RequestSetterDto.RequestType.GET));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("name", Arrays.asList("jojoldu"));
        params.put("amount", Arrays.asList("1000"));
        params.put("date", Arrays.asList("2019-02-22"));
        params.put("requestType", Arrays.asList("GET"));

        mvc
                .perform(get("/request/setter")
                        .params(params)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }

    @Test
    public void initBinder는_다양한_타입도_허용한다() throws Exception{
        String content = objectMapper.writeValueAsString(new RequestSetterDto("jojoldu", 1000L, LocalDate.of(2019,2,22), RequestSetterDto.RequestType.GET));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("name", Arrays.asList("jojoldu"));
        params.put("amount", Arrays.asList("1000"));
        params.put("date", Arrays.asList("2019-02-22"));
        params.put("requestType", Arrays.asList("GET"));

        mvc
                .perform(get("/request/setter")
                        .params(params)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(content));
    }
}
