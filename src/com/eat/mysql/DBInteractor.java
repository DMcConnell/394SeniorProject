package com.eat.mysql;

import java.sql.*;

public class DBInteractor {
    
    private Connection con;
    private Statement st;

    public DBInteractor(String url) {
        try {
            con = DriverManager.getConnection(url);
            st = con.createStatement();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public DBInteractor(String url, String username, String password) throws Exception {
        try {
            con = DriverManager.getConnection(url, username, password);
            st = con.createStatement();
        } catch(Exception e) {
            throw e;
        }
    }

    public void executeStatement(String statement) throws Exception {
        try {
            st.executeUpdate(statement);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public ResultSet executeQuery(String query) throws Exception {
        try {
            return st.executeQuery(query);
        }
        catch(Exception e){
            throw e;
        }
    }

    public DatabaseMetaData getMetaData() throws Exception {
        try {
            return con.getMetaData();
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void closeEverything() throws Exception {
        try {
            if(st != null) {
                st.close();
            }
            if(con != null) {
                con.close();
            }
        }
        catch(Exception e) {
            throw e;
        }
    }
}