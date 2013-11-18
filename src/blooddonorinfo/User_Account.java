

package blooddonorinfo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Mohammad Rakibul Haider
 */
public class User_Account extends Config{

    //private variables
    private int id;
    private int rowSize;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private String bloodGroup;

    //insert donor infomation method
    int insert(String name, String email, String contactNumber, String address, String bloodGroup){

        //set the value of the private variables
        this.name           = name;
        this.email          = email;
        this.contactNumber  = contactNumber;
        this.address        = address;
        this.bloodGroup     = bloodGroup;

        //initialize return value
        int res = 0;
        
        try {
            String sql =      "INSERT INTO "
                            + "DONORSINFO (ID, NAME, EMAIL, CONTACTNUMBER, ADDRESS, BLOODGROUP) "
                            + "VALUES("
                            + null + ","
                            + "\'" + this.name             + "\', "
                            + "\'" + this.email            + "\', "
                            + "\'" + this.contactNumber    + "\', "
                            + "\'" + this.address          + "\', "
                            + "\'" + this.bloodGroup       + "\' "
                            + ")";

            res = this.statement.executeUpdate(sql);
            this.endConnection();
            this.closeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User_Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    //update donor infomation method
    int update(int id, String name, String email, String contactNumber, String address, String bloodGroup){
        
        //set the value of the private variables
        this.id             = id;
        this.name           = name;
        this.email          = email;
        this.contactNumber  = contactNumber;
        this.address        = address;
        this.bloodGroup     = bloodGroup;

        //initialize return value
        int res = 0;

        try {
            String sql =   "UPDATE DONORSINFO SET "
                         + "NAME = \'" + this.name                      + "\', "
                         + "EMAIL = \'" + this.email                    + "\', "
                         + "CONTACTNUMBER = \'" + this.contactNumber    + "\', "
                         + "ADDRESS = \'" + this.address    + "\', "
                         + "BLOODGROUP = \'" + this.bloodGroup          + "\' "
                         + "WHERE ID = " + this.id;
        
            this.statement = this.connection.createStatement();
            res = this.statement.executeUpdate(sql);

            //end everything
            this.closeStatement();
            this.endConnection();
            
        } catch (SQLException ex) {
            Logger.getLogger(User_Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    //delete donor
    int delete(int id){

        this.id  = id;
        int res  = 0;

        try {
            this.statement  = this.connection.createStatement();
            String sql      =   "DELETE FROM DONORSINFO "
                              + "WHERE ID = \'" + this.id + "\'";
            res             = this.statement.executeUpdate(sql);

            //end everything
            this.endConnection();
            this.closeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User_Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    //search donors by blood group
    String [][] search(String bloodGroup){

        //setting the varible
        this.bloodGroup = bloodGroup.trim();
        
        int val = 0;
        try {
            String sqlCount = "SELECT Count(*) AS COUNT FROM DONORSINFO  WHERE BLOODGROUP = \'" + this.bloodGroup + "\'";
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery(sqlCount);
           
            while(this.resultSet.next()){
                val = this.resultSet.getInt("COUNT");
            }

        } catch (SQLException ex) {
            Logger.getLogger(User_Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //declaring the array of blood donors
        String as[][] = new String[val][4];
        
        try {
            String sql = "SELECT * FROM DONORSINFO WHERE BLOODGROUP = \'" + this.bloodGroup + "\'";
            
            this.statement = this.connection.createStatement();
            this.resultSet = this.statement.executeQuery(sql);
            
            int i = 0;
            while ((this.resultSet).next()) {
                as[i][0] = this.resultSet.getString("NAME").toString();
                as[i][1] = this.resultSet.getString("EMAIL").toString();
                as[i][2] = this.resultSet.getString("CONTACTNUMBER").toString();
                as[i][3] = this.resultSet.getString("ADDRESS");
                i++;
            }


            //end everything
            this.endConnection();
            this.closeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User_Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return as;
    }

    //get All infomation of a specific donor
    void getAll(int id){

        this.id = id;
        try {
            String sql = "SELECT * FROM DONORSINFO WHERE ID = " + this.id;
            this.statement = this.connection.createStatement();

            this.resultSet = this.statement.executeQuery(sql);

            this.name           = this.resultSet.getString("NAME").toString();
            this.email          = this.resultSet.getString("EMAIL").toString();
            this.contactNumber  = this.resultSet.getString("CONTACTNUMBER").toString();
            this.address        = this.resultSet.getString("ADDRESS");
            this.bloodGroup     = this.resultSet.getString("BLOODGROUP").toString();

            //end everything
            this.endConnection();
            this.closeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User_Account.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //find all blood donors name
    ArrayList findDonors(){

        ArrayList as = new ArrayList();
        
        try {
            String sql      = "SELECT ID, NAME FROM DONORSINFO";
            this.statement  = this.connection.createStatement();

            this.resultSet  = this.statement.executeQuery(sql);

            int i = 0;
            while (this.resultSet.next()) {
                String str =   this.resultSet.getString("ID").toString()
                          + " - "
                          + this.resultSet.getString("NAME").toString();
                as.add(str);
            }
            
            //end everything
            this.endConnection();
            this.closeStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User_Account.class.getName()).log(Level.SEVERE, null, ex);
        }
        return as;
    }

    //parse id from string
    int parseId(String as){
        String bs = "";
        for(int i=0; i<as.length(); i++){
            if(as.charAt(i)>='0' && as.charAt(i)<='9'){
                bs += as.charAt(i);
            }else {
                break;
            }
        }
        int res = Integer.parseInt(bs);
        return res;
    }

    //get the id
    int getId(){
        return this.id;
    }
    
    //get the name
    String getName() {
        return this.name;
    }

    //get the email
    String getEmail(){
        return this.email;
    }

    //get the contact number
    String getContactNumber(){
        return this.contactNumber;
    }

    //get the address
    String getAddress(){
        return this.address;
    }

     //get blood Group
    String getBloodGroup(){
        return this.bloodGroup;
    }

    int getRowSize(){
        return this.rowSize;
    }

}
