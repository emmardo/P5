package com.emmardo.p5.firestation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FireStationControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void MockMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllFireStations() throws Exception {

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());
    }

    @Test
    public void getFireStation_FireStationExists_FireStationReturned() throws Exception {

        mockMvc.perform(get("/FireStation").param("address", "1509 Culver St")).andExpect(content().json("{ \"address\":\"1509 Culver St\", \"station\":\"3\" }")).andExpect(status().isOk());
    }

    @Test
    public void getFireStation_FireStationNonexistent_EmptyFireStationReturned() throws Exception {

        mockMvc.perform(get("/FireStation").param("address", "Danziger Strasse 31")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void createFireStation_AddressNonexistent_FireStationCreated() throws Exception {

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());

        mockMvc.perform(post("/FireStation").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"address\":\"Danziger Strasse 31\", \"station\":\"2\" }")).andExpect(status().isOk());

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" },\n" +
                "\n" +
                "       { \"address\":\"Danziger Strasse 31\", \"station\":\"2\" }\t]")).andExpect(status().isOk());
    }


    @Test
    public void createFireStation_AddressExists_NothingHappens() throws Exception {

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());

        mockMvc.perform(post("/FireStation").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"address\":\"1509 Culver St\", \"station\":\"2\" }")).andExpect(status().isOk());

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());
    }

    @Test
    public void updateStation_AddressNonexistent_NothingHappens() throws Exception {

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());

        mockMvc.perform(put("/FireStation").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"address\":\"Danziger Strasse 31\", \"station\":\"2\" }")).andExpect(status().isOk());

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());
    }

    @Test
    public void updateStation_AddressExists_StationNumberUpdated() throws Exception {

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());

        mockMvc.perform(put("/FireStation").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"address\":\"1509 Culver St\", \"station\":\"1\" }")).andExpect(status().isOk());

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());
    }

    @Test
    public void deleteFireStation_AddressExists_FireStationDeleted() throws Exception {

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());

        mockMvc.perform(delete("/FireStation").param("address", "1509 Culver St")).andExpect(status().isOk());

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());
    }

    @Test
    public void deleteFireStation_AddressNonexistent_NothingHappens() throws Exception {

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());

        mockMvc.perform(delete("/FireStation").param("address", "Danziger Strasse 31")).andExpect(status().isOk());

        mockMvc.perform(get("/FireStations")).andExpect(content().json("[\n" +
                "\n" +
                "\t{ \"address\":\"1509 Culver St\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"29 15th St\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"834 Binoc Ave\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"644 Gershwin Cir\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"748 Townings Dr\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"112 Steppes Pl\", \"station\":\"3\" },\n" +
                "\n" +
                "        { \"address\":\"489 Manchester St\", \"station\":\"4\" },\n" +
                "\n" +
                "        { \"address\":\"892 Downing Ct\", \"station\":\"2\" },\n" +
                "\n" +
                "        { \"address\":\"908 73rd St\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"947 E. Rose Dr\", \"station\":\"1\" },\n" +
                "\n" +
                "        { \"address\":\"951 LoneTree Rd\", \"station\":\"2\" }\n" +
                "\n" +
                "\t]")).andExpect(status().isOk());
    }

    @Test
    public void adultsAndChildrenServicedByFireStation_FireStationExists_AdultsAndChildrenReturned() throws Exception {

        mockMvc.perform(get("/firestation").param("stationNumber", "1")).andExpect(content().json("[\n" +
                "    {\n" +
                "        \"firstName\": \"Peter\",\n" +
                "        \"lastName\": \"Duncan\",\n" +
                "        \"address\": \"644 Gershwin Cir\",\n" +
                "        \"phone\": \"841-874-6512\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Reginold\",\n" +
                "        \"lastName\": \"Walker\",\n" +
                "        \"address\": \"908 73rd St\",\n" +
                "        \"phone\": \"841-874-8547\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Jamie\",\n" +
                "        \"lastName\": \"Peters\",\n" +
                "        \"address\": \"908 73rd St\",\n" +
                "        \"phone\": \"841-874-7462\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Brian\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"address\": \"947 E. Rose Dr\",\n" +
                "        \"phone\": \"841-874-7784\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Shawna\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"address\": \"947 E. Rose Dr\",\n" +
                "        \"phone\": \"841-874-7784\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Kendrik\",\n" +
                "        \"lastName\": \"Stelzer\",\n" +
                "        \"address\": \"947 E. Rose Dr\",\n" +
                "        \"phone\": \"841-874-7784\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"numberOfAdults\": \"5\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"numberOfChildren\": \"1\"\n" +
                "    }\n" +
                "]")).andExpect(status().isOk());
    }

    @Test
    public void adultsAndChildrenServicedByFireStation_FireStationInexistent_EmptyListReturned() throws Exception {

        mockMvc.perform(get("/firestation").param("stationNumber", "11")).andExpect(content().json("[]")).andExpect(status().isOk());
    }

    @Test
    public void getPhonesByStation_FireStationExists_PhonesReturned() throws Exception {

        mockMvc.perform(get("/phoneAlert").param("firestation", "1")).andExpect(content().json("[\n" +
                "    \"841-874-6512\",\n" +
                "    \"841-874-8547\",\n" +
                "    \"841-874-7462\",\n" +
                "    \"841-874-7784\",\n" +
                "    \"841-874-7784\",\n" +
                "    \"841-874-7784\"\n" +
                "]")).andExpect(status().isOk());
    }

    @Test
    public void getPhonesByStation_FireStationInexistent_EmptyListReturned() throws Exception {

        mockMvc.perform(get("/phoneAlert").param("firestation", "11")).andExpect(content().json("[]")).andExpect(status().isOk());
    }

    @Test
    public void getFireStationAndPersonsByAddress_AddressExists_FireStationCoveringAddressAndPeopleLivingThereReturned() throws Exception {

        mockMvc.perform(get("/fire").param("address", "1509 Culver St")).andExpect(content().json("{\n" +
                "    \"3\": [\n" +
                "        {\n" +
                "            \"firstName\": \"John\",\n" +
                "            \"lastName\": \"Boyd\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"35\",\n" +
                "            \"medications\": [\n" +
                "                \"aznol:350mg\",\n" +
                "                \"hydrapermazol:100mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"nillacilan\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jacob\",\n" +
                "            \"lastName\": \"Boyd\",\n" +
                "            \"phone\": \"841-874-6513\",\n" +
                "            \"age\": \"30\",\n" +
                "            \"medications\": [\n" +
                "                \"pharmacol:5000mg\",\n" +
                "                \"terazine:10mg\",\n" +
                "                \"noznazol:250mg\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Tenley\",\n" +
                "            \"lastName\": \"Boyd\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"7\",\n" +
                "            \"allergies\": [\n" +
                "                \"peanut\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Roger\",\n" +
                "            \"lastName\": \"Boyd\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Felicia\",\n" +
                "            \"lastName\": \"Boyd\",\n" +
                "            \"phone\": \"841-874-6544\",\n" +
                "            \"age\": \"33\",\n" +
                "            \"medications\": [\n" +
                "                \"tetracyclaz:650mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"xilliathal\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Tessa\",\n" +
                "            \"lastName\": \"Carman\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"7\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Foster\",\n" +
                "            \"lastName\": \"Shepard\",\n" +
                "            \"phone\": \"841-874-6544\",\n" +
                "            \"age\": \"39\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Clive\",\n" +
                "            \"lastName\": \"Ferguson\",\n" +
                "            \"phone\": \"841-874-6741\",\n" +
                "            \"age\": \"25\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Tony\",\n" +
                "            \"lastName\": \"Cooper\",\n" +
                "            \"phone\": \"841-874-6874\",\n" +
                "            \"age\": \"25\",\n" +
                "            \"medications\": [\n" +
                "                \"hydrapermazol:300mg\",\n" +
                "                \"dodoxadin:30mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"shellfish\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Ron\",\n" +
                "            \"lastName\": \"Peters\",\n" +
                "            \"phone\": \"841-874-8888\",\n" +
                "            \"age\": \"54\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Allison\",\n" +
                "            \"lastName\": \"Boyd\",\n" +
                "            \"phone\": \"841-874-9888\",\n" +
                "            \"age\": \"54\",\n" +
                "            \"medications\": [\n" +
                "                \"aznol:200mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"nillacilan\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}")).andExpect(status().isOk());
    }

    @Test
    public void getFireStationAndPersonsByAddress_AddressInexistent_EmptyHashMapReturned() throws Exception {

        mockMvc.perform(get("/fire").param("address", "1509 Culver St")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void getHouseholdsCoveredByStations_TwoStationsExist_HouseholdsCoveredByStationsReturned() throws Exception {

        mockMvc.perform(get("/flood/stations").param("stations", "1,2,11,12")).andExpect(content().json("{\n" +
                "    \"FireStation: 1. Address: 908 73rd St\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Peter\",\n" +
                "            \"lastName\": \"Duncan\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"19\",\n" +
                "            \"allergies\": [\n" +
                "                \"shellfish\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Reginold\",\n" +
                "            \"lastName\": \"Walker\",\n" +
                "            \"phone\": \"841-874-8547\",\n" +
                "            \"age\": \"40\",\n" +
                "            \"medications\": [\n" +
                "                \"thradox:700mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"illisoxian\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jamie\",\n" +
                "            \"lastName\": \"Peters\",\n" +
                "            \"phone\": \"841-874-7462\",\n" +
                "            \"age\": \"37\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Brian\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"44\",\n" +
                "            \"medications\": [\n" +
                "                \"ibupurin:200mg\",\n" +
                "                \"hydrapermazol:400mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"nillacilan\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Shawna\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"39\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Kendrik\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"5\",\n" +
                "            \"medications\": [\n" +
                "                \"noxidian:100mg\",\n" +
                "                \"pharmacol:2500mg\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jonanathan\",\n" +
                "            \"lastName\": \"Marrack\",\n" +
                "            \"phone\": \"841-874-6513\",\n" +
                "            \"age\": \"30\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Sophia\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7878\",\n" +
                "            \"age\": \"31\",\n" +
                "            \"medications\": [\n" +
                "                \"aznol:60mg\",\n" +
                "                \"hydrapermazol:900mg\",\n" +
                "                \"pharmacol:5000mg\",\n" +
                "                \"terazine:500mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"peanut\",\n" +
                "                \"shellfish\",\n" +
                "                \"aznol\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Warren\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"34\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Zach\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Eric\",\n" +
                "            \"lastName\": \"Cadigan\",\n" +
                "            \"phone\": \"841-874-7458\",\n" +
                "            \"age\": \"74\",\n" +
                "            \"medications\": [\n" +
                "                \"tradoxidine:400mg\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"FireStation: 2. Address: 29 15th St\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Peter\",\n" +
                "            \"lastName\": \"Duncan\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"19\",\n" +
                "            \"allergies\": [\n" +
                "                \"shellfish\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Reginold\",\n" +
                "            \"lastName\": \"Walker\",\n" +
                "            \"phone\": \"841-874-8547\",\n" +
                "            \"age\": \"40\",\n" +
                "            \"medications\": [\n" +
                "                \"thradox:700mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"illisoxian\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jamie\",\n" +
                "            \"lastName\": \"Peters\",\n" +
                "            \"phone\": \"841-874-7462\",\n" +
                "            \"age\": \"37\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Brian\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"44\",\n" +
                "            \"medications\": [\n" +
                "                \"ibupurin:200mg\",\n" +
                "                \"hydrapermazol:400mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"nillacilan\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Shawna\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"39\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Kendrik\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"5\",\n" +
                "            \"medications\": [\n" +
                "                \"noxidian:100mg\",\n" +
                "                \"pharmacol:2500mg\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jonanathan\",\n" +
                "            \"lastName\": \"Marrack\",\n" +
                "            \"phone\": \"841-874-6513\",\n" +
                "            \"age\": \"30\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Sophia\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7878\",\n" +
                "            \"age\": \"31\",\n" +
                "            \"medications\": [\n" +
                "                \"aznol:60mg\",\n" +
                "                \"hydrapermazol:900mg\",\n" +
                "                \"pharmacol:5000mg\",\n" +
                "                \"terazine:500mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"peanut\",\n" +
                "                \"shellfish\",\n" +
                "                \"aznol\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Warren\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"34\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Zach\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Eric\",\n" +
                "            \"lastName\": \"Cadigan\",\n" +
                "            \"phone\": \"841-874-7458\",\n" +
                "            \"age\": \"74\",\n" +
                "            \"medications\": [\n" +
                "                \"tradoxidine:400mg\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"FireStation: 1. Address: 947 E. Rose Dr\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Peter\",\n" +
                "            \"lastName\": \"Duncan\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"19\",\n" +
                "            \"allergies\": [\n" +
                "                \"shellfish\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Reginold\",\n" +
                "            \"lastName\": \"Walker\",\n" +
                "            \"phone\": \"841-874-8547\",\n" +
                "            \"age\": \"40\",\n" +
                "            \"medications\": [\n" +
                "                \"thradox:700mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"illisoxian\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jamie\",\n" +
                "            \"lastName\": \"Peters\",\n" +
                "            \"phone\": \"841-874-7462\",\n" +
                "            \"age\": \"37\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Brian\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"44\",\n" +
                "            \"medications\": [\n" +
                "                \"ibupurin:200mg\",\n" +
                "                \"hydrapermazol:400mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"nillacilan\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Shawna\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"39\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Kendrik\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"5\",\n" +
                "            \"medications\": [\n" +
                "                \"noxidian:100mg\",\n" +
                "                \"pharmacol:2500mg\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jonanathan\",\n" +
                "            \"lastName\": \"Marrack\",\n" +
                "            \"phone\": \"841-874-6513\",\n" +
                "            \"age\": \"30\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Sophia\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7878\",\n" +
                "            \"age\": \"31\",\n" +
                "            \"medications\": [\n" +
                "                \"aznol:60mg\",\n" +
                "                \"hydrapermazol:900mg\",\n" +
                "                \"pharmacol:5000mg\",\n" +
                "                \"terazine:500mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"peanut\",\n" +
                "                \"shellfish\",\n" +
                "                \"aznol\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Warren\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"34\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Zach\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Eric\",\n" +
                "            \"lastName\": \"Cadigan\",\n" +
                "            \"phone\": \"841-874-7458\",\n" +
                "            \"age\": \"74\",\n" +
                "            \"medications\": [\n" +
                "                \"tradoxidine:400mg\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"FireStation: 2. Address: 951 LoneTree Rd\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Peter\",\n" +
                "            \"lastName\": \"Duncan\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"19\",\n" +
                "            \"allergies\": [\n" +
                "                \"shellfish\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Reginold\",\n" +
                "            \"lastName\": \"Walker\",\n" +
                "            \"phone\": \"841-874-8547\",\n" +
                "            \"age\": \"40\",\n" +
                "            \"medications\": [\n" +
                "                \"thradox:700mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"illisoxian\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jamie\",\n" +
                "            \"lastName\": \"Peters\",\n" +
                "            \"phone\": \"841-874-7462\",\n" +
                "            \"age\": \"37\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Brian\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"44\",\n" +
                "            \"medications\": [\n" +
                "                \"ibupurin:200mg\",\n" +
                "                \"hydrapermazol:400mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"nillacilan\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Shawna\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"39\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Kendrik\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"5\",\n" +
                "            \"medications\": [\n" +
                "                \"noxidian:100mg\",\n" +
                "                \"pharmacol:2500mg\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jonanathan\",\n" +
                "            \"lastName\": \"Marrack\",\n" +
                "            \"phone\": \"841-874-6513\",\n" +
                "            \"age\": \"30\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Sophia\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7878\",\n" +
                "            \"age\": \"31\",\n" +
                "            \"medications\": [\n" +
                "                \"aznol:60mg\",\n" +
                "                \"hydrapermazol:900mg\",\n" +
                "                \"pharmacol:5000mg\",\n" +
                "                \"terazine:500mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"peanut\",\n" +
                "                \"shellfish\",\n" +
                "                \"aznol\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Warren\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"34\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Zach\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Eric\",\n" +
                "            \"lastName\": \"Cadigan\",\n" +
                "            \"phone\": \"841-874-7458\",\n" +
                "            \"age\": \"74\",\n" +
                "            \"medications\": [\n" +
                "                \"tradoxidine:400mg\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"FireStation: 1. Address: 644 Gershwin Cir\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Peter\",\n" +
                "            \"lastName\": \"Duncan\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"19\",\n" +
                "            \"allergies\": [\n" +
                "                \"shellfish\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Reginold\",\n" +
                "            \"lastName\": \"Walker\",\n" +
                "            \"phone\": \"841-874-8547\",\n" +
                "            \"age\": \"40\",\n" +
                "            \"medications\": [\n" +
                "                \"thradox:700mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"illisoxian\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jamie\",\n" +
                "            \"lastName\": \"Peters\",\n" +
                "            \"phone\": \"841-874-7462\",\n" +
                "            \"age\": \"37\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Brian\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"44\",\n" +
                "            \"medications\": [\n" +
                "                \"ibupurin:200mg\",\n" +
                "                \"hydrapermazol:400mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"nillacilan\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Shawna\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"39\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Kendrik\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"5\",\n" +
                "            \"medications\": [\n" +
                "                \"noxidian:100mg\",\n" +
                "                \"pharmacol:2500mg\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jonanathan\",\n" +
                "            \"lastName\": \"Marrack\",\n" +
                "            \"phone\": \"841-874-6513\",\n" +
                "            \"age\": \"30\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Sophia\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7878\",\n" +
                "            \"age\": \"31\",\n" +
                "            \"medications\": [\n" +
                "                \"aznol:60mg\",\n" +
                "                \"hydrapermazol:900mg\",\n" +
                "                \"pharmacol:5000mg\",\n" +
                "                \"terazine:500mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"peanut\",\n" +
                "                \"shellfish\",\n" +
                "                \"aznol\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Warren\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"34\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Zach\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Eric\",\n" +
                "            \"lastName\": \"Cadigan\",\n" +
                "            \"phone\": \"841-874-7458\",\n" +
                "            \"age\": \"74\",\n" +
                "            \"medications\": [\n" +
                "                \"tradoxidine:400mg\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"FireStation: 2. Address: 892 Downing Ct\": [\n" +
                "        {\n" +
                "            \"firstName\": \"Peter\",\n" +
                "            \"lastName\": \"Duncan\",\n" +
                "            \"phone\": \"841-874-6512\",\n" +
                "            \"age\": \"19\",\n" +
                "            \"allergies\": [\n" +
                "                \"shellfish\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Reginold\",\n" +
                "            \"lastName\": \"Walker\",\n" +
                "            \"phone\": \"841-874-8547\",\n" +
                "            \"age\": \"40\",\n" +
                "            \"medications\": [\n" +
                "                \"thradox:700mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"illisoxian\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jamie\",\n" +
                "            \"lastName\": \"Peters\",\n" +
                "            \"phone\": \"841-874-7462\",\n" +
                "            \"age\": \"37\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Brian\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"44\",\n" +
                "            \"medications\": [\n" +
                "                \"ibupurin:200mg\",\n" +
                "                \"hydrapermazol:400mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"nillacilan\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Shawna\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"39\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Kendrik\",\n" +
                "            \"lastName\": \"Stelzer\",\n" +
                "            \"phone\": \"841-874-7784\",\n" +
                "            \"age\": \"5\",\n" +
                "            \"medications\": [\n" +
                "                \"noxidian:100mg\",\n" +
                "                \"pharmacol:2500mg\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Jonanathan\",\n" +
                "            \"lastName\": \"Marrack\",\n" +
                "            \"phone\": \"841-874-6513\",\n" +
                "            \"age\": \"30\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Sophia\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7878\",\n" +
                "            \"age\": \"31\",\n" +
                "            \"medications\": [\n" +
                "                \"aznol:60mg\",\n" +
                "                \"hydrapermazol:900mg\",\n" +
                "                \"pharmacol:5000mg\",\n" +
                "                \"terazine:500mg\"\n" +
                "            ],\n" +
                "            \"allergies\": [\n" +
                "                \"peanut\",\n" +
                "                \"shellfish\",\n" +
                "                \"aznol\"\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Warren\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"34\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Zach\",\n" +
                "            \"lastName\": \"Zemicks\",\n" +
                "            \"phone\": \"841-874-7512\",\n" +
                "            \"age\": \"2\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"firstName\": \"Eric\",\n" +
                "            \"lastName\": \"Cadigan\",\n" +
                "            \"phone\": \"841-874-7458\",\n" +
                "            \"age\": \"74\",\n" +
                "            \"medications\": [\n" +
                "                \"tradoxidine:400mg\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}")).andExpect(status().isOk());
    }

    @Test
    public void getHouseholdsCoveredByStations_NoStationExists_EmptyHashMapReturned() throws Exception {

        mockMvc.perform(get("/flood/stations").param("stations", "11,12")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void getPersonsInformation_PersonExists_PersonsInformationReturned() throws Exception {

        mockMvc.perform(get("/personInfo").param("firstName", "John").param("lastName", "Boyd")).andExpect(content().json("{\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Boyd\",\n" +
                "    \"address\": \"1509 Culver St\",\n" +
                "    \"email\": \"jaboyd@email.com\",\n" +
                "    \"age\": \"35\",\n" +
                "    \"medications\": [\n" +
                "        \"aznol:350mg\",\n" +
                "        \"hydrapermazol:100mg\"\n" +
                "    ],\n" +
                "    \"allergies\": [\n" +
                "        \"nillacilan\"\n" +
                "    ]\n" +
                "}")).andExpect(status().isOk());
    }

    @Test
    public void getPersonsInformation_FirstNameError_EmptyPersonReturned() throws Exception {

        mockMvc.perform(get("/personInfo").param("firstName", "Jane").param("lastName", "Boyd")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void getPersonsInformation_LastNameError_EmptyPersonReturned() throws Exception {

        mockMvc.perform(get("/personInfo").param("firstName", "John").param("lastName", "Corden")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void getPersonsInformation_PersonInexistent_EmptyPersonReturned() throws Exception {

        mockMvc.perform(get("/personInfo").param("firstName", "Jane").param("lastName", "Corden")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void getChildrenLivingAtAddressAsWellAsAnyAdult_ChildrenLiveAtAddress_ChildrenAndAnyAdultLivingAtAddressReturned() throws Exception {

        mockMvc.perform(get("/childAlert").param("address", "1509 Culver St")).andExpect(content().json("[\n" +
                "    {\n" +
                "        \"firstName\": \"Tenley\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"age\": \"7\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Roger\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"age\": \"2\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"John\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"age\": \"35\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Jacob\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"age\": \"30\"\n" +
                "    },\n" +
                "    {\n" +
                "        \"firstName\": \"Felicia\",\n" +
                "        \"lastName\": \"Boyd\",\n" +
                "        \"age\": \"33\"\n" +
                "    }\n" +
                "]")).andExpect(status().isOk());
    }

    @Test
    public void getChildrenLivingAtAddressAsWellAsAnyAdult_NoChildrenLiveAtAddress_EmptyListReturned() throws Exception {

        mockMvc.perform(get("/childAlert").param("address", "29 15th St")).andExpect(content().json("[]")).andExpect(status().isOk());
    }
}
