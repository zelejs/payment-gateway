SET FOREIGN_KEY_CHECKS=0;


INSERT INTO `perm_group` (`id`, `name`, `identifier`) VALUES
('902067895349260290', '微信管理', 'wechat.management');

INSERT INTO `perm` (`id`, `groupid`, `name`, `identifier`) VALUES
('902067895349260291', '902067895349260290', '查看公众号配置', 'wechat.config.view'),
('902067895349260292', '902067895349260290', '更新公众号配置', 'wechat.config.update'),
('902067895349260293', '902067895349260290', '查看菜单配置', 'wechat.menu.view'),
('902067895349260294', '902067895349260290', '更新菜单配置', 'wechat.menu.update'),
('902067895349260295', '902067895349260290', '查看模版消息配置', 'wechat.template.message.view'),
('902067895349260296', '902067895349260290', '更新模版消息配置', 'wechat.template.message.update'),
('902067895349260297', '902067895349260290', '删除模版消息配置', 'wechat.template.message.delete');



insert into wechat_message_type (id, name, display_name) values
('902067895349260300', 'message-notification', '消息通知');
insert into wechat_message_type_prop (id, type_id, name, display_name) values
('902067895349260301', '902067895349260300', 'title', '标题'),
('902067895349260302', '902067895349260300', 'content', '内容'),
('902067895349260303', '902067895349260300', 'remark', '备注');

