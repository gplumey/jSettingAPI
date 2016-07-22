package org.gplumey.setting.dto;

import java.util.function.Function;

import org.gplumey.setting.model.BooleanSetting;
import org.gplumey.setting.model.DateSetting;
import org.gplumey.setting.model.IntegerSetting;
import org.gplumey.setting.model.Setting;
import org.gplumey.setting.model.StringSetting;

public interface SettingDtoMapper {
	public final static Function<Setting<?>, SettingDto> toDto = new Function<Setting<?>, SettingDto>() {

		@Override
		public SettingDto apply(Setting<?> setting) {
			if (setting == null) {
				return null;
			}
			SettingDto dto = new SettingDto();
			dto.setType(setting.getType());
			if (setting.getValue() != null) {
				switch (setting.getType()) {
				case Integer:
				case String:
				case Boolean:
					dto.setValue(setting.getValue());
					break;
				case Date:
					dto.setValue(setting.getValue().toString());
					break;
				default:
					break;

				}

			}
			return dto;
		}
	};

	public final static Function<SettingDto, Setting<?>> fromDto = new Function<SettingDto, Setting<?>>() {

		@Override
		public Setting<?> apply(SettingDto dto) {
			if (dto == null) {
				return null;
			}
			switch (dto.getType()) {
			case Integer:
				return new IntegerSetting(dto.getValue());
			case String:
				return new StringSetting(dto.getValue());
			case Date:
				return new DateSetting((String) dto.getValue());
			case Boolean:
				return new BooleanSetting(dto.getValue());
			default:
				break;

			}
			return null;
		}
	};
}
