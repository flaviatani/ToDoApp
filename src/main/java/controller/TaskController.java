
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;
import model.Task;
import util.ConnectionFactory;


public class TaskController {
    
    public void save(Task task){
        
        String sql = "INSERT INTO tasks (idProject"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
         try {
            
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,task.getIdProject());
            statement.setString(2,task.getName());
            statement.setString(3,task.getDescription());
            statement.setBoolean(4,task.isIsCompleted());
            statement.setString(5,task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setBoolean(7, task.isCompleted());
            statement.setDate(8, new Date(task.getCreatedAt().getTime()));
            statement.setDate(9, new Date(task.getUpdatedAt().getTime()));
            
            
        }catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa");
        }finally {
            ConnectionFactory.closeConnection(conn);
        }
        
    }
    
    public void update(Task task){
        
    }
    
    public void removeById(int taskId) throws SQLException{
        
        String sql = "DELETE FROM task WHERE id = ?";
        
        Connection conn = null;
        PreparedStatement statement = null;
        
        try {
            
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1,taskId);
            statement.execute();            
        }catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa");
        }finally {
            ConnectionFactory.closeConnection(conn);
        }
    }
    
    public List<Task> getAll(int idProject){
        return null;
    }
}
