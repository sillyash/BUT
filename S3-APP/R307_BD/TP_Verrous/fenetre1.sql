SET SERVEROUTPUT ON;
ALTER SESSION SET NLS_DATE_FORMAT='DD-MON-YYYY';

UPDATE client SET adresse = 'a'
WHERE login = 'xXjohnXx' ;

SELECT * FROM Client;

COMMIT;

SELECT * FROM Client;

SELECT * FROM Client;
