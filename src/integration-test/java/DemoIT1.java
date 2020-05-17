//@RunWith(SpringRunner.class)
//@SpringBootTest(
//        classes = com.example.ecom.MyExampleApplication.class,
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//@AutoConfigureMockMvc
//@ActiveProfiles("test")
class DemoIT1 {

/*    @Autowired
    MockMvc mockMvc;

    @Test
    void testParallel() throws Exception {

        UserBareDTO obj = new UserBareDTO();
        obj.setEmail("chnadr@in.com");
        obj.selectedPlan(2);
        obj.setUserNumber("12345678");
        ContactInfoDTO e1 = new ContactInfoDTO();
        e1.setFirstName("chandra");
        e1.setLastName("kumar");
        e1.setPhone("22222");
        obj.setContactInfo(List.of(e1));
        AuditModelDTO au = new AuditModelDTO();
        au.setCreatedDate("2018-01-11");
        au.setUpdateDate("2018-11-01");
        obj.setAuditModel(au);
        MvcResult mvcResult = mockMvc.perform(
                post("/users")
                        .content(asJsonString(obj))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andReturn();
        System.out.println(mvcResult.getResponse());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}