ALTER TABLE `woodposters`.`product`
ADD COLUMN `visible` TINYINT(1) NOT NULL DEFAULT '1' AFTER `creation_date`;