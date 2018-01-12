CREATE OR REPLACE PACKAGE EDUMAN.JAVALIBRARYAPP
/*
   Change History:
   When                 Who                         What                                                        
   10.Jan.2018          Ercan DUMAN                 1.6: This is a sample Java to Oracle interaction project.
 
   **************************************************************************************/
 IS

  PROCEDURE AddNewBook
  (
    pis_BookName      IN EDUMAN.JAVALIB_BOOKS.BOOK_NAME%TYPE,
    pis_AuthorName    IN EDUMAN.JAVALIB_BOOKS.AUTHOR%TYPE,
    pis_OutputMessage OUT VARCHAR2
  );

  FUNCTION RemoveBook(pin_ID IN EDUMAN.JAVALIB_BOOKS.ID%TYPE) RETURN VARCHAR2;
  FUNCTION ListAllBooks RETURN SYS_REFCURSOR;

END JAVALIBRARYAPP;
/
