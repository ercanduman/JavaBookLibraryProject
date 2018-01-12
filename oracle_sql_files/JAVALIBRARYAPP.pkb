CREATE OR REPLACE PACKAGE BODY EDUMAN.JAVALIBRARYAPP
/*
   Change History:
   When                 Who                         What                                                        
   10.Jan.2018          Ercan DUMAN                 1.6: This is a sample Java Library project with Oracle database interactions.
 
   **************************************************************************************/
 IS

  PROCEDURE AddNewBook
  (
    pis_BookName      IN EDUMAN.JAVALIB_BOOKS.BOOK_NAME%TYPE,
    pis_AuthorName    IN EDUMAN.JAVALIB_BOOKS.AUTHOR%TYPE,
    pis_OutputMessage OUT VARCHAR2
  )
  /**************************************************************************************************
    * Purpose    : To add new book.
    * Notes      : N/A
    * -------------------------------------------------------------------------------------
    * Parameters :
     - pis_BookName   : The name of book
     - pis_AuthorName : The Author of book
    * Return     : N/A
    * Exceptions : N/A
    * -------------------------------------------------------------------------------------
    * History    :
     | Author                 | Date                | Purpose
     |-------                 |-----------          |----------------------------------------------
     | Ercan DUMAN            | 10.01.2018          | Procedure creation. 
    **************************************************************************************************/
   IS
  
  BEGIN
    INSERT INTO eduman.javalib_books
      (id, book_name, author, create_date)
    VALUES
      (Eduman.Javalib_Books_Idseq.Nextval, pis_BookName, pis_AuthorName, SYSDATE);
    COMMIT;
    pis_OutputMessage := '''' || pis_BookName ||
                         ''' is successfully added to library!';
  EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
      pis_OutputMessage := 'ERROR> ''' || pis_BookName ||
                           ''' already available in library!';
      dbms_output.put_line(pis_OutputMessage);
    
  END AddNewBook;

  FUNCTION ListAllBooks RETURN SYS_REFCURSOR
  /**************************************************************************************************
    * Purpose    : To List all books that available in library.
    * Notes      : N/A
    * -------------------------------------------------------------------------------------
    * Parameters : N/A
    * Return     : N/A
    * Exceptions : N/A
    * -------------------------------------------------------------------------------------
    * History    :
     | Author                 | Date                | Purpose
     |-------                 |-----------          |----------------------------------------------
     | Ercan DUMAN            | 10.01.2018          | Function creation. 
    **************************************************************************************************/
   IS
    pis_OutputMessage SYS_REFCURSOR;
  BEGIN
  
    OPEN pis_OutputMessage FOR
      SELECT * FROM eduman.javalib_books;
  
    RETURN pis_OutputMessage;
  END ListAllBooks;

  FUNCTION RemoveBook(pin_ID IN EDUMAN.JAVALIB_BOOKS.ID%TYPE) RETURN VARCHAR2
  /**************************************************************************************************
    * Purpose    : To delete a book based on given id number.
    * Notes      : N/A
    * -------------------------------------------------------------------------------------
    * Parameters :
     - pin_ID    : The id number of book
    * Return     : N/A
    * Exceptions : No_data_found
    * -------------------------------------------------------------------------------------
    * History    :
     | Author                 | Date                | Purpose
     |-------                 |-----------          |----------------------------------------------
     | Ercan DUMAN            | 10.01.2018          | Function creation. 
    **************************************************************************************************/
   IS
    vb_Exist         NUMBER;
    vs_OutputMessage VARCHAR2(100);
    vs_BookName      EDUMAN.JAVALIB_BOOKS.BOOK_NAME%TYPE;
  BEGIN
    vb_Exist := -1;
    BEGIN
    
      -- check if book available for given id  
      SELECT COUNT(*), book_name
        INTO vb_Exist, vs_BookName
        FROM eduman.javalib_books
       WHERE id = pin_ID
       GROUP BY book_name;
    
      IF vb_Exist IS NOT NULL AND vb_Exist > 0 THEN
        DELETE FROM eduman.javalib_books WHERE id = pin_ID;
        COMMIT;
        vs_OutputMessage := 'INFO> ''' || vs_BookName ||
                            ''' deleted successfully for id: ' || pin_ID;
      
      END IF;
    
    EXCEPTION
      WHEN NO_DATA_FOUND THEN
        vs_OutputMessage := 'ERROR> Book not found for id: ' || pin_ID;
      
      WHEN OTHERS THEN
        vs_OutputMessage := substr('ERROR> ' || SQLERRM ||
                                   dbms_utility.format_error_backtrace,
                                   1,
                                   200);
    END;
  
    RETURN vs_OutputMessage;
  END RemoveBook;
END JAVALIBRARYAPP;
/
