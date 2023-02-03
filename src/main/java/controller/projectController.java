/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import model.project;
import util.ConnectionFactory;

/**
 *
 * @author flavi
 */
public class projectController {
    
     public void save(project proj){
        
        String sql = "INSERT INTO tasks (id"
                + "name,"
                + "description,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
         try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,proj.getId());
            statement.setString(2,proj.getName());
            statement.setString(3,proj.getDescription());
            statement.setDate(4, new Date(proj.getCreatedAt().getTime()));
            statement.setDate(5, new Date(proj.getUpdatedAt().getTime()));
            statement.execute();
            
        }catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa" + ex.getMessage(), ex);
        }finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
}
