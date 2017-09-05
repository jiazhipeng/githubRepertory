package com.hy.security.dao;

import java.util.List;

public interface NationMapper<Nation> extends BaseDao<Nation> {

	List<Nation> selectAllCity(Nation nation);
}
