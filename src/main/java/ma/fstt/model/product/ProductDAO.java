package ma.fstt.model.product;

import ma.fstt.model.base.BaseDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProductDAO extends BaseDAO<Product> {

    public ProductDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Product object) throws SQLException {

        String request = "insert into product (nom , prix, description) values (? , ?, ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setFloat(2 , object.getPrix());
        this.preparedStatement.setString(3 , object.getDescription());


        this.preparedStatement.execute();
    }

    @Override
    public void update(Product object) throws SQLException {
        String request = "Update product SET nom = ?, prix= ? ,description = ? where nom = ? ";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());
        this.preparedStatement.setFloat(2 , object.getPrix());
        this.preparedStatement.setString(3 , object.getDescription());
        this.preparedStatement.setString(4 , object.getNom());

        this.preparedStatement.execute();
    }

    @Override
    public void delete(Product object) throws SQLException {
        String request = "DELETE FROM product  where nom = ?  ";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getNom());

        this.preparedStatement.execute();
        // fix override of id
        // request = "ALTER TABLE product AUTO_INCREMENT = 1";
        // this.preparedStatement = this.connection.prepareStatement(request);
        // this.preparedStatement.execute();
    }

    @Override
    public List<Product> getAll()  throws SQLException {

        List<Product> mylist1 = new ArrayList<Product>();

        String request = "select * from product ";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist1.add(new Product(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) ,
                    this.resultSet.getFloat(3),
                    this.resultSet.getString(4)));

        }

        return mylist1;
    }

    @Override
    public Product getOne(String nom) throws SQLException {
        Product product = null;
        String request = "SELECT * FROM product WHERE nom = ? ";
        this.preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(request);
            preparedStatement.setString(1, nom);


            this.resultSet = preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                product = new Product(
                        this.resultSet.getLong(1),
                        this.resultSet.getString(2) ,
                        this.resultSet.getFloat(3),
                        this.resultSet.getString(4)
                );
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return product;
    }

}
