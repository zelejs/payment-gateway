SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `wechat_config`;
CREATE TABLE `wechat_config` (
  `id` bigint(20) NOT NULL,
  `tenant_id` bigint(20),
  `app_id` varchar(250),
  `app_secret` varchar(250),
  `token` varchar(250),
  `encrypt_message` int(11) DEFAULT 0,
  `encoding_aes_key` varchar(250),
  `partner_id` varchar(250),
  `partner_key` varchar(250),
  `host` varchar(250),
  `wxa_app_id` varchar(250),
  `wxa_app_secret` varchar(250),
  `cert_path` varchar(250),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS wechat_message_type;
CREATE TABLE IF NOT EXISTS wechat_message_type (
    id bigint(20) NOT NULL,
    name varchar(50) not null,
    display_name varchar(50),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS wechat_message_type_prop;
CREATE TABLE IF NOT EXISTS wechat_message_type_prop (
    id bigint(20) NOT NULL,
    type_id bigint(20) not null,
    name varchar(50) not null,
    display_name varchar(50),
    PRIMARY KEY (`id`),
    foreign key (type_id) references wechat_message_type (id) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS wechat_template_message;
CREATE TABLE IF NOT EXISTS wechat_template_message (
    id bigint(20) NOT NULL,
    `tenant_id` bigint(20),
    type_id bigint(20) NOT NULL,
    template_id varchar(200) not null,
    name varchar(200) not null,
    enabled integer not null default 0,
    PRIMARY KEY (`id`),
   foreign key (type_id) references wechat_message_type (id) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

DROP TABLE IF EXISTS wechat_field;
CREATE TABLE IF NOT EXISTS wechat_field (
    id bigint(20) NOT NULL,
    prop_id bigint(20) not null,
    template_message_id bigint(20) not null,
    name varchar(50) not null,
    display_value varchar(200) default null,
    PRIMARY KEY (`id`),
    foreign key (prop_id) references wechat_message_type_prop (id) on delete cascade,
    foreign key (template_message_id) references wechat_template_message (id) on delete cascade
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

