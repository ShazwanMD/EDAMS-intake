INSERT INTO IN_PRGM_CODE (ID, CODE, DESCRIPTION_MS, DESCRIPTION_EN, PROGRAM_LEVEL_ID,GRADUATE_CENTER_ID,FACULTY_CODE_ID, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_IN_PRGM_CODE'), 'LP35', 'IJAZAH SARJANA MUDA KEUSAHAWANAN PERDAGANGAN DENGAN KEPUJIAN',
        'BACHELOR OF ENTREPRENEURSHIP TRADING  WITH HONOURS',(SELECT ID FROM IN_PRGM_LEVL WHERE CODE = 'DEGREE'),(SELECT ID FROM IN_GRDT_CNTR WHERE CODE = 'UMKCEE'),
        (SELECT ID FROM IN_FCTY_CODE WHERE CODE='A01'),1, 0, CURRENT_TIMESTAMP);
        
INSERT INTO IN_PRGM_CODE (ID, CODE, DESCRIPTION_MS, DESCRIPTION_EN, PROGRAM_LEVEL_ID,GRADUATE_CENTER_ID, FACULTY_CODE_ID,M_ST, C_ID, C_TS)
VALUES (nextval('SQ_IN_PRGM_CODE'), 'LP37', 'IJAZAH SARJANA MUDA KEUSAHAWANAN LOGISTIK DAN PERNIAGAAN PENGEDARAN DENGAN KEPUJIAN',
        'BACHELOR OF ENTREPRENEURSHIP LOGISTIC AND DISTRIBUTIVE BUSINESS WITH HONOURS',(SELECT ID FROM IN_PRGM_LEVL WHERE CODE = 'DEGREE'),(SELECT ID FROM IN_GRDT_CNTR WHERE CODE = 'UMKCEE'),
        (SELECT ID FROM IN_FCTY_CODE WHERE CODE='A01'),1, 0, CURRENT_TIMESTAMP);        
        
INSERT INTO IN_PRGM_CODE (ID, CODE, DESCRIPTION_MS, DESCRIPTION_EN, PROGRAM_LEVEL_ID,GRADUATE_CENTER_ID,FACULTY_CODE_ID, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_IN_PRGM_CODE'), 'LE12', 'IJAZAH SARJANA MUDA KEUSAHAWANAN PERUNCITAN DENGAN KEPUJIAN',
        'BACHELOR OF ENTREPRENEURSHIP RETAIL WITH HONOURS',(SELECT ID FROM IN_PRGM_LEVL WHERE CODE = 'DEGREE'),(SELECT ID FROM IN_GRDT_CNTR WHERE CODE = 'UMKCEE'),
        (SELECT ID FROM IN_FCTY_CODE WHERE CODE='A01'),1, 0, CURRENT_TIMESTAMP);         

INSERT INTO IN_PRGM_CODE (ID, CODE, DESCRIPTION_MS, DESCRIPTION_EN, PROGRAM_LEVEL_ID,GRADUATE_CENTER_ID,FACULTY_CODE_ID, M_ST, C_ID, C_TS)
VALUES (nextval('SQ_IN_PRGM_CODE'), 'LP48', 'IJAZAH SARJANA MUDA KEUSAHAWANAN PELANCONGAN DENGAN KEPUJIAN',
        'BACHELOR OF ENTREPRENEURSHIP TOURISM WITH HONOURS',(SELECT ID FROM IN_PRGM_LEVL WHERE CODE = 'DEGREE'),(SELECT ID FROM IN_GRDT_CNTR WHERE CODE = 'UMKCEE'),
        (SELECT ID FROM IN_FCTY_CODE WHERE CODE='A11'),1, 0, CURRENT_TIMESTAMP);         