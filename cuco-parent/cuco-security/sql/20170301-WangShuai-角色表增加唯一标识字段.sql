alter table td_role add column `unique_code` varchar(100) default null comment '角色唯一标识(用于程序获取指定角色)';

create UNIQUE INDEX unique_role_code on td_role(unique_code);