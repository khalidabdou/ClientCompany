package comproducts.company.Modul;

public class Service {
    
    public int IdService;
    public String  image;
    public String titleFr;
    public String titleAr;
    public String DescriptionFr;
    public String DescriptionAr;
    public String PhoneNumber;

    public Service(int idService, String  image, String titleFr, String descriptionFr) {
        IdService = idService;
        this.image = image;
        this.titleFr = titleFr;
        DescriptionFr = descriptionFr;
    }


}
