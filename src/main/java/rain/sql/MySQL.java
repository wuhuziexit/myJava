package rain.sql;

import java.sql.*;

public class MySQL {
    private String url = "jdbc:mysql://localhost:3306/";//默认数据库的本地地址
    private String username = "root";//默认用户名
    private String password = "123456";//默认密码
    private String className = "com.mysql.cj.jdbc.Driver";//默认驱动
    private String database;//要连接的数据库
    private Connection connection;//建立连接
    private Statement statement;//
    private ResultSet resultSet;//查询结果集
    private PreparedStatement preparedStatement;

    public MySQL(String database) {
        this.database = database;
        try {
            Class.forName(className);// 加载驱动
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format("驱动 %s 加载异常：%s", className, e.getMessage()));
        }
    }

    public MySQL(String username, String password, String database) {
        this.username = username;
        this.password = password;
        this.database = database;
    }

    /**
     * 数据库连接对象
     *
     * @param url      数据库地址，例如：jdbc:mysql://localhost:3306/数据库
     * @param username 用户名
     * @param password 密码
     * @return Connection
     */
    public Connection getConnection(String url, String username, String password) {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    /**
     * 使用默认的连接 jdbc:mysql://localhost:3306/数据库
     *
     * @return Connection
     */
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url + database, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public Statement getStatement() {
        try {
            connection = DriverManager.getConnection(url + database, username, password);
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    /**
     * 执行查询语句并获取结果集
     *
     * @param sql 查询语句
     * @return 结果集
     */
    public ResultSet getResultSet(String sql) {
        try {
            getStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取 PreparedStatement
     *
     * @param query sql语句
     * @return PreparedStatement
     */
    public PreparedStatement getPreparedStatement(String query) {
        try {
            this.connection = DriverManager.getConnection(url + database, username, password);
            this.preparedStatement = connection.prepareStatement(query);
            return preparedStatement;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭流
     */
    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
