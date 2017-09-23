package com.classify;
import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.core.Instances;
import weka.experiment.InstanceQuery;


/**
 *
 * @author Admin
 * 
* 
* create table `heart`.persontest
(
	Cp VARCHAR(10) default 'Null',
	Trestbps VARCHAR(10) default 'Null',
	Chol VARCHAR(10) default 'Null',
	Fbs VARCHAR(10) default 'Null',
	Restecg VARCHAR(10) default 'Null',
	Thalach VARCHAR(10) default 'Null',
	Exang VARCHAR(10) default 'Null',
	Oldpeak VARCHAR(10) default 'Null',
	Slope VARCHAR(10) default 'Null',
	Ca VARCHAR(10) default 'Null',
	Thal VARCHAR(10) default 'Null',
	Num VARCHAR(10) default 'Null'
        Id SMALLINT not null,
	primary key (Id)
)
*  create table `heart`.persondetail
        (
*       Age SMALLINT default 'Null',
	Sex SMALLINT default 'Null',
*       Id SMALLINT not null,
	primary key (Id)
        )
        * 
*  create table `heart`.Personalhistory
(
	CigsPerDay VARCHAR(10) default 'Null',
	yearsAsSmoker VARCHAR(10) default 'Null',
	historyOfDiabetes VARCHAR(10) default 'Null',
	familyHistory VARCHAR(10) default 'Null',
	durationOfExercise VARCHAR(10) default 'Null',
        monthOfExercise VARCHAR(10) default 'Null',
        dayOfExercise VARCHAR(10) default 'Null',
        Id SMALLINT not null,
	primary key (Id)
)
* 
* 
 */
public class BigDataProject {

    
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/heartdb", "root", "Mysuccess@26");
	return connection;
    }
    
    private static void readCsvUsingLoad() {
        
        try (Connection connection = getConnection()) {
 
                String loadQuery = "LOAD DATA LOCAL INFILE '" + "F://data//data.csv" + "' INTO TABLE persontest FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' IGNORE 1 ROWS (cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num) ";
                Statement stmt = connection.createStatement();
                stmt.execute(loadQuery);
                
                /*String loadQuery1 = "LOAD DATA LOCAL INFILE '" + "F://data//Info.csv" + "' INTO TABLE persondetail FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (age,Sex,id) ";
                Statement stmt1 = connection.createStatement();
                stmt1.execute(loadQuery1);
                
                String loadQuery2 = "LOAD DATA LOCAL INFILE '" + "F://data//history.csv" + "' INTO TABLE personalhistory FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (CigsPerDay,yearsAsSmoker,historyOfDiabetes,familyHistory,durationOfExercise,monthOfExercise,dayOfExercise,Id) ";
                Statement stmt2 = connection.createStatement();
                stmt1.execute(loadQuery2);*/
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }

        
public static void csvupload(String name, String name2) {
        
        try (Connection connection = getConnection()) {
        	if(name != null){
        		StringBuilder sb = new StringBuilder();
        		for(int i = 0; i < name.length(); i++) {
        			sb.append(name.charAt(i));
        			if(name.charAt(i) == '\\' ) {
        				sb.append('\\');
        			}
        		}
                String loadQuery = "LOAD DATA LOCAL INFILE '" + sb.toString() + "' INTO TABLE persontest FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' IGNORE 1 ROWS (cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,num) ";
                Statement stmt = connection.createStatement();
                stmt.execute(loadQuery);
                update();
        	}
        	if(name2 != null){    
                StringBuilder sb2 = new StringBuilder();
        		for(int i = 0; i < name2.length(); i++) {
        			sb2.append(name2.charAt(i));
        			if(name2.charAt(i) == '\\' ) {
        				sb2.append('\\');
        			}
        		}
                /*String loadQuery1 = "LOAD DATA LOCAL INFILE '" + "F://data//Info.csv" + "' INTO TABLE persondetail FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (age,Sex,id) ";
                Statement stmt1 = connection.createStatement();
                stmt1.execute(loadQuery1);*/
                String loadQuery2 = "LOAD DATA LOCAL INFILE '" + sb2.toString() + "' INTO TABLE personhistory FIELDS TERMINATED BY ','" + " LINES TERMINATED BY '\n' (cigsperday,yearsassmoker,historyofdiabetes,familyhistory,durationofexcercise,monthofexcercise,dayofexcercise) ";
                Statement stmt2 = connection.createStatement();
                stmt2.execute(loadQuery2);
        	}
                
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }
     public static void update() {
         ResultSet rs = null;
         String a[] = new String[20];
         try (Connection connection = getConnection()) {
              String sql = "Select Cp,Chol,Trestbps,Fbs,Restecg,Thalach,Exang,Oldpeak,Slope,Ca,Thal,Num,Id from persontest";
              Statement stmt = connection.createStatement();
              rs = stmt.executeQuery(sql);
               while(rs.next())        //loop through resul set and store each field into columns of jTable object
            {   
                int count = 0;
                a[0] = rs.getString("Cp");
                a[1] = rs.getString("Trestbps");
                a[2] = rs.getString("Chol");
                a[3] = rs.getString("Fbs");
                a[4] = rs.getString("Restecg");
                a[5] = rs.getString("Thalach");
                a[6] = rs.getString("Exang");
                a[7] = rs.getString("Oldpeak");
                a[8] = rs.getString("Slope");
                a[9] = rs.getString("Ca");
                a[10] = rs.getString("Thal");
                a[11] = rs.getString("Num");
                a[12] = rs.getString("Id");
                
                if(a[11] == null) {
                	String query1 = "delete from persontest where id = " + a[11];
                	 PreparedStatement statement = connection.prepareStatement(query1);
                     statement.executeUpdate();
                }
                else {
	                for ( int i = 0; i < 13; i++) {
	                    if(a[i].equals("?")) {
	                        a[i] = "-1";
	                        count = 1;
	                    }
	                }
	                if (count == 1 ) { 
	                    String query = "update persontest set Cp = " + a[0] + ",Trestbps = " + a[1] + ",Chol = "+ a[2]
	                            + ",Fbs = " + a[3] + ",Restecg = " + a[4] + ",Thalach = "+ a[5]
	                            + ",Exang = " + a[6] + ",Oldpeak = " + a[7] + ",Slope = "+ a[8]
	                            + ",Ca = " + a[9] + ",Thal = " + a[10] + ",Num = "+ a[11]
	                            + " where Id = " + a[12];
	                    PreparedStatement statement = connection.prepareStatement(query);
	                    statement.executeUpdate();
	                }
                }
            }
        }
        catch (Exception e) {
                e.printStackTrace();
        }
     }
     
     
    public static void main(String[] args) throws Exception {
    }
}
