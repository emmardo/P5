package com.emmardo.p5.medicalrecord;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MedicalRecordControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void MockMvc() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllMedicalRecords() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void getMedicalRecord_MedicalRecordExists_MedicalRecordReturned() throws Exception {

        mockMvc.perform(get("/MedicalRecord").param("firstName", "John").param("lastName", "Boyd")).andExpect(content().json("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")).andExpect(status().isOk());
    }

    @Test
    public void getMedicalRecord_FirstNameError_EmptyMedicalRecordReturned() throws Exception {

        mockMvc.perform(get("/MedicalRecord").param("firstName", "Jane").param("lastName", "Boyd")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void getMedicalRecord_LastNameError_EmptyMedicalRecordReturned() throws Exception {

        mockMvc.perform(get("/MedicalRecord").param("firstName", "John").param("lastName", "Corden")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void getMedicalRecord_MedicalRecordNonexistent_EmptyMedicalRecordReturned() throws Exception {

        mockMvc.perform(get("/MedicalRecord").param("firstName", "Jane").param("lastName", "Corden")).andExpect(content().json("{}")).andExpect(status().isOk());
    }

    @Test
    public void createMedicalRecord_PersonNonexistent_MedicalRecordCreated() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(post("/MedicalRecord").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"firstName\":\"Jane\", \"lastName\":\"Corden\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Jane\", \"lastName\":\"Corden\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }] \n")).andExpect(status().isOk());
    }

    @Test
    public void createMedicalRecord_FirstNameAlreadyExistst_MedicalRecordCreated() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(post("/MedicalRecord").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"firstName\":\"John\", \"lastName\":\"Corden\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Corden\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }] \n")).andExpect(status().isOk());
    }

    @Test
    public void createMedicalRecord_LastNameAlreadyExistst_MedicalRecordCreated() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(post("/MedicalRecord").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"firstName\":\"Jane\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Jane\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }] \n")).andExpect(status().isOk());
    }

    @Test
    public void createMedicalRecord_MedicalRecordAlreadyExistst_NothingHappens() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(post("/MedicalRecord").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"11/11/1984\", \"medications\":[], \"allergies\":[] }")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void updateMedicalRecord_PersonExists_MedicalRecordUpdated() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(put("/MedicalRecord").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1994\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1994\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void updateMedicalRecord_FirstNameError_NothingHappens() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(put("/MedicalRecord").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"firstName\":\"Jane\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1994\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void updateMedicalRecord_LastNameError_NothingHappens() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(put("/MedicalRecord").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"firstName\":\"John\", \"lastName\":\"Corden\", \"birthdate\":\"03/06/1994\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void updateMedicalRecord_PersonInexistent_NothingHappens() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(put("/MedicalRecord").contentType(MediaType.APPLICATION_JSON_UTF8).content("{ \"firstName\":\"Jane\", \"lastName\":\"Corden\", \"birthdate\":\"03/06/1994\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecord_MedicalRecordExists_MedicalRecordDeleted() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(delete("/MedicalRecord").param("firstName", "John").param("lastName", "Boyd")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecord_FirstNameError_NothingHappens() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(delete("/MedicalRecord").param("firstName", "Jane").param("lastName", "Boyd")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecord_LastNameError_NothingHappens() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(delete("/MedicalRecord").param("firstName", "John").param("lastName", "Corden")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecord_MedicalRecordInexistent_NothingHappens() throws Exception {

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());

        mockMvc.perform(delete("/MedicalRecord").param("firstName", "Jane").param("lastName", "Corden")).andExpect(status().isOk());

        mockMvc.perform(get("/MedicalRecords")).andExpect(content().json("[\n" +
                "\n" +
                "        { \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jacob\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1989\", \"medications\":[\"pharmacol:5000mg\", \"terazine:10mg\", \"noznazol:250mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tenley\", \"lastName\":\"Boyd\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[\"peanut\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Roger\", \"lastName\":\"Boyd\", \"birthdate\":\"09/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Felicia\", \"lastName\":\"Boyd\",\"birthdate\":\"01/08/1986\", \"medications\":[\"tetracyclaz:650mg\"], \"allergies\":[\"xilliathal\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jonanathan\", \"lastName\":\"Marrack\", \"birthdate\":\"01/03/1989\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tessa\", \"lastName\":\"Carman\", \"birthdate\":\"02/18/2012\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Peter\", \"lastName\":\"Duncan\", \"birthdate\":\"09/06/2000\", \"medications\":[], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Foster\", \"lastName\":\"Shepard\", \"birthdate\":\"01/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Tony\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[\"hydrapermazol:300mg\", \"dodoxadin:30mg\"], \"allergies\":[\"shellfish\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Lily\", \"lastName\":\"Cooper\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Sophia\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1988\", \"medications\":[\"aznol:60mg\", \"hydrapermazol:900mg\", \"pharmacol:5000mg\", \"terazine:500mg\"], \"allergies\":[\"peanut\", \"shellfish\", \"aznol\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Warren\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/1985\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Zach\", \"lastName\":\"Zemicks\", \"birthdate\":\"03/06/2017\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Reginold\", \"lastName\":\"Walker\", \"birthdate\":\"08/30/1979\", \"medications\":[\"thradox:700mg\"], \"allergies\":[\"illisoxian\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Jamie\", \"lastName\":\"Peters\", \"birthdate\":\"03/06/1982\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Ron\", \"lastName\":\"Peters\", \"birthdate\":\"04/06/1965\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Allison\", \"lastName\":\"Boyd\", \"birthdate\":\"03/15/1965\", \"medications\":[\"aznol:200mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Brian\", \"lastName\":\"Stelzer\", \"birthdate\":\"12/06/1975\", \"medications\":[\"ibupurin:200mg\", \"hydrapermazol:400mg\"], \"allergies\":[\"nillacilan\"] },\n" +
                "\n" +
                "        { \"firstName\":\"Shawna\", \"lastName\":\"Stelzer\", \"birthdate\":\"07/08/1980\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Kendrik\", \"lastName\":\"Stelzer\", \"birthdate\":\"03/06/2014\", \"medications\":[\"noxidian:100mg\", \"pharmacol:2500mg\"], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Clive\", \"lastName\":\"Ferguson\", \"birthdate\":\"03/06/1994\", \"medications\":[], \"allergies\":[] },\n" +
                "\n" +
                "        { \"firstName\":\"Eric\", \"lastName\":\"Cadigan\", \"birthdate\":\"08/06/1945\", \"medications\":[\"tradoxidine:400mg\"], \"allergies\":[] }\n" +
                "\n" +
                "        ] \n")).andExpect(status().isOk());
    }
}
