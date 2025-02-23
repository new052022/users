ALTER TABLE user_exchange_info
ADD CONSTRAINT unique_user_exchange
UNIQUE (user_info_id, exchange_id);
