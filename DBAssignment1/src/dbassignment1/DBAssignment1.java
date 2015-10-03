

package dbassignment1;

public class DBAssignment1 {

    
    public static void main(String[] args) {
        /*
        InvoiceSave is = new InvoiceSave();
        is.setVisible(true);
                */
        //To create BookStore object, has to pass host name, port number and mongoDB database name as parameters
        BookStore bs = new BookStore("localhost", 27017, "sample");
        
        //To save invoices from a *.csv
        //call BookStore.invoiceSaver with mongoDB collection name and *.csv file path as parameters.
        bs.invoiceSaver("invoice", "D:/Developer/My Projects/BookStoreAPI/input.csv");
    }
    
}
