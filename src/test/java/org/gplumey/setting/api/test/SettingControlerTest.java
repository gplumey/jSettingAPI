package org.gplumey.setting.api.test;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.gplumey.setting.SettingAPI;
import org.gplumey.setting.model.BooleanSetting;
import org.gplumey.setting.model.Folder;
import org.gplumey.setting.model.IntegerSetting;
import org.gplumey.setting.model.SettingId;
import org.gplumey.setting.model.StringSetting;
import org.gplumey.setting.model.dao.FolderRepository;
import org.gplumey.setting.model.dao.SettingRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SettingAPI.class)
@WebAppConfiguration
public class SettingControlerTest {
	@Autowired
	private WebApplicationContext ctx;
	private MockMvc mockMvc;

	@Autowired
	private SettingRepository settingRepository;
	@Autowired
	private FolderRepository folderRepository;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();

		settingRepository.deleteAll();
		folderRepository.deleteAll();

		Folder folder = new Folder();
		folder.setId("folder1");
		folder.setName("folder1");
		folderRepository.save(folder);

		StringSetting stringSetting = new StringSetting();
		stringSetting.setId(new SettingId(folder, "stringSetting1"));
		stringSetting.setValue("azerty");
		settingRepository.save(stringSetting);

		IntegerSetting integerSetting = new IntegerSetting();
		integerSetting.setId(new SettingId(folder, "integerSetting1"));
		integerSetting.setValue(12345);
		settingRepository.save(integerSetting);

		BooleanSetting booleanSetting = new BooleanSetting();
		booleanSetting.setId(new SettingId(folder, "booleanSetting1"));
		booleanSetting.setValue(true);
		settingRepository.save(booleanSetting);
	}

	@Test
	public void test() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/settings").accept(MediaType.APPLICATION_JSON))
				// ASSERT
				.andExpect(MockMvcResultMatchers.status().isOk());

		mockMvc.perform(
				MockMvcRequestBuilders.get("/settings/folder1/stringSetting1").accept(MediaType.APPLICATION_JSON))
				// ASSERTS
				.andExpect(MockMvcResultMatchers.status().isOk())//
				.andExpect(jsonPath("$.type", is("String")))//
				.andExpect(jsonPath("$.value", is("azerty")));

		mockMvc.perform(
				MockMvcRequestBuilders.get("/settings/folder1/integerSetting1").accept(MediaType.APPLICATION_JSON))
				// ASSERTS
				.andExpect(MockMvcResultMatchers.status().isOk())//
				.andExpect(jsonPath("$.type", is("Integer")))//
				.andExpect(jsonPath("$.value", is(12345)));

		mockMvc.perform(
				MockMvcRequestBuilders.get("/settings/folder1/booleanSetting1").accept(MediaType.APPLICATION_JSON))
				// ASSERTS
				.andExpect(MockMvcResultMatchers.status().isOk())//
				.andExpect(jsonPath("$.type", is("Boolean")))//
				.andExpect(jsonPath("$.value", is(true)));

	}

	@Configuration
	public static class TestConfiguration {

	}

}
