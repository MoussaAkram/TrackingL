package ma.fstt.model.command;


public class Command {

    private Long id_command;

    private String date_debut;

    private String date_fin ;

    private Float km;

    private String client;

    private Long id_livreur;

    private  Long id_product;


    public Command(){

    }

    public Command(Long id_command, String date_debut, String date_fin, Float km, String client, Long id_livreur, Long id_product) {
        this.id_command = id_command;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.km = km;
        this.client = client;
        this.id_livreur = id_livreur;
        this.id_product = id_product;
    }

    public Long getId_command() {
        return id_command;
    }

    public void setId_command(Long id_command) {
        this.id_command = id_command;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public Float getKm() {
        return km;
    }

    public void setKm(Float km) {
        this.km = km;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getId_livreur() {
        return id_livreur;
    }

    public void setId_livreur(Long id_livreur) {
        this.id_livreur = id_livreur;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    @Override
    public String toString() {
        return "Command{" +
                "id_command=" + id_command +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", km=" + km +
                ", client='" + client + '\'' +
                ", id_livreur=" + id_livreur +
                ", id_product=" + id_product +
                '}';
    }
}
