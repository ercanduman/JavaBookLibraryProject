DECLARE
  pis_OutputMessage VARCHAR2(1000);
BEGIN
  -- Call the procedure
  javalibraryapp.addnewbook(pis_bookname => 'Sefiller', pis_authorname => 'Victor Hugo', pis_OutputMessage => pis_OutputMessage);

  dbms_output.put_line(pis_OutputMessage);
END;
