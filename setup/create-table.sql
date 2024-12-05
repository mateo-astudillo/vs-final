CREATE TABLE `User`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `password` CHAR(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
ALTER TABLE
    `User` ADD UNIQUE `user_username_unique`(`username`);

CREATE TABLE `Candidate`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `president_first_name` VARCHAR(255) NOT NULL,
    `president_last_name` VARCHAR(255) NOT NULL,
    `vice_president_first_name` VARCHAR(255) NOT NULL,
    `vice_president_last_name` VARCHAR(255) NOT NULL,
    `political_party` VARCHAR(255) NOT NULL,
    `list` INT UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `Person`(
    `document` CHAR(8) NOT NULL,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `voted` BOOLEAN NOT NULL DEFAULT '0',
    `cancelled` BOOLEAN NOT NULL DEFAULT '0',
    `year_of_birth` INT UNSIGNED NOT NULL,
    `document_type` VARCHAR(255) NOT NULL,
    `circuit` VARCHAR(255) NOT NULL,
    `gender` CHAR(1) NOT NULL,
    `address` VARCHAR(255) NOT NULL,
    PRIMARY KEY(`document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `VotingTable`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT UNSIGNED NOT NULL,
    `opening_time` TIME NOT NULL,
    `closing_time` TIME NOT NULL,
    `real_opening_time` TIME NOT NULL,
    `real_closing_time` TIME,
    `date` DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `Vote`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `candidate_id` INT UNSIGNED NOT NULL,
    `createdAt` TIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `Incidence`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` INT UNSIGNED NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `observation` VARCHAR(255) NOT NULL,
    `createdAt` TIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `BlankVote`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `createdAt` TIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `FailedAttempts`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `password` CHAR(64) NOT NULL,
    `createdAt` TIME NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

ALTER TABLE
    `Incidence` ADD CONSTRAINT `incidence_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `User`(`id`);

ALTER TABLE
    `VotingTable` ADD CONSTRAINT `votingtable_user_id_foreign` FOREIGN KEY(`user_id`) REFERENCES `User`(`id`);

ALTER TABLE
    `Vote` ADD CONSTRAINT `vote_candidate_id_foreign` FOREIGN KEY(`candidate_id`) REFERENCES `Candidate`(`id`);
