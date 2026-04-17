-- =====================================================
-- Migration: Add League Support
-- Description: Adds league functionality where users can
--              belong to multiple leagues and have separate
--              predictions, rankings, chats, and posts per league
-- =====================================================

-- 1. Create league table
CREATE TABLE league (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    created_by_user_id BIGINT,
    FOREIGN KEY (created_by_user_id) REFERENCES user(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Create user_league join table (many-to-many relationship)
CREATE TABLE user_league (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    league_id BIGINT NOT NULL,
    joined_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    is_admin BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (league_id) REFERENCES league(id) ON DELETE CASCADE,
    UNIQUE KEY uk_user_league (user_id, league_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Add league_id to prediction table
-- User can have different predictions for same game in different leagues
ALTER TABLE prediction
    ADD COLUMN league_id BIGINT NOT NULL DEFAULT 1 AFTER game_id,
    ADD CONSTRAINT fk_prediction_league FOREIGN KEY (league_id) REFERENCES league(id);

-- Drop old unique constraint if exists and create new one including league_id
ALTER TABLE prediction
    DROP INDEX IF EXISTS uk_user_game,
    ADD UNIQUE KEY uk_user_game_league (user_id, game_id, league_id);

-- 4. Add league_id to ranking table
-- Each user has separate ranking per league
ALTER TABLE ranking
    ADD COLUMN league_id BIGINT NOT NULL DEFAULT 1 AFTER user_id,
    ADD CONSTRAINT fk_ranking_league FOREIGN KEY (league_id) REFERENCES league(id);

-- Update unique constraint to include league_id
ALTER TABLE ranking
    DROP INDEX IF EXISTS user_id,
    ADD UNIQUE KEY uk_user_league (user_id, league_id);

-- 5. Add league_id to ranking_history table
ALTER TABLE ranking_history
    ADD COLUMN league_id BIGINT NOT NULL DEFAULT 1 AFTER game_id,
    ADD CONSTRAINT fk_ranking_history_league FOREIGN KEY (league_id) REFERENCES league(id);

-- Add index for better query performance
ALTER TABLE ranking_history
    ADD INDEX idx_league_game (league_id, game_id);

-- 6. Add league_id to user_points table
ALTER TABLE user_points
    ADD COLUMN league_id BIGINT NOT NULL DEFAULT 1 AFTER prediction_id,
    ADD CONSTRAINT fk_user_points_league FOREIGN KEY (league_id) REFERENCES league(id);

-- Add index for better query performance
ALTER TABLE user_points
    ADD INDEX idx_user_league_points (user_id, league_id);

-- 7. Add league_id to chat_message table
ALTER TABLE chat_message
    ADD COLUMN league_id BIGINT NOT NULL DEFAULT 1 AFTER user_id,
    ADD CONSTRAINT fk_chat_message_league FOREIGN KEY (league_id) REFERENCES league(id);

-- Add index for better query performance
ALTER TABLE chat_message
    ADD INDEX idx_league_created (league_id, created_at);

-- 8. Add league_id to post table
ALTER TABLE post
    ADD COLUMN league_id BIGINT NOT NULL DEFAULT 1 AFTER user_id,
    ADD CONSTRAINT fk_post_league FOREIGN KEY (league_id) REFERENCES league(id);

-- Add index for better query performance
ALTER TABLE post
    ADD INDEX idx_league_created (league_id, created_at);

-- 9. Insert default league (for existing data)
INSERT INTO league (id, name, description, created_at, is_active)
VALUES (1, 'Liga Główna', 'Domyślna liga dla wszystkich użytkowników', NOW(), TRUE);

-- 10. Add all existing users to default league
INSERT INTO user_league (user_id, league_id, joined_at, is_admin)
SELECT id, 1, NOW(), FALSE
FROM user;

-- 11. Create indexes for performance optimization
CREATE INDEX idx_user_league_active ON user_league(user_id, league_id) WHERE is_admin = TRUE;
CREATE INDEX idx_league_active ON league(is_active) WHERE is_active = TRUE;