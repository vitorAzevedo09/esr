CREATE TABLE permissions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    description VARCHAR(60) NOT NULL,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE group_permission (
    group_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (group_id, permission_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE group_permission ADD CONSTRAINT fk_group_permission_permission
FOREIGN KEY (permission_id) REFERENCES permissions (id);

ALTER TABLE group_permission ADD CONSTRAINT fk_group_permission_group
FOREIGN KEY (group_id) REFERENCES groups(id);
