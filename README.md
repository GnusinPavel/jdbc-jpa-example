# jdbc-jpa-example
Тестовый проект для IT-Лаборатории 2015. Пример, как осуществляется взаимодействие с БД из java.

Предварительно создать роль pavel с паролем 1234 и БД itlab.
Выполнить скрипт создания таблицы:
```sql
CREATE TABLE employee
(
  id bigserial NOT NULL,
  age integer,
  gender boolean,
  name character varying(255),
  surname character varying(255),
  CONSTRAINT employee_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE employee
  OWNER TO pavel;
```
