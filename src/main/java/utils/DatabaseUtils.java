    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package utils;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.ArrayList;
    import java.util.List;
    import models.Product;

    /**
     *
     * @author kiara
     */
    public class DatabaseUtils {    

        //all DB related functions
        String url = "jdbc:mysql://localhost:8000/products";
        String user = "root";
        String pass = "admin";
        Connection conn;

        public DatabaseUtils() {
            try{
                conn = DriverManager.getConnection(url, user,pass);
                System.out.println("Connection successful..");

            }catch(SQLException ex){
                System.out.println("Connection Failed.." + ex.getMessage());
            }

        }

        public List<Product> getProducts(){
           List<Product> products = new ArrayList<>(); 
           String query = "SELECT * FROM product";
           try{
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()){
                    //store data
                     //first column in db is id which is an int
                    //getInt(can pass columnLable or columnIndex-index starts from 1 not 0 like arrays)

                    Product product  = new Product(rs.getInt(1),
                            rs.getString(2), rs.getDouble(3));

                    products.add(product);
                }
                stmt.close();

           }catch(SQLException ex){
               System.out.println("Errot while fetching data..."+ ex.getMessage());   
           }

           return products;
        }

        public boolean insertProduct(Product product){
            //use prepared statement when you want to modify the db
            String query = "INSERT INTO product(productName,price) VALUES (?, ?)";
            try{
               PreparedStatement ps = conn.prepareStatement(query);
               ps.setString(1, product.getProductName());
               ps.setDouble(2, product.getPrice());
               int row = ps.executeUpdate();
               ps.close();
               if(row > 0){
                   return true;
               }else{
                   return false;
               }
            }catch(SQLException ex){
               System.out.println("Errot while inserting data..."+ ex.getMessage());
               return false;
           }
        }

        public boolean updateProduct(Product product){
            String query = "UPDATE product SET productName=?,price=? WHERE id=?";
            try{
               PreparedStatement ps = conn.prepareStatement(query);
               //setString(accepts parameterIndex and value)
               ps.setString(1, product.getProductName());
               ps.setDouble(2, product.getPrice());
               ps.setInt(3, product.getId());

               int row = ps.executeUpdate();
               ps.close();
               if(row > 0){
                   return true;
               }else{
                   return false;
               }

            }catch(SQLException ex){
               System.out.println("Errot while updating data..."+ ex.getMessage());
               return false;
           }
        }

          public boolean deleteProduct(int id){
            String query = "DELETE FROM product WHERE id=?";
            try{
               PreparedStatement ps = conn.prepareStatement(query);
               //setString(accepts parameterIndex and value)
                ps.setInt(1, id);
               int row = ps.executeUpdate();
               ps.close();
               if(row > 0){
                   return true;
               }else{
                   return false;
               }

            }catch(SQLException ex){
               System.out.println("Errot while updating data..."+ ex.getMessage());
               return false;
           }
        }

        public void close(){
            try{
               conn.close();  
            }catch(SQLException ex){
               System.out.println("Errot while updating data..."+ ex.getMessage());

           }

        }
    }

