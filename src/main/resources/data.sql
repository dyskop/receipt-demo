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
       ('Happy New Year', true, 20.23);

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

insert into receipt.public.orders (card_id)
values (1);

insert into receipt.public.item (order_id, product_id, quantity)
values (1, 5, 1),
       (1, 7, 2),
       (1, 3, 1);

insert into receipt.public.receipt (cashbox_id, info, date_time, order_id)
values (1, 'DREAM MARKET', '2022-12-16 22:05:47', 1);
