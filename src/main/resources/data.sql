

-- === 1) 권한 테이블 초기화 ===
INSERT INTO roles (name) VALUES ('ROLE_USER')
    ON DUPLICATE KEY UPDATE name = name;
INSERT INTO roles (name) VALUES ('ROLE_ADMIN')
    ON DUPLICATE KEY UPDATE name = name;

-- === 2) 관리자 계정 생성 ===
--    이메일: admin@example.com
--    비밀번호: admin123 → 아래 해시는 본인이 생성한 BCrypt 해시로 바꿔주세요.
INSERT INTO users (email, password)
VALUES (
           'admin@example.com',
           '$2a$10$QpIBAf.jXBlujmE8LikNpuk3lK45UUjbfKBsqjGesYJbeMxzl6.IK'  -- ← 여기를 본인 해시로 교체
       )
    ON DUPLICATE KEY UPDATE email = email;

-- === 3) 관리자 권한 맵핑 ===
INSERT INTO users_roles (user_id, role_id)
SELECT u.id, r.id
FROM users u
         JOIN roles r ON r.name = 'ROLE_ADMIN'
WHERE u.email = 'admin@example.com'
    ON DUPLICATE KEY UPDATE user_id = user_id;

-- === 4) 상품 데이터 적재 ===
insert into product (name, brand, made_in, price) values ('Galaxy S6', 'Samsung Corp', 'Korea', 600.0);
insert into product (name, brand, made_in, price) values ('Galaxy S8', 'Samsung Corp', 'Korea', 800.0);
insert into product (name, brand, made_in, price) values ('Galaxy S10', 'Samsung Corp', 'Korea', 1000.0);
insert into product (name, brand, made_in, price) values ('Galaxy S21', 'Samsung Corp', 'Korea', 1000.0);

insert into product (name, brand, made_in, price) values ('MacBook Air1', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Air2', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Air3', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Air4', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Air5', 'Apple', 'China',  10000);
insert into product (name, brand, made_in, price) values ('MacBook Pro1', 'Apple', 'China',  15000);
insert into product (name, brand, made_in, price) values ('MacBook Pro2', 'Apple', 'China',  15000);

insert into product (name, brand, made_in, price) values ('iPad Air', 'Apple', 'China',  500);
insert into product (name, brand, made_in, price) values ('iPad Pro', 'Apple', 'China',  800);

insert into product (name, brand, made_in, price) values ('소나타', 'Hyundai', 'Japan',  20000);
insert into product (name, brand, made_in, price) values ('그랜저', 'Hyundai', 'Japan',  30000);
insert into product (name, brand, made_in, price) values ('제너시스', 'Hyundai', 'Japan',  50000);
insert into product (name, brand, made_in, price) values ('에쿠스', 'Hyundai', 'Japan',  60000);

insert into product (name, brand, made_in, price) values ('Accord', 'Honda', 'Japan',  25000);
insert into product (name, brand, made_in, price) values ('sienna', 'Honda', 'Japan',  40000);

insert into product (name, brand, made_in, price) values ('Camry', 'Toyota', 'Japan',  25000);
insert into product (name, brand, made_in, price) values ('Lexus', 'Toyota', 'Japan',  50000);