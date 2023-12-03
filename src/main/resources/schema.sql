CREATE TABLE SHELFS
(
    SHELF_ID INT AUTO_INCREMENT PRIMARY KEY,
    SHELF_NAME  NVARCHAR(250) NOT NULL,
    PRODUCT    DATE          NOT NULL,
    FACTORY       NVARCHAR(250) NOT NULL
);

CREATE TABLE CLOTHINGS
(
    CLOTHING_ID      INT AUTO_INCREMENT PRIMARY KEY,
    SHELF_ID   INT REFERENCES SHELFS,
    ISSUE_DATE  DATE          NOT NULL,
    CLOTHING_PRICE  INT         NOT NULL,
    IS_MALE BOOLEAN       NOT NULL,
    TYPE         INT           NOT NULL
);