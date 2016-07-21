package org.gplumey.setting.model.dao;

import org.gplumey.setting.model.Setting;
import org.gplumey.setting.model.SettingId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SettingRepository extends MongoRepository<Setting<?>, SettingId> {

	// public Setting get(String path) {
	// Setting setting = new Setting();
	// setting.setPath(path);
	// setting.setName("finalPath");
	// return setting;
	// }
}
