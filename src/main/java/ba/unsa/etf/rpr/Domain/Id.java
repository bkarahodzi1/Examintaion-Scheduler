package ba.unsa.etf.rpr.Domain;
/**
 * Interface that forces all POJO beans to have ID field. Name is stupid but per standard
 *
 * @author Berin Karahodžić
 */
public interface Id {
    void setId(int id);
    int getId();
}
