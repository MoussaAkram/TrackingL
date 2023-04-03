package ma.fstt.model.livreur;

import ma.fstt.model.base.BaseDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreurDAO extends BaseDAO<Livreur> {
    public LivreurDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Livreur object) throws SQLException {

        String request = "insert into livreur (nom , telephone) values (? , ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1, object.getNom());

        this.preparedStatement.setString(2, object.getTelephone());


        this.preparedStatement.execute();
    }

    @Override
    public void update(Livreur object) throws SQLException {
        String request = "Update livreur SET telephone = ? where nom = ? ";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping

        this.preparedStatement.setString(1, object.getTelephone());
        this.preparedStatement.setString(2, object.getNom());

        this.preparedStatement.execute();
    }

    @Override
    public void delete(Livreur object) throws SQLException {
        String request = "DELETE FROM livreur  where nom = ? AND telephone = ? ";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1, object.getNom());

        this.preparedStatement.setString(2, object.getTelephone());

        this.preparedStatement.execute();
        // fix override of id
        // request = "ALTER TABLE livreur AUTO_INCREMENT = 1";
        // this.preparedStatement = this.connection.prepareStatement(request);
        // this.preparedStatement.execute();
    }

    @Override
    public List<Livreur> getAll() throws SQLException {

        List<Livreur> mylist = new ArrayList<Livreur>();

        String request = "select * from livreur ";

        this.statement = this.connection.createStatement();

        this.resultSet = this.statement.executeQuery(request);

// parcours de la table
        while (this.resultSet.next()) {

// mapping table objet
            mylist.add(new Livreur(this.resultSet.getLong(1),
                    this.resultSet.getString(2), this.resultSet.getString(3)));


        }


        return mylist;
    }

    @Override
    public Livreur getOne(String nom) throws SQLException {
        Livreur livreur = null;
        String request = "SELECT * FROM livreur WHERE nom = ? ";
        this.preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(request);
            preparedStatement.setString(1, nom);

            this.resultSet = preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                livreur = new Livreur(
                        this.resultSet.getLong(1),
                        this.resultSet.getString(2),
                        this.resultSet.getString(3)
                );
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return livreur;
    }


}
