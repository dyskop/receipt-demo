insert into receipt.public.card (name)
values ('Golden'),
       ('Silver'),
       ('Bronze');

insert into receipt.public.cashbox
values (default),
       (default),
       (default);

insert into receipt.public.product (name, price)
values ('bread', 1.50),
       ('butter', 3.20),
       ('gum', 1.15),
       ('milk', 1.75),
       ('pizza', 18.50),
       ('champagne', 12.00),
       ('beer', 4.60);

insert into receipt.public.promotion (name, is_active, percent)
values ('Fantastic 5', true, 10.00),
       ('Happy Birthday', true, 15.00),
       ('Happy New Year', true, 20.00);

insert into receipt.public.promotion_card
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 1),
       (2, 2),
       (3, 1);

insert into receipt.public.promotion_product
values (1, 1),
       (1, 2),
       (1, 4),
       (2, 3),
       (2, 5),
       (2, 7),
       (3, 6);

insert into receipt.public.orders (card_id, total)
values (1, 24.52),
       (1, 23.55),
       (1, 15.73),
       (null, 1.50),
       (2, 15.73);

insert into receipt.public.item (order_id, product_id, quantity)
values (1, 5, 1),
       (1, 7, 2),
       (1, 3, 1),
       (2, 5, 1),
       (2, 7, 2),
       (3, 5, 1),
       (4, 1, 1),
       (5, 5, 1);

insert into receipt.public.receipt (cashbox_id, info, date_time, order_id)
values (1, 'DREAM MARKET', '2022-01-16 11:05:47', 1),
       (1, 'DREAM MARKET', '2022-02-16 12:05:47', 2),
       (1, 'DREAM MARKET', '2022-03-16 13:05:47', 3),
       (1, 'DREAM MARKET', '2022-04-16 14:05:47', 4),
       (1, 'DREAM MARKET', '2022-05-16 15:05:47', 5);
