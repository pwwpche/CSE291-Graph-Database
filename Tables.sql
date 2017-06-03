DROP TABLE IF EXISTS NodeLabel;
DROP TABLE IF EXISTS Edge;
DROP TABLE IF EXISTS Person;
DROP TABLE IF EXISTS Movie;
DROP TABLE IF EXISTS Relationship;


CREATE TABLE Person (
  pid int NOT NULL AUTO_INCREMENT,
  birthday VARCHAR(255),
  birthplace VARCHAR(255),
  deg int,
  name VARCHAR(255),
  lastModified VARCHAR(255),
  id VARCHAR(255),
  biography VARCHAR(1500),
  version int,
  profileImageUrl VARCHAR(500),
  PRIMARY KEY (pid),
  KEY (id)
);
CREATE INDEX idx_Person_pid ON Person(pid);
CREATE INDEX idx_Person_id ON Person(id);

CREATE TABLE Movie (
  mid int NOT NULL AUTO_INCREMENT,
  studio VARCHAR(255),
  releaseDate VARCHAR(255),
  imdbId VARCHAR(255),
  runtime int,
  description VARCHAR(1000),
  language VARCHAR(255),
  title VARCHAR(255),
  version int,
  trailer VARCHAR(500),
  imageUrl VARCHAR(500),
  genre VARCHAR(255),
  tagline VARCHAR(255),
  lastModified VARCHAR(255),
  id VARCHAR(255),
  homepage VARCHAR(255),
  PRIMARY KEY (mid),
  KEY (id)
);
CREATE INDEX idx_movie_id ON Movie(id);
CREATE INDEX idx_movie_mid ON Movie(mid);

CREATE TABLE Relationship(
  rid int NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (rid)
);
CREATE INDEX idx_relationship_rid ON Relationship(rid);
CREATE INDEX idx_relationship_name ON Relationship(name);

CREATE TABLE Edge(
  eid int NOT NULL AUTO_INCREMENT,
  pid VARCHAR(255) NOT NULL,
  mid VARCHAR(255) NOT NULL,
  rid int NOT NULL,
  rel_type VARCHAR(255),

  PRIMARY KEY (eid),
  FOREIGN KEY (pid) REFERENCES Person(id),
  FOREIGN KEY (mid) REFERENCES Movie(id),
  FOREIGN KEY (rid) REFERENCES Relationship(rid)
);
CREATE INDEX idx_Edge_eid ON Edge(eid);
CREATE INDEX idx_Edge_pid ON Edge(pid);
CREATE INDEX idx_Edge_mid ON Edge(mid);
CREATE INDEX idx_Edge_rel_type ON Edge(rel_type);



CREATE TABLE NodeLabel(
  pid VARCHAR(255),
  mid VARCHAR(255),
  label VARCHAR(255),
  FOREIGN KEY (pid) REFERENCES Person(id),
  FOREIGN KEY (mid) REFERENCES Movie(id)
);

CREATE INDEX idx_NodeLabel_pid ON NodeLabel(pid);
CREATE INDEX idx_NodeLabel_mid ON NodeLabel(mid);
CREATE INDEX idx_NodeLabel_label ON NodeLabel(label);






DROP TABLE IF EXISTS typeProperty;
DROP TABLE IF EXISTS nodeLabel;
DROP TABLE IF EXISTS relLabel;
CREATE TABLE typeProperty(
  id VARCHAR(255),
  name VARCHAR(255)
);

CREATE INDEX idx_typeProperty_id ON typeProperty(id);

CREATE TABLE nodeLabel(
  id VARCHAR(255),
  label VARCHAR(255)
);

CREATE TABLE relLabel(
  id VARCHAR(255),
  label VARCHAR(255)
);

CREATE INDEX idx_nodeLabel_id ON nodeLabel(id);
CREATE INDEX idx_relLabel_id ON relLabel(id);


CREATE TABLE Edge(
  eid int AUTO_INCREMENT,
  fromId VARCHAR(255),
  toId VARCHAR(255),
  fromtype VARCHAR(255),
  totype VARCHAR(255),
  relId VARCHAR(255),
  reltype VARCHAR(255),
  PRIMARY KEY (eid)
);


CREATE INDEX idx_Edge_eid ON Edge(eid);
CREATE INDEX idx_Edge_fromid ON Edge(eid);
CREATE INDEX idx_Edge_toid ON Edge(eid);
CREATE INDEX idx_Edge_relid ON Edge(eid);
CREATE INDEX idx_Edge_fromtype ON Edge(eid);
CREATE INDEX idx_Edge_totype ON Edge(eid);
CREATE INDEX idx_Edge_reltype ON Edge(eid);

