DROP TABLE ENTRY IF EXISTS;
DROP TABLE PHONE_BOOK IF EXISTS;


CREATE TABLE PHONE_BOOK (
   ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
   NAME VARCHAR(30) NOT NULL,
   PRIMARY KEY (ID),
   UNIQUE (NAME)
);

CREATE TABLE ENTRY(
   ENTRY_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1 INCREMENT BY 1),
   NAME VARCHAR(30) NOT NULL,
   PHONE_NUMBER BIGINT NOT NULL,
   PHONE_BOOK_ID BIGINT NOT NULL,
   PRIMARY KEY (ENTRY_ID),
   CONSTRAINT FK_ENTRY FOREIGN KEY (PHONE_BOOK_ID) REFERENCES PHONE_BOOK (ID)
);

INSERT INTO PHONE_BOOK(NAME) VALUES('SRIMANTH');

INSERT INTO ENTRY(NAME, PHONE_NUMBER, PHONE_BOOK_ID) VALUES('HOME', 21813173711, 1);

COMMIT;