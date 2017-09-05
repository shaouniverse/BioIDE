
-- -----------------------------------------------------
-- Table `biodata`.`taxon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxon` (
  `id` VARCHAR(50) NOT NULL COMMENT '记录id',
  `scientificname` VARCHAR(80) NOT NULL COMMENT '学名:正式接受的拉丁名 ,不包括命名人和命名年代',
  `authorstr` VARCHAR(80) NULL COMMENT '命名人和命名年代',
  `epithet` VARCHAR(45) NULL COMMENT '种加词 或 亚种加词',
  `rankid` INT NULL COMMENT '等级，如界、门、纲、目等，关联到另外一张表中',
  `nomencode` VARCHAR(50) NULL COMMENT 'Nomenclatural Code 命名法规: ICZN、ICBN...',
  `remark` VARCHAR(1000) NULL COMMENT '备注',
  `sourcesid` VARCHAR(45) NULL COMMENT '数据源的id',
  `tci` VARCHAR(100) NULL COMMENT 'taxon concept identifier\ntaxon 概念标识，',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(50) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '分类单元表';


-- -----------------------------------------------------
-- Table `biodata`.`specimendata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`specimendata` (
  `id` VARCHAR(50) NOT NULL,
  `collector` VARCHAR(50) NULL COMMENT '采集人',
  `collectdate` VARCHAR(40) NULL COMMENT '采集时间',
  `location` VARCHAR(200) NULL COMMENT '采集地',
  `lng` DOUBLE NULL COMMENT '经度',
  `lat` DOUBLE NULL COMMENT '纬度',
  `idenby` VARCHAR(45) NULL COMMENT '鉴定人',
  `idendate` VARCHAR(20) NULL COMMENT '鉴定时间',
  `specimenno` VARCHAR(45) NULL COMMENT '标本号',
  `sex` VARCHAR(10) NULL COMMENT '性别',
  `storedin` VARCHAR(45) NULL COMMENT '馆藏地点',
  `specimentype` VARCHAR(45) NULL COMMENT '标本类型',
  `mediajson` JSON NULL COMMENT '相关多媒体信息（json）',
  `taxonid` VARCHAR(50) NULL,
  `desid` VARCHAR(50) NULL,
  `refjson` JSON NULL COMMENT '相关参考文献的json数组',
  `sourcesid` VARCHAR(45) NULL COMMENT '数据源的id',
  `state` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '标本信息';


-- -----------------------------------------------------
-- Table `biodata`.`citation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`citation` (
  `id` VARCHAR(50) NOT NULL,
  `sciname` VARCHAR(100) NULL,
  `authorship` VARCHAR(100) NULL,
  `nametype` INT NULL,
  `citationstr` VARCHAR(500) NULL,
  `shortrefs` JSON NULL,
  `refjson` JSON NULL COMMENT '相关参考文献的json数组',
  `sourcesid` VARCHAR(45) NULL COMMENT '数据源的id',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '引证信息';


-- -----------------------------------------------------
-- Table `biodata`.`distributiondata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`distributiondata` (
  `id` VARCHAR(50) NOT NULL,
  `taxonid` VARCHAR(50) NULL COMMENT '与taxon表关联',
  `geoid` VARCHAR(50) NULL COMMENT '与geoobject表关联',
  `desid` VARCHAR(50) NULL COMMENT '与description表关联',
  `specimenid` VARCHAR(45) NULL COMMENT '与标本相关的分布记录',
  `lng` DOUBLE NULL COMMENT '经度',
  `lat` DOUBLE NULL COMMENT '纬度',
  `refjson` JSON NULL COMMENT '相关参考文献的json数组',
  `sourcesid` VARCHAR(45) NULL COMMENT '数据源的id',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '分布信息';


-- -----------------------------------------------------
-- Table `biodata`.`molecular`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`molecular` (
  `id` VARCHAR(50) NOT NULL COMMENT 'UUID',
  `taxonid` VARCHAR(45) NULL,
  `authorname` VARCHAR(500) NULL COMMENT '数据的作者',
  `authorinstitution` VARCHAR(500) NULL COMMENT '数据的所属机构',
  `fastaurl` VARCHAR(500) NULL COMMENT 'FASTA文件存储的路径',
  `title` VARCHAR(800) NULL COMMENT '对分子数据的描述',
  `sequence` LONGTEXT NULL COMMENT '序列内容',
  `type` VARCHAR(500) NULL COMMENT '序列的种类',
  `location` VARCHAR(500) NULL COMMENT '序列取样的来源(细胞核、线粒体...)',
  `otherinfo` JSON NULL,
  `sourcesjson` JSON NULL COMMENT '相关数据来源集的json数组',
  `ncbiid` VARCHAR(50) NULL COMMENT 'NCBI的ID',
  `refjson` JSON NULL COMMENT '相关参考文献的json数组',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '分子数据';


-- -----------------------------------------------------
-- Table `biodata`.`multimedia`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`multimedia` (
  `id` VARCHAR(50) NOT NULL,
  `title` VARCHAR(30) NULL COMMENT '多媒体信息标题',
  `info` VARCHAR(100) NULL COMMENT '简介',
  `rightsholder` VARCHAR(60) NULL COMMENT '版权人',
  `sourcetext` VARCHAR(60) NULL COMMENT '来源',
  `lisenceid` VARCHAR(50) NULL COMMENT '共享协议',
  `path` VARCHAR(60) NULL COMMENT '存储路径',
  `desid` VARCHAR(50) NULL COMMENT '描述的ID，及该记录来源于哪个描述，可以为空',
  `source` VARCHAR(45) NULL COMMENT '数据源',
  `taxonid` VARCHAR(50) NULL,
  `refjson` JSON NULL COMMENT '相关参考文献的json数组',
  `sourcesid` VARCHAR(45) NULL COMMENT '数据源的id',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '多媒体信息';


-- -----------------------------------------------------
-- Table `biodata`.`traitdata`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`traitdata` (
  `id` VARCHAR(50) NOT NULL,
  `taxonid` VARCHAR(50) NULL COMMENT '对应的分类单元id，与taxon表关联',
  `trainsetid` VARCHAR(45) NULL COMMENT '所使用的术语集id',
  `traitjson` JSON NULL COMMENT 'json表示的生物特征',
  `desid` VARCHAR(50) NULL COMMENT '提取特征的相关原始描述文本，与description表关联',
  `refjson` JSON NULL COMMENT '相关参考文献的json数组',
  `sourcesjson` JSON NULL COMMENT '相关数据来源集的json数组',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '特征';


-- -----------------------------------------------------
-- Table `biodata`.`description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`description` (
  `id` VARCHAR(50) NOT NULL COMMENT 'UUID',
  `describer` VARCHAR(45) NULL COMMENT '描述人',
  `desdate` VARCHAR(45) NULL COMMENT '描述时间，一般为年份',
  `destitle` VARCHAR(100) NULL COMMENT '描述标题',
  `descontent` LONGTEXT NULL COMMENT '描述内容',
  `destypeid` VARCHAR(50) NULL COMMENT '描述类型ID, 外联类型表',
  `rightsholder` VARCHAR(100) NULL COMMENT '权利所有人',
  `licenseid` VARCHAR(45) NULL COMMENT '共享协议id，外联共享协议表',
  `remark` VARCHAR(500) NULL COMMENT '备注',
  `taxonid` VARCHAR(50) NULL,
  `language` VARCHAR(45) NULL COMMENT '语言',
  `relation` JSON NULL COMMENT '关系，如谁是谁的翻译',
  `sourcesid` VARCHAR(45) NULL COMMENT '数据源的id',
  `refjson` JSON NULL COMMENT '相关参考文献的json数组',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(50) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '添加时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '描述信息';


-- -----------------------------------------------------
-- Table `biodata`.`taxkey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxkey` (
  `id` VARCHAR(50) NOT NULL,
  `keytitle` VARCHAR(100) NULL COMMENT '检索表名',
  `taxonid` VARCHAR(50) NULL COMMENT '哪个taxon的检索表',
  `desid` VARCHAR(50) NULL COMMENT '与description关联，存储整张检索表',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '检索表';


-- -----------------------------------------------------
-- Table `biodata`.`phytree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`phytree` (
  `id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '进化树';


-- -----------------------------------------------------
-- Table `biodata`.`taxon_taxtree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxon_taxtree` (
  `id` VARCHAR(50) NOT NULL,
  `taxonid` VARCHAR(50) NULL,
  `taxontreeid` VARCHAR(45) NULL,
  `parentid` VARCHAR(50) NULL,
  `datasetid` VARCHAR(45) NULL,
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '分类树';


-- -----------------------------------------------------
-- Table `biodata`.`dataset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`dataset` (
  `id` VARCHAR(50) NOT NULL,
  `dsname` VARCHAR(45) NULL COMMENT '数据集名称',
  `dsabstract` VARCHAR(1000) NULL COMMENT '数据集简介',
  `lisenceid` VARCHAR(50) NULL COMMENT '共享协议id，外联共享协议表',
  `createdDate` DATE NULL COMMENT '添加日期',
  `teamid` VARCHAR(50) NULL COMMENT '所属团队的ID',
  `creator` VARCHAR(50) NULL COMMENT '创建人',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '数据集';


-- -----------------------------------------------------
-- Table `biodata`.`taxon_taxaset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxon_taxaset` (
  `id` VARCHAR(50) NOT NULL,
  `taxonid` VARCHAR(50) NULL,
  `taxasetid` VARCHAR(45) NULL,
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '分类单元集合';


-- -----------------------------------------------------
-- Table `biodata`.`taxaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxaction` (
  `id` VARCHAR(50) NOT NULL,
  `taxonid` VARCHAR(50) NULL COMMENT '动作对象taxon',
  `actions` JSON NULL COMMENT '动作，json',
  `actdate` DATE NULL COMMENT '动作时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '描述分类动作';


-- -----------------------------------------------------
-- Table `biodata`.`datasources`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`datasources` (
  `id` VARCHAR(50) NOT NULL COMMENT 'UUID',
  `title` VARCHAR(100) NULL COMMENT '数据源标题',
  `info` TEXT NULL COMMENT '描述',
  `versions` VARCHAR(200) NULL COMMENT '版本或日期',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '数据源';


-- -----------------------------------------------------
-- Table `biodata`.`refs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`refs` (
  `id` VARCHAR(50) NOT NULL,
  `refstr` VARCHAR(1000) NULL COMMENT '完整题录',
  `author` VARCHAR(100) NULL COMMENT '作者',
  `pyear` VARCHAR(45) NULL COMMENT '发表时间',
  `leftstr` VARCHAR(900) NULL COMMENT '除去作者、年代剩余部分',
  `title` VARCHAR(45) NULL COMMENT '文章标题',
  `ptype` VARCHAR(45) NULL COMMENT '类型',
  `journal` VARCHAR(45) NULL COMMENT '期刊/专著的名字',
  `languages` VARCHAR(45) NULL COMMENT '语言',
  `olang` INT NULL COMMENT '原始语言',
  `keywords` VARCHAR(500) NULL COMMENT '关键字',
  `translator` VARCHAR(45) NULL COMMENT '书的译者',
  `press` VARCHAR(45) NULL COMMENT '出版社',
  `place` VARCHAR(45) NULL COMMENT '出版地',
  `tpage` VARCHAR(45) NULL COMMENT '总页数',
  `tchar` VARCHAR(45) NULL COMMENT '总字数',
  `version` VARCHAR(45) NULL COMMENT '第几版',
  `isbn` VARCHAR(45) NULL COMMENT 'ISBN',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  `remark` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '文献';


-- -----------------------------------------------------
-- Table `biodata`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`user` (
  `id` VARCHAR(50) NOT NULL COMMENT 'UUID',
  `user_name` VARCHAR(100) NOT NULL COMMENT '用户的账户名',
  `password` VARCHAR(45) NOT NULL COMMENT '密码，MD5加密',
  `email` VARCHAR(100) NOT NULL COMMENT '注册邮箱',
  `phone` VARCHAR(45) NULL COMMENT '手机号',
  `role` VARCHAR(45) NULL COMMENT '角色标识,预留字段，可扩展',
  `adddate` DATETIME NULL COMMENT '注册/添加时间',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`user_name` ASC))
ENGINE = InnoDB
COMMENT = '用户';


-- -----------------------------------------------------
-- Table `biodata`.`protection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`protection` (
  `id` VARCHAR(50) NOT NULL,
  `desid` VARCHAR(50) NULL,
  `protlevel` VARCHAR(100) NULL COMMENT '保护级别',
  `protstandardid` VARCHAR(50) NULL COMMENT '保护类型（外链protectstandard）',
  `refjson` JSON NULL COMMENT '相关参考文献的json数组',
  `sourcesid` VARCHAR(45) NULL COMMENT '相关参考文献的json数组',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '保护信息';


-- -----------------------------------------------------
-- Table `biodata`.`traitontology`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`traitontology` (
  `id` INT NOT NULL,
  `enterm` VARCHAR(100) NULL COMMENT '术语 英文',
  `cnterm` VARCHAR(100) NULL COMMENT '术语 中文',
  `definition` TEXT NULL COMMENT '定义',
  `synonymys` JSON NULL COMMENT '同义术语，json对象',
  `sourcesjson` JSON NULL COMMENT '数据源的各种信息\n',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  `catalog1` VARCHAR(45) NULL COMMENT '第一层',
  `catalog2` VARCHAR(45) NULL COMMENT '第二层',
  `catalog3` VARCHAR(45) NULL COMMENT '第3层',
  `group` VARCHAR(45) NULL COMMENT '类群',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`geoobject`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`geoobject` (
  `id` VARCHAR(50) NOT NULL,
  `cngeoname` VARCHAR(100) NULL COMMENT '地理实体中文名',
  `engeoname` VARCHAR(100) NULL COMMENT '地理实体英文名',
  `geodata` JSON NULL COMMENT '实体数据',
  `pid` VARCHAR(50) NULL COMMENT '父ID，表示层次结构',
  `geotype` VARCHAR(50) NULL COMMENT '实体类型',
  `version` VARCHAR(45) NULL COMMENT '版本',
  `relation` JSON NULL COMMENT '关系：同义，包含等',
  `centerx` DOUBLE NULL,
  `centery` DOUBLE NULL,
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '描述地理实体，如省、市、县；保护区；河流；山峰等';


-- -----------------------------------------------------
-- Table `biodata`.`keyitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`keyitem` (
  `id` VARCHAR(50) NOT NULL,
  `item` VARCHAR(500) NULL,
  `innerorder` INT NULL COMMENT '同一级检索特征排序',
  `orderid` INT NULL COMMENT '整形表达的id，用于排序',
  `branchid` INT NULL COMMENT '分支id，整型',
  `taxonid` VARCHAR(50) NULL COMMENT '分类到哪个taxon，与taxon表关联',
  `taxkeyid` VARCHAR(50) NULL COMMENT '与taxkey表关联',
  `pid` VARCHAR(50) NULL,
  `featureimgjson` VARCHAR(50) NULL COMMENT '特征图片，json',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`protectstandard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`protectstandard` (
  `id` VARCHAR(50) NOT NULL,
  `standardname` VARCHAR(100) NULL COMMENT '保护标准名称',
  `standardinfo` VARCHAR(600) NULL COMMENT '标准简介',
  `version` VARCHAR(45) NULL COMMENT '版本',
  `releasedate` VARCHAR(45) NULL COMMENT '发布时间',
  `source` VARCHAR(45) NULL COMMENT '标准来源，IUCN、CITES等',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '保护区实体';


-- -----------------------------------------------------
-- Table `biodata`.`taxaset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxaset` (
  `id` VARCHAR(50) NOT NULL,
  `tsname` VARCHAR(45) NULL COMMENT 'taxaset 名称',
  `tsinfo` VARCHAR(500) NULL COMMENT 'taxaset 简介',
  `refsjson` JSON NULL COMMENT '相关文献',
  `sourcejson` JSON NULL COMMENT '相关数据源',
  `datasetid` VARCHAR(50) NULL COMMENT '所属数据集的ID',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`taxtree`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxtree` (
  `id` VARCHAR(50) NOT NULL,
  `treename` VARCHAR(45) NULL COMMENT 'tree 名称',
  `treeinfo` VARCHAR(500) NULL COMMENT 'tree 简介',
  `refsjson` JSON NULL COMMENT '相关文献',
  `sourcejson` JSON NULL COMMENT '相关数据源',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`taxon_taxon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxon_taxon` (
  `taxon` VARCHAR(50) NOT NULL COMMENT 'taxon1的 ID',
  `relationship` JSON NOT NULL COMMENT '关系：\n0.与B无关; \n1.A包含B; \n2.A被B包含；\n3.A与B有重合的部分；\n4.A等价于B',
  `sourcesid` VARCHAR(45) NULL COMMENT '数据源的id',
  `refjson` VARCHAR(45) NULL COMMENT '相关参考文献的json数组',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '添加时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步\n',
  `synchtime` DATETIME NULL COMMENT '最后同步时间')
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`team` (
  `id` VARCHAR(50) NOT NULL COMMENT 'UUID',
  `name` VARCHAR(100) NULL COMMENT '团队名称',
  `leader` VARCHAR(50) NOT NULL COMMENT '团队负责人（有添加和移除团队成员的权限）',
  `adddate` DATETIME NULL COMMENT '添加时间',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`user_team`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`user_team` (
  `user_id` VARCHAR(50) NOT NULL,
  `team_id` VARCHAR(45) NOT NULL)
ENGINE = InnoDB
COMMENT = '用户表和团队表的关联表';


-- -----------------------------------------------------
-- Table `biodata`.`rank`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`rank` (
  `id` INT NOT NULL,
  `chname` VARCHAR(100) NULL COMMENT '等级的中文名',
  `enname` VARCHAR(100) NOT NULL COMMENT '等级的拉丁？英文名？',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`license`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`license` (
  `id` VARCHAR(45) NOT NULL COMMENT 'uuid',
  `title` VARCHAR(200) NULL COMMENT '标题',
  `text` LONGTEXT NULL COMMENT '内容',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`taxon_ref`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`taxon_ref` (
  `taxonid` VARCHAR(50) NOT NULL,
  `refid` VARCHAR(50) NULL,
  `page` VARCHAR(45) NULL COMMENT '引用的页码范围',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期')
ENGINE = InnoDB
COMMENT = '补充文献表';


-- -----------------------------------------------------
-- Table `biodata`.`traitset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`traitset` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `remark` TEXT NULL COMMENT '备注',
  `sourcesid` JSON NULL COMMENT '特征集的来源',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `biodata`.`trait_traitset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`trait_traitset` (
  `traitsetid` VARCHAR(45) NULL COMMENT '术语集id',
  `traitontologyid` INT NULL COMMENT '术语id',
  `pid` VARCHAR(45) NULL COMMENT '在此术语集的结构中的上级术语，最高级为‘0’',
  `status` INT NULL DEFAULT 1 COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(45) NULL COMMENT '录入人',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL DEFAULT 0 COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期')
ENGINE = InnoDB;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Table `biodata`.`descriptiontype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`descriptiontype` (
  `id` VARCHAR(50) NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '描述类型';


-- -----------------------------------------------------
-- Table `biodata`.`traitdescription`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`traitdescription` (
  `id` INT NOT NULL,
  `traitontologyid` INT NULL COMMENT '术语id',
  `propertyid` INT NULL COMMENT '属性id',
  `traitdescriptioncol` VARCHAR(45) NULL,
  `definition` TEXT NULL COMMENT '定义',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '术语组合描述表';


-- -----------------------------------------------------
-- Table `biodata`.`commoname`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `biodata`.`commoname` (
  `id` VARCHAR(50) NOT NULL COMMENT 'UUID',
  `taxonid` VARCHAR(50) NULL COMMENT '所指向的阶元id',
  `commonname` VARCHAR(200) NULL COMMENT '俗名',
  `language` VARCHAR(100) NULL COMMENT '所属语言',
  `refjson` JSON NULL COMMENT '参考文献json',
  `sourcesid` VARCHAR(50) NULL COMMENT '数据源id',
  `status` INT NULL COMMENT '状态（默认1、可用；0、不可用）',
  `inputer` VARCHAR(50) NULL COMMENT '添加人的id,外链user表',
  `inputtime` DATETIME NULL COMMENT '录入时间',
  `synchstatus` INT NULL COMMENT '同步状态，即是否与服务器进行同步\n0 本地有更新，未与服务器同步\n1 与服务器同步中\n2 完成同步',
  `synchdate` DATETIME NULL COMMENT '最后同步日期',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;