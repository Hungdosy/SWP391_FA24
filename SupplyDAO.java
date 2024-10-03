package DAO;

import Model.Supply;
import DBConnect.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplyDAO {
    private final Connection connection;

    public SupplyDAO() throws SQLException {
        connection = DBConnection.getConnection();
    }

    public List<Supply> getSuppliesByProjectId(int projectId) {
        List<Supply> supplies = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM Supplies WHERE project_id = ?"
            );
            ps.setInt(1, projectId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Supply supply = new Supply();
                supply.setSupplyId(rs.getInt("supply_id"));
                supply.setProjectId(rs.getInt("project_id"));
                supply.setItemName(rs.getString("item_name"));
                supply.setQuantityNeeded(rs.getInt("quantity_needed"));
                supply.setQuantityProvided(rs.getObject("quantity_provided") != null ? rs.getInt("quantity_provided") : null);
                supply.setUnit(rs.getString("unit"));
                supplies.add(supply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplies;
    }

    // Add other methods as needed (e.g., addSupply, updateSupply, deleteSupply)
}
