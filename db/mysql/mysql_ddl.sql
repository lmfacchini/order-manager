create table ordr(
     id BIGINT not null PRIMARY KEY AUTO_INCREMENT,
     ordr_numbr varchar(6) not null,
     crtd TIMESTAMP not null,
     clnt_cde varchar(6) not null,
     sb_ttl numeric(7,2) not null,
     UNIQUE (ordr_numbr)
);

create table itm_ordr(
     id BIGINT not null PRIMARY KEY AUTO_INCREMENT,
     nm_prdct varchar(100) not null,
     un_vle numeric(7,2) not null,
     amnt numeric(7,2) not null,
     crtd TIMESTAMP not null,
     ordr_id BIGINT not null,
     sb_ttl numeric(7,2) not null,
     FOREIGN KEY (ordr_id) REFERENCES ordr(id)
);