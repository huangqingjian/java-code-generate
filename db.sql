CREATE TABLE `tbl_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(64) NOT NULL DEFAULT '' COMMENT '标题',
  `url` varchar(64) NOT NULL DEFAULT '' COMMENT '链接',
  `pic` varchar(64) NOT NULL DEFAULT '' COMMENT '图片',
  `desc` varchar(256) NOT NULL DEFAULT '' COMMENT '描述',
  `order` int(10) NOT NULL DEFAULT 0 COMMENT '顺序',
  `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新人',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `ix_create_time` (`create_time`),
  KEY `ix_update_time` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='banner';