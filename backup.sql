DROP TABLE IF EXISTS typeProperty;

DROP TABLE IF EXISTS typeLabel;

DROP TABLE IF EXISTS nodeLabel;

DROP TABLE IF EXISTS Edge;

DROP TABLE IF EXISTS ObjectType;

CREATE TABLE ObjectType(
  gid VARCHAR(64),
  type VARCHAR(100),
  PRIMARY KEY (gid));

Create INDEX idx_nodetype_gid ON ObjectType(gid)
;

CREATE TABLE typeProperty(
  id VARCHAR(64),
  name VARCHAR(255)
);


CREATE INDEX idx_typeProperty_id ON typeProperty(id);


CREATE TABLE typeLabel(
  id VARCHAR(64),
  label VARCHAR(255)
);


CREATE INDEX idx_typeLabel_id ON typeLabel(id);


CREATE TABLE nodeLabel(
  gid VARCHAR(64),
  label VARCHAR(255)
);


CREATE INDEX idx_nodeLabel_id ON nodeLabel(gid);

CREATE TABLE Edge(
  eid int AUTO_INCREMENT,
  node2Label VARCHAR(255),
  node2 VARCHAR(255),
  rel_type VARCHAR(255),
  node1Label VARCHAR(255),
  relationship VARCHAR(255),
  node1 VARCHAR(255),
  PRIMARY KEY (eid)
);

CREATE INDEX idx_Edge_eid ON Edge(eid);

CREATE INDEX idx_Edge_node2Label ON Edge(node2Label);

CREATE INDEX idx_Edge_node2 ON Edge(node2);

CREATE INDEX idx_Edge_rel_type ON Edge(rel_type);

CREATE INDEX idx_Edge_node1Label ON Edge(node1Label);

CREATE INDEX idx_Edge_relationship ON Edge(relationship);

CREATE INDEX idx_Edge_node1 ON Edge(node1);

DROP TABLE IF EXISTS P_lastused;
CREATE TABLE P_lastused(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_lastused_gid ON P_lastused(gid);

DROP TABLE IF EXISTS P_friendcount;
CREATE TABLE P_friendcount(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_friendcount_gid ON P_friendcount(gid);

DROP TABLE IF EXISTS P_created;
CREATE TABLE P_created(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_created_gid ON P_created(gid);

DROP TABLE IF EXISTS P_latitude;
CREATE TABLE P_latitude(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_latitude_gid ON P_latitude(gid);

DROP TABLE IF EXISTS P_follower_count;
CREATE TABLE P_follower_count(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_follower_count_gid ON P_follower_count(gid);

DROP TABLE IF EXISTS P_tid;
CREATE TABLE P_tid(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_tid_gid ON P_tid(gid);

DROP TABLE IF EXISTS P_uid;
CREATE TABLE P_uid(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_uid_gid ON P_uid(gid);

DROP TABLE IF EXISTS P_screen_name;
CREATE TABLE P_screen_name(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_screen_name_gid ON P_screen_name(gid);

DROP TABLE IF EXISTS P_name;
CREATE TABLE P_name(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_name_gid ON P_name(gid);

DROP TABLE IF EXISTS P_text;
CREATE TABLE P_text(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_text_gid ON P_text(gid);

DROP TABLE IF EXISTS P_id;
CREATE TABLE P_id(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_id_gid ON P_id(gid);

DROP TABLE IF EXISTS P_lang;
CREATE TABLE P_lang(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_lang_gid ON P_lang(gid);

DROP TABLE IF EXISTS P_timestamp;
CREATE TABLE P_timestamp(
  gid VARCHAR(64),
  value TEXT,
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_timestamp_gid ON P_timestamp(gid);
INSERT INTO typeProperty(id, name) VALUES ("2", "lastused");
INSERT INTO typeProperty(id, name) VALUES ("2", "latitude");
INSERT INTO typeProperty(id, name) VALUES ("2", "text");
INSERT INTO typeProperty(id, name) VALUES ("2", "id");
INSERT INTO typeProperty(id, name) VALUES ("2", "lang");
INSERT INTO typeProperty(id, name) VALUES ("2", "tid");
INSERT INTO typeProperty(id, name) VALUES ("2", "timestamp");
INSERT INTO typeProperty(id, name) VALUES ("3", "lastused");
INSERT INTO typeProperty(id, name) VALUES ("3", "uid");
INSERT INTO typeProperty(id, name) VALUES ("3", "friendcount");
INSERT INTO typeProperty(id, name) VALUES ("3", "screen_name");
INSERT INTO typeProperty(id, name) VALUES ("3", "created");
INSERT INTO typeProperty(id, name) VALUES ("3", "id");
INSERT INTO typeProperty(id, name) VALUES ("3", "follower_count");
INSERT INTO typeProperty(id, name) VALUES ("4", "lastused");
INSERT INTO typeProperty(id, name) VALUES ("4", "created");
INSERT INTO typeProperty(id, name) VALUES ("4", "name");
INSERT INTO typeProperty(id, name) VALUES ("4", "id");
INSERT INTO typeLabel(id, label) VALUES ("2", "tweet");
INSERT INTO typeLabel(id, label) VALUES ("3", "user");
INSERT INTO typeLabel(id, label) VALUES ("4", "hashtag");
