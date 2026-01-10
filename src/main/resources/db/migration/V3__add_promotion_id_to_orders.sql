ALTER TABLE t_orders ADD COLUMN promotion_id BIGINT;

ALTER TABLE t_orders ADD CONSTRAINT fk_orders_promotion
    FOREIGN KEY (promotion_id) REFERENCES t_promotions(id);

