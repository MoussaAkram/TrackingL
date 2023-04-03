package ma.fstt.model.command;


import ma.fstt.model.base.BaseDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CommandDAO extends BaseDAO<Command> {
    public CommandDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Command object) throws SQLException {

        String request = "insert into command (date_debut , date_fin , km , client , id_product , id_livreur) values (? , ? , ? , ? , ? , ?)";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getDate_debut());
        this.preparedStatement.setString(2 , object.getDate_fin());
        this.preparedStatement.setFloat(3 , object.getKm());
        this.preparedStatement.setString(4 , object.getClient());
        this.preparedStatement.setLong(5 , object.getId_product());
        this.preparedStatement.setLong(6 , object.getId_livreur());


        this.preparedStatement.execute();
    }

    @Override
    public void update(Command object) throws SQLException {
        String request = "Update command SET date_debut = ?,date_fin = ? , km= ? , id_product = ? , id_livreur = ? where client = ? ";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getDate_debut());
        this.preparedStatement.setString(2 , object.getDate_fin());
        this.preparedStatement.setFloat(3 , object.getKm());
        this.preparedStatement.setLong(4 , object.getId_product());
        this.preparedStatement.setLong(5 , object.getId_livreur());
        this.preparedStatement.setString(6 , object.getClient());


        this.preparedStatement.execute();
    }

    @Override
    public void delete(Command object) throws SQLException {
        String request = "DELETE FROM command  where client = ? AND id_product = ? AND id_livreur = ? ";

        // mapping objet table

        this.preparedStatement = this.connection.prepareStatement(request);
        // mapping
        this.preparedStatement.setString(1 , object.getClient());
        this.preparedStatement.setLong(2 , object.getId_product());
        this.preparedStatement.setLong(3 , object.getId_livreur());


        this.preparedStatement.execute();
        // fix override of id
        // request = "ALTER TABLE command AUTO_INCREMENT = 1";
        // this.preparedStatement = this.connection.prepareStatement(request);
        // this.preparedStatement.execute();
    }

    @Override
    public List<Command> getAll()  throws SQLException {

        List<Command> mylist = new ArrayList<Command>();

        String request = " select * from((command INNER JOIN livreur ON command.id_livreur = livreur.id_livreur ) INNER JOIN product ON command.id_product = product.id_product) ORDER BY id_command";

        this.statement = this.connection.createStatement();

        this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
        while ( this.resultSet.next()){

// mapping table objet
            mylist.add(new Command(this.resultSet.getLong(1) ,
                    this.resultSet.getString(2) ,
                    this.resultSet.getString(3),
                    this.resultSet.getFloat(4),
                    this.resultSet.getString(5),
                    this.resultSet.getLong(6),
                    this.resultSet.getLong(7)));


        }


        return mylist;
    }

    @Override
    public Command getOne(String client) throws SQLException {
        Command command = null;
        String request = "SELECT * FROM command WHERE client = ? ";
        this.preparedStatement = null;
        try {
            preparedStatement = this.connection.prepareStatement(request);
            preparedStatement.setString(1, client);

            this.resultSet = preparedStatement.executeQuery();

            if (this.resultSet.next()) {
                command = new Command(
                        this.resultSet.getLong(1),
                        this.resultSet.getString(2) ,
                        this.resultSet.getString(3),
                        this.resultSet.getFloat(4),
                        this.resultSet.getString(5),
                        this.resultSet.getLong(6),
                        this.resultSet.getLong(7)
                );
            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
        return command;
    }


}