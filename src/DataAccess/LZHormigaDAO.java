package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.LZHormigaDTO;
import Framework.PatException;

public class LZHormigaDAO extends SQLiteDataHelper implements IDAO<LZHormigaDTO> {
    @Override
    public LZHormigaDTO readBy(Integer id) throws Exception {
        LZHormigaDTO hormiga = new LZHormigaDTO();
        String query = "SELECT IdHormiga, IdHormigaClasificacion, Nombre, Estado, FechaCrea, FechaModifica " +
                       "FROM Hormiga " +
                       "WHERE Estado = 'A' AND IdHormiga = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                hormiga = new LZHormigaDTO(
                    rs.getInt("IdHormiga"), 
                    rs.getInt("IdHormigaClasificacion"), 
                    rs.getString("Nombre"),
                    rs.getString("Estado"), 
                    rs.getTimestamp("FechaCrea"), 
                    rs.getTimestamp("FechaModifica")
                );
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readBy()");
        }
        return hormiga;
    }

    @Override
    public List<LZHormigaDTO> readAll() throws Exception {
        List<LZHormigaDTO> hormigas = new ArrayList<>();
        String query = "SELECT IdHormiga, IdHormigaClasificacion, Nombre, Estado, FechaCrea, FechaModifica " +
                       "FROM Hormiga " +
                       "WHERE Estado = 'A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LZHormigaDTO hormiga = new LZHormigaDTO(
                    rs.getInt("IdHormiga"), 
                    rs.getInt("IdHormigaClasificacion"), 
                    rs.getString("Nombre"),
                    rs.getString("Estado"), 
                    rs.getTimestamp("FechaCrea"), 
                    rs.getTimestamp("FechaModifica")
                );
                hormigas.add(hormiga);
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "readAll()");
        }
        return hormigas;
    }

    @Override
    public boolean create(LZHormigaDTO entity) throws Exception {
        String query = "INSERT INTO Hormiga (IdHormigaClasificacion, Nombre, Estado) VALUES (?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdHormigaClasificacion());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getEstado());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "create()");
        }
    }

    @Override
    public boolean update(LZHormigaDTO entity) throws Exception {
        String query = "UPDATE Hormiga SET IdHormigaClasificacion = ?, Nombre = ?, Estado = ? WHERE IdHormiga = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, entity.getIdHormigaClasificacion());
            pstmt.setString(2, entity.getNombre());
            pstmt.setString(3, entity.getEstado());
            pstmt.setInt(4, entity.getIdHormiga());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "update()");
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Hormiga SET Estado = 'X' WHERE IdHormiga = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "delete()");
        }
    }

    public Integer getMaxRow() throws Exception {
        String query = "SELECT COUNT(*) AS TotalReg FROM Hormiga WHERE Estado = 'A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                return rs.getInt("TotalReg");
            }
        } catch (SQLException e) {
            throw new PatException(e.getMessage(), getClass().getName(), "getMaxRow()");
        }
        return 0;
    }    
}
