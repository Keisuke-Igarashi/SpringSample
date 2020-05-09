/*従業員テーブルのデータ（第３章で作成）*/
INSERT INTO employee(employee_id,employee_name,age) VALUES(1001,'山田太郎',30);

/*ユーザマスタのデータ(アドミン権限)*/
INSERT INTO m_user(user_id,password,user_name,birthday,age,marriage,role)
VALUES ('yamada@xxx.co.jp','$2a$10$PSAU3rf1O8VaWuC2sq59L.beBjKYF5gbOCPxOxCIQOFo.C9ghw2PG','山田太郎','1990-01-01',28,false,'ROLE_ADMIN');

/* ユーザマスタのデータ(一般権限) */
INSERT INTO m_user (user_id, password, user_name, birthday, age, marriage, role)
VALUES ('tamura@xxx.co.jp','$2a$10$PSAU3rf1O8VaWuC2sq59L.beBjKYF5gbOCPxOxCIQOFo.C9ghw2PG','田村達也','1986-11-05',31,false,'ROLE_GENERAL');