package org.gplumey.setting.api.test;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.gplumey.setting.SettingAPI;
import org.gplumey.setting.model.Folder;
import org.gplumey.setting.model.dao.FolderRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SettingAPI.class)
@WebAppConfiguration
public class FolderControlerTest {
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;

	@Autowired
	private FolderRepository folderRepository;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		folderRepository.deleteAll();

		Folder parent = new Folder();
		parent.setId("PARENT_FOLDER");
		parent.setName("PARENT_FOLDER");
		Folder folder = new Folder();
		folder.setParent(parent);
		folder.setId("PARENT_FOLDER/TEST_FOLDER");
		folder.setName("TEST_FOLDER");
		folderRepository.save(parent);
		folderRepository.save(folder);
	}

	@Test
	public void test() throws Exception {

		ResultActions then = mockMvc.perform(MockMvcRequestBuilders.get("/folders").accept(MediaType.APPLICATION_JSON));

		then.andExpect(MockMvcResultMatchers.status().isOk())
				//
				.andExpect(jsonPath("$", hasSize(2)));

		ResultActions then2 = mockMvc.perform(
				MockMvcRequestBuilders.get("/folders/PARENT_FOLDER/TEST_FOLDER").accept(MediaType.APPLICATION_JSON));

		then2.andExpect(MockMvcResultMatchers.status().isOk())
				//
				.andExpect(jsonPath("$.name").isNotEmpty())
				//
				.andExpect(jsonPath("$.name", is("TEST_FOLDER")))

				.andExpect(jsonPath("$.parent.name", is("PARENT_FOLDER")));

	}

	@Configuration
	public static class TestConfiguration {

	}

}
