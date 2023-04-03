package ma.fstt.model.dashboard;

public class Dashboard {
    private String nom;     //nom de livreur
    private Long id_command;


    public Dashboard() {
    }

    public Dashboard(String nom, Long id_command) {
        this.nom = nom;
        this.id_command = id_command;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId_command() {
        return id_command;
    }

    public void setId_command(Long id_command) {
        this.id_command = id_command;
    }

    @Override
    public String toString() {
        return "Dashboard{" +
                "nom='" + nom + '\'' +
                ", id_command=" + id_command +
                '}';
    }
}