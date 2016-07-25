package org.gplumey.setting.dto;

import java.util.function.Function;

import org.gplumey.setting.model.Folder;

public interface FolderDtoMapper {
	public final static Function<Folder, FolderDto> toDto = new Function<Folder, FolderDto>() {

		@Override
		public FolderDto apply(Folder folder) {
			if (folder == null) {
				return null;
			}
			FolderDto dto = new FolderDto();
			dto.setName(folder.getName());
			return dto;
		}
	};

	public final static Function<FolderDto, Folder> fromDto = new Function<FolderDto, Folder>() {

		@Override
		public Folder apply(FolderDto dto) {
			if (dto == null) {
				return null;
			}
			Folder folder = new Folder();
			folder.setName(dto.getName());
			return folder;
		}
	};
}
