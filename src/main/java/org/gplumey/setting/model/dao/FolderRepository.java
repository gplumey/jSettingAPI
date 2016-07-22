package org.gplumey.setting.model.dao;

import org.gplumey.setting.model.Folder;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FolderRepository extends MongoRepository<Folder, String> {

}
