package org.gplumey.setting.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.gplumey.setting.dto.SettingDto;
import org.gplumey.setting.dto.SettingDtoMapper;
import org.gplumey.setting.model.Folder;
import org.gplumey.setting.model.Setting;
import org.gplumey.setting.model.SettingId;
import org.gplumey.setting.model.dao.FolderRepository;
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

	@Autowired
	private FolderRepository folderRepository;

	private String getFolder(HttpServletRequest request, String name) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		AntPathMatcher apm = new AntPathMatcher();
		String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
		String folder = finalPath.replace("/" + name, "");
		return folder;
	}

	@RequestMapping(value = "/settings", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<SettingDto> list() {
		List<Setting<?>> settings = repository.findAll();
		if (settings != null) {
			return settings.stream().map(SettingDtoMapper.toDto).collect(Collectors.toList());
		} else {
			return null;
		}
	}

	@RequestMapping(value = "/settings", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public void clear() {
		repository.deleteAll();
	}

	@RequestMapping(value = "/settings/**/{name}", method = RequestMethod.GET, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public SettingDto get(HttpServletRequest request, @PathVariable("name") String name) {
		Folder folder = folderRepository.findOne(getFolder(request, name));
		Setting<?> setting = repository.findOne(new SettingId(folder, name));
		SettingDto dto = SettingDtoMapper.toDto.apply(setting);
		return dto;
	}

	@RequestMapping(value = "/settings/**/{name}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void create(HttpServletRequest request, @RequestBody SettingDto settingDto,
			@PathVariable("name") String name) {

		Setting<?> setting = SettingDtoMapper.fromDto.apply(settingDto);

		if (setting != null) {
			Folder folder = folderRepository.findOne(getFolder(request, name));
			setting.setId(new SettingId(folder, name));
			repository.insert(setting);
		}
	}
}
