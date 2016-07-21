package org.gplumey.setting.api.controller;

import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import org.gplumey.setting.model.BooleanSetting;
import org.gplumey.setting.model.DateSetting;
import org.gplumey.setting.model.IntegerSetting;
import org.gplumey.setting.model.Setting;
import org.gplumey.setting.model.SettingDto;
import org.gplumey.setting.model.SettingId;
import org.gplumey.setting.model.StringSetting;
import org.gplumey.setting.model.dao.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@RestController
public class SettingController {
	@Autowired
	private SettingRepository repository;

	Function<Setting<?>, SettingDto> toDto = new Function<Setting<?>, SettingDto>() {

		@Override
		public SettingDto apply(Setting<?> t) {
			SettingDto dto = new SettingDto();
			return dto;
		}
	};

	private String getFolder(HttpServletRequest request, String name) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		AntPathMatcher apm = new AntPathMatcher();
		String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
		String folder = finalPath.replace("/" + name, "");
		return folder;
	}

	@RequestMapping(value = "/settings/**/{name}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public Setting<?> get(HttpServletRequest request, @PathVariable("name") String name) {
		Setting<?> setting = repository.findOne(new SettingId(getFolder(request, name), name));
		return setting;
	}

	@RequestMapping(value = "/settings/**/{name}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void create(HttpServletRequest request, @RequestBody SettingDto settingJSON,
			@PathVariable("name") String name) {

		Setting<?> setting = null;

		switch (settingJSON.getType()) {
		case Integer:
			setting = new IntegerSetting(settingJSON.getValue());
			break;
		case String:
			setting = new StringSetting(settingJSON.getValue());
			break;
		case Date:
			setting = new DateSetting(settingJSON.getValue());
			break;
		case Boolean:
			setting = new BooleanSetting(settingJSON.getValue());
			break;
		default:
			break;

		}
		if (setting != null) {
			setting.setId(new SettingId(getFolder(request, name), name));
			repository.insert(setting);
		}
	}
}
