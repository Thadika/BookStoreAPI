/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbassignment1;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vihanga Liyanage
 */
public class BookStore {

    private MongoClient mongo;
    private DB db;
    private DBCollection collection;
    
    public BookStore(String host, int port, String dbName) {
        try {
            mongo = new MongoClient(host, port);
            db = mongo.getDB(dbName);
        } catch (UnknownHostException ex) {
            Logger.getLogger(BookStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int invoiceSaver(String collectionName, String csvFile) {
        BufferedReader br = null;
        String line;
        String[] headLine = null, data;
        String csvSplitBy = ",";
        BasicDBObject document;
        collection = db.getCollection(collectionName);

        try {

            br = new BufferedReader(new FileReader(csvFile));
            if ((line = br.readLine()) != null) {
                headLine = line.split(csvSplitBy);
            }
            while ((line = br.readLine()) != null) {
                data = line.split(csvSplitBy);
                document = documentCreater(headLine, data);
                collection.insert(document);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");

        return 0;
    }

    private BasicDBObject documentCreater(String[] headLine, String[] data) {
        BasicDBObject document = new BasicDBObject();
        if (headLine.length == data.length) {
            for (int i = 0; i < headLine.length; i++) {
                document.put(headLine[i].trim(), data[i].trim());
            }
        }
        return document;
    }
}
