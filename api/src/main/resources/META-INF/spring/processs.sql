CREATE TABLE `process`
(
    `id`                      bigint(20)       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`                    varchar(32)      NOT NULL COMMENT '流程名称',
    `version`                 int(11) unsigned NOT NULL DEFAULT '0' COMMENT '版本号',
    `record_process_instance` tinyint(1)       NOT NULL DEFAULT '1' COMMENT '是否记录流程实例；1—记录，0—不记录',
    `record_node_instance`    tinyint(1)       NOT NULL DEFAULT '1' COMMENT '是否记录节点实例；1—记录，0—不记录',
    `record_global_param`     tinyint(1)       NOT NULL DEFAULT '1' COMMENT '是否记录全局变量；1—记录，0—不记录',
    `record_process_input`    tinyint(1)       NOT NULL DEFAULT '1' COMMENT '是否记录流程输入；1—记录，0—不记录',
    `record_trigger_input`    tinyint(1)       NOT NULL DEFAULT '1' COMMENT '是否记录触发节点输入；1—记录，0—不记录',
    `record_trigger_result`   tinyint(1)       NOT NULL DEFAULT '1' COMMENT '是否记录触发节点执行节点；1—记录，0—不记录',
    `create_time`             datetime         NOT NULL COMMENT '创建时间',
    `modified_time`           datetime         NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_process_name_version` (`name`, `version`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '流程表';


CREATE TABLE `process_node`
(
    `id`                bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `process_id`        bigint(20)   NOT NULL COMMENT '流程ID',
    `name`              varchar(32)  NOT NULL COMMENT '流程节点名称',
    `node_type`         int(11)      NOT NULL COMMENT '流程节点类型；1—自动节点，2—触发节点（被动节点），3—网关节点，4—定时节点',
    `pre_node_id`       varchar(128)          DEFAULT NULL COMMENT '上一个节点标识，逗号分隔',
    `execute_compoment` varchar(128) NOT NULL COMMENT '执行组件',
    `max_exe_time`      int(11)      NOT NULL COMMENT '最大执行时间',
    `is_sync`           tinyint(1)   NOT NULL DEFAULT '1' COMMENT '是否同步；1—同步，0—不同步',
    `is_protected`      tinyint(1)   NOT NULL DEFAULT '1' COMMENT '是否被保护；1—保护，0—不保护',
    `stage`             varchar(64)  NOT NULL COMMENT '模块名称',
    `create_time`       datetime     NOT NULL COMMENT '创建时间',
    `modified_time`     datetime     NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_process_id_name` (`process_id`, `name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '流程节点表';


CREATE TABLE `process_instance`
(
    `id`            bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `process_id`    bigint(20)  NOT NULL COMMENT '流程ID',
    `biz_no`        varchar(64) NOT NULL COMMENT '业务流水号',
    `create_time`   datetime    NOT NULL COMMENT '创建时间',
    `modified_time` datetime    NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `key_biz_no` (`biz_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '流程实例表';


CREATE TABLE `process_node_instance`
(
    `id`                  bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `process_instance_id` bigint(20)  NOT NULL COMMENT '流程实例ID',
    `biz_no`              varchar(64) NOT NULL COMMENT '业务流水号',
    `node_id`             bigint(20)  NOT NULL COMMENT '节点ID',
    `status`              int(11)     NOT NULL COMMENT '节点实例执行状态；1—开始，2—完成，3—异常，4—等待',
    `failed_count`        int(11)     NOT NULL COMMENT '失败执行次数',
    `exec_count`          int(11)     NOT NULL COMMENT '已执行次数',
    `recover_time`        datetime    NOT NULL COMMENT '下次执行时间',
    `create_time`         datetime    NOT NULL COMMENT '创建时间',
    `modified_time`       datetime    NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_process_id_biz_no_node_id_status` (`process_instance_id`, `biz_no`, `node_id`, `status`),
    KEY `key_status_modified_failed_count` (`status`, `failed_count`, `modified_time`),
    KEY `key_status_modified_failed_count_rocover` (`status`, `failed_count`, `modified_time`, `recover_time`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '节点实例表';


CREATE TABLE `process_schedule_plan`
(
    `id`                  bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `biz_no`              varchar(64) NOT NULL COMMENT '业务流水号',
    `process_instance_id` bigint(20)  NOT NULL COMMENT '流程实例ID',
    `node_name`           varchar(32) NOT NULL COMMENT '流程节点名称',
    `exec_time`           datetime    NOT NULL COMMENT '执行时间',
    `exec_counts`         int(11)     NOT NULL COMMENT '执行次数',
    `status`              varchar(16) NOT NULL COMMENT '定时任务执行状态；READY/RUNNING',
    `create_time`         datetime    NOT NULL COMMENT '创建时间',
    `modified_time`       datetime    NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `key_exce_time` (`exec_time`),
    KEY `key_biz_no` (`biz_no`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '定时任务表';


CREATE TABLE `process_global_variable`
(
    `id`                  bigint(20)    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `process_instance_id` bigint(20)    NOT NULL COMMENT '流程实例ID',
    `biz_no`              varchar(64)   NOT NULL COMMENT '业务流水号',
    `name`                varchar(32)   NOT NULL COMMENT '键名',
    `value`               varchar(1024) NOT NULL COMMENT '值',
    `create_time`         datetime      NOT NULL COMMENT '创建时间',
    `modified_time`       datetime      NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_process_biz_no_name` (`process_instance_id`, `biz_no`, `name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '全局变量表';


CREATE TABLE `process_node_input`
(
    `id`                  bigint(20)    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `process_instance_id` bigint(20)    NOT NULL COMMENT '流程实例ID',
    `biz_no`              varchar(64)   NOT NULL COMMENT '业务流水号',
    `node_id`             bigint(20)    NOT NULL COMMENT '节点ID',
    `type`                varchar(1)    NOT NULL COMMENT '1-processInput; 2-triggerInput',
    `value`               varchar(2048) NOT NULL COMMENT '值',
    `create_time`         datetime      NOT NULL COMMENT '创建时间',
    `modified_time`       datetime      NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `key_biz_no_type` (`biz_no`, `type`),
    KEY `key_biz_no_node_id_type` (`biz_no`, `node_id`, `type`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '节点输入表';



CREATE TABLE `process_trigger_result`
(
    `id`                  bigint(20)    NOT NULL AUTO_INCREMENT COMMENT '主键',
    `process_instance_id` bigint(20)    NOT NULL COMMENT '流程实例ID',
    `biz_no`              varchar(64)   NOT NULL COMMENT '业务流水号',
    `node_id`             bigint(20)    NOT NULL COMMENT '节点ID',
    `value`               varchar(2048) NOT NULL COMMENT '执行结果',
    `create_time`         datetime      NOT NULL COMMENT '创建时间',
    `modified_time`       datetime      NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY `key_biz_no_node_id` (`biz_no`, `node_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '执行结果表';




