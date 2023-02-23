
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.project;
import util.ConnectionFactory;

/**
 *
 * @author flavi
 */
public class projectController {
    
     public void save(project proj){
        
        String sql = "INSERT INTO projects(name, description, createdAt, updatedAt) VALUES (?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
         try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
           
            statement.setString(1,proj.getName());
            statement.setString(2,proj.getDescription());
            statement.setDate(3, new Date(proj.getCreatedAt().getTime()));
            statement.setDate(4, new Date(proj.getUpdatedAt().getTime()));
            statement.execute();
            
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar o projeto", ex);
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }    
         
         public void update(project proj){
        
        String sql = "UPDATE projects SET "                
                + "name = ?, "
                + "description = ?, "                
                + "createdAt = ?, "
                + "updatedAt = ? "
                + "WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {  
            connection = ConnectionFactory.getConnection();
            
            statement = connection.prepareStatement(sql);
            
            statement.setString(1,proj.getName());
            statement.setString(2,proj.getDescription());
            statement.setDate(3, new Date(proj.getCreatedAt().getTime()));
            statement.setDate(4, new Date(proj.getUpdatedAt().getTime()));            
            statement.setInt(5,proj.getId());
            
            statement.execute();   
            
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar o projeto", ex);
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    
    }
         
     public void removeById(int projId) throws SQLException{
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,projId);
            statement.execute();            
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar o projeto", ex);
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public List<project> getAll(){
     
        String sql = "SELECT * FROM projects";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        
        List<project> projects = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
                 
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {

                project proj = new project();
                proj.setId(resultSet.getInt("id"));
                proj.setName(resultSet.getString("name"));
                proj.setDescription(resultSet.getString("description"));
                proj.setCreatedAt(resultSet.getDate("createdAt"));
                proj.setCreatedAt(resultSet.getDate("updatedAt"));
                projects.add(proj);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar os projetos", ex);
        } finally {
           ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
          
        return projects;
    }
}
