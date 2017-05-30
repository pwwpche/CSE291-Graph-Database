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
