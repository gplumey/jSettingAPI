package org.gplumey.setting.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.gplumey.setting.dto.FolderDto;
import org.springframework.http.MediaType;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

@RestController
public class FolderController {

	private String getFolder(HttpServletRequest request, String name) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		AntPathMatcher apm = new AntPathMatcher();
		String finalPath = apm.extractPathWithinPattern(bestMatchPattern, path);
		String folder = finalPath.replace("/" + name, "");
		return folder;
	}

	@RequestMapping(value = "/folders", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<FolderDto> list() {
		List<FolderDto> folders = new ArrayList<>();
		FolderDto dto = new FolderDto();
		dto.setName("FOLDER1");
		folders.add(dto);
		return folders;
	}

	@RequestMapping(value = "/folders/**/{name}", method = RequestMethod.POST, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public void create(HttpServletRequest request, @RequestBody FolderDto dto, @PathVariable("name") String name) {

	}

}
