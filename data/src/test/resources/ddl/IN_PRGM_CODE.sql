-- todo(uda): working on it
INSERT INTO IN_PRGM_CODE (ID, CODE, DESCRIPTION, C_TS, C_ID, M_ST)
VALUES
  (nextval('SQ_IN_PRGM'), 'MEM', 'MASTER OF ENTREPRENEURSHIP (MANAGEMENT)',
   3, (SELECT ID
       FROM IN_FCTY_CODE F
       WHERE F.CODE = '45'), CURRENT_TIMESTAMP, 0, 1);
