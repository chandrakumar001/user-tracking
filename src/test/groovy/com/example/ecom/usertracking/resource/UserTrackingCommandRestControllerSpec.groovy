package com.example.ecom.usertracking.resource

import com.example.ecom.usertracking.service.UserTrackingCommandService
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import spock.lang.Specification
import spock.lang.Unroll
import spock.mock.DetachedMockFactory

import static groovy.json.JsonOutput.toJson
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

//@Unroll
//@WebMvcTest(value = UserTrackingCommandRestController.class)
public class UserTrackingCommandRestControllerSpec{

    @Autowired
    MockMvc mockMvc
    @Autowired
    UserTrackingCommandService trackingCommandService

//    def " example Groovy"() {
//
////        Mockito.when(
////                studentService.retrieveCourse(Mockito.anyString(),
////                        Mockito.anyString())).thenReturn(mockCourse);
////
////        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
////                "/students/Student1/courses/Course1").accept(
////                MediaType.APPLICATION_JSON);
////
////        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
////
////        System.out.println(result.getResponse());
////        String expected = "{id:Course1,name:Spring,description:10 Steps}";
////
////        // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}
////
////        JSONAssert.assertEquals(expected, result.getResponse()
////                .getContentAsString(), false);
//
//        given:
//        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .delete("/users/1")
//                .accept(MediaType.APPLICATION_JSON)
//        // Send course as body to /students/Student1/courses
////        RequestBuilder requestBuilder = MockMvcRequestBuilders
////                .post("/users")
////                .accept(MediaType.APPLICATION_JSON)
////                .content(exampleCourseJson)
////                .contentType(MediaType.APPLICATION_JSON);
//
//        when:
//        MvcResult result = mockMvc.perform(requestBuilder).andReturn()
//        System.out.println(result.getResponse())
//        then:
//        result.getResponse() != null
////        expect: "controller is available"
////        def get = MockMvcRequestBuilders.get("/")
////        mockMvc.perform(get)
////                .andExpect(status().isOk())
////                .andExpect(content().string("hello world"))
//    }


    @Autowired
    ObjectMapper objectMapper

    @Unroll
    def "should not allow to create a user with an invalid name: #name"() {
        given:
        def jsonSlurper = new JsonSlurper()
        def jsonObject = jsonSlurper.parseText '''
           {
           "email": "abc@in.com",
           "userNumber": "12345678",
           "contactInfo": [
                   {
                       "firstName": "string",
                       "lastName": "string",
                       "phone": "string"
                   }
           ],
           "selectedPlan": 1,
           "auditModel": {
               "createdDate": "string",
               "updateDate": "string"
           }
       }
       '''

        when:
        def requestBuilder = post('/users')
                .contentType(APPLICATION_JSON)
                .content(toJson(jsonObject));
        def results = doRequest(requestBuilder)

        then:
        results.andExpect(status().isUnprocessableEntity())

        and:
        results.andExpect(jsonPath('$.errors[0].code').value('MethodArgumentNotValidException'))
        results.andExpect(jsonPath('$.errors[0].path').value('name'))
        results.andExpect(jsonPath('$.errors[0].message').value(errorMessage))

        where:
        email || errorMessage
        null  || 'Name must be provided.'
        'I'   || 'Name must be at least 2 characters and at most 50 characters long.'
        ''    || 'Name must be at least 2 characters and at most 50 characters long.'
    }


    @TestConfiguration
    static class MockConfig {

        def detachedMockFactory = new DetachedMockFactory()

        @Bean
        UserTrackingCommandService userTrackingCommandService() {
            return detachedMockFactory.Stub(UserTrackingCommandService)
        }
    }

    ResultActions doRequest(MockHttpServletRequestBuilder requestBuilder) throws Exception {
        return mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
    }
}
