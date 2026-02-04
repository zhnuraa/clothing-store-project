package database;

import model.ClothingItem;
import model.Pants;
import model.Shirt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClothingItemDAO {

    // ================= CREATE (Week 7) =================

    public boolean insertShirt(Shirt s) {
        String sql = "INSERT INTO clothing_item " +
                "(item_id, name, size, price, brand, stock_quantity, item_type, sleeve_type, material) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'SHIRT', ?, ?)";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, s.getItemId());
            st.setString(2, s.getName());
            st.setString(3, s.getSize());
            st.setDouble(4, s.getPrice());
            st.setString(5, s.getBrand());
            st.setInt(6, s.getStockQuantity());
            st.setString(7, s.getSleeveType().name());
            st.setString(8, s.getMaterial());

            int rows = st.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public boolean insertPants(Pants p) {
        String sql = "INSERT INTO clothing_item " +
                "(item_id, name, size, price, brand, stock_quantity, item_type, fit_type, waist, inseam, material) " +
                "VALUES (?, ?, ?, ?, ?, ?, 'PANTS', ?, ?, ?, ?)";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, p.getItemId());
            st.setString(2, p.getName());
            st.setString(3, p.getSize());
            st.setDouble(4, p.getPrice());
            st.setString(5, p.getBrand());
            st.setInt(6, p.getStockQuantity());
            st.setString(7, p.getFitType().name());
            st.setInt(8, p.getWaist());
            st.setInt(9, p.getInseam());
            st.setString(10, p.getMaterial());

            int rows = st.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ================= READ (Week 7) =================

    public List<ClothingItem> getAllItems() {
        String sql = "SELECT * FROM clothing_item ORDER BY item_id";
        List<ClothingItem> list = new ArrayList<>();

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement st = c.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                ClothingItem item = mapRow(rs);
                if (item != null) {
                    list.add(item);
                }
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return list;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public ClothingItem getById(int itemId) {
        String sql = "SELECT * FROM clothing_item WHERE item_id = ?";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return null;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, itemId);

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ================= UPDATE (Week 8) =================

    public boolean updateShirt(Shirt s) {
        String sql = "UPDATE clothing_item SET name=?, size=?, price=?, brand=?, stock_quantity=?, sleeve_type=?, material=? " +
                "WHERE item_id=? AND item_type='SHIRT'";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setString(1, s.getName());
            st.setString(2, s.getSize());
            st.setDouble(3, s.getPrice());
            st.setString(4, s.getBrand());
            st.setInt(5, s.getStockQuantity());
            st.setString(6, s.getSleeveType().name());
            st.setString(7, s.getMaterial());
            st.setInt(8, s.getItemId());

            int rows = st.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public boolean updatePants(Pants p) {
        String sql = "UPDATE clothing_item SET name=?, size=?, price=?, brand=?, stock_quantity=?, fit_type=?, waist=?, inseam=?, material=? " +
                "WHERE item_id=? AND item_type='PANTS'";

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setString(1, p.getName());
            st.setString(2, p.getSize());
            st.setDouble(3, p.getPrice());
            st.setString(4, p.getBrand());
            st.setInt(5, p.getStockQuantity());
            st.setString(6, p.getFitType().name());
            st.setInt(7, p.getWaist());
            st.setInt(8, p.getInseam());
            st.setString(9, p.getMaterial());
            st.setInt(10, p.getItemId());

            int rows = st.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ================= DELETE (Week 8) =================

    public boolean deleteItem(int itemId) {
        String sql = "DELETE FROM clothing_item WHERE item_id = ?";
        Connection c = DatabaseConnection.getConnection();
        if (c == null) return false;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setInt(1, itemId);

            int rows = st.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ================= SEARCH (Week 8) =================

    public List<ClothingItem> searchByName(String namePart) {
        String sql = "SELECT * FROM clothing_item WHERE name ILIKE ? ORDER BY name";
        List<ClothingItem> list = new ArrayList<>();

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setString(1, "%" + namePart + "%");

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ClothingItem item = mapRow(rs);
                    if (item != null) {
                        list.add(item);
                    }
                }
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return list;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public List<ClothingItem> searchByPriceRange(double min, double max) {
        String sql = "SELECT * FROM clothing_item WHERE price BETWEEN ? AND ? ORDER BY price DESC";
        List<ClothingItem> list = new ArrayList<>();

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setDouble(1, min);
            st.setDouble(2, max);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ClothingItem item = mapRow(rs);
                    if (item != null) {
                        list.add(item);
                    }
                }
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return list;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    public List<ClothingItem> searchByMinPrice(double min) {
        String sql = "SELECT * FROM clothing_item WHERE price >= ? ORDER BY price DESC";
        List<ClothingItem> list = new ArrayList<>();

        Connection c = DatabaseConnection.getConnection();
        if (c == null) return list;

        try (PreparedStatement st = c.prepareStatement(sql)) {

            st.setDouble(1, min);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    ClothingItem item = mapRow(rs);
                    if (item != null) {
                        list.add(item);
                    }
                }
            }
            return list;

        } catch (SQLException e) {
            e.printStackTrace();
            return list;

        } finally {
            DatabaseConnection.closeConnection(c);
        }
    }

    // ================= Mapper =================

    private ClothingItem mapRow(ResultSet rs) throws SQLException {
        String type = rs.getString("item_type");
        int id = rs.getInt("item_id");
        String name = rs.getString("name");
        String size = rs.getString("size");
        double price = rs.getDouble("price");
        String brand = rs.getString("brand");
        int stock = rs.getInt("stock_quantity");
        String material = rs.getString("material");

        if ("SHIRT".equalsIgnoreCase(type)) {
            String sleeve = rs.getString("sleeve_type");
            Shirt.SleeveType st = (sleeve == null)
                    ? Shirt.SleeveType.SHORT
                    : Shirt.SleeveType.valueOf(sleeve);

            if (material == null) material = "Unknown";
            return new Shirt(id, name, size, price, brand, stock, st, material);
        }

        if ("PANTS".equalsIgnoreCase(type)) {
            String fit = rs.getString("fit_type");
            Pants.FitType ft = (fit == null)
                    ? Pants.FitType.REGULAR
                    : Pants.FitType.valueOf(fit);

            int waist = rs.getInt("waist");
            int inseam = rs.getInt("inseam");

            if (material == null) material = "Unknown";
            return new Pants(id, name, size, price, brand, stock, ft, waist, inseam, material);
        }

        return null;
    }
}
