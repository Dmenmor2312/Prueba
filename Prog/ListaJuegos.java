import java.util.List;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
class ListaDeJuegos {

    @XmlElement(name = "NJuegos")
    private List<NJuegos> nJuegos;

    public List<NJuegos> getNJuegos() {
        return nJuegos;
    }

    public void setNJuegos(List<NJuegos> nJuegos) {
        this.nJuegos = nJuegos;
    }
}
