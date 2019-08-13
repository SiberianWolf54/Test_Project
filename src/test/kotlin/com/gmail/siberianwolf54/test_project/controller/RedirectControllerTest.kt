package com.gmail.siberianwolf54.test_project.controller

import com.gmail.siberianwolf54.test_project.TestProjectApplication
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [TestProjectApplication::class])
@WebAppConfiguration
class RedirectControllerTest {
    private val PATH = "/home"
    private val BAD_PATH = "/houm"
    private val REDIRECT_STATUS: Int = 302
    private val NOT_FOUND: Int = 404
    private val HEADER_VALUE = "https://www.youtube.com/"
    private val HEADER_NAME = "Location"

    @Autowired lateinit var webApplicationContext: WebApplicationContext

    lateinit var mockMVC: MockMvc

    @Before
    fun init() {
        mockMVC = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    @Test fun controllerMustRedirectUsWhenRequestIsSuccessful() {
        mockMVC.perform(get(PATH))
                .andExpect(status().`is`(REDIRECT_STATUS))
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }
    
    @Test fun controllerMustReturn404IfBadKey() {
        mockMVC.perform(get(BAD_PATH))
                .andExpect(status().`is`(NOT_FOUND))
    }
}