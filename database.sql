-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.28-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for project
CREATE DATABASE IF NOT EXISTS `project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `project`;

-- Dumping structure for table project.cart
CREATE TABLE IF NOT EXISTS `cart` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_9emlp6m95v5er2bcqkjsw48he` (`user_id`),
  CONSTRAINT `FKl70asp4l4w0jmbm1tqyofho4o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table project.cart: ~0 rows (approximately)
INSERT INTO `cart` (`id`, `user_id`) VALUES
	('b4b457d4-5a11-4430-8c97-bfa564b9a059', '25609c9e-a2ec-42ce-831f-8f1453e865cc');

-- Dumping structure for table project.cart_item
CREATE TABLE IF NOT EXISTS `cart_item` (
  `id` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `cart_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
  KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
  CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table project.cart_item: ~3 rows (approximately)
INSERT INTO `cart_item` (`id`, `quantity`, `cart_id`, `product_id`) VALUES
	('555cfb84-6156-469f-bd13-7ab0f8fca39a', 1, 'b4b457d4-5a11-4430-8c97-bfa564b9a059', '7d0baa21-f745-47d2-b52a-9f7becdfe93b'),
	('73a6ab0e-bfa9-4d46-999b-f00c48e25850', 1, 'b4b457d4-5a11-4430-8c97-bfa564b9a059', 'fb9a360f-7d74-40d7-ac8d-df192f9fded5'),
	('b192bc01-8f31-49b2-bf54-c151af58af91', 1, 'b4b457d4-5a11-4430-8c97-bfa564b9a059', '29789fb6-2de7-4ba9-81e8-b03074570859'),
	('f9221cce-f9df-4ecc-a5b5-24bc17f3ffbc', 11, 'b4b457d4-5a11-4430-8c97-bfa564b9a059', '125b57c2-40e7-4a57-ab9d-ab68f969b965');

