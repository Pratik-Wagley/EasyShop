package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
    private final DataSource dataSource;
    public MySqlCategoryDao(DataSource dataSource, DataSource dataSource1)
    {
        super(dataSource);
        this.dataSource = dataSource1;
    }

    @Override
    public List<Category> getAllCategories()
    {
        // get all categories
        List<Category> categories = new ArrayList<>();

        String sql = "SELECT * FROM categories";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                Category category = mapRow(resultSet);
                categories.add(category);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  // Handle the exception appropriately in your application
        }

        return categories;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        Category category = null;

        String sql = "SELECT * FROM categories WHERE category_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, categoryId);

            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    category = mapRow(resultSet);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();  // Handle the exception appropriately in your application
        }

        return category;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        return null;

    }

    @Override
    public void update(int categoryId, Category category)
    {

        // update category
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        return null;
    }

}
