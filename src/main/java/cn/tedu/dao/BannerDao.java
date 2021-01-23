package cn.tedu.dao;

import cn.tedu.entity.Banner;
import cn.tedu.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BannerDao {
    public List<Banner> findAll() {
        ArrayList<Banner> list = new ArrayList<>();
        //获取连接
        try (Connection coon = DBUtils.getConn()){
          String sql = "select * from banner";
            Statement s = coon.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt(1);
                String url = rs.getString(2);
                list.add(new Banner(id,url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void deleteById(String id) {
        //获取连接
        try (Connection coon = DBUtils.getConn()){
            String sql = "delete from banner where id=?";
            PreparedStatement ps = coon.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Banner findAll(String id) {
        //获取连接
        try (Connection coon = DBUtils.getConn()){
            String sql = "select * from banner where id=?";
            PreparedStatement ps = coon.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int bId = rs.getInt(1);
                String url = rs.getString(2);
                return new Banner(bId,url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(Banner banner) {
        //获取连接
        try (Connection coon = DBUtils.getConn()){
            String sql = "insert into banner values(null,?)";
            PreparedStatement ps = coon.prepareStatement(sql);
            ps.setString(1,banner.getUrl());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
