package ma.fstt.model.product;

public class Product {
    private Long id_product;
    private String nom;
    private Float prix;
    private String description;

    public Product() {}

    public Product(Long id_product, String nom, Float prix, String description) {
        this.id_product = id_product;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id_product=" + id_product +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                '}';
    }
}
