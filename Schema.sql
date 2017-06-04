DROP TABLE IF EXISTS typeProperty;
DROP TABLE IF EXISTS nodeLabel;
DROP TABLE IF EXISTS edge;
CREATE TABLE typeProperty(
  id VARCHAR(64),
  name VARCHAR(255)
);
CREATE INDEX idx_typeProperty_id ON typeProperty(id);

CREATE TABLE NodeType(
  gid VARCHAR(64),
  type VARCHAR
);
Create INDEX idx_nodetype_gid ON nodeType(id);


CREATE TABLE nodeLabel(
  id VARCHAR(64),
  label VARCHAR(255)
);

CREATE INDEX idx_nodeLabel_id ON nodeLabel(id);
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
