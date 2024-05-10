-- select * from users;

--         SELECT * 
--         FROM USERS 
--         WHERE userId = 1;
--         
-- 		SELECT * 
--         FROM USERS 
--         WHERE email = 'user1@example.com';

-- 20개의 임의의 유저 데이터 삽입 쿼리
INSERT INTO `USERS` (`email`, `password`, `phone`, `name`, `address`, `longitude`, `latitude`, `profileImgUrl`, `height`, `weight`, `auth`)
VALUES
('trainer1@example.com', 'password1', '010-1234-5678', '김민지', '서울 강남구 역삼동', 37.504062, 127.036209, NULL, 160, 50, 'trainer'),
('trainer2@example.com', 'password2', '010-2345-6789', '이민준', '서울 강남구 논현동', 37.516066, 127.035760, NULL, 170, 60, 'trainer'),
('user1@example.com', 'password3', '010-3456-7890', '박서연', '서울 강남구 대치동', 37.501704, 127.058740, NULL, 175, 65, 'user'),
('user2@example.com', 'password4', '010-4567-8901', '최지훈', '서울 강남구 삼성동', 37.510571, 127.060315, NULL, 165, 55, 'user'),
('user3@example.com', 'password5', '010-5678-9012', '정예진', '서울 강남구 역삼동', 37.498255, 127.039721, NULL, 180, 70, 'user'),
('user4@example.com', 'password6', '010-6789-0123', '강지우', '서울 강남구 역삼동', 37.502194, 127.039634, NULL, 165, 55, 'user'),
('user5@example.com', 'password7', '010-7890-1234', '손민지', '서울 강남구 역삼동', 37.497974, 127.038981, NULL, 170, 60, 'user'),
('user6@example.com', 'password8', '010-8901-2345', '윤서연', '서울 강남구 역삼동', 37.500649, 127.042879, NULL, 175, 65, 'user'),
('user7@example.com', 'password9', '010-9012-3456', '임지훈', '서울 강남구 역삼동', 37.499939, 127.035914, NULL, 170, 60, 'user'),
('user8@example.com', 'password10', '010-0123-4567', '장예진', '서울 강남구 역삼동', 37.502901, 127.037615, NULL, 160, 50, 'user'),
('user9@example.com', 'password11', '010-1111-1111', '김태연', '서울 강남구 역삼동', 37.503170, 127.036722, NULL, NULL, NULL, 'user'),
('user10@example.com', 'password12', '010-2222-2222', '이서연', '서울 강남구 역삼동', 37.504019, 127.035682, NULL, NULL, NULL, 'user'),
('user11@example.com', 'password13', '010-3333-3333', '박민지', '서울 강남구 역삼동', 37.502497, 127.036539, NULL, NULL, NULL, 'user'),
('user12@example.com', 'password14', '010-4444-4444', '최지훈', '서울 강남구 역삼동', 37.503349, 127.036525, NULL, NULL, NULL, 'user'),
('user13@example.com', 'password15', '010-5555-5555', '정예진', '서울 강남구 역삼동', 37.502864, 127.036124, NULL, NULL, NULL, 'user'),
('user14@example.com', 'password16', '010-6666-6666', '강지우', '서울 강남구 역삼동', 37.503878, 127.037178, NULL, NULL, NULL, 'user'),
('user15@example.com', 'password17', '010-7777-7777', '손민지', '서울 강남구 역삼동', 37.502937, 127.036466, NULL, NULL, NULL, 'user'),
('user16@example.com', 'password18', '010-8888-8888', '윤서연', '서울 강남구 역삼동', 37.503768, 127.036856, NULL, NULL, NULL, 'user'),
('user17@example.com', 'password19', '010-9999-9999', '임지훈', '서울 강남구 역삼동', 37.503204, 127.036267, NULL, NULL, NULL, 'user'),
('user18@example.com', 'password20', '010-0000-0000', '장예진', '서울 강남구 역삼동', 37.504177, 127.037266, NULL, NULL, NULL, 'user');