INSERT INTO test.user_tracking
(user_tracking_id, created_by, creation_date, last_modified_by, last_modified_date, test_name, email, selected_plan, user_number)
VALUES('a87bd06a-9e9c-4a66-8f37-5ca73c1e5391', 'Naresh', '2020-05-19 10:32:18.128', 'Naresh', '2020-05-19 10:32:18.128', NULL, 'abc@demo.in', 1, '12345678');

INSERT INTO test.contact_info
(contact_info_id, first_name, last_name, phone, user_tracking_id)
VALUES('54728c9e-a2c0-49fa-8b3f-0e2821349b88', 'string', 'string', 'string', 'a87bd06a-9e9c-4a66-8f37-5ca73c1e5391');