CREATE TABLE p_id(
  gid VARCHAR(255),
  value varchar(255),
  PRIMARY KEY (gid)
);

CREATE INDEX idx_p_id_gid ON p_id(gid);


DROP TABLE IF EXISTS typeProperty;
DROP TABLE IF EXISTS nodeLabel;
DROP TABLE IF EXISTS relLabel;
CREATE TABLE typeProperty(
  id VARCHAR(64),
  name VARCHAR(255)
);

CREATE INDEX idx_typeProperty_id ON typeProperty(id);

CREATE TABLE nodeLabel(
  id VARCHAR(64),
  label VARCHAR(255)
);

CREATE TABLE relLabel(
  id VARCHAR(64),
  label VARCHAR(255)
);

CREATE INDEX idx_nodeLabel_id ON nodeLabel(id);
CREATE INDEX idx_relLabel_id ON relLabel(id);
CREATE TABLE Edge(
  eid int AUTO_INCREMENT,node2Label VARCHAR(255),node2 VARCHAR(255),rel_type VARCHAR(255),node1Label VARCHAR(255),relationship VARCHAR(255),node1 VARCHAR(255),  PRIMARY KEY (eid)
);
CREATE INDEX idx_Edge_eid ON Edge(eid);
CREATE INDEX idx_Edge_node2Label ON Edge(node2Label);
CREATE INDEX idx_Edge_node2 ON Edge(node2);
CREATE INDEX idx_Edge_rel_type ON Edge(rel_type);
CREATE INDEX idx_Edge_node1Label ON Edge(node1Label);
CREATE INDEX idx_Edge_relationship ON Edge(relationship);
CREATE INDEX idx_Edge_node1 ON Edge(node1);

DROP TABLE IF EXISTS P_studio;
CREATE TABLE P_studio(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_studio_gid ON P_studio(gid);

DROP TABLE IF EXISTS P_birthday;
CREATE TABLE P_birthday(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_birthday_gid ON P_birthday(gid);

DROP TABLE IF EXISTS P_releaseDate;
CREATE TABLE P_releaseDate(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_releaseDate_gid ON P_releaseDate(gid);

DROP TABLE IF EXISTS P_imdbId;
CREATE TABLE P_imdbId(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_imdbId_gid ON P_imdbId(gid);

DROP TABLE IF EXISTS P_deg;
CREATE TABLE P_deg(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_deg_gid ON P_deg(gid);

DROP TABLE IF EXISTS P_runtime;
CREATE TABLE P_runtime(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_runtime_gid ON P_runtime(gid);

DROP TABLE IF EXISTS P_description;
CREATE TABLE P_description(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_description_gid ON P_description(gid);

DROP TABLE IF EXISTS P_language;
CREATE TABLE P_language(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_language_gid ON P_language(gid);

DROP TABLE IF EXISTS P_biography;
CREATE TABLE P_biography(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_biography_gid ON P_biography(gid);

DROP TABLE IF EXISTS P_title;
CREATE TABLE P_title(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_title_gid ON P_title(gid);

DROP TABLE IF EXISTS P_version;
CREATE TABLE P_version(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_version_gid ON P_version(gid);

DROP TABLE IF EXISTS P_trailer;
CREATE TABLE P_trailer(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_trailer_gid ON P_trailer(gid);

DROP TABLE IF EXISTS P_birthplace;
CREATE TABLE P_birthplace(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_birthplace_gid ON P_birthplace(gid);

DROP TABLE IF EXISTS P_imageUrl;
CREATE TABLE P_imageUrl(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_imageUrl_gid ON P_imageUrl(gid);

DROP TABLE IF EXISTS P_genre;
CREATE TABLE P_genre(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_genre_gid ON P_genre(gid);

DROP TABLE IF EXISTS P_name;
CREATE TABLE P_name(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_name_gid ON P_name(gid);

DROP TABLE IF EXISTS P_tagline;
CREATE TABLE P_tagline(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_tagline_gid ON P_tagline(gid);

DROP TABLE IF EXISTS P_lastModified;
CREATE TABLE P_lastModified(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_lastModified_gid ON P_lastModified(gid);

DROP TABLE IF EXISTS P_id;
CREATE TABLE P_id(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_id_gid ON P_id(gid);

DROP TABLE IF EXISTS P_profileImageUrl;
CREATE TABLE P_profileImageUrl(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_profileImageUrl_gid ON P_profileImageUrl(gid);

DROP TABLE IF EXISTS P_homepage;
CREATE TABLE P_homepage(
  gid VARCHAR(64),
  value VARCHAR(255),
  PRIMARY KEY (gid)
);
CREATE INDEX idx_p_homepage_gid ON P_homepage(gid);
