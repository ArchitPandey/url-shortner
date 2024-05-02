--to select database
use url-shortner;

--create table
create table shorturl_map (id bigint signed not null, short_url varchar(11) not null, long_url varchar(255) not null, constraint pk_id primary key(id), constraint uc_short_url unique(short_url));