-- Dumping structure for table project.category
CREATE TABLE IF NOT EXISTS `category` (
  `id` varchar(255) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  `category_status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table project.category: ~5 rows (approximately)
INSERT INTO `category` (`id`, `category_name`, `category_status`) VALUES
	('16a4a957-6bff-4ef8-9da1-045c029396c9', 'New Balance', b'1'),
	('5e4af669-9329-443a-a94d-51a754bf4b34', 'Nike', b'1'),
	('6cde462d-db28-47dc-a75f-0ed94b22b41f', 'Puma', b'1'),
	('a097edff-94a1-43c4-977f-69cc877eadbe', 'Adidas', b'1'),
	('fedb0c85-8b5d-4b11-ae36-cd2f3cff7773', 'Reebok', b'1');

-- Dumping structure for table project.invalidated_token
CREATE TABLE IF NOT EXISTS `invalidated_token` (
  `id` varchar(255) NOT NULL,
  `expiry_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table project.invalidated_token: ~0 rows (approximately)

-- Dumping structure for table project.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` varchar(255) NOT NULL,
  `description` text DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `category_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`),
  CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table project.product: ~9 rows (approximately)
INSERT INTO `product` (`id`, `description`, `image`, `price`, `product_name`, `category_id`) VALUES
	('125b57c2-40e7-4a57-ab9d-ab68f969b965', '<p>Giày adiFom Superstar \'Core White\' 2023 HQ8750 Chính hãng, LikeAuth tại BD Sneaker. Ưu đãi Freeship toàn quốc.</p>', 'adidas-adifom-superstar.jpg', 1000000, 'Giày Adidas ADIFOM Superstar', 'a097edff-94a1-43c4-977f-69cc877eadbe'),
	('29789fb6-2de7-4ba9-81e8-b03074570859', '<p>Reebok Royal Techque Green White là đôi giày thể thao mang thiết kế cổ điển với phần trên bằng da tổng hợp, kết hợp hai màu xanh lá cây và trắng thanh lịch. Đế ngoài bằng cao su chắc chắn cung cấp độ bám tốt và độ bền cao. Giày mang phong cách retro, phù hợp cho cả các hoạt động hàng ngày và các dịp thể thao nhẹ.</p>', 'reebok-royal-techque-greenw-white.jpg', 3990000, 'Reebok Royal Techque Green White', 'fedb0c85-8b5d-4b11-ae36-cd2f3cff7773'),
	('61c2371f-4ce5-4f6a-8a48-9ddb126ad5d6', '<p>Nike SB Force 58 là đôi giày trượt ván được thiết kế để mang lại sự thoải mái và độ bền cao. Giày có phần trên làm từ da và vải, đảm bảo độ thoáng khí và sự bền bỉ. Đế giày bằng cao su linh hoạt với họa tiết bánh xe trượt, giúp tăng cường độ bám và sự ổn định. Thiết kế cổ điển nhưng hiện đại, phù hợp cả khi trượt ván lẫn khi dạo phố.</p>', 'Giay-nike-sb-force58.jpg', 3000000, 'Nike SB Force 58', '5e4af669-9329-443a-a94d-51a754bf4b34'),
	('7d0baa21-f745-47d2-b52a-9f7becdfe93b', '<p>Nike Air Zoom Winflo 10 là đôi giày chạy bộ được thiết kế để mang lại hiệu suất cao và sự thoải mái tối đa cho người chạy. Đôi giày có phần trên làm từ vải lưới thoáng khí, giúp giữ cho chân luôn mát mẻ và khô ráo. Bộ đệm Air Zoom ở phần gót và mũi giày cung cấp độ đàn hồi và khả năng phản hồi tốt, giúp tăng cường tốc độ và sự linh hoạt. Đế giữa bằng bọt nhẹ hỗ trợ thêm cho mỗi bước chạy. Đế ngoài bằng cao su có các rãnh linh hoạt giúp tăng cường độ bám và độ bền. Thiết kế hiện đại và năng động, phù hợp cho cả chạy bộ và sử dụng hàng ngày.</p>', 'Nike-air-zoom-winflo10.jpg', 5990000, 'Nike Air Zoom Winflo 10', '5e4af669-9329-443a-a94d-51a754bf4b34'),
	('8a59a2b2-5860-4d66-97c0-df54e512a19e', '<p>Puma Palermo là đôi giày thể thao mang phong cách cổ điển, với phần trên bằng da mềm và chi tiết vải lưới, mang lại sự thoải mái và thoáng khí. Đế ngoài bằng cao su tăng cường độ bám và độ bền. Thiết kế retro và thời trang, phù hợp cho cả các hoạt động hàng ngày và thể thao nhẹ.</p>', 'puma-palermo.jpg', 3490000, 'Puma Palermo', '6cde462d-db28-47dc-a75f-0ed94b22b41f'),
	('a089e431-1e01-4997-bc3b-89857db6d9f3', '<p>Puma Softride Cruise 2 là đôi giày thể thao được thiết kế cho sự thoải mái tối đa. Với phần trên bằng lưới thoáng khí và chất liệu tổng hợp, giày mang lại cảm giác nhẹ nhàng và thoải mái. Bộ đệm Softride mềm mại giúp hấp thụ lực và giảm chấn hiệu quả. Đế ngoài bằng cao su tăng cường độ bám và độ bền. Thiết kế hiện đại và phong cách, phù hợp cho cả luyện tập và sử dụng hàng ngày.</p>', 'puma-softride-cruise-2.jpg', 1990000, 'Puma Softride Cruise 2', '6cde462d-db28-47dc-a75f-0ed94b22b41f'),
	('aca5e0ab-f300-4c55-a4e1-ef6f3cbd1457', '<p>New Balance 530 là đôi giày thể thao phong cách retro, kết hợp giữa da và vải lưới, mang lại sự thoải mái và bền bỉ. Đôi giày có bộ đệm ENCAP giảm chấn tốt và đế cao su bền bỉ. Thiết kế thời trang, phù hợp cho cả luyện tập và sử dụng hàng ngày.</p>', 'newbalance-530.jpg', 1490000, 'New Balance 530', '16a4a957-6bff-4ef8-9da1-045c029396c9'),
	('dbc7ad7b-4228-47be-99b8-c7105c53d257', '<p>Nike Air Max AP là đôi giày thể thao mang thiết kế hiện đại kết hợp với công nghệ Air Max đặc trưng của Nike. Được làm từ chất liệu vải lưới thoáng khí và da tổng hợp, đôi giày mang lại sự thoải mái và bền bỉ. Đế giữa với bộ đệm Air Max dài toàn bàn chân giúp giảm chấn và tạo cảm giác êm ái khi di chuyển. Đế ngoài bằng cao su tăng cường độ bám và độ bền. Thiết kế thanh lịch, phù hợp cho cả luyện tập thể thao và sử dụng hàng ngày.</p>', 'Nike-air-max-ap.jpg', 2990000, 'Nike Air Max AP', '5e4af669-9329-443a-a94d-51a754bf4b34'),
	('fb9a360f-7d74-40d7-ac8d-df192f9fded5', '<p>Puma Suede Croc là phiên bản đặc biệt của dòng giày Puma Suede cổ điển, nổi bật với phần trên bằng da lộn và họa tiết da cá sấu sang trọng. Đôi giày kết hợp giữa sự bền bỉ và phong cách thời trang, với đế cao su chắc chắn đảm bảo độ bám và độ bền cao. Thiết kế thanh lịch và thời trang, phù hợp cho các dịp hàng ngày và sự kiện đặc biệt.</p>', 'puma-suede-croc.jpg', 7990000, 'Puma Suede Croc', '6cde462d-db28-47dc-a75f-0ed94b22b41f');

-- Dumping structure for table project.role
CREATE TABLE IF NOT EXISTS `role` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table project.role: ~2 rows (approximately)
INSERT INTO `role` (`id`, `name`) VALUES
	('2afdf609-a184-44e5-aa96-cdc11f2fe0eb', 'USER'),
	('9c6af0c1-c18e-4198-89da-b17cb866f414', 'ADMIN');

-- Dumping structure for table project.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enable` bit(1) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table project.user: ~2 rows (approximately)
INSERT INTO `user` (`id`, `address`, `birthday`, `email`, `enable`, `first_name`, `gender`, `last_name`, `password`, `phone_number`, `username`) VALUES
	('25609c9e-a2ec-42ce-831f-8f1453e865cc', 'Ho Chi Minh', '1999-02-25', 'abc@gmail.com', b'1', 'Nguyen', 'Nam', 'Nam', '$2a$10$wTb/eDEIXGr2XwOr8bbFJuhC3EuE82sm7Rkty8ne4uGWuxHAZ88ka', '0947573829', 'user'),
	('3d222900-34aa-498b-9afc-6c16d75a8c6e', 'Ho Chi Minh', '2002-06-10', 'abc@gmail.com', b'1', 'Nguyen', 'Nam', 'Tri', '$2a$10$tetzOIH.rG8Q.KMXiKsM9.Yd2DS6A5I2p0mYwv5Pw0/iJsQPK5NcS', '0573846284', 'employee'),
	('d27f534c-f40b-459b-ab34-e54615cbb74d', 'HCM', '2004-07-01', 'dnd@gmail.com', b'1', 'Dang', 'Name', 'Danh', NULL, '0495837639', 'admin');

-- Dumping structure for table project.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` varchar(255) NOT NULL,
  `roles_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FKj9553ass9uctjrmh0gkqsmv0d` (`roles_id`),
  CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKj9553ass9uctjrmh0gkqsmv0d` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table project.user_roles: ~4 rows (approximately)
INSERT INTO `user_roles` (`user_id`, `roles_id`) VALUES
	('25609c9e-a2ec-42ce-831f-8f1453e865cc', '2afdf609-a184-44e5-aa96-cdc11f2fe0eb'),
	('3d222900-34aa-498b-9afc-6c16d75a8c6e', '2afdf609-a184-44e5-aa96-cdc11f2fe0eb'),
	('3d222900-34aa-498b-9afc-6c16d75a8c6e', '9c6af0c1-c18e-4198-89da-b17cb866f414'),
	('d27f534c-f40b-459b-ab34-e54615cbb74d', '9c6af0c1-c18e-4198-89da-b17cb866f414');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
