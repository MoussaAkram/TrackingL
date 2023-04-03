package ma.fstt.model.dashboard;

import ma.fstt.model.base.BaseDAO;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;




public class DashboardDAO extends BaseDAO<Dashboard> {

    public DashboardDAO() throws SQLException {

        super();
    }

    @Override
    public void save(Dashboard object) throws SQLException {

    }


    @Override
    public void update(Dashboard object) throws SQLException {
    }

    @Override
    public void delete(Dashboard object) throws SQLException {

    }


    @Override
    public List<Dashboard> getAll()  throws SQLException {

            List<Dashboard> mylist = new ArrayList<Dashboard>();

            String request = "SELECT livreur.nom , COUNT(command.id_command)  FROM command INNER JOIN livreur ON command.id_livreur = livreur.id_livreur GROUP BY nom ";

            this.statement = this.connection.createStatement();

            this.resultSet =   this.statement.executeQuery(request);

// parcours de la table
            while ( this.resultSet.next()){

// mapping table objet
                mylist.add(new Dashboard(this.resultSet.getString(1) ,
                        this.resultSet.getLong(2)
 ));


            }


            return mylist;
        }



    @Override
    public Dashboard getOne(String nom) throws SQLException {
        return null;
    }

}
