------------------------CREATE SCHEMA--------------------------------------------------
create SCHEMA test;

create table test.hardware (
    hardware_id uuid not null,
    manufacture varchar(255),
    memory varchar(255),
    model varchar(255),
    processor varchar(255),
    virtualization varchar(255),
    primary key (hardware_id)
 );

CREATE TABLE test.user_tracking (
	user_tracking_id uuid NOT NULL,
	created_by varchar(255) NULL,
	creation_date timestamp NULL,
	last_modified_by varchar(255) NULL,
	last_modified_date timestamp NULL,
	test_name varchar(255) NULL,
	email varchar(255) NULL,
	selected_plan int4 NULL,
	user_number varchar(255) NULL,
	CONSTRAINT user_tracking_pkey PRIMARY KEY (user_tracking_id)
);


CREATE TABLE test.contact_info (
	contact_info_id uuid NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	phone varchar(255) NULL,
	user_tracking_id uuid NULL,
	CONSTRAINT contact_info_pkey PRIMARY KEY (contact_info_id),
	CONSTRAINT fklrpgvblpewek3ukw5gtqyjlg9 FOREIGN KEY (user_tracking_id) REFERENCES test.user_tracking(user_tracking_id)
);