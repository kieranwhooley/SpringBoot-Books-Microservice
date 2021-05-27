package edu.ait.books;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BooksControllerTests {

    //Mocking the RestController
    @Autowired
    private MockMvc mockMvc;

    //Test to confirm the Book RestController GET all books endpoint is working correctly
    @Test
    public void bookRestController_GetAllTest() throws Exception {
        this.mockMvc.perform(get("/books"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Test to confirm the Book RestController GET endpoint is working correctly for a specific book id
    @Test
    public void bookRestController_GetByIdTest() throws Exception {
        this.mockMvc.perform(get("/books/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Test to confirm the Book RestController GET endpoint is working correctly for a specific book id that does not exist
    @Test
    public void bookRestController_GetByNonExistentIdTest() throws Exception {
        this.mockMvc.perform(get("/books/20"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    //Test to confirm the Book RestController DELETE endpoint is working correctly for a specific book id
    @Test
    public void bookRestController_DeleteTest() throws Exception {
        this.mockMvc.perform(delete("/books/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    //Test to confirm the Book RestController DELETE endpoint is working correctly for a specific book id that does not exist
    @Test
    public void bookRestController_DeleteNonExistentIdTest() throws Exception {
        this.mockMvc.perform(delete("/books/20"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    //Test to confirm the Book RestController POST endpoint is working correctly for a specific book id
    @Test
    public void bookRestController_PostTest() throws Exception {
        this.mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"title\": \"A Tale of Two Cities\",\n" +
                        "    \"author\": \"Charles Dickens\",\n" +
                        "    \"publishedYear\": \"1859\",\n" +
                        "    \"publisher\": \"Chapman & Hall\",\n" +
                        "    \"genre\": \"Fiction\",\n" +
                        "    \"format\": \"Hardback\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    //Test to confirm the Book RestController PUT endpoint is working correctly for an id that DOES exist
    @Test
    public void bookRestController_PutTest() throws Exception {
        this.mockMvc.perform(put("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": "+ 1 + ",\n" +
                        "    \"title\": \"Update Title\",\n" +
                        "    \"author\": \"Charles Dickens\",\n" +
                        "    \"publishedYear\": \"1890\",\n" +
                        "    \"publisher\": \"Chapman & Hall\",\n" +
                        "    \"genre\": \"Fiction\",\n" +
                        "    \"format\": \"eBook\"\n" +
                        "}"))
                .andExpect(status().isOk());
    }

    //Test to confirm the Book RestController PUT endpoint is working correctly for an id that DOES NOT exist
    @Test
    public void bookRestController_PutNonExistentIdTest() throws Exception {
        this.mockMvc.perform(put("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": "+ null + ",\n" +
                        "    \"title\": \"Update Title\",\n" +
                        "    \"author\": \"Charles Dickens\",\n" +
                        "    \"publishedYear\": \"1890\",\n" +
                        "    \"publisher\": \"Chapman & Hall\",\n" +
                        "    \"genre\": \"Fiction\",\n" +
                        "    \"format\": \"eBook\"\n" +
                        "}"))
                .andExpect(status().isCreated());
    }

    //Test to confirm the Book RestController POST endpoint is working correctly when invalid and missing data is provided
    @Test
    public void bookRestController_PostInvalidDataTest() throws Exception {
        this.mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"id\": "+ null + ",\n" +
                        "    \"title\": \"\",\n" +
                        "    \"author\": \"\",\n" +
                        "    \"publishedYear\": \"20\",\n" +
                        "    \"publisher\": \"\",\n" +
                        "    \"genre\": \"\",\n" +
                        "    \"format\": \"\"\n" +
                        "}"))
                .andExpect(status().isBadRequest());

    }
}
