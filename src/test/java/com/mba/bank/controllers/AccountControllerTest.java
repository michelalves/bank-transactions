package com.mba.bank.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mba.bank.dtos.AccountDTO;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountController.class)
class AccountControllerTest {

  private static final String EXPECTED_CONTENT =
      "{\"account_id\":1,\"document_number\":\"04725128970\"}";
  // @MockBean private AccountService accountService;
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  public void saveNewAccount() throws Exception {
    String request =
        objectMapper.writeValueAsString(AccountDTO.builder().document("04725128970").build());
    mockMvc
        .perform(post("/accounts").content(request).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();
  }
}